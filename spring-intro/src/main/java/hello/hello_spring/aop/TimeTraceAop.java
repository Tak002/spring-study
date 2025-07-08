package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.getSignature().getName());
        try{
            return joinPoint.proceed();
        }finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("END: "+ joinPoint.getSignature().getName() + " duration: "+ duration + "ms");

        }
    }
}
