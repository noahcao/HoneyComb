package data.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import data.dao.PanelDao;
import data.model.PanelEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository("PanelDaoImpl")
public class PanelDaoImpl implements PanelDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<PanelEntity> findList(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(new Order(Direction.DESC, "time")));
        if (limit == 0) query.skip(skip);
        else query.skip(skip).limit(limit);
        return this.mongoTemplate.find(query, PanelEntity.class);
    }

    @Override
    public PanelEntity findOne(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return this.mongoTemplate.findOne(query, PanelEntity.class);
    }

    @Override
    public void insert(PanelEntity entity) {
        entity.setTime(new Date());
        entity.setPostId(0);
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void update(PanelEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(entity.getId()));
        entity.setTime(new Date());
        Update update = new Update();
        if (entity.getOwner() != null) update.set("owner", entity.getOwner());
        if (entity.getTitle() != null) update.set("title", entity.getTitle());
        update.set("time", entity.getTime());
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        this.mongoTemplate.remove(query, PanelEntity.class);
    }

    @Override
    public List<PanelEntity> findUserList(Integer owner) {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("posts", false);
        Query query = new BasicQuery(new BasicDBObject("owner", owner), fieldsObject);
        query.with(new Sort(new Order(Direction.DESC, "time")));
        return this.mongoTemplate.find(query, PanelEntity.class);
    }
}
