package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import service.AppService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueryPapersAction extends ActionSupport{
    private String key;
    private Set<Paper> papers;

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public Set<Paper> getPapers() {
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
        this.papers = new HashSet<>();
        if (this.key == null) return ERROR;
        String[] keys = this.key.split(" ");
        for (String key: keys) {
            if (key == null) continue;
            List<Paper> result = appService.queryPaper(key);
            if (result == null) continue;
            this.papers.addAll(result);
        }
        return SUCCESS;
    }
}
