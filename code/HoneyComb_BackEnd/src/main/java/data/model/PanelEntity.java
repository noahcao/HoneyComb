package data.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class PanelEntity {

    @Id
    private String id;
    private String title;
    private Integer owner;
    private Date time;
    private Integer postId = 0;
    private ArrayList<PostEntity> posts;

    public ArrayList<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostEntity> posts) {
        this.posts = posts;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
