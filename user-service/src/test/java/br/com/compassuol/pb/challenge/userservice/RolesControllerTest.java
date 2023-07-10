package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.controller.RolesController;
import br.com.compassuol.pb.challenge.userservice.dto.RolesRequest;
import br.com.compassuol.pb.challenge.userservice.dto.RolesResponse;
import br.com.compassuol.pb.challenge.userservice.service.RolesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RolesControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RolesService rolesService;

    private RolesController rolesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rolesController = new RolesController(rolesService);
        mockMvc = MockMvcBuilders.standaloneSetup(rolesController).build();
    }

    @Test
    public void testCreateRole() throws Exception {
        RolesRequest rolesRequest = new RolesRequest();
        rolesRequest.setName("Test Role");

        mockMvc.perform(post("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Role\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Role criada com sucesso!"));

        verify(rolesService).createRole(rolesRequest);
    }

    @Test
    public void testGetAllRoles() throws Exception {
        RolesResponse role = new RolesResponse();
        role.setId("1");
        role.setName("Test Role");
        List<RolesResponse> rolesList = Collections.singletonList(role);

        when(rolesService.getAllRoles()).thenReturn(rolesList);

        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Test Role"));

        verify(rolesService).getAllRoles();
    }

    @Test
    public void testGetRoleById() throws Exception {
        RolesResponse role = new RolesResponse();
        role.setId("1");
        role.setName("Test Role");

        when(rolesService.getRoleById("1")).thenReturn(role);

        mockMvc.perform(get("/roles/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Role"));

        verify(rolesService).getRoleById("1");
    }

    @Test
    public void testUpdateRole() throws Exception {
        RolesRequest rolesRequest = new RolesRequest();
        rolesRequest.setName("Updated Role");

        mockMvc.perform(put("/roles/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Role\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Role atualizada com sucesso!"));

        verify(rolesService).updateRole(eq("1"), any(RolesRequest.class));
    }

    @Test
    public void testDeleteRole() throws Exception {
        mockMvc.perform(delete("/roles/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Role deletada com sucesso!"));

        verify(rolesService).deleteRole("1");
    }
}
