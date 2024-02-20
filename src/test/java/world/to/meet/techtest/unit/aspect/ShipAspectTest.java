package world.to.meet.techtest.unit.aspect;

import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.to.meet.techtest.aspect.ShipAspect;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipAspectTest {

    @Mock
    private Logger logger;
    @Mock
    private JoinPoint joinPoint;
    @InjectMocks
    private ShipAspect shipAspect;

    @Test
    public void givenNegativeId_whenLogFindingNegativeId_thenWarningLogged() {

        try (MockedStatic<LoggerFactory> integerMock = mockStatic(LoggerFactory.class)) {
            integerMock.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(logger);

            shipAspect.logFindingNegativeId(joinPoint, -1L);

            verify(logger).warn(any());
        }
    }
}