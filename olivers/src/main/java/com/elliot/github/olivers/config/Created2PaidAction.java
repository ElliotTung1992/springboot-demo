package com.elliot.github.olivers.config;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.domain.OrderStatusParam;
import com.elliot.github.olivers.enums.OrderEventEnum;
import com.elliot.github.olivers.enums.OrderStatusEnum;
import com.elliot.github.olivers.mapper.OrderMapper;
import com.elliot.github.olivers.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
public class Created2PaidAction implements Action<OrderStatusEnum, OrderEventEnum> {

    @Override
    public void execute(StateContext<OrderStatusEnum, OrderEventEnum> stateContext) {
        log.info("payAction");
        OrderStatusParam orderStatusParam =
                (OrderStatusParam) stateContext.getExtendedState().getVariables().get("orderStatusParam");
        OrderService orderService =
                (OrderService) stateContext.getExtendedState().getVariables().get("orderService");
        Assert.notNull(orderStatusParam, "orderStatusParam is null");
        Order order = orderStatusParam.getOrder();
        Assert.notNull(order, "order is null");

        orderService.update();
    }
}
