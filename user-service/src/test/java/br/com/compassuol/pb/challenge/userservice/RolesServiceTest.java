package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.RolesRequest;
import br.com.compassuol.pb.challenge.userservice.dto.RolesResponse;
import br.com.compassuol.pb.challenge.userservice.model.Roles;
import br.com.compassuol.pb.challenge.userservice.repository.RolesRepository;
import br.com.compassuol.pb.challenge.userservice.service.RolesService;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RolesServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolesServiceTest.class);

    @Mock
    private RolesRepository rolesRepository;

    @InjectMocks
    private RolesService rolesService;

    @Test
    public void testGetRoleById() {
        Roles role = new Roles();
        role.setId("1");
        role.setName("admin");

        when(rolesRepository.findById("1")).thenReturn(Optional.of(role));

        RolesResponse expected = new RolesResponse();
        expected.setId("1");
        expected.setName("admin");

        RolesResponse found = rolesService.getRoleById("1");

        assertThat(found).isEqualTo(expected);
    }

    @Test
    public void testGetRoleByIdNotFound() {
        when(rolesRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rolesService.getRoleById("1"));
    }

    @Test
    public void testGetAllRoles() {
        Roles role1 = new Roles();
        role1.setId("1");
        role1.setName("admin");

        Roles role2 = new Roles();
        role2.setId("2");
        role2.setName("user");

        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(role1);
        rolesList.add(role2);

        when(rolesRepository.findAll()).thenReturn(rolesList);

        List<RolesResponse> found = rolesService.getAllRoles();

        List<Tuple> tuples = new ArrayList<>();
        for (RolesResponse role : found) {
            tuples.add(tuple(role.getId(), role.getName()));
        }

        assertThat(tuples)
                .asList()
                .hasSize(2)
                .contains(tuple(role1.getId(), role1.getName()), tuple(role2.getId(), role2.getName()));
    }







    @Test
    public void testUpdateRole() {
        RolesRequest rolesRequest = new RolesRequest();
        rolesRequest.setName("admin");

        Roles role = new Roles();
        role.setId("1");
        role.setName("user");

        when(rolesRepository.findById("1")).thenReturn(Optional.of(role));

        rolesService.updateRole("1", rolesRequest);

        assertThat(role.getName()).isEqualTo(rolesRequest.getName());

        verify(rolesRepository, times(1)).save(role);
    }

    @Test
    public void testUpdateRoleNotFound() {
        RolesRequest rolesRequest = new RolesRequest();
        rolesRequest.setName("admin");

        when(rolesRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rolesService.updateRole("1", rolesRequest));
    }

    @Test
    public void testDeleteRole() {
        Roles role = new Roles();
        role.setId("1");
        role.setName("admin");

        when(rolesRepository.findById("1")).thenReturn(Optional.of(role));

        rolesService.deleteRole("1");

        verify(rolesRepository, times(1)).delete(role);
    }

    @Test
    public void testDeleteRoleNotFound() {
        when(rolesRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rolesService.deleteRole("1"));
    }


}

