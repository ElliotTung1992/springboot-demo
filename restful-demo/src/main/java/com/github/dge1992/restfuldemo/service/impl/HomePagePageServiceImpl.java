package com.github.dge1992.restfuldemo.service.impl;

import com.github.dge1992.restfuldemo.service.HomePageService;
import com.github.dge1992.restfuldemo.utils.AuthenticationUtil;
import org.springframework.stereotype.Service;

@Service
public class HomePagePageServiceImpl implements HomePageService {

    @Override
    public void add() {
        AuthenticationUtil authenticationUtil = new AuthenticationUtil() {
            @Override
            protected void pass() {
                System.out.println("HomePageService Add");
            }
        };
    }
}
