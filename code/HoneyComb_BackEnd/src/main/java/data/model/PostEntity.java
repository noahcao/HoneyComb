package data.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class PostEntity {

    @Id
    private Integer id;
    private String content;
    private Integer userId;
    private Date time;
    private Integer commentId = 0;
    private String type;
    private ArrayList<CommentEntity> comments;

    public ArrayList<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentEntity> comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
