package com.github.elliot.springbootlearn.controller;

import com.github.elliot.springbootlearn.domain.ImportPersonDomain;
import com.github.elliot.springbootlearn.domain.ImportSelectorPersonDomain;
import com.github.elliot.springbootlearn.domain.PersonDomainMapperBean;
import com.github.elliot.springbootlearn.service.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ImportPersonDomain importPersonDomain;

    @Autowired
    private ImportSelectorPersonDomain importSelectorPersonDomain;

    @Autowired
    private CatchService catchService;

    @Autowired
    private PersonDomainMapperBean personDomainMapperBean;

    @GetMapping("/testImport")
    public void testImport(){
        System.out.println(importPersonDomain.getClass().getName());
        System.out.println(importPersonDomain.getName());
    }

    @GetMapping("/testImportSelector")
    public void testImportSelector(){
        System.out.println(importSelectorPersonDomain.getClass().getName());
    }

    @GetMapping("/testImportSelectorTwo")
    public void testImportSelectorTwo(){
        catchService.setDate();
    }

    @GetMapping("/testImportSelectorThree")
    public void testImportSelectorThree(){
        System.out.println(personDomainMapperBean.getClass().getName());
        System.out.println(personDomainMapperBean.getName());
    }
}
