package action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import service.AppService;

import java.util.List;

public class QueryUsersAction extends ActionSupport {
    private String name;
    private List<User> users;
    private AppService appService;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String allUsers() throws Exception {
        List<User> users = appService.getAllUsers();
        setUsers(users);
        return SUCCESS;
    }

    public String search() throws Exception {
        if (this.name == null) return ERROR;
        setUsers(appService.queryUser(name));
        return SUCCESS;
    }
}
