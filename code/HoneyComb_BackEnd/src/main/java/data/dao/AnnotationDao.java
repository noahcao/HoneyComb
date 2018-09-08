package data.dao;

import data.model.Annotation;

import java.util.List;

public interface AnnotationDao {
    void insert(Annotation entity);

    void deleteById(String id);

    List<Annotation> findList(Long paperId);
}
