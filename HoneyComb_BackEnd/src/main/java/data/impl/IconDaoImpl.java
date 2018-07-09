package data.impl;

import com.mongodb.DB;
import data.IconDao;
import data.model.IconEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Repository("IconDaoImpl")
public class IconDaoImpl implements IconDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void _test() {
        Set<String> colls = this.mongoTemplate.getCollectionNames();
        for (String coll : colls) {
            System.out.println("CollectionName=" + coll);
        }
        DB db = this.mongoTemplate.getDb();
        System.out.println("db=" + db.toString());
    }

    @Override
    public void createCollection() {
        if (!this.mongoTemplate.collectionExists(IconEntity.class)) {
            this.mongoTemplate.createCollection(IconEntity.class);
        }
    }

    @Override
    public List<IconEntity> findList(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(new Order(Direction.ASC, "_id")));
        query.skip(skip).limit(limit);
        return this.mongoTemplate.find(query, IconEntity.class);
    }

    @Override
    public IconEntity findOne(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return this.mongoTemplate.findOne(query, IconEntity.class);
    }

    @Override
    public void insert(IconEntity entity) {
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void update(IconEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(entity.getId()));
        Update update = new Update();
        update.set("icon", entity.getIcon());
        this.mongoTemplate.updateFirst(query, update, IconEntity.class);

    }

}
