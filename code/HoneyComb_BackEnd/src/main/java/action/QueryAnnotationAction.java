package action;

import com.opensymphony.xwork2.ActionSupport;
import data.dao.impl.AnnotationDaoImpl;
import data.model.Annotation;
import model.User;
import service.AppService;

import javax.annotation.Resource;
import java.util.ArrayList;

public class QueryAnnotationAction extends ActionSupport {
    private ArrayList<Annotation> paperComments;
    private Long paperId;

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperComments(ArrayList<Annotation> paperComments) {
        this.paperComments = paperComments;
    }

    public ArrayList<Annotation> getPaperComments() {
        return paperComments;
    }

    @Resource
    private AnnotationDaoImpl annotationDao;
    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public void setAnnotationDao(AnnotationDaoImpl annotationDao) {
        this.annotationDao = annotationDao;
    }

    @Override
    public String execute() throws Exception {
        if (this.paperId == null) return ERROR;
        this.paperComments = new ArrayList<>();
        this.paperComments.addAll(annotationDao.findList(this.paperId));
        for (Annotation annotation : this.paperComments) {
            User user = appService.getUserById(annotation.getUserId());
            if (user == null) {
                continue;
            }
            annotation.setUserName(user.getName());
        }
        return SUCCESS;
    }
}
