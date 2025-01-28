package org.pd.pdhc.database.dao;

import java.util.List;

public interface BaseDao<T> {

    int create(T entitiy);
}
