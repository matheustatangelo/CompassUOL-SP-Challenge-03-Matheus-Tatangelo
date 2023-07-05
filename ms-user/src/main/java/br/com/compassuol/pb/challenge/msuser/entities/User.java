package br.com.compassuol.pb.challenge.msuser.entities;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
