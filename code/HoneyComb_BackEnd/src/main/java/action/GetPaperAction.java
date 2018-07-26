package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Author;
import model.Paper;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import service.AppService;

import java.time.Year;
import java.util.Set;

public class GetPaperAction extends ActionSupport {
    private java.lang.Long id;
    private String title;
    private String url;
    private Integer year;
    private Set<Author> authors;
    private Integer cited;
    private String _abstract;
    private Set<Long> reference;

    public Set<Long> getReference() {
        return reference;
    }

    public void setReference(Set<Long> reference) {
        this.reference = reference;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String get_abstract() {
        return _abstract;
    }

    public Integer getCited() {
        return cited;
    }

    public void setCited(Integer cited) {
        this.cited = cited;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }
    
    @Override
    public String execute() throws Exception {
        if (this.id == null) return ERROR;
        Paper result = appService.getPaperById(id);
        if (result != null) {
            Hibernate.initialize(result.getAuthors());
            setAuthors(result.getAuthors());
            setTitle(result.getTitle());
            setUrl(result.getUrl());
            setCited(result.getCited());
            setYear(result.getYear());
            set_abstract(result.get_abstract());
            Hibernate.initialize(result.getReference());
            setReference(result.getReference());
            return SUCCESS;
        }
        return NONE;
    }
}
