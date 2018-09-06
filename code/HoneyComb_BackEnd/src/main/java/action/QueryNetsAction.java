package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.NetDaoImpl;
import data.model.NetEntity;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryNetsAction extends ActionSupport{
    private Integer userId;
    private ArrayList<NetEntity> nets;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<NetEntity> getNets() {
        return nets;
    }

    public void setNets(ArrayList<NetEntity> nets) {
        this.nets = nets;
    }

    @Resource
    private NetDaoImpl netDao;

    public void setNetDao(NetDaoImpl netDao) {
        this.netDao = netDao;
    }

    public String userNet() throws Exception {
        if (this.userId == null) return ERROR;
        nets = new ArrayList<>();
        nets.addAll(netDao.findUserList(this.userId));
        return SUCCESS;
    }
}
