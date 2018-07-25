package model;

import org.springframework.data.annotation.Id;

import java.util.Set;

public class Author {
    @Id
    private Long id;
    private String name;
    private String url;
    private Set<Long> papers;

    public Set<Long> getPapers() {
        return papers;
    }

    public void setPapers(Set<Long> papers) {
        this.papers = papers;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
