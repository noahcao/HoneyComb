package action;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import model.Paper;
import service.AppService;
import tfidf.GraphData;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class GraphDataAction extends ActionSupport {

    public class PaperRank {
        private Long paperid;
        private Double pagerank;
        private Integer level;
        private Set<Long> authors;
        private Set<Long> reference;

        public Set<Long> getReference() {
            return reference;
        }

        public void setReference(Set<Long> reference) {
            this.reference = reference;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Set<Long> getAuthors() {
            return authors;
        }

        public void setAuthors(Set<Long> authors) {
            this.authors = authors;
        }

        public Double getPagerank() {
            return pagerank;
        }

        public Long getPaperid() {
            return paperid;
        }

        public void setPagerank(Double pagerank) {
            this.pagerank = pagerank;
        }

        public void setPaperid(Long paperid) {
            this.paperid = paperid;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (obj instanceof PaperRank) {
                PaperRank paper = (PaperRank) obj;
                return paper.paperid.equals(this.paperid);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return paperid.hashCode();
        }
    }

    public class AuthorRank {
        private Long authorid;
        private Double pagerank;
        private Integer level;
        private Set<Long> papers;

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return level;
        }

        public void setPapers(Set<Long> papers) {
            this.papers = papers;
        }

        public Set<Long> getPapers() {
            return papers;
        }

        public void setPagerank(Double pagerank) {
            this.pagerank = pagerank;
        }

        public Double getPagerank() {
            return pagerank;
        }

        public Long getAuthorid() {
            return authorid;
        }

        public void setAuthorid(Long authorid) {
            this.authorid = authorid;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (obj instanceof AuthorRank) {
                AuthorRank author = (AuthorRank) obj;
                return author.authorid.equals(this.authorid);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return authorid.hashCode();
        }
    }

    private ArrayList<PaperRank> paper = new ArrayList<>();
    private ArrayList<AuthorRank> author = new ArrayList<>();
    private Long id;
    private Integer hierarchyLimit;

    public Integer getHierarchyLimit() {
        return hierarchyLimit;
    }

    public void setHierarchyLimit(Integer hierarchyLimit) {
        this.hierarchyLimit = hierarchyLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<AuthorRank> getAuthor() {
        return author;
    }

    public ArrayList<PaperRank> getPaper() {
        return paper;
    }

    public void setAuthor(ArrayList<AuthorRank> author) {
        this.author = author;
    }

    public void setPaper(ArrayList<PaperRank> paper) {
        this.paper = paper;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    private void getData(int hierarchy, int hierarchyLimit, JsonArray paperArray, ArrayList<Long> papers, int itPapaer, ArrayList<Long> authors, int itAuthor) {
        if (hierarchy > hierarchyLimit) return;
        int tempPaperSize = papers.size();
        int tempAuthorSize = authors.size();
        for (int i = itPapaer; i < tempPaperSize; i++) {
            JsonObject obj = new JsonObject();
            Paper paper = appService.getPaperById(papers.get(i));
            obj.addProperty("`id`", paper.getId());

            PaperRank e = new PaperRank();
            e.paperid = paper.getId();
            e.level = hierarchy;
            e.authors = new HashSet<>();
            e.reference = paper.getReference();
            for (Author tempAuthor : paper.getAuthors()) {
                e.authors.add(tempAuthor.getId());
            }
            this.paper.add(e);

            JsonArray a = new JsonArray();
            JsonArray r = new JsonArray();
            if (hierarchy + 1 <= hierarchyLimit) {
                for (Author author : paper.getAuthors()) {
                    JsonObject temp = new JsonObject();
                    temp.addProperty("`id`", author.getId());
                    a.add(temp);
                    if (!authors.contains(author.getId())) {
                        authors.add(author.getId());

                        AuthorRank e1 = new AuthorRank();
                        e1.authorid = author.getId();
                        e1.level = hierarchy + 1;
                        e1.papers = author.getPapers();
                        this.author.add(e1);
                    }
                }
            }

            if (hierarchy + 1 <= hierarchyLimit) {
                for (Long reference : paper.getReference()) {
                    JsonObject temp = new JsonObject();
                    temp.addProperty("`id`", reference);
                    r.add(temp);
                    if (!papers.contains(reference)) {
                        papers.add(reference);
                    }
                }
            }

            obj.add("`authors`", a);
            obj.add("`reference`", r);

            paperArray.add(obj);
        }
        for (int i = itAuthor; i < tempAuthorSize; i++) {

            if (hierarchy + 1 <= hierarchyLimit) {
                Author author = appService.getAuthorById(authors.get(i));
                for (Long paper : author.getPapers()) {
                    if (!papers.contains(paper)) {
                        papers.add(paper);
                    }
                }
            }
        }
        getData(hierarchy + 1, hierarchyLimit, paperArray, papers, tempPaperSize, authors, tempAuthorSize);
    }

    @Override
    public String execute() throws Exception {
        if (this.id == null || this.hierarchyLimit == null) return ERROR;
        Paper result = appService.getPaperById(id);
        if (result == null) return NONE;
        this.hierarchyLimit = this.hierarchyLimit > 5 ? 5 : this.hierarchyLimit;
        JsonArray paperArray = new JsonArray();
        JsonArray authorArray = new JsonArray();
        ArrayList<Long> authors = new ArrayList<>();
        ArrayList<Long> papers = new ArrayList<>();
        papers.add(this.id);
        getData(1, hierarchyLimit, paperArray, papers, 0, authors, 0);

        for (Long i : authors) {
            JsonObject temp = new JsonObject();
            temp.addProperty("`id`", i);
            authorArray.add(temp);
        }

        JsonObject paper = new JsonObject();
        JsonObject author = new JsonObject();
        paper.add("`papers`", paperArray);
        author.add("`authors`", authorArray);
        JsonArray resultArray = GraphData.run(paper, author);

        if (resultArray == null) return ERROR;
        if (resultArray.size() < 2) return ERROR;
        for (JsonElement i : resultArray.get(0).getAsJsonObject().getAsJsonArray("paper")) {
            PaperRank temp = new PaperRank();
            temp.paperid = i.getAsJsonObject().get("paperid").getAsLong();
            this.paper.get(this.paper.indexOf(temp)).pagerank = i.getAsJsonObject().get("pagerank").getAsDouble();
        }
        for (JsonElement i : resultArray.get(1).getAsJsonObject().getAsJsonArray("author")) {
            AuthorRank temp = new AuthorRank();
            temp.authorid = i.getAsJsonObject().get("authorid").getAsLong();
            this.author.get(this.author.indexOf(temp)).pagerank = i.getAsJsonObject().get("pagerank").getAsDouble();
        }
        return SUCCESS;
    }
}
