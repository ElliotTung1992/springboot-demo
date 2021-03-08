package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.FishApplicationTests;
import com.github.dge1992.fish.async.FlowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-08 13:55
 */
class FlowServiceImplTest extends FishApplicationTests {

    @Autowired
    private FlowService flowService;

    @Test
    void execute() {
        flowService.execute();
    }
}