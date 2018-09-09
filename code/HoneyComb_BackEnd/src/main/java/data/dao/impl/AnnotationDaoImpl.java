package data.dao.impl;

import data.dao.AnnotationDao;
import data.model.Annotation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository("AnnotationDaoImpl")
public class AnnotationDaoImpl implements AnnotationDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Annotation entity) {
        entity.setTime(new Date());
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("commentId").is(id));
        this.mongoTemplate.remove(query, Annotation.class);
    }

    @Override
    public List<Annotation> findList(Long paperId) {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "time")));
        query.addCriteria(new Criteria("paperId").is(paperId));
        return this.mongoTemplate.find(query, Annotation.class);
    }
}
