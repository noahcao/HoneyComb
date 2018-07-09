package model;

import java.time.Year;
import java.util.Set;

public class Paper {
    private Integer id;
    private String name;
    private String url;
    private Set<Author> authors;
    private Year year;
    private Set<Paper> reference;

    public Set<Paper> getReference() {
        return reference;
    }

    public void setReference(Set<Paper> reference) {
        this.reference = reference;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
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
}
