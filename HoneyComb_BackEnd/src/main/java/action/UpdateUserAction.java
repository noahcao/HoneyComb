package action;

import com.opensymphony.xwork2.ActionSupport;
import data.IconDao;
import data.impl.IconDaoImpl;
import data.model.IconEntity;
import model.Paper;
import model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AppService;

import java.util.Set;


public class UpdateUserAction extends ActionSupport {
    private Integer id;

    private String pwd;
    private String icon;
    private String email;
    private String name;
    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

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
        setBio(item.getBio());
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

            ConfigurableApplicationContext context = null;
            context = new ClassPathXmlApplicationContext("mongo.xml");
            IconDao iconDao = context.getBean(IconDaoImpl.class);
            iconDao.createCollection();
            IconEntity e = iconDao.findOne("" + id);
            if (e == null) {
                e = new IconEntity();
                e.setId("" + id);
                iconDao.insert(e);
                iconDao.update(e);
            }
            setIcon(e.getIcon());
            return SUCCESS;
        }
        return NONE;
    }

    public String updateEmail() throws Exception {
        if (this.email == null || this.id == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            result.setEmail(email);
            appService.updateUser(result);
            return SUCCESS;
        }
        return NONE;
    }

    public String updatePassword() throws Exception {
        if (this.pwd == null || this.id == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            result.setPwd(this.pwd);
            appService.updateUser(result);
            return SUCCESS;
        }
        setPwd(null);
        return NONE;
    }

//    public String updateIcon() throws Exception {
//        if (this.id == null) return ERROR;
//        User result = appService.getUserById(id);
//        if (result != null) {
//            result.setIcon(this.icon);
//            appService.updateUser(result);
//            return SUCCESS;
//        }
//        return NONE;
//    }

    public String updateIcon() throws  Exception {
        if (this.id == null) return ERROR;
        User result = appService.getUserById(this.id);
        if (result != null) {
            ConfigurableApplicationContext context;
            context = new ClassPathXmlApplicationContext("mongo.xml");
            IconDao iconDao = context.getBean(IconDaoImpl.class);
            iconDao.createCollection();
            IconEntity e = iconDao.findOne("" + this.id);
            if (e == null) {
                setIcon(null);
                return ERROR;
            }
            e.setIcon(this.icon);
            iconDao.update(e);
            return SUCCESS;
        }
        setIcon(null);
        return NONE;
    }

    public String updateBio() throws Exception {
        if (this.id == null) return ERROR;
        User result = appService.getUserById(id);
        if (result != null) {
            result.setBio(this.bio);
            appService.updateUser(result);
            return SUCCESS;
        }
        return NONE;
    }
}
