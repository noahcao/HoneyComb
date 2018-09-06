package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.CommentDaoImpl;
import data.model.CommentEntity;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryCommentsAction extends ActionSupport {
    private Integer userId;
    private ArrayList<CommentEntity> comments;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setComments(ArrayList<CommentEntity> comments) {
        this.comments = comments;
    }

    public ArrayList<CommentEntity> getComments() {
        return comments;
    }

    @Resource
    private CommentDaoImpl commentDao;

    public void setCommentDao(CommentDaoImpl commentDao) {
        this.commentDao = commentDao;
    }

    public String userComment() throws Exception {
        if (this.userId == null) return ERROR;
        comments = new ArrayList<>();
        comments.addAll(commentDao.findUserList(this.userId));
        return SUCCESS;
    }
}
