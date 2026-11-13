package tn.esprit.autoloc.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* tn.esprit.autoloc.Services..*(..))")
    public Object surveillerExecution(
            ProceedingJoinPoint joinPoint) throws Throwable {
        String methode =
                joinPoint.getSignature().toShortString();
        long debut = System.currentTimeMillis();
        log.info("Début d'exécution : {}", methode);
        try {
            Object resultat = joinPoint.proceed();
            long duree = System.currentTimeMillis() - debut;
            log.info("Fin d'exécution : {}", methode);
            log.info("Durée : {} ms", duree);
            return resultat;
        } catch (Throwable ex) {
            log.error("Erreur dans {} : {}", methode,
                    ex.getMessage());
            throw ex;
        }
    }
}