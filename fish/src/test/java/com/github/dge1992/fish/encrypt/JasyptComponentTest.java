package com.github.dge1992.fish.encrypt;

import com.github.dge1992.fish.FishApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class JasyptComponentTest extends FishApplicationTests {

    @Autowired
    private JasyptComponent jasyptComponent;

    @Test
    void testEncrypt() {
        jasyptComponent.testEncrypt("哈哈");
    }

    @Test
    void testDecrypt() {
        jasyptComponent.testDecrypt();
    }
}