package action;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import model.Paper;
import model.PaperSmall;
import org.hibernate.Hibernate;
import service.AppService;
import tfidf.Tfidf;

import java.util.*;

public class QueryPapersAction extends ActionSupport {

    private class TfPair {
        private PaperSmall key;
        private Float value;

        public TfPair(PaperSmall key, Float value) {
            this.key = key;
            this.value = value;
        }


        public PaperSmall getKey() {
            return key;
        }

        public Float getValue() {
            return value;
        }

        public void setKey(PaperSmall key) {
            this.key = key;
        }

        public void setValue(Float value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (obj instanceof TfPair) {
                TfPair tfPair = (TfPair) obj;
                return tfPair.getKey().equals(key);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    public class SimplePaper {
        private Long id;
        private String url;
        private Set<String> authors;
        private Integer year;
        private String title;
        private String _abstract;
        private Integer cited;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setAuthors(Set<String> authors) {
            this.authors = authors;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public void set_abstract(String _abstract) {
            this._abstract = _abstract;
        }

        public String get_abstract() {
            return _abstract;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setCited(Integer cited) {
            this.cited = cited;
        }

        public Integer getCited() {
            return cited;
        }

        public Set<String> getAuthors() {
            return authors;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this == obj) return true;
            if (obj instanceof SimplePaper) {
                SimplePaper paper = (SimplePaper) obj;
                return paper.getId().equals(this.id);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    private class ComparePair implements Comparator {
        public int compare(Object arg0, Object arg1) {
            @SuppressWarnings("unchecked")
            TfPair p0 = (TfPair) arg0;
            @SuppressWarnings("unchecked")
            TfPair p1 = (TfPair) arg1;
            if (p0.getValue() > p1.getValue()) return -1;
            else if (p0.getValue().equals(p1.getValue())) return 0;
            else return 1;
        }
    }

    private String key;
    private ArrayList<SimplePaper> papers;
    private Integer start;
    private Integer end;
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getEnd() {
        return end;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStart() {
        return start;
    }

    public void setPapers(ArrayList<SimplePaper> papers) {
        this.papers = papers;
    }

    public ArrayList<SimplePaper> getPapers() {
        return papers;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    private SimplePaper simplifyPaper(Paper paper) {
        SimplePaper result = new SimplePaper();
        result._abstract = paper.get_abstract();
        result.cited = paper.getCited();
        result.id = paper.getId();
        result.title = paper.getTitle();
        result.url = paper.getUrl();
        result.year = paper.getYear();
        Hibernate.initialize(paper.getAuthors());
        result.authors = new HashSet<>();
        for (Author author : paper.getAuthors()) {
            result.authors.add(author.getName());
        }
        return result;
    }

    private void tfidfAlgorithm(ArrayList<String> allKeys, ArrayList<TfPair> candidates, boolean flag, ArrayList<String> restKeys) {
        restKeys.clear();
        for (String key : allKeys) {
            long startTime = System.currentTimeMillis();
            if (key == null) continue;
            List<PaperSmall> paperSmalls = appService.getPaperSmallLikeTitle(key);
            if (flag && paperSmalls.size() > 20000) {
                restKeys.add(key);
                continue;
            }
            HashMap<PaperSmall, HashMap<String, Float>> tf = Tfidf.tfOfAll(paperSmalls, 100);
            for (PaperSmall i : tf.keySet()) {
                HashMap<String, Float> dict = tf.get(i);
                Float tot = new Float(0);
                for (String keys : dict.keySet()) {
                    if (keys.contains(key)) {
                        tot += dict.get(keys);
                    }
                }
                TfPair tfPair = new TfPair(i, tot);
                int index = candidates.indexOf(tfPair);
                if (index >= 0) {
                    TfPair tempPair = candidates.get(index);
                    candidates.get(index).setValue(tempPair.getValue() + tfPair.getValue());
                } else {
                    candidates.add(tfPair);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(key + " " + paperSmalls.size() + " algorithm time: " + (endTime - startTime));
//        System.out.println("-------------end: " + end + "----------------");
//            System.out.println("-----------------" + title + ": " + result.size() + "--------------------");
        }
    }

    public String search() throws Exception {
        this.papers = new ArrayList<>();
        if (this.key == null || this.start == null || this.end == null) return ERROR;
        if (this.start > this.end) return ERROR;
        this.key = this.key.toLowerCase();
        String SEARCH = "search";
        String SEARCHRESULT = "searchresult";

        Map<String, Object> usersession = ActionContext.getContext().getSession();
        if (usersession.get(SEARCH) != null) {
            if ((usersession.get(SEARCH)).equals(key)) {
                if (usersession.get(SEARCHRESULT) != null) {
                    ArrayList<PaperSmall> searchList = (ArrayList<PaperSmall>) usersession.get(SEARCHRESULT);

                    this.total = searchList.size();
                    if (start + 1 > searchList.size()) return SUCCESS;
                    if (end > searchList.size()) {
                        end = searchList.size();
                    }
                    if (start > end) return SUCCESS;
                    for (int i = start; i < end; i++) {
                        this.papers.add(simplifyPaper(appService.getPaperById(searchList.get(i).getId())));
                    }
//                    this.papers.addAll(searchList.subList(start, end));
                    return SUCCESS;
                }
            } else {
                usersession.replace(SEARCH, key);
            }
        } else {
            usersession.put(SEARCH, key);
        }

        ArrayList<TfPair> candidates = new ArrayList<>();
        int limit = 100;
        List<PaperSmall> results = appService.getPaperSmallLikeTitle(key);
        if (results.size() > 0 && results.size() <= 5) {
            for (PaperSmall result : results) {
                SimplePaper e = simplifyPaper(appService.getPaperById(result.getId()));
                this.papers.add(e);
            }
            this.total = results.size();
            this.start = 0;
            this.end = results.size();
            usersession.put(SEARCHRESULT, results);
            return SUCCESS;
        }

        String[] temp = this.key.split(" ");
        if (temp.length == 0) return SUCCESS;
        ArrayList<String> allKeys = new ArrayList<>();
        for (String key : temp) {
            if (!allKeys.contains(key)) {
                allKeys.add(key);
            }
        }
        long endTime;
        long startTime;

        ArrayList<String> restKeys = new ArrayList<>();
        tfidfAlgorithm(allKeys, candidates, true, restKeys);
        if (restKeys.size() > allKeys.size() / 2) tfidfAlgorithm(restKeys, candidates, false, new ArrayList<>());
        ComparePair com = new ComparePair();
        candidates.sort(com);

        int listEnd = limit <= candidates.size() ? limit : candidates.size();
        ArrayList<PaperSmall> searchList = new ArrayList<>();
        for (int i = 0; i < listEnd; i++) {
            PaperSmall paperSmall = candidates.get(i).getKey();
            if (!searchList.contains(paperSmall)) searchList.add(paperSmall);
        }

        usersession.put(SEARCHRESULT, searchList);
        this.total = searchList.size();
        if (start + 1 > searchList.size()) return SUCCESS;
        if (end > searchList.size()) {
            end = searchList.size();
        }
        if (start > end) return SUCCESS;

        startTime = System.currentTimeMillis();
        for (int i = start; i < end; i++) {
            SimplePaper e = simplifyPaper(appService.getPaperById(searchList.get(i).getId()));
            this.papers.add(e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("cast time: " + (endTime - startTime));
        return SUCCESS;
    }
}
