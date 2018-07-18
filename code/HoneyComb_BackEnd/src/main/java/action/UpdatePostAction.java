package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.dao.impl.PostDaoImpl;
import data.model.PostEntity;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

public class UpdatePostAction extends ActionSupport{

    private String panelId;
    private Integer id;
    private String content;
    private Integer userId;
    private Date time;

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Resource
    private PostDaoImpl postDao;
    @Resource
    private PanelDaoImpl panelDao;

    public String add() throws Exception {
        if (this.panelId == null || this.userId == null) return ERROR;
        PostEntity post = new PostEntity();
        post.setContent(this.content);
        post.setUserId(this.userId);

        postDao.insert(panelId, post);
        this.id = post.getId();
        this.time = post.getTime();
        return SUCCESS;
    }

    public String update() throws Exception {
        if (this.panelId == null || this.userId == null || this.id == null) return ERROR;
        PostEntity post = new PostEntity();
        post.setId(this.id);
        post.setContent(this.content);
        post.setUserId(this.userId);

        postDao.update(panelId, post);
        this.time = post.getTime();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.panelId == null || this.id == null) return ERROR;
        postDao.deleteById(this.panelId, this.id);
        return SUCCESS;
    }
}
