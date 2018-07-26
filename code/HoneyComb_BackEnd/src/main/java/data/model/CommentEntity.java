package data.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class CommentEntity {
    @Id
    private Integer id;
    private Integer userId;
    private String content;
    private Date time;
    private Integer target;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTarget() {
        return target;
    }
}
