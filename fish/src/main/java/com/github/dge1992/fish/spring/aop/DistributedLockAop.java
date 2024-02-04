package com.github.dge1992.fish.spring.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DistributedLockAop {

    @Autowired
    private BeanFactory beanFactory;

    @Around("@annotation(distributedLock)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, DistributedLock distributedLock){

        System.out.println(proceedingJoinPoint.getTarget().getClass().getName());
        Method method = ((MethodSignature)proceedingJoinPoint.getSignature()).getMethod();
        System.out.println(method.getName());

        String lockKey = distributedLock.lockKey();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));


        if(StringUtils.isNotBlank(lockKey)){
            Object[] args = proceedingJoinPoint.getArgs();
            LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }



        String parseLockKey = parser.parseExpression(lockKey, new TemplateParserContext()).getValue(context, String.class);
        System.out.println(parseLockKey);
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
