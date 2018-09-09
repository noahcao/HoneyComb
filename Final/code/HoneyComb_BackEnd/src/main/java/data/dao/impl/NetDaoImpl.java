package data.dao.impl;

import com.mongodb.BasicDBObject;
import data.dao.NetDao;
import data.model.NetEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("NetDaoImpl")
public class NetDaoImpl implements NetDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<NetEntity> findUserList(Integer userId) {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("nodeArray", false);
        fieldsObject.put("linkArray", false);
        Query query = new BasicQuery(new BasicDBObject("userId", userId), fieldsObject);
        return this.mongoTemplate.find(query, NetEntity.class);
    }

    @Override
    public NetEntity findOne(String netId) {
        Query query = new Query();
        query.addCriteria(new Criteria("netId").is(netId));
        return this.mongoTemplate.findOne(query, NetEntity.class);
    }

    @Override
    public void insert(NetEntity entity) {
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void update(NetEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("netId").is(entity.getNetId()));
        Update update = new Update();
        if (entity.getDescription() != null) update.set("description", entity.getDescription());
        if (entity.getName() != null) update.set("name", entity.getName());
        if (entity.getNodeArray() != null) update.set("nodeArray", entity.getNodeArray());
        if (entity.getLinkArray() != null) update.set("linkArray", entity.getLinkArray());
        this.mongoTemplate.updateFirst(query, update, NetEntity.class);
    }

    @Override
    public void deleteById(String netId) {
        Query query = new Query();
        query.addCriteria(new Criteria("netId").is(netId));
        this.mongoTemplate.remove(query, NetEntity.class);
    }

}
