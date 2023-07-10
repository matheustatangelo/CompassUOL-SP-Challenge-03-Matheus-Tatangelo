package br.com.compassuol.pb.challenge.userservice.repository;

import br.com.compassuol.pb.challenge.userservice.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, String> {

}
