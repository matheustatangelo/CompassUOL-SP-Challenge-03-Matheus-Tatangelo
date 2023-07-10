package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.RolesResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolesResponseTest {

    @Test
    public void testGettersAndSetters() {
        RolesResponse response = new RolesResponse();
        response.setId("1");
        response.setName("admin");
        assertEquals("1", response.getId());
        assertEquals("admin", response.getName());
    }
}