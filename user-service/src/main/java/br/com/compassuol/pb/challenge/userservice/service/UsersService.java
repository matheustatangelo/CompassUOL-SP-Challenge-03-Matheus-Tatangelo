package br.com.compassuol.pb.challenge.userservice.service;

import br.com.compassuol.pb.challenge.userservice.dto.UsersRequest;
import br.com.compassuol.pb.challenge.userservice.dto.UsersResponse;
import br.com.compassuol.pb.challenge.userservice.model.Users;
import br.com.compassuol.pb.challenge.userservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    public void createUser(UsersRequest usersRequest) {
        Users users = Users.builder()
                .firstName(usersRequest.getFirstName())
                .lastName(usersRequest.getLastName())
                .email(usersRequest.getEmail())
                .password(usersRequest.getPassword())
                .roles(usersRequest.getRoles())
                .build();


        usersRepository.save(users);
        log.info("Product is save: {}", users.getId());

    }

    public List<UsersResponse> getAllUsers() {
        //paginação
        List<Users> users = usersRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UsersResponse mapToUserResponse(Users users) {
        return UsersResponse.builder()
                .id(users.getId())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRoles())
                .build();
    }

    public UsersResponse getUserById(String id) {
        return usersRepository.findById(id)
                .map(this::mapToUserResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));


    }

    public List<UsersResponse> getAllUsersPagination(int pageNumber, int recordCount, String direction, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber,  recordCount, Sort.Direction.valueOf(direction), orderBy);
        List<Users> users = usersRepository.findAll(pageable).toList();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    public void updateUser(String id, UsersRequest usersRequest) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Users not found"));

        users.setFirstName(usersRequest.getFirstName());
        users.setLastName(usersRequest.getLastName());
        users.setEmail(usersRequest.getEmail());
        users.setPassword(usersRequest.getPassword());
        users.setRoles(usersRequest.getRoles());

        usersRepository.save(users);
        log.info("User is update: {}", users.getId());
    }


    public void deleteUser(String id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        usersRepository.delete(users);
        log.info("User is delete: {}", users.getId());
    }
}
