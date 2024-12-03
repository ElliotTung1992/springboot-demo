package com.github.dge1992.restfuldemo.service.impl;

import com.github.dge1992.restfuldemo.service.StatisticiansPageService;
import com.github.dge1992.restfuldemo.utils.AuthenticationUtil;

public class StatisticiansPageServiceImpl implements StatisticiansPageService {


    @Override
    public void add() {
        AuthenticationUtil authenticationUtil = new AuthenticationUtil() {
            @Override
            protected void pass() {
                System.out.println("StatisticiansPageService Add");
            }
        };
    }
}
