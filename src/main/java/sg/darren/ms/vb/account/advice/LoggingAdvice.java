package sg.darren.ms.vb.account.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAdvice {

    @Pointcut(value = "execution(* sg.darren.ms.vb.account.controller.*.*(..))")
    public void configurePointCut() {
        // Not required to implement
    }

    @Around("configurePointCut()")
    public Object configurePointCut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        // request
        log.info("ENTER > {}.{}()", className, method);
        if (args != null) {
            for (Object obj : args) {
                log.info("      > argument {}", obj.toString());

            }
        }
        // proceed
        Object object = proceedingJoinPoint.proceed();
        // response
        log.info("EXIT  < {}.{}()", className, method);
        if (args != null) {
            for (Object obj : args) {
                log.info("      < argument {}", obj.toString());

            }
        }
        return object;
    }

}
