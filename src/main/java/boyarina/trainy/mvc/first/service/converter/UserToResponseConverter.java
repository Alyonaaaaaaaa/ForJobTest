package boyarina.trainy.mvc.first.service.converter;

import boyarina.trainy.mvc.first.api.dto.UserResponse;
import boyarina.trainy.mvc.first.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseConverter implements Converter<User, UserResponse> {
    @Override
    public UserResponse convert(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}