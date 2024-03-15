package boyarina.trainy.security.service;

import boyarina.trainy.security.api.dto.RegistrationPersonResponse;
import boyarina.trainy.security.entity.Person;
import boyarina.trainy.security.repository.PersonRepository;
import boyarina.trainy.security.repository.RoleRepository;
import boyarina.trainy.security.service.converter.PersonToDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PersonToDtoConverter converter;

    public RegistrationPersonResponse createNewPerson(String username,
                                                      String email,
                                                      String password,
                                                      String confirmPassword,
                                                      PasswordEncoder passwordEncoder) {
        Person person = new Person()
                .setUsername(username)
                .setEmail(email)
                .setPassword(passwordEncoder.encode(password))
                .setRoles(List.of(roleRepository.findByName("USER")));
        if (!person.getPassword().equals(confirmPassword)) {
            throw new BadCredentialsException("Password not equals");
        }
        if (personRepository.findByUsername(person.getUsername()) != null) {
            throw new BadCredentialsException("Person already exist");
        }
        return converter.convert(personRepository.save(person));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUserName(username);
        return new User(
                person.getUsername(),
                person.getPassword(),
                person.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }

    public Person findByUserName(String username) {
        try {
            return personRepository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Person with this username not found");
        }
    }
}