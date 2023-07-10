package br.com.compassuol.pb.challenge.userservice;

import br.com.compassuol.pb.challenge.userservice.dto.UsersResponse;
import br.com.compassuol.pb.challenge.userservice.model.Users;
import br.com.compassuol.pb.challenge.userservice.repository.UsersRepository;
import br.com.compassuol.pb.challenge.userservice.service.UsersService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testGetAllUsers() throws Exception {
        List<UsersResponse> users = Arrays.asList(
                new UsersResponse("1", "John", "Smith", "john@example.com", "123456", new HashSet<>()),
                new UsersResponse("2", "John", "Titor", "titor@example.com", "123456", new HashSet<>())
        );
        Mockito.when(usersService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Titor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("titor@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("123456"));
    }

    @Test
    public void testGetUserById() throws Exception {
        UsersResponse user = new UsersResponse("1", "John", "Smith", "john@example.com", "123456", new HashSet<>());
        Mockito.when(usersService.getUser("1")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123456"));
    }


    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCreateUser() throws Exception {
        String requestBody = "{\"firstName\":\"John\",\"lastName\":\"Smith\",\"email\":\"john@example.com\",\"password\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Usuário criado com sucesso!"));

        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail("john@example.com");
        user.setPassword("123456");

        List<Users> users = new ArrayList<>();
        users.add(user);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals("John", users.get(0).getFirstName());
        Assert.assertEquals("Smith", users.get(0).getLastName());
        Assert.assertEquals("john@example.com", users.get(0).getEmail());
        Assert.assertEquals("123456", users.get(0).getPassword());
    }




}