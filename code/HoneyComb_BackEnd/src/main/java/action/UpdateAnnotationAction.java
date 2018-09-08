package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.AnnotationDaoImpl;
import data.model.Annotation;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.util.Date;

public class UpdateAnnotationAction extends ActionSupport {
    private Integer userId;
    private Long paperId;
    private String userName;
    private Date time;
    private String content;
    private String commentId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentId() {
        return commentId;
    }

    @Resource
    private AnnotationDaoImpl annotationDao;
    private AppService appService;

    public void setAnnotationDao(AnnotationDaoImpl annotationDao) {
        this.annotationDao = annotationDao;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String add() throws Exception {
        if (this.userId == null || this.paperId == null) return ERROR;
        User result = appService.getUserById(this.userId);
        if (result == null) return ERROR;
        if (appService.getPaperById(this.paperId) == null) return ERROR;
        Annotation e = new Annotation();
        e.setContent(this.content);
        e.setPaperId(this.paperId);
        e.setUserId(this.userId);
        annotationDao.insert(e);

        this.commentId = e.getCommentId();
        this.time = e.getTime();
        this.userName = result.getName();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.commentId == null) return ERROR;
        annotationDao.deleteById(this.commentId);
        return SUCCESS;
    }
}
