package data.dao;

import data.model.IconEntity;

import java.util.List;

public interface IconDao {

    public abstract IconEntity findOne(String id);

    public abstract void insert(IconEntity entity);

    public abstract void update(IconEntity entity);

}