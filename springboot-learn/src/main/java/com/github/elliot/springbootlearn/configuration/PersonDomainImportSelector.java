package com.github.elliot.springbootlearn.configuration;

import com.github.elliot.springbootlearn.domain.ImportSelectorPersonDomain;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class PersonDomainImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String name = ImportSelectorPersonDomain.class.getName();
        return new String[]{name};
    }
}
