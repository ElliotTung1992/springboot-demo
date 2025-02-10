package com.github.elliot.springbootlearn.configuration;

import com.github.elliot.springbootlearn.domain.ImportPersonDomain;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(PersonDomainBeanDefinitionRegistrar.class)
//@Import(ImportPersonDomain.class)
@Configuration
public class DomainConfiguration {


}
