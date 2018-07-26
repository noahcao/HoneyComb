package model;

import org.springframework.data.annotation.Id;

import java.util.Set;

public class User {
    @Id
    private Integer id;

    private String pwd;
    private String email;
    private String name;
    private String icon;
    private Set<Paper> stars;
    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Paper> getStars() {
        return stars;
    }

    public void setStars(Set<Paper> stars) {
        this.stars = stars;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
