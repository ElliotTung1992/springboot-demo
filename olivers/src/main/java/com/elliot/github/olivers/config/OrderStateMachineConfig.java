package com.elliot.github.olivers.config;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.domain.OrderStatusParam;
import com.elliot.github.olivers.enums.OrderEventEnum;
import com.elliot.github.olivers.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Objects;

@Slf4j
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {

    /**
     * 定义状态
     * @param states states
     * @throws Exception exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatusEnum.CREATED)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 定义转换
     * @param transitions transitions
     * @throws Exception exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatusEnum.CREATED)
                .target(OrderStatusEnum.PAID)
                .event(OrderEventEnum.PAY)
                .action(payAction())
                .guard(payGuard())
                .and()
                .withExternal()
                .source(OrderStatusEnum.PAID)
                .target(OrderStatusEnum.SHIPPED)
                .event(OrderEventEnum.SHIP);
    }

    private Guard<OrderStatusEnum, OrderEventEnum> payGuard() {
        return new Guard<OrderStatusEnum, OrderEventEnum>() {
            @Override
            public boolean evaluate(StateContext<OrderStatusEnum, OrderEventEnum> stateContext) {
                log.info("payGuard");
                OrderStatusParam orderStatusParam =
                        (OrderStatusParam) stateContext.getExtendedState().getVariables().get("orderStatusParam");
                Assert.notNull(orderStatusParam, "orderStatusParam is null");
                Order order = orderStatusParam.getOrder();
                Assert.notNull(order, "order is null");
                if(Objects.nonNull(order.getAmount()) && order.getAmount().compareTo(BigDecimal.ZERO) > 0){
                    return true;
                }
                return false;
            }
        };
    }

    private Action<OrderStatusEnum, OrderEventEnum> payAction() {
        return new Created2PaidAction();
    }

    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatusEnum, OrderEventEnum, OrderStatusParam>() {

            @Override
            public void write(StateMachineContext<OrderStatusEnum, OrderEventEnum> stateMachineContext, OrderStatusParam orderStatusParam) throws Exception {
                OrderStatusEnum orderStatusEnum = stateMachineContext.getState();
                log.info("订单状态持久化,订单ID：{},目标状态:{}", orderStatusParam.getOrder().getId(), orderStatusEnum);
            }

            @Override
            public StateMachineContext<OrderStatusEnum, OrderEventEnum> read(OrderStatusParam orderStatusParam) throws Exception {
                log.info("恢复订单状态机状态");
                return new DefaultStateMachineContext<>(OrderStatusEnum.CREATED, null, null, null);
            }
        });
    }

}
