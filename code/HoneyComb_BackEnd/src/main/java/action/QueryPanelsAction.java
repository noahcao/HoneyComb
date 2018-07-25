package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.PanelDaoImpl;
import data.model.PanelEntity;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryPanelsAction extends ActionSupport {
    private String key;
    private ArrayList<PanelEntity> panels;
    private Integer start;
    private Integer end;

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

    @Resource
    private PanelDaoImpl panelDao;

    public String search() throws Exception {
        this.panels = new ArrayList<>();
        this.panels.addAll(panelDao.findList(0,0));
        return SUCCESS;
    }
}
