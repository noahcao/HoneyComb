package data.dao;

import data.model.CommentEntity;

public interface CommentDao {
    void insert(String panelId, Integer postId, CommentEntity entity);

    void deleteById(String panelId, Integer postId, Integer id);
}
