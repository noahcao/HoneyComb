package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.NetDaoImpl;
import data.model.LinkEntity;
import data.model.NetEntity;
import data.model.NodeEntity;
import service.AppService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class UpdateNetAction extends ActionSupport {
    private Integer userId;
    private String netId;
    private Set<NodeEntity> nodeArray = new HashSet<>();
    private Set<LinkEntity> linkArray = new HashSet<>();
    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Set<LinkEntity> getLinkArray() {
        return linkArray;
    }

    public Set<NodeEntity> getNodeArray() {
        return nodeArray;
    }

    public void setLinkArray(Set<LinkEntity> linkArray) {
        this.linkArray = linkArray;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public void setNodeArray(Set<NodeEntity> nodeArray) {
        this.nodeArray = nodeArray;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Resource
    private NetDaoImpl netDao;

    public void setNetDao(NetDaoImpl netDao) {
        this.netDao = netDao;
    }

    public String query() throws Exception {
        if (this.netId == null) return ERROR;
        NetEntity e = netDao.findOne(this.netId);
        if (e == null) return NONE;
        setDescription(e.getDescription());
        setLinkArray(e.getLinkArray());
        setName(e.getName());
        setUserId(e.getUserId());
        setNodeArray(e.getNodeArray());
        return SUCCESS;
    }

    public String add() throws Exception {
        if (this.userId == null) return ERROR;
        if (appService.getUserById(this.userId) == null) return ERROR;
        NetEntity e = new NetEntity();
        e.setDescription(this.description);
        e.setName(this.name);
        e.setNodeArray(this.nodeArray);
        e.setLinkArray(this.linkArray);
        e.setUserId(this.userId);
        netDao.insert(e);
        this.netId = e.getNetId();
        return SUCCESS;
    }

    public String delete() throws Exception {
        if (this.netId == null) return ERROR;
        netDao.deleteById(this.netId);
        return SUCCESS;
    }

    public String update() throws Exception {
        if (this.netId == null) return ERROR;
        NetEntity e = new NetEntity();
        e.setNetId(this.netId);
        e.setDescription(this.description);
        e.setName(this.name);
        e.setLinkArray(this.linkArray);
        e.setNodeArray(this.nodeArray);
        netDao.update(e);
        query();
        return SUCCESS;
    }
}
