package com.github.dge1992.fish.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DistributedLockAop {

    @Autowired
    private BeanFactoryResolver beanFactoryResolver;

    @Bean
    public BeanFactoryResolver beanFactoryResolver(BeanFactory beanFactory) {
        return new BeanFactoryResolver(beanFactory);
    }

    @Around("@annotation(distributedLock)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock){
        String lockKey = distributedLock.lockKey();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(beanFactoryResolver);
        String parseLockKey = parser.parseExpression(lockKey).getValue(context, String.class);
        System.out.println(parseLockKey);
        return null;
    }
}
