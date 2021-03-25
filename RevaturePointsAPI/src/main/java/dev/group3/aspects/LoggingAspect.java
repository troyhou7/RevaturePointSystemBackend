package dev.group3.aspects;

import dev.group3.entities.Employee;
import net.bytebuddy.asm.Advice;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("logJP()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        Logger logger = Logger.getLogger(pjp.getSignature().toString());
        logger.info(pjp.getSignature() + " attempted");
        Object[] args = pjp.getArgs();

        if(args.length > 0){
            logger.info("with args: ");
            for(Object arg : args){
                logger.info(arg);
            }
            logger.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }
        Object obj = pjp.proceed();
        if(obj == null){
            logger.error("Execution Failed.");
            logger.error(pjp.getSignature().toString() + " returned null");
            logger.error("Invalid argument(s)");
        }else if(obj.equals(false)){
            logger.error("Execution Failed.");
            logger.error(pjp.getSignature().toString() + " returned false");
            logger.error("Unable to delete");
        }else if(obj instanceof String) {
            logger.error(obj);
        }else{
            logger.info("Success.");
            logger.info(pjp.getSignature().toString() + " executed successfully");
            logger.info("Return: " + obj);
        }
        logger.info("**********************************************************");
        return obj;
    }


    @Pointcut("@annotation(dev.group3.aspects.Logged)")
    private void logJP(){}
}
