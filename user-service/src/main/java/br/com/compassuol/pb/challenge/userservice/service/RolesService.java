package br.com.compassuol.pb.challenge.userservice.service;

import br.com.compassuol.pb.challenge.userservice.dto.RolesRequest;
import br.com.compassuol.pb.challenge.userservice.dto.RolesResponse;
import br.com.compassuol.pb.challenge.userservice.model.Roles;
import br.com.compassuol.pb.challenge.userservice.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public void createRole(RolesRequest rolesRequest) {
        Roles roles = Roles.builder()
                .name(rolesRequest.getName())
                .build();

        rolesRepository.save(roles);
        log.info("Role criada com sucesso!");
    }



    public List<RolesResponse> getAllRoles() {
        List<Roles> roles = rolesRepository.findAll();
        return roles.stream().map(this::mapToRoleResponse).toList();

    }

    private RolesResponse mapToRoleResponse(Roles roles) {
        return RolesResponse.builder()
                .id(roles.getId())
                .name(roles.getName())
                .build();
    }

    public void updateRole(String id, RolesRequest rolesRequest) {
        Roles roles = rolesRepository.findById(id)
                //.orElseThrow(() -> new RuntimeException("Role not found"));
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        roles.setName(rolesRequest.getName());
            rolesRepository.save(roles);
            log.info("Role atualizada com sucesso!");
    }

    public RolesResponse getRoleById(String id) {
        return rolesRepository.findById(id)
                .map(this::mapToRoleResponse)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        //.orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public void deleteRole(String id) {
      Roles roles =  rolesRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Role not found"));
              //.orElseThrow(() -> new RuntimeException("Role not found"));

        rolesRepository.delete(roles);
        log.info("Role is delete: {}", roles.getId());
    }

}
