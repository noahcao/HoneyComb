package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.model.CommentEntity;
import data.model.PanelEntity;
import data.model.PostEntity;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

public class UpdatePanelAction extends ActionSupport {
    private String id;
    private String title;
    private Integer owner;
    private String ownerName;
    private Date time;
    private ArrayList<PostEntity> posts;

    public void setPosts(ArrayList<PostEntity> posts) {
        this.posts = posts;
    }

    public ArrayList<PostEntity> getPosts() {
        return posts;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Resource
    private PanelDaoImpl panelDao;

    public void setPanelDao(PanelDaoImpl panelDao) {
        this.panelDao = panelDao;
    }

    public String add() throws Exception {
        if (this.owner == null || this.title == null) return ERROR;
        PanelEntity e = new PanelEntity();
        e.setOwner(this.owner);
        e.setTitle(this.title);
        e.setPosts(new ArrayList<>());
        panelDao.insert(e);
        this.id = e.getId();
        this.time = e.getTime();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.id == null) return ERROR;
        panelDao.deleteById(this.id);
        return SUCCESS;
    }

    public String update() throws Exception {
        if (this.id == null || (this.title == null && this.owner == null)) return ERROR;
        PanelEntity e = new PanelEntity();
        if (this.owner != null) e.setOwner(this.owner);
        if (this.title != null) e.setTitle(this.title);
        panelDao.update(e);
        query();
        return SUCCESS;
    }

    public String query() throws Exception {
        if (this.id == null) return ERROR;
        PanelEntity e = panelDao.findOne(id);
        if (e == null) return NONE;
        this.owner = e.getOwner();
        User result1 = appService.getUserById(this.owner);
        if (result1 == null) return ERROR;
        this.ownerName = result1.getName();
        this.title = e.getTitle();
        this.posts = e.getPosts();
        for (PostEntity post : this.posts) {
            User result2 = appService.getUserById(post.getUserId());
            if (result2 == null) return ERROR;
            post.setUserName(result2.getName());
            for (CommentEntity comment : post.getComments()) {
                User result3 = appService.getUserById(comment.getUserId());
                if (result3 == null) return ERROR;
                comment.setUserName(result3.getName());
            }
        }
        this.time = e.getTime();
        return SUCCESS;
    }
}
