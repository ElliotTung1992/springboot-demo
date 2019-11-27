package com.github.dge1992.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RocketmqApplicationTests {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Test
	void contextLoads() {
	}

}
