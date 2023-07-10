package br.com.compassuol.pb.challenge.userservice.controller;

import br.com.compassuol.pb.challenge.userservice.dto.UsersRequest;
import br.com.compassuol.pb.challenge.userservice.dto.UsersResponse;
import br.com.compassuol.pb.challenge.userservice.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import br.com.compassuol.pb.challenge.userservice.model.Users;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {



    private final UsersService usersService;

    // METHOD: POST PATH: /users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UsersRequest usersRequest){
        usersService.createUser(usersRequest);
        return "Usuário criado com sucesso!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAllUsers(){
        return usersService.getAllUsers();
    }



    // METHOD: GET PATH: /users
    @GetMapping("/{pageNumber}/{recordCount}/{direction}/{orderBy}")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> getAllUsersPagination(@PathVariable int pageNumber, @PathVariable int recordCount, @PathVariable String direction, @PathVariable String orderBy){
        return usersService.getAllUsersPagination(pageNumber, recordCount, direction, orderBy);
    }


    // METHOD: GET PATH: /users/{id}


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsersResponse getUserById(@PathVariable String id){
        return usersService.getUserById(id);
    }

    // METHOD: PUT PATH: /users/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable String id, @RequestBody UsersRequest usersRequest){
       usersService.updateUser(id, usersRequest);
         return "Usuário atualizado com sucesso!";
    }


    // METHOD: DELETE PATH: /users/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable String id){
        usersService.deleteUser(id);
        return "Usuário deletedo com sucesso!";
    }


}

