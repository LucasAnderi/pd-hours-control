package org.pd.pdhc.database.dao;

import java.util.List;

public interface BaseDao<T> {

    List<T> find();

    int create(T entitiy);
}
