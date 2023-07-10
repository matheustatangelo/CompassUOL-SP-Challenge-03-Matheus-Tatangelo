package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.UsersRequest;
import br.com.compassuol.pb.challenge.userservice.model.Roles;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersRequestTest {

    @Test
    public void testGettersAndSetters() {
        UsersRequest request = new UsersRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password");
        Roles role = new Roles();
        role.setId("1");
        role.setName("admin");
        request.setRoles(Set.of(role));
        assertEquals("John", request.getFirstName());
        assertEquals("Doe", request.getLastName());
        assertEquals("johndoe@example.com", request.getEmail());
        assertEquals("password", request.getPassword());
        assertEquals(1, request.getRoles().size());
        assertEquals(role, request.getRoles().iterator().next());
    }
}