package com.github.elliot.springbootlearn.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(PersonDomainImportSelector.class)
@Configuration
public class PersonDomainImportSelectorConfiguration {


}
