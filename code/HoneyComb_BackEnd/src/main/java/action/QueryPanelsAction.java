package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.model.PanelEntity;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryPanelsAction extends ActionSupport {
    private String key;
    private ArrayList<PanelEntity> panels;
    private Integer start;
    private Integer end;
    private Integer userId;

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

    public String userPanel() throws Exception {
        if (this.userId == null) return ERROR;
        panels = new ArrayList<>();
        panels.addAll(panelDao.findUserList(this.userId));
        return SUCCESS;
    }
}
