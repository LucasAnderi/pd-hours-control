package org.pd.pdhcapi.service.impl;

import org.pd.pdhc.database.dao.SquadDao;
import org.pd.pdhc.models.Squad;
import org.pd.pdhcapi.service.SquadRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SquadRestServiceImpl implements SquadRestService<Squad> {

    @Autowired
    private SquadDao squadDao;


    @Override
    public int create(Squad entity) {
        return squadDao.create(entity);
    }
}
