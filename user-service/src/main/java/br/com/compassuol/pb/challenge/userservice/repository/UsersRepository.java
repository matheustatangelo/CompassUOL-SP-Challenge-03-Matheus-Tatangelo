package br.com.compassuol.pb.challenge.userservice.repository;

import br.com.compassuol.pb.challenge.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

}
