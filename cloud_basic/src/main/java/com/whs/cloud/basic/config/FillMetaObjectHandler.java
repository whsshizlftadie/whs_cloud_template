package com.whs.cloud.basic.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.whs.cloud.basic.constant.FiledConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FiledConstant.CREATE_TIME, Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, FiledConstant.MODIFY_TIME, Date.class, new Date());
    }

}