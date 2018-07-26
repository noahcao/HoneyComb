package data.dao;

import data.model.PanelEntity;

import java.util.List;

public interface PanelDao {
    List<PanelEntity> findList(int skip, int limit);

    PanelEntity findOne(String id);

    void insert(PanelEntity entity);

    void update(PanelEntity entity);

    void deleteById(String id);
}
