package br.com.compassuol.pb.challenge.userservice.controller;

import br.com.compassuol.pb.challenge.userservice.dto.RolesRequest;
import br.com.compassuol.pb.challenge.userservice.dto.RolesResponse;
import br.com.compassuol.pb.challenge.userservice.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    // METHOD: POST PATH: /roles
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRole(@RequestBody RolesRequest rolesRequest){
        rolesService.createRole(rolesRequest);
        return "Role criada com sucesso!";
    }
    // METHOD: GET PATH: /roles
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RolesResponse> getAllRoles(){
        return rolesService.getAllRoles();
    }
    // METHOD: GET PATH: /roles/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RolesResponse getRoleById(@PathVariable String id){
        return rolesService.getRoleById(id);
    }
    // METHOD: PUT PATH: /roles/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateRole(@PathVariable String id, @RequestBody RolesRequest rolesRequest){
       rolesService.updateRole(id, rolesRequest);
         return "Role atualizada com sucesso!";
    }
    // METHOD: DELETE PATH: /roles/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteRole(@PathVariable String id){
        rolesService.deleteRole(id);
        return "Role deletada com sucesso!";
    }



}
