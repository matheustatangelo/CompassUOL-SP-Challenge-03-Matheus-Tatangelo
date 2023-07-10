package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.model.Roles;
import br.com.compassuol.pb.challenge.userservice.model.Users;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {

    @Test
    public void testGettersAndSetters() {
        Users user = new Users();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        Roles role = new Roles();
        role.setId("1");
        role.setName("admin");
        user.setRoles(Set.of(role));
        assertEquals("1", user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(1, user.getRoles().size());
        assertEquals(role, user.getRoles().iterator().next());
    }
}