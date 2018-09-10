package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.model.PanelEntity;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class QueryPanelsAction extends ActionSupport {
    private String key;
    private String type;
    private ArrayList<PanelEntity> panels;
    private Integer start;
    private Integer end;
    private Integer userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<PanelEntity> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<PanelEntity> panels) {
        this.panels = panels;
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

    public String queryLatest() throws Exception {
        this.panels = new ArrayList<>();
        this.panels.addAll(panelDao.findList(0, 0));
        for (PanelEntity panel : this.panels) {
            User result = appService.getUserById(panel.getOwner());
            if (result == null) continue;
            panel.setOwnerName(result.getName());
        }
        return SUCCESS;
    }

    public String search() throws Exception {
        if (this.key == null || this.type == null) return ERROR;
        this.key = this.key.toLowerCase();
        this.panels = new ArrayList<>();
        if (this.type.equals("title")) {
            this.panels.addAll(panelDao.findListByTitle(this.key));
        }
        else if (this.type.equals("content")) {
            this.panels.addAll(panelDao.findListByContent(this.key));
        }
        else if (this.type.equals("user")) {
            List<User> users = appService.queryUser(this.key);
            for (User user : users) {
                this.panels.addAll(panelDao.findUserList(user.getId()));
            }
        }
        else return ERROR;
        for (PanelEntity panel : this.panels) {
            User result = appService.getUserById(panel.getOwner());
            if (result == null) {
                this.panels.remove(panel);
                panelDao.deleteById(panel.getId());
                continue;
            }
            panel.setOwnerName(result.getName());
        }
        return SUCCESS;
    }

    public String userPanel() throws Exception {
        if (this.userId == null) return ERROR;
        panels = new ArrayList<>();
        panels.addAll(panelDao.findUserList(this.userId));
        return SUCCESS;
    }
}
