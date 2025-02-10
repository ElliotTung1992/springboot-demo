package com.github.elliot.springbootlearn.configuration;

import com.github.elliot.springbootlearn.annotation.EnableCatch;
import com.github.elliot.springbootlearn.enums.CatchTypeEnum;
import com.github.elliot.springbootlearn.service.impl.LocalCatchServiceImpl;
import com.github.elliot.springbootlearn.service.impl.RedisCatchServiceImpl;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class CatchServiceImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableCatch.class.getName());
        CatchTypeEnum catchTypeEnum = (CatchTypeEnum) annotationAttributes.get("catchType");
        switch (catchTypeEnum){
            case LOCAL: return new String[]{LocalCatchServiceImpl.class.getName()};
            case REDIS: return new String[]{RedisCatchServiceImpl.class.getName()};
            default: throw new RuntimeException("not fund right catch type");

        }
    }
}
