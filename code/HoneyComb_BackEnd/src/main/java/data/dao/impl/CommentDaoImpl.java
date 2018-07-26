package data.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import data.dao.CommentDao;
import data.model.CommentEntity;
import data.model.PanelEntity;
import data.model.PostEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

@Repository("CommentDaoImpl")
public class CommentDaoImpl implements CommentDao{
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(String panelId, Integer postId, CommentEntity entity) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and(new BasicDBObject("_id", panelId), new BasicDBObject("posts._id", postId));

        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("posts.$", true);

        Update updateInc = new Update();
        updateInc.inc("posts.$.commentId", 1);

        int id = this.mongoTemplate.findAndModify(new BasicQuery(queryBuilder.get(), fieldsObject), updateInc, PanelEntity.class).getPosts().get(0).getCommentId();

        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("_id").is(panelId), Criteria.where("posts").elemMatch(Criteria.where("_id").is(postId))));
        entity.setTime(new Date());
        entity.setId(id);
        Update update = new Update();
        update.push("posts.$.comments", entity);
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }

    @Override
    public void deleteById(String panelId, Integer postId, Integer id) {
        Update update = new Update();
        update.pull("posts.$.comments", new BasicDBObject("_id", id));
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("_id").is(panelId), Criteria.where("posts").elemMatch(Criteria.where("_id").is(postId))));
        this.mongoTemplate.updateFirst(query, update, PanelEntity.class);
    }
}
