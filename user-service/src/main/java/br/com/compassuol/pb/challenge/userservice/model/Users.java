package br.com.compassuol.pb.challenge.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @ManyToMany
    private Set<Roles> roles;

}
