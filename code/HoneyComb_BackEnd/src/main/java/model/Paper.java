package model;

import org.springframework.data.annotation.Id;

import java.util.Set;

public class Paper {

    @Id
    private Long id;
    private String url;
    private Set<Author> authors;
    private Integer year;
    private Set<Long> reference;
    private String title;
    private Integer cited;
    private String _abstract;

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public Integer getCited() {
        return cited;
    }

    public void setCited(Integer cited) {
        this.cited = cited;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Long> getReference() {
        return reference;
    }

    public void setReference(Set<Long> reference) {
        this.reference = reference;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Paper) {
            Paper paper = (Paper) obj;
            return paper.getId().equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
