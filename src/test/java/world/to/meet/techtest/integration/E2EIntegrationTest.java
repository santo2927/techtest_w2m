package world.to.meet.techtest.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class E2EIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindAllEndpointInvokesControllerMethod() throws Exception {
        // Perform GET request to /api/naves
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindByIdEndpointInvokesServiceFindById() throws Exception {
        Long shipId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves/{id}", shipId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("X-Wing"));
    }

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindByNameEndpointInvokesServiceFindByName() throws Exception {
        String shipName = "wing";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves/findByName").param("nombre", shipName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("X-Wing"));
    }

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreateEndpointInvokesServiceCreate() throws Exception {
        // Perform POST request to /api/naves
        mockMvc.perform(MockMvcRequestBuilders.post("/api/naves")
                        .contentType("application/json")
                        .content("{ \"name\": \"TIE Fighter\", \"model\": \"TIE/LN starfighter\", \"type\": \"Sienar Fleet Systems\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Perform GET request to /api/naves
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("TIE Fighter"));

    }

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateEndpointInvokesServiceUpdate() throws Exception {
        Long shipId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/naves/{id}", shipId)
                        .contentType("application/json")
                        .content("{ \"name\": \"X-Wing\", \"model\": \"T-65B X-wing\", \"type\": \"Incom Corporation 2\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Perform GET request to /api/naves/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves/{id}", shipId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("Incom Corporation 2"));
    }

    @Test
    @Sql(value = {"/create_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete_spacial_ship_test_table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteEndpointInvokesServiceDelete() throws Exception {
        Long shipId = 1L;
        // Perform DELETE request to /api/naves/{id}
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/naves/{id}", shipId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Perform GET request to /api/naves
        mockMvc.perform(MockMvcRequestBuilders.get("/api/naves"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }
}
