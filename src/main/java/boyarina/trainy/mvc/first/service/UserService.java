package boyarina.trainy.mvc.first.service;

import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.first.api.dto.ReportResponse;
import boyarina.trainy.mvc.first.api.dto.UserResponse;
import boyarina.trainy.mvc.first.entity.User;
import boyarina.trainy.mvc.first.model.ReportModel;
import boyarina.trainy.mvc.first.repository.OrderRepository;
import boyarina.trainy.mvc.first.repository.UserRepository;
import boyarina.trainy.mvc.first.service.converter.ReportModelToResponseConverter;
import boyarina.trainy.mvc.first.service.converter.UserToResponseConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserToResponseConverter userConverter;
    private final ReportModelToResponseConverter reportConverter;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserResponse create(String name, String email) {
        return userConverter.convert(userRepository
                .save(new User()
                        .setName(name)
                        .setEmail(email)));
    }

    public void updateUserName(UUID id, String newName) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
        userRepository.save(user.setName(newName));
    }

    public void delete(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
        userRepository.delete(user);
    }
    public List<UserResponse> findAllUser() {
        return userRepository.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
    }

    public ReportResponse findUserWithOrder(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
        return reportConverter.convert(new ReportModel()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setOrders(orderRepository.findAllByUser_Id(id)));
    }
}