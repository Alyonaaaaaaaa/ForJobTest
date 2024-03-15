package boyarina.trainy.security.service.converter;

import boyarina.trainy.security.api.dto.RegistrationPersonResponse;
import boyarina.trainy.security.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToDtoConverter implements Converter<Person, RegistrationPersonResponse> {
    @Override
    public RegistrationPersonResponse convert(Person person) {
        return new RegistrationPersonResponse()
                .setUsername(person.getUsername())
                .setEmail(person.getEmail())
                .setPassword(person.getPassword())
                .setConfirmPassword(person.getConfirmPassword());
    }
}