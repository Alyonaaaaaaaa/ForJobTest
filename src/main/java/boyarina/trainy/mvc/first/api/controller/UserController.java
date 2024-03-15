package boyarina.trainy.mvc.first.api.controller;

import boyarina.trainy.AbstractController;
import boyarina.trainy.mvc.first.api.dto.ReportResponse;
import boyarina.trainy.mvc.first.api.dto.UserRequest;
import boyarina.trainy.mvc.first.api.dto.UserResponse;
import boyarina.trainy.mvc.first.service.UserService;
import boyarina.trainy.mvc.first.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController extends AbstractController {
    private final UserService userService;

    @JsonView(Views.USERSummary.class)
    @GetMapping("/users")
    private ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @JsonView(Views.USERDetails.class)
    @PostMapping("/users_with_orders")
    private ResponseEntity<ReportResponse> findAllUsersWithOrders(@RequestBody @Validated UserRequest request) {
        return ResponseEntity.ok(userService.findUserWithOrder(request.getId()));
    }
}