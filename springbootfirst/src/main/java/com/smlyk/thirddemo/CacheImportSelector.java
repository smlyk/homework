package com.smlyk.thirddemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author yekai
 */
public class CacheImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableDefineService.class.getName());
        //动态注入bean :自己去实现判断逻辑实现动态配置
        if (((Class[])attributes.get("exclude")).length == 0){

            //返回的是一个固定的CacheService
            return new String[]{CacheService.class.getName()};
        }

        return new String[]{};
    }
}
