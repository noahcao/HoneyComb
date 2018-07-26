package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

public class LogoutAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        Map<String, Object> usersession = ActionContext.getContext().getSession();
        if (usersession.get("username") != null) {
            usersession.clear();
        }
        return SUCCESS;
    }
}
