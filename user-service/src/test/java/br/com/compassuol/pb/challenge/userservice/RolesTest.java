package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.model.Roles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolesTest {

    @Test
    public void testGettersAndSetters() {
        Roles role = new Roles();
        role.setId("1");
        role.setName("admin");
        assertEquals("1", role.getId());
        assertEquals("admin", role.getName());
    }
}