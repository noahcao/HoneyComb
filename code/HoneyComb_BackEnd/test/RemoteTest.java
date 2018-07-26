import action.*;
import data.dao.impl.IconDaoImpl;
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

@ContextConfiguration(locations = "classpath:applicationContext-remote.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class RemoteTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private AppService appService;

    @Transactional
    @Rollback
    @Test
    public void testGraphDataAction () throws Exception{
        GraphDataAction action = new GraphDataAction();
        action.setAppService(appService);
        action.setId(37819L);
        action.setHierarchyLimit(4);
        action.execute();
    }

    @Transactional
    @Rollback
    @Test
    public void testGetAuthorAction () throws Exception{
        GetAuthorAction action = new GetAuthorAction();
        action.setAppService(appService);
        action.setId(62154L);
        action.execute();
    }

    @Transactional
    @Rollback
    @Test
    public void testGetPaperAction () throws Exception{
        GetPaperAction action = new GetPaperAction();
        action.setAppService(appService);
        action.setId(37819L);
        action.execute();
    }

    @Transactional
    @Rollback
    @Test
    public void testQueryUsersAction () throws Exception{
        QueryUsersAction action = new QueryUsersAction();
        action.setAppService(appService);
        action.setName("test-user");
        action.search();
        action.allUsers();
    }
}
