package data.dao;

import data.model.PostEntity;

import java.util.List;

public interface PostDao {
    List<PostEntity> findList(String panelId, int start, int end);

    PostEntity findOne(String panelId, Integer id);

    void insert(String panelId, PostEntity entity);

    void update(String panelId, PostEntity entity);

    void deleteById(String panelId, Integer id);
}
