package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Paper;
import model.User;
import org.hibernate.Hibernate;
import service.AppService;

import java.util.HashSet;
import java.util.Set;

public class UpdateStarAction extends ActionSupport{
    private Integer userId;
    private Long paperId;
    private Set<Paper> stars = new HashSet<>();

    public void setStars(Set<Paper> stars) {
        this.stars = stars;
    }

    public Set<Paper> getStars() {
        return stars;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String query() throws Exception {
        if (this.userId == null) return ERROR;
        User result = appService.getUserById(userId);
        if (result != null) {
            setStars(result.getStars());
            return SUCCESS;
        }
        return ERROR;
    }

    public String add() throws Exception {
        if (this.userId == null || this.paperId == null) return ERROR;
        User result = appService.getUserById(userId);
        if (result != null) {
            Paper paper = appService.getPaperById(paperId);
            if (paper == null) return ERROR;
            Set<Paper> temp = result.getStars();
            if (temp.contains(paper)) return ERROR;
            temp.add(paper);
            result.setStars(temp);
            appService.updateUser(result);
            return SUCCESS;
        }
        return ERROR;
    }

    public String delete() throws Exception {
        if (this.userId == null || this.paperId == null) return ERROR;
        User result = appService.getUserById(userId);
        if (result != null) {
            Paper paper = appService.getPaperById(paperId);
            if (paper == null) return ERROR;
            Set<Paper> temp = result.getStars();
            if (!temp.contains(paper)) return ERROR;
            temp.remove(paper);
            result.setStars(temp);
            appService.updateUser(result);
            return SUCCESS;
        }
        return ERROR;
    }
}
