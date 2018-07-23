package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import service.AppService;

import java.util.Set;

public class GetAuthorAction extends ActionSupport {
    private Long id;
    private String url;
    private String name;
    private Set<Long> papers;

    public Set<Long> getPapers() {
        return papers;
    }

    public void setPapers(Set<Long> papers) {
        this.papers = papers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        if (this.id == null) return ERROR;
        Author result = appService.getAuthorById(id);
        if (result != null) {
            setUrl(result.getUrl());
            setName(result.getName());
            setPapers(result.getPapers());
            return SUCCESS;
        }
        return NONE;
    }
}
