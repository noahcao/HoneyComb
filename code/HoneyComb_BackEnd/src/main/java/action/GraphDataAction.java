package action;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import model.Paper;
import service.AppService;
import tfidf.GraphData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphDataAction extends ActionSupport {

    private class PaperRank {
        public Long paperid;
        public double pagerank;

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

    private class AuthorRank {
        public Long authorid;
        public double pagerank;

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

    private List<PaperRank> paper;
    private List<AuthorRank> author;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AuthorRank> getAuthor() {
        return author;
    }

    public List<PaperRank> getPaper() {
        return paper;
    }

    public void setAuthor(List<AuthorRank> author) {
        this.author = author;
    }

    public void setPaper(List<PaperRank> paper) {
        this.paper = paper;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    private void getData(int hierarchy, JsonArray paperArray, ArrayList<Long> papers, int itPapaer, ArrayList<Long> authors) {
        if (hierarchy >= 1) return;
        int tempSize = papers.size();
        for (int i = itPapaer; i < tempSize; i++) {
            JsonObject obj = new JsonObject();
            Paper paper = appService.getPaperById(papers.get(i));
            obj.addProperty("`id`", paper.getId());

            JsonArray a = new JsonArray();
            JsonArray r = new JsonArray();
            for (Author author : paper.getAuthors()) {
                JsonObject temp = new JsonObject();
                temp.addProperty("`id`", author.getId());
                a.add(temp);
                if (!authors.contains(author.getId())) {
                    authors.add(author.getId());
                    for (Long paper1: author.getPapers()) {
                        if (!papers.contains(paper1)) {
                            papers.add(paper1);
                        }
                    }
                }
            }

            for (Long reference : paper.getReference()) {
                JsonObject temp = new JsonObject();
                temp.addProperty("`id`", reference);
                r.add(temp);
                if (!papers.contains(reference)) {
                    papers.add(reference);
                }
            }

            obj.add("`authors`", a);
            obj.add("`reference`", r);

            paperArray.add(obj);
        }
        getData(hierarchy + 1, paperArray, papers, tempSize, authors);
    }

    @Override
    public String execute() throws Exception {
        if (this.id == null) return ERROR;
        Paper result = appService.getPaperById(id);
        if (result == null) return NONE;
        JsonArray paperArray = new JsonArray();
        JsonArray authorArray = new JsonArray();
        ArrayList<Long> authors = new ArrayList<>();
        ArrayList<Long> papers = new ArrayList<>();
        papers.add(this.id);
        getData(0, paperArray, papers, 0, authors);
        for (Long i : authors) {
            JsonObject temp = new JsonObject();
            temp.addProperty("`id`", i);
            authorArray.add(temp);
        }
        JsonObject obj1 = new JsonObject();
        JsonObject obj2 = new JsonObject();
        obj1.add("`papers`", paperArray);
        obj2.add("`authors`", authorArray);
        return GraphData.run(obj1, obj2);
    }
}
