package model;

import java.sql.Timestamp;
import java.util.Comparator;

public class History {
    private PaperSmall paper;
    private Timestamp time;

    public static class ComparePair implements Comparator {
        public int compare(Object arg0, Object arg1) {
            @SuppressWarnings("unchecked")
            History p0 = (History) arg0;
            @SuppressWarnings("unchecked")
            History p1 = (History) arg1;
            if (p0.getTime().before(p1.getTime())) return -1;
            else if (p0.getTime().equals(p1.getTime())) return 0;
            else return 1;
        }
    }

    public void setPaper(PaperSmall paper) {
        this.paper = paper;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public PaperSmall getPaper() {
        return paper;
    }

    public Timestamp getTime() {
        return time;
    }
}
