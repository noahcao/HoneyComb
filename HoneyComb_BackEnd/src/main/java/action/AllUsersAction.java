package action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import service.AppService;

import java.util.List;

public class AllUsersAction extends ActionSupport {
    private List<User> users;
    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String execute() throws Exception {
        List<User> users = appService.getAllUsers();
        setUsers(users);
        return SUCCESS;
    }

}
