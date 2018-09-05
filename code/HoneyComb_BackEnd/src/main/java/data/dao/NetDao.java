package data.dao;

import data.model.NetEntity;

import java.util.List;

public interface NetDao {
    List<NetEntity> findUserList(Integer userId);

    NetEntity findOne(String netId);

    void insert(NetEntity entity);

    void update(NetEntity entity);

    void deleteById(String netId);
}
