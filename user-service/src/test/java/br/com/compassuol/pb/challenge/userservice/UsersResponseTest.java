package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.UsersResponse;
import br.com.compassuol.pb.challenge.userservice.model.Roles;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersResponseTest {

    @Test
    public void testGettersAndSetters() {
        UsersResponse response = new UsersResponse();
        response.setId("1");
        response.setFirstName("John");
        response.setLastName("Doe");
        Roles role = new Roles();
        role.setId("2");
        role.setName("admin");
        response.setRoles(Set.of(role));
        assertEquals("1", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals(1, response.getRoles().size());
        assertEquals(role, response.getRoles().iterator().next());
    }
}