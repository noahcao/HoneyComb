package action;

import com.opensymphony.xwork2.ActionSupport;
import model.History;
import model.Paper;
import model.PaperSmall;
import model.User;
import org.hibernate.Hibernate;
import service.AppService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UpdateHistoryAction extends ActionSupport {
    private Integer userId;
    private Long paperId;
    private ArrayList<History> history = new ArrayList<>();

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public Long getPaperId() {
        return paperId;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public String query() throws Exception {
        if (this.userId == null) return ERROR;
        User result = appService.getUserById(userId);
        if (result != null) {
            this.history.addAll(result.getHistory());
            History.ComparePair com = new History.ComparePair();
            this.history.sort(com);
            if (this.history.size() > 1000) {
                result.setHistory(new HashSet<>(this.history.subList(0, 1000)));
                appService.updateUser(result);
            }
            return SUCCESS;
        }
        return ERROR;
    }

    public String add() throws Exception {
        if (this.userId == null || this.paperId == null) return ERROR;
        User result = appService.getUserById(this.userId);
        if (result != null) {
            Paper paper = appService.getPaperById(this.paperId);
            if (paper == null) return ERROR;
            Set<History> temp = result.getHistory();

            PaperSmall newPaper = new PaperSmall();
            newPaper.setUrl(paper.getUrl());
            newPaper.setId(paper.getId());
            newPaper.setTitle(paper.getTitle());

            History newHistory = new History();
            newHistory.setPaper(newPaper);
            newHistory.setTime(new Timestamp(new Date().getTime()));
            temp.add(newHistory);
            result.setHistory(temp);
            appService.updateUser(result);
            return SUCCESS;
        }
        return ERROR;
    }
}
