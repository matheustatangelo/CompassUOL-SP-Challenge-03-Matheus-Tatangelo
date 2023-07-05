package br.com.compassuol.pb.challenge.msuser.repository;

import br.com.compassuol.pb.challenge.msuser.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
