package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import model.Paper;
import service.AppService;

import java.util.Set;

public class UpdatePaperAction extends ActionSupport {
    private Integer id;
    private String name;
    private String url;
    private Set<Author> authors;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String query() throws Exception {
        if (this.id == null) return ERROR;
        Paper result = appService.getPaperById(id);
        if (result != null) {
            setId(result.getId());
            setAuthors(result.getAuthors());
            setName(result.getName());
            setUrl(result.getUrl());
            return SUCCESS;
        }
        return ERROR;
    }
}
