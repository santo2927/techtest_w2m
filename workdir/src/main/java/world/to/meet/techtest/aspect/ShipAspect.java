package world.to.meet.techtest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ShipAspect {

    @Before("execution(* world.to.meet.techtest.service.SpacialShipService.findById(..)) && args(id)")
    public void logFindingNegativeId(JoinPoint joinPoint, Long id) {
        Logger logger = LoggerFactory.getLogger(ShipAspect.class);
        if (id < 0) {
            logger.warn("Finding a ship with a negative id: " + id);
        }
    }
}