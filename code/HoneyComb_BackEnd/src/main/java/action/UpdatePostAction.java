package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.dao.impl.PostDaoImpl;
import data.model.CommentEntity;
import data.model.PostEntity;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class UpdatePostAction extends ActionSupport{

    private String panelId;
    private Integer id;
    private String content;
    private Integer userId;
    private String userName;
    private Date time;
    private String type;
    private ArrayList<CommentEntity> comments;

    public ArrayList<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentEntity> comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

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

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Resource
    private PostDaoImpl postDao;

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }

    public String add() throws Exception {
        if (this.panelId == null || this.userId == null) return ERROR;
        PostEntity post = new PostEntity();
        post.setContent(this.content);
        post.setUserId(this.userId);
        post.setType(this.type);
        post.setComments(new ArrayList<>());

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
        post.setType(this.type);

        postDao.update(panelId, post);
        this.time = post.getTime();
        this.comments = post.getComments();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.panelId == null || this.id == null) return ERROR;
        postDao.deleteById(this.panelId, this.id);
        return SUCCESS;
    }
}
