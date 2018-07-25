package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.model.PanelEntity;
import data.model.PostEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

public class UpdatePanelAction extends ActionSupport {
    private String id;
    private String title;
    private Integer owner;
    private Date time;
    private ArrayList<PostEntity> posts;

    public void setPosts(ArrayList<PostEntity> posts) {
        this.posts = posts;
    }

    public ArrayList<PostEntity> getPosts() {
        return posts;
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

    @Resource
    private PanelDaoImpl panelDao;

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
        e.setOwner(this.owner);
        e.setTitle(this.title);
        panelDao.update(e);
        query();
        return SUCCESS;
    }

    public String query() throws Exception {
        if (this.id == null) return ERROR;
        PanelEntity e = panelDao.findOne(id);
        if (e == null) return NONE;
        this.owner = e.getOwner();
        this.title = e.getTitle();
        this.posts = e.getPosts();
        this.time = e.getTime();
        return SUCCESS;
    }
}
