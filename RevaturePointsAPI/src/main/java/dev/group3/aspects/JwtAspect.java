package dev.group3.aspects;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.group3.entities.Employee;
import dev.group3.utils.JwtUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
public class JwtAspect {

    // Just checks to see if jwt is valid, if yes then you are an employee
    @Around("authAssociateJP()")
    public Object authorizeAssociate(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Logger logger = Logger.getLogger(pjp.getSignature().toString());
        String jwt = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JwtUtil.verifyToken(jwt);

        if(decodedJWT == null){
            logger.error("Illegal attempt to access endpoint made");
            logger.error("Method illegally called: " + pjp.getSignature());
            response.sendError(403);
            return null;
        }else{
            logger.info("JWT Authorized");
            return pjp.proceed();
        }
    }

    // Checks to see if jwt is valid AND checks if you are a trainer
    @Around("authTrainerJP()")
    public Object authorizeTrainer(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Logger logger = Logger.getLogger(pjp.getSignature().toString());
        String jwt = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JwtUtil.verifyToken(jwt);
        if(decodedJWT == null){
            logger.error("Illegal attempt to access endpoint made");
            logger.error("Method illegally called: " + pjp.getSignature());
            response.sendError(403);
            return null;
        }else if(!decodedJWT.getClaim("role").asString().equals("trainer")){
            logger.error("Improper permissions to access that endpoint");
            logger.error("Attempt made by employee " + decodedJWT.getClaim("firstName") + " " +decodedJWT.getClaim("lastName") + ", EmployeeID: "+decodedJWT.getClaim("employeeId"));
            logger.error("Method they attempted to access: " + pjp.getSignature());
            response.sendError(403);
            return null;
        }else{
            return pjp.proceed();
        }
    }

    @Pointcut("@annotation(dev.group3.aspects.AuthorizedAssociate)")
    private void authAssociateJP(){}

    @Pointcut("@annotation(dev.group3.aspects.AuthorizedTrainer)")
    private void authTrainerJP(){}

}
