package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PostDaoImpl;
import data.model.PostEntity;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryPostsAction extends ActionSupport{
    private Integer userId;
    private ArrayList<PostEntity> posts;

    public ArrayList<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostEntity> posts) {
        this.posts = posts;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Resource
    private PostDaoImpl postDao;

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }

    public String userPost() throws Exception {
        if (this.userId == null) return ERROR;
        posts = new ArrayList<>();
        posts.addAll(postDao.findUserList(this.userId));
        return SUCCESS;
    }
}
