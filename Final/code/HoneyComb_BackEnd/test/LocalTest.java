import action.*;
import data.dao.impl.CommentDaoImpl;
import data.dao.impl.IconDaoImpl;
import data.dao.impl.PanelDaoImpl;
import data.dao.impl.PostDaoImpl;
import model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import service.AppService;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.opensymphony.xwork2.Action.SUCCESS;

@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class LocalTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private AppService appService;
    private ArrayList<Integer> userId = new ArrayList<>();
    private ArrayList<String> panelId = new ArrayList<>();
    private ArrayList<Integer> postId = new ArrayList<>();
    private ArrayList<Integer> commentId = new ArrayList<>();

    @Resource
    private IconDaoImpl iconDao;

    @Transactional
    @Rollback
    @Test
    public void testUpdateUserAction() throws Exception {
        RegisterAction action1 = new RegisterAction();
        action1.setEmail("test-email-a");
        action1.setName("test-user-a");
        action1.setPwd("123");
        action1.setAppService(appService);
        action1.execute();

        UpdateUserAction action = new UpdateUserAction();
        action.setAppService(appService);
        action.setIconDao(iconDao);
        action.setId(action1.getId());
        action.query();

        action.setEmail("update");
        action.updateEmail();

        action.setPwd("1234");
        action.updatePassword();

        action.setBio("new bio");
        action.updateBio();

        action.setIcon("icon");
        action.updateIcon();

        action.queryUserName();
        action.query();
//        System.out.println(action.getName());
//        System.out.println(action.getBio());
//        System.out.println(action.getEmail());
//        System.out.println(action.getPwd());
//        System.out.println(action.getIcon());
        User user = new User();
        user.setId(action1.getId());
        appService.deleteUser(user);
    }

    @Transactional
    @Rollback
    @Test
    public void testGetUser() {
        System.out.println(appService.getAllUsers().size());
        System.out.println(appService.getUserByEmail("test-email0").getId());
        System.out.println(appService.getUserByName("test-user0").getId());
    }

    @Transactional
    @Rollback
    @Test
    public void testAddUser() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setPwd("123");
            user.setName("test-user" + i);
            user.setEmail("test-email" + i);
            userId.add(appService.addUser(user));
        }
        System.out.println(userId.size());
    }

    @Transactional
    @Rollback
    @Test
    @Repeat(100)
    public void testQueryPaper() {
        appService.getPaperById(38712L);
        appService.getPaperSmallByTitle("a");
        appService.getPaperByTitle("a");
        appService.getPaperSmallLikeTitle("mk");
    }

    @Transactional
    @Rollback
    @Test
    public void testUserLogin() {
        for (Integer i : userId) {
            User user = appService.UserAuthorize("test-user" + i, "123");
            if (user == null) System.out.println("fail at" + i);
        }
    }

    @Resource
    private PanelDaoImpl panelDao;

    @Test
    public void testUpdatePanelAction() throws Exception {
        UpdatePanelAction action = new UpdatePanelAction();
        QueryPanelsAction queryPanelsAction = new QueryPanelsAction();
        queryPanelsAction.setPanelDao(panelDao);
        action.setPanelDao(panelDao);
        for (int i = 0; i < 100; i++) {
            action.setTitle("test-title" + i);
            action.setOwner(i + 1);
            if (action.add().equals(SUCCESS)) {
                panelId.add(action.getId());
            } else {
                System.out.println("fail add at" + i);
            }
        }
        System.out.println("add " + panelId.size());
        for (String i : panelId) {
            action.setId(i);
            if (action.query().equals(SUCCESS)) {
                action.setTitle(action.getTitle() + " update");
                action.update();
                action.delete();
            } else {
                System.out.println("fail query at" + i);
            }
        }
        queryPanelsAction.queryLatest();
        System.out.println("finally " + queryPanelsAction.getPanels().size());
    }

    @Resource
    private PostDaoImpl postDao;

    @Test
    public void testUpdatePostAction() throws Exception {
        UpdatePanelAction action2 = new UpdatePanelAction();
        action2.setPanelDao(panelDao);
        action2.setTitle("test-post");
        action2.setOwner(0);
        action2.add();
        panelId.add(action2.getId());

        UpdatePostAction action = new UpdatePostAction();
        action.setPostDao(postDao);
        for (int i = 0; i < 100; i++) {
            action.setPanelId(panelId.get(0));
            action.setUserId(i + 1);
            action.setContent("content " + i);
            if (action.add().equals(SUCCESS)) {
                postId.add(action.getId());
            } else {
                System.out.println("fail add at" + i);
            }
        }
        System.out.println("add " + postId.size());
        for (Integer i : postId) {
            action.setId(i);
            action.setContent(action.getContent() + " update");
            action.update();
            action.delete();
        }
        action2.query();
        System.out.println("finally " + action2.getPosts().size());
        action2.delete();
    }

    @Resource
    private CommentDaoImpl commentDao;

    @Test
    public void testUpdateCommentAction() throws Exception {
        UpdatePanelAction action2 = new UpdatePanelAction();
        action2.setPanelDao(panelDao);
        action2.setTitle("test-post");
        action2.setOwner(0);
        action2.add();
        panelId.add(action2.getId());

        UpdatePostAction action1 = new UpdatePostAction();
        action1.setUserId(0);
        action1.setPanelId(panelId.get(0));
        action1.setPostDao(postDao);
        action1.add();
        postId.add(action1.getId());

        UpdateCommentAction action = new UpdateCommentAction();
        action.setCommentDao(commentDao);
        for (int i = 0; i < 100; i++) {
            action.setPanelId(panelId.get(0));
            action.setUserId(i + 1);
            action.setContent("content " + i);
            action.setPostId(postId.get(0));
            if (action.add().equals(SUCCESS)) {
                commentId.add(action.getId());
            } else {
                System.out.println("fail add at" + i);
            }
        }
        System.out.println("add " + commentId.size());
        for (Integer i : commentId) {
            action.setId(i);
            action.delete();
        }
        action2.query();
        System.out.println("finally " + action2.getPosts().get(0).getComments().size());
        action2.delete();
    }
}
