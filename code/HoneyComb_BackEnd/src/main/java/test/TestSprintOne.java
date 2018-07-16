package test;

import action.LoginAction;
import action.RegisterAction;
import dao.impl.PaperDaoImpl;
import dao.impl.UserDaoImpl;
import service.AppService;
import service.impl.AppServiceImpl;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class TestSprintOne {
    private static boolean LoginActionTest(AppService appService) throws Exception{
        boolean flag;
        LoginAction loginAction = new LoginAction();
        loginAction.setAppService(appService);
        loginAction.setName("testuser");
        loginAction.setPwd("123");
        flag = loginAction.login().equals(SUCCESS);
        loginAction.setPwd(null);
        flag = flag & loginAction.login().equals(ERROR);
        flag = flag & loginAction.loginStatus().equals(SUCCESS);
        return flag;
    }

    private static boolean RegisterActionTest(AppService appService) throws Exception {
        boolean flag;
        RegisterAction registerAction = new RegisterAction();
        flag = registerAction.execute().equals(ERROR);
        registerAction.setAppService(appService);
        registerAction.setName("testuser");
        registerAction.setPwd("123");
        registerAction.setEmail("test@testuser.top");
        flag = flag & registerAction.execute().equals(SUCCESS);
        return flag;
    }

    public static void main (String args[]) throws Exception{
        AppServiceImpl appService = new AppServiceImpl();
        appService.setPaperDao(new PaperDaoImpl());
        appService.setUserDao(new UserDaoImpl());

        System.out.println("RegisterActionTest");
        long start = System.currentTimeMillis();
        String msg = RegisterActionTest(appService) ? "Pass!" : "Fail!";
        long end = System.currentTimeMillis();
        System.out.println(msg);
        System.out.println("RegisterAction runtime: " + (end - start) + "ms");

        System.out.println("LoginActionTest");
        start = System.currentTimeMillis();
        msg = LoginActionTest(appService) ? "Pass!" : "Fail!";
        end = System.currentTimeMillis();
        System.out.println(msg);
        System.out.println("LoginAction runtime: " + (end - start) + "ms");
    }
}
