package com.github.dge1992;

import com.github.dge1992.commonforwardweb.CommonForwardWebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CommonForwardWebApplication.class)
public class CommonForwardWebApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("哈哈");
    }
}
