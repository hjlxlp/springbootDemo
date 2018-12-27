package com.example.demo.study.springBootApplication;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author hjl
 * @date 2018/12/27 15:00
 */
public class OnUnixCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        return osName.contains("unix") || osName.contains("linux") || osName.contains("Mac OS X");
    }

}
