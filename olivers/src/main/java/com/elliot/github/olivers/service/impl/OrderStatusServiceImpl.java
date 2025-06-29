package com.elliot.github.olivers.service.impl;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.domain.OrderStatusParam;
import com.elliot.github.olivers.enums.OrderEventEnum;
import com.elliot.github.olivers.enums.OrderStatusEnum;
import com.elliot.github.olivers.mapper.OrderMapper;
import com.elliot.github.olivers.service.OrderService;
import com.elliot.github.olivers.service.OrderStatusService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Resource(name = "orderStateMachine")
    private StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine;

    @Resource
    private OrderService orderService;

    @Autowired
    private DefaultStateMachinePersister persister;

    @Override
    public boolean sendEvent(Order order, OrderEventEnum eventEnum) {
        OrderStatusParam param = new OrderStatusParam();
        param.setOrder(order);
        Message message = MessageBuilder.withPayload(eventEnum).build();
        return sendEvent(message, param);
    }

    private boolean sendEvent(Message<OrderEventEnum> message, OrderStatusParam param){
        boolean result = false;
        try {
            stateMachine.start();
            persister.restore(stateMachine, param);
            stateMachine.getExtendedState().getVariables().put("orderStatusParam", param);
            stateMachine.getExtendedState().getVariables().put("orderService", orderService);
            result = stateMachine.sendEvent(message);
            persister.persist(stateMachine, param);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stateMachine.stop();
        }
        return result;
    }
}
