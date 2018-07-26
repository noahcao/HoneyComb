package action;

import com.opensymphony.xwork2.ActionSupport;
import model.User;
import service.AppService;

public class RegisterAction extends ActionSupport {
    private Integer id;
    private String pwd;
    private String email;
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        if (this.email == null || this.name == null || this.pwd == null) return ERROR;
        User resultEmail = appService.getUserByEmail(email);
        User resultName = appService.getUserByName(name);
        if (resultEmail == null && resultName == null) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPwd(pwd);
            appService.addUser(user);
            setId(user.getId());
        }
        if (resultName != null) setName(null);
        if (resultEmail != null) setEmail(null);
        setPwd(null);
        return SUCCESS;
    }
}
