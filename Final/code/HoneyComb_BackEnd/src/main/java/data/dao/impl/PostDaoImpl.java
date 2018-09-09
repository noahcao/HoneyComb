package data.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import data.dao.PostDao;
import data.model.PanelEntity;
import data.model.PostEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository("PostDaoImpl")
public class PostDaoImpl implements PostDao {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private PanelDaoImpl panelDao;

    @Override
    public List<PostEntity> findList(String panelId, int start, int end) {
        PanelEntity panel = panelDao.findOne(panelId);
        List<PostEntity> results = panel.getPosts();
        if (start + 1 > results.size()) return new ArrayList<>();
        if (end > results.size()) end = results.size();
        return results.subList(start, end);
    }

    @Override
    public PostEntity findOne(String panelId, Integer id) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and(new BasicDBObject("_id", panelId), new BasicDBObject("posts._id", id));
//        DBObject dbObject = (DBObject) JSON.parse("{ _id: \"" + panelId + "\", posts: { $elemMatch: { _id: \"" + id + "\" } } }");

        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("posts.$", true);

        Query query = new BasicQuery(queryBuilder.get(), fieldsObject);
        PanelEntity temp = this.mongoTemplate.findOne(query, PanelEntity.class);
        return temp.getPosts().get(0);
    }

    @Override
    public void insert(String panelId, PostEntity entity) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", panelId);

        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("posts", false);

        Update updateInc = new Update();
        updateInc.inc("postId", 1);

        int id = this.mongoTemplate.findAndModify(new BasicQuery(dbObject, fieldsObject), updateInc, PanelEntity.class).getPostId();

        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(panelId));
        entity.setTime(new Date());
        entity.setId(id);
        entity.setCommentId(0);
        Update update = new Update();
        update.push("posts", entity);
        update.set("time", entity.getTime());
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }

    @Override
    public void update(String panelId, PostEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("_id").is(panelId), Criteria.where("posts").elemMatch(Criteria.where("_id").is(entity.getId()))));
        entity.setTime(new Date());
        Update update = new Update();
        update.set("posts.$.content", entity.getContent());
        update.set("posts.$.time", entity.getTime());
        update.set("posts.$.userId", entity.getUserId());
        update.set("posts.$.type", entity.getType());
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }

    @Override
    public void deleteById(String panelId, Integer id) {
        Update update = new Update();
        update.pull("posts", new BasicDBObject("_id", id));
        Query query = Query.query(Criteria.where("_id").is(panelId));
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }

    @Override
    public List<PostEntity> findUserList(Integer userId) {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("posts.comments", false);
        fieldsObject.put("title", false);
        Query query = new BasicQuery(new BasicDBObject("posts.userId", userId), fieldsObject);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "posts.time")));
        List<PostEntity> result = new LinkedList<>();
        for (PanelEntity panel : this.mongoTemplate.find(query, PanelEntity.class)) {
            result.addAll(panel.getPosts());
        }
        return result;
    }
}
