package world.to.meet.techtest.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllEndpointInvokesControllerMethod() throws Exception {
        Long shipId = -1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ships/{id}", shipId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
