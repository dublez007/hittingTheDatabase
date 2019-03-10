package com.dublez007.hittingDatabase.util;

import com.dublez007.hittingDatabase.model.AbstractBaseEntity;

public class ValidationUtil {

    public static <T extends AbstractBaseEntity> T checkNotFoundWithId(T entity, int id){
        if(entity == null)
            throw new NotFoundException("Entity not found with id: " + id);
        return entity;
    }
}
