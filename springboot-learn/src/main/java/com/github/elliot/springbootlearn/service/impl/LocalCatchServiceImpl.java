package com.github.elliot.springbootlearn.service.impl;

import com.github.elliot.springbootlearn.service.CatchService;

public class LocalCatchServiceImpl implements CatchService {

    @Override
    public void setDate() {
        System.out.println("LocalCatchServiceImpl");
    }
}
