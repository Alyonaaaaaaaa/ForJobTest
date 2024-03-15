package boyarina.trainy.mvc.controller;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.first.api.controller.UserController;
import boyarina.trainy.mvc.first.api.dto.ReportResponse;
import boyarina.trainy.mvc.first.api.dto.UserRequest;
import boyarina.trainy.mvc.first.api.dto.UserResponse;
import boyarina.trainy.mvc.first.entity.Order;
import boyarina.trainy.mvc.first.entity.User;
import boyarina.trainy.mvc.first.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class PersonControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAllUsers() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        List<UserResponse> users = List.of(
                new UserResponse(id1, "Alyona", "alyona@mail.ru"),
                new UserResponse(id2, "Petya", "petya@mail.ru")
        );
        when(userService.findAllUser()).thenReturn(users);
        mockMvc.perform(get("/api/user/users")).andExpect(status().isOk());
        verify(userService, times(1)).findAllUser();
    }

    @Test
    public void notFindUsers() throws Exception {
        when(userService.findAllUser()).thenThrow(new NotFoundException("User with this id not found"));
        mockMvc.perform(get("/api/user/users")).andExpect(status().isNotFound());
        verify(userService, times(1)).findAllUser();
    }

    @Test
    public void findAllUsersWithOrders() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        User user = new User().setId(id1).setName("Alyona").setEmail("alyona@mail.ru");
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id1);

        List<Order> orders = List.of(
                new Order()
                        .setId(id1)
                        .setProduct("Iphone")
                        .setPrice(new BigDecimal(1000))
                        .setStatus("complete")
                        .setUser(user),
                new Order()
                        .setId(id2)
                        .setProduct("Android")
                        .setPrice(new BigDecimal(1000))
                        .setStatus("complete")
                        .setUser(user)
        );

        ReportResponse report = new ReportResponse("ALyona", "alyona@mail.ru", orders);

        when(userService.findUserWithOrder(id1)).thenReturn(report);
        mockMvc.perform(post("/api/user/users_with_orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk());
        verify(userService, times(1)).findUserWithOrder(id1);
    }

    @Test
    public void notFindUsersWithOrders() throws Exception {
        UUID id = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);

        when(userService.findUserWithOrder(id)).thenThrow(new NotFoundException("User with this id not found"));
        mockMvc.perform(post("/api/user/users_with_orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).findUserWithOrder(id);
    }
}