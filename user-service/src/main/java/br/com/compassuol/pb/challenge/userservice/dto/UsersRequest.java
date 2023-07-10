package br.com.compassuol.pb.challenge.userservice.dto;

import br.com.compassuol.pb.challenge.userservice.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<Roles> roles;
}
