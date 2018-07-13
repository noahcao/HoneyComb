package action;

import com.opensymphony.xwork2.ActionSupport;
import javafx.util.Pair;
import model.Paper;
import service.AppService;
import tfidf.Tfidf;
import java.util.*;

public class QueryPapersAction extends ActionSupport {

    private class TfPair {
        private String key;
        private Float value;

        public TfPair(String key, Float value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Float getValue() {
            return value;
        }

        public void setKey(String key) {
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
    private ArrayList<Paper> papers;

    public void setPapers(ArrayList<Paper> papers) {
        this.papers = papers;
    }

    public ArrayList<Paper> getPapers() {
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

    public String search() throws Exception {
        this.papers = new ArrayList<>();
        if (this.key == null) return ERROR;

        String[] temp = this.key.split(" ");
        if (temp.length == 0) return SUCCESS;
        ArrayList<String> allKeys = new ArrayList<>();
        for (String key : temp) {
            if (!allKeys.contains(key)) allKeys.add(key);
        }
        int limit = 100;

        ArrayList<TfPair> candidates = new ArrayList<>();
        for (String key : allKeys) {
            if (key == null) continue;
            List<String> titles = appService.queryTitles(key);
            HashMap<String, HashMap<String, Float>> tf = Tfidf.tfOfAll(titles, 100);
            for (String i : tf.keySet()) {
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
//        System.out.println("-------------end: " + end + "----------------");
//            System.out.println("-----------------" + title + ": " + result.size() + "--------------------");
        }
        ComparePair com = new ComparePair();
        candidates.sort(com);
        int end = limit <= candidates.size() ? limit : candidates.size();
        for (int i = 0; i < end; i++) {
            List<Paper> results = appService.getPaperByTitle(candidates.get(i).getKey());
            if (results == null) continue;
            for (Paper result : results) {
                if (!this.papers.contains(result)) this.papers.add(result);
            }
        }
//        int limit = 100 / ((keys.size() * keys.size() + 1) / 2);
//        if (limit == 0) limit = 10;
//        int tot = 0;
//        for (int i = keys.size(); i > 0; i--) {
//            for (int j = 0; j + i <= keys.size(); j++) {
//                if (++tot > 50) break;
//                String key = keys.get(j);
//                for (int k = j + 1; k < j + i; k++) {
//                    key = key.concat(" " + keys.get(k));
//                }
        return SUCCESS;
    }
}
