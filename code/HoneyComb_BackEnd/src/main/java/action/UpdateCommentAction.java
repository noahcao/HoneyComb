package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.CommentDaoImpl;
import data.model.CommentEntity;
import service.AppService;

import javax.annotation.Resource;
import java.util.Date;

public class UpdateCommentAction extends ActionSupport {
    private Integer id;
    private Integer userId;
    private String content;
    private Date time;
    private Integer target;
    private String panelId;
    private Integer postId;

    public Integer getTarget() {
        return target;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getId() {
        return id;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    @Resource
    private CommentDaoImpl commentDao;
    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public void setCommentDao(CommentDaoImpl commentDao) {
        this.commentDao = commentDao;
    }

    public String add() throws Exception {
        if (this.userId == null || this.panelId == null || this.postId == null) return ERROR;
        if (appService.getUserById(this.userId) == null) return ERROR;
        CommentEntity e = new CommentEntity();
        e.setContent(this.content);
        e.setTarget(this.target);
        e.setUserId(this.userId);
        commentDao.insert(this.panelId, this.postId, e);

        this.id = e.getId();
        this.time = e.getTime();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.panelId == null || this.postId == null || this.id == null) return ERROR;
        commentDao.deleteById(this.panelId, this.postId, this.id);
        return SUCCESS;
    }
}
