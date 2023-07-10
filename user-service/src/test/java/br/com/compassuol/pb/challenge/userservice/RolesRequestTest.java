package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.RolesRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolesRequestTest {

    @Test
    public void testSetName() {
        RolesRequest request = new RolesRequest();
        request.setName("admin");
        assertEquals("admin", request.getName());
    }
}