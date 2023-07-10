package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.UsersRequest;
import br.com.compassuol.pb.challenge.userservice.dto.UsersResponse;
import br.com.compassuol.pb.challenge.userservice.model.Users;
import br.com.compassuol.pb.challenge.userservice.repository.UsersRepository;
import br.com.compassuol.pb.challenge.userservice.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsersServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceTest.class);

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    public void testCreateUser() {
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setEmail("johndoe@example.com");
        usersRequest.setPassword("password");
        usersRequest.setRoles(new HashSet<>());

        Users users = Users.builder()
                .firstName(usersRequest.getFirstName())
                .lastName(usersRequest.getLastName())
                .email(usersRequest.getEmail())
                .password(usersRequest.getPassword())
                .roles(usersRequest.getRoles())
                .build();

        when(usersRepository.save(users)).thenReturn(users);

        usersService.createUser(usersRequest);

        verify(usersRepository).save(users);
        LOGGER.info("User created successfully!");
    }

    @Test
    public void testGetUserById() {
        Users user = new Users();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UsersResponse found = usersService.getUser(user.getId());

        assertThat(found.getId())
                .isEqualTo(user.getId());
        assertThat(found.getFirstName())
                .isEqualTo(user.getFirstName());
        assertThat(found.getLastName())
                .isEqualTo(user.getLastName());
        assertThat(found.getEmail())
                .isEqualTo(user.getEmail());
        assertThat(found.getPassword())
                .isEqualTo(user.getPassword());
        assertThat(found.getRoles())
                .isEqualTo(user.getRoles());
    }

    @Test
    public void testGetAllUsers() {
        Users user1 = new Users();
        user1.setId("1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("johndoe@example.com");
        user1.setPassword("password");
        user1.setRoles(new HashSet<>());

        Users user2 = new Users();
        user2.setId("2");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("janedoe@example.com");
        user2.setPassword("password");
        user2.setRoles(new HashSet<>());

        HashSet<Users> usersSet = new HashSet<>();
        usersSet.add(user1);
        usersSet.add(user2);

        when(usersRepository.findAll()).thenReturn(new ArrayList<>(usersSet));

        List<UsersResponse> found = usersService.getAllUsers();

        assertThat(found)
                .hasSize(2)
                .extracting(UsersResponse::getId, UsersResponse::getFirstName, UsersResponse::getLastName, UsersResponse::getEmail, UsersResponse::getRoles)
                .contains(tuple(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getRoles()),
                        tuple(user2.getId(), user2.getFirstName(), user2.getLastName(), user2.getEmail(), user2.getRoles()));
    }

    @Test
    public void testUpdateUser() {
        Users user = new Users();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("Jane");
        usersRequest.setLastName("Doe");
        usersRequest.setEmail("janedoe@example.com");
        usersRequest.setPassword("newpassword");
        usersRequest.setRoles(new HashSet<>());

        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));

        usersService.updateUser(user.getId(), usersRequest);

        verify(usersRepository).save(user);
        assertThat(user.getFirstName())
                .isEqualTo(usersRequest.getFirstName());
        assertThat(user.getLastName())
                .isEqualTo(usersRequest.getLastName());
        assertThat(user.getEmail())
                .isEqualTo(usersRequest.getEmail());
        assertThat(user.getPassword())
                .isEqualTo(usersRequest.getPassword());
        assertThat(user.getRoles())
                .isEqualTo(usersRequest.getRoles());
    }

    @Test
    public void testDeleteUser() {
        Users user = new Users();
        user.setId("1");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));

        usersService.deleteUser(user.getId());

        verify(usersRepository).delete(user);
    }
}