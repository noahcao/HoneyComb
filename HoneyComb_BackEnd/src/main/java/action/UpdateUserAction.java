package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.User;
import service.AppService;

import java.util.Set;


public class UpdateUserAction extends ActionSupport {
    private Integer id;

    private String pwd;
    private String icon;
    private String email;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setUserInfo(User item) {
        setName(item.getName());
        setEmail(item.getEmail());
        setId(item.getId());
        setIcon(item.getIcon());
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String query() throws Exception {
        if (this.id == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            setUserInfo(result);
            // Hibernate.initialize(result.getOrders());
            return SUCCESS;
        }
        return NONE;
    }

    public String updateEmail() throws Exception {
        if (this.email == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            result.setEmail(email);
            appService.updateUser(result);
            return SUCCESS;
        }
        return NONE;
    }

    public String updatePassword() throws Exception {
        if (this.pwd == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            result.setPwd(this.pwd);
            appService.updateUser(result);
            return SUCCESS;
        }
        return NONE;
    }

//
//    public String updateIcon() throws Exception {
//        User result = appService.getUserById(id);
//        if (result != null) {
//            ConfigurableApplicationContext context = null;
//            context = new ClassPathXmlApplicationContext("mongo.xml");
//            IconDao iconDao = context.getBean(IconDaoImpl.class);
//            iconDao.createCollection();
//            IconEntity e = iconDao.findOne("" + id);
//            if (e == null) {
//                setIcon(null);
//                return "error";
//            }
//            e.setIcon(icon);
//            iconDao.update(e);
//            return "success";
//        }
//        setIcon(null);
//        return "error";
//    }



}
