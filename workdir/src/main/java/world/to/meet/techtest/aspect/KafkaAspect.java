package world.to.meet.techtest.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import world.to.meet.techtest.model.SpacialShip;

@Aspect
@Component
@RequiredArgsConstructor
public class KafkaAspect {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @AfterReturning(pointcut = "execution(* world.to.meet.techtest.service.SpacialShipService.create(..))", returning = "result")
    public void sendCreateToKafka(SpacialShip result) {
        kafkaTemplate.send("topicName", "Created: " + result.toString());
    }

    @AfterReturning(pointcut = "execution(* world.to.meet.techtest.service.SpacialShipService.update(..))", returning = "result")
    public void sendUpdateToKafka(SpacialShip result) {
        kafkaTemplate.send("topicName", "Updated: " + result.toString());
    }

    @AfterReturning(pointcut = "execution(* world.to.meet.techtest.service.SpacialShipService.delete(..)) && args(id)")
    public void sendDeleteToKafka(Long id) {
        kafkaTemplate.send("topicName", "Deleted: " + id);
    }
}
