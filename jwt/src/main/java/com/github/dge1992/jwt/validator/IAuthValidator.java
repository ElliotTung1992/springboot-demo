package com.github.dge1992.jwt.validator;

import com.github.dge1992.jwt.doamin.AuthObject;

public interface IAuthValidator {

    boolean validate(AuthObject authObject);
}
