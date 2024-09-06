package com.tropical.services;

import com.tropical.data.dto.ClientDto;
import com.tropical.data.dto.CreateUserDto;
import com.tropical.data.dto.EnterpriseDto;
import com.tropical.data.dto.UserDto;
import com.tropical.exceptions.UserAlreadyExistsException;
import com.tropical.exceptions.UserAlreadyUsedException;
import com.tropical.model.Client;
import com.tropical.model.Enterprise;
import com.tropical.model.Role;
import com.tropical.model.User;
import com.tropical.producers.UserProducer;
import com.tropical.repository.ClientRepository;
import com.tropical.repository.EnterpriseRepository;
import com.tropical.repository.RoleRepository;
import com.tropical.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  final UserProducer userProducer;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EnterpriseRepository enterpriseRepository;
    private final ClientRepository clientRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserProducer userProducer,
                       BCryptPasswordEncoder bCryptPasswordEncoder, EnterpriseRepository enterpriseRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userProducer = userProducer;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.enterpriseRepository = enterpriseRepository;
        this.clientRepository = clientRepository;
    }
    @Transactional
    public ResponseEntity<Void> newUser(@RequestBody @Valid CreateUserDto createUserDto) {
        var role = roleRepository.findByName(Role.Values.BASIC.name());

        var userFromdb = userRepository.findByEmail(createUserDto.email());
        if (userFromdb.isPresent())
            throw new UserAlreadyExistsException();
        var user = new User();
        user.setEmail(createUserDto.email());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
@Transactional
    public ResponseEntity<Void> createNewAdmin(@RequestBody CreateUserDto createUserDto) {
        var adminRole = roleRepository.findByName(Role.Values.ADMIN.name());
        var userFromdb = userRepository.findByEmail(createUserDto.email());
        if (userFromdb.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        var user = new User();
        user.setEmail(createUserDto.email());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDto.password()));
        user.setRoles(Set.of(adminRole));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
@Transactional
    public ResponseEntity<Void> createNewEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
    System.out.println(enterpriseDto);

        var companyRole = roleRepository.findByName(Role.Values.EMPRESA.name());
        var userDB = userRepository.findByEmail(enterpriseDto.getUser().getEmail());
        if (userDB.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        var user = new User();
        user.setEmail(enterpriseDto.getUser().getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(enterpriseDto.getUser().getPassword()));
        user.setRoles(Set.of(companyRole));
        userRepository.save(user);

        Enterprise enterprise = new Enterprise();

        if (enterpriseRepository.findByUser(user).isEmpty()) {
            enterprise.setUser(user);
            enterprise.setName(enterpriseDto.getName());
            enterprise.setCnpj(enterpriseDto.getCnpj());
            enterprise.setAddress(enterpriseDto.getAddress());
            enterpriseRepository.save(enterprise);
        } else {
            throw new UserAlreadyUsedException();
        }
        return ResponseEntity.ok().build();
    }
    @Transactional
    public ResponseEntity<Void> newClient(@RequestBody ClientDto clientDto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userDB = userRepository.findByEmail(clientDto.getUser().getEmail());
        if (userDB.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        var user = new User();
        user.setEmail(clientDto.getUser().getEmail());
        user.getPassword();
        System.out.println("password" + clientDto.getUser().getPassword().toString());
        user.setPassword(bCryptPasswordEncoder.encode(clientDto.getUser().getPassword()));
        user.setRoles(Set.of(basicRole));
        userRepository.save(user);
        userProducer.publishMessageEmail(user);

        Client client = new Client();

        if (clientRepository.findByUser(user).isEmpty()) {
            client.setUser(user);
            client.setName(clientDto.getName());
            client.setBirthday(clientDto.getBirthday());
            client.setPhone(clientDto.getPhone());
            client.setZipCode(clientDto.getZipCode());
            clientRepository.save(client);
        } else {
            throw new UserAlreadyUsedException();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<UserDto>> listUsers() {
        var users = userRepository.findAll().stream()
            .map(UserDto::new).toList();
        return ResponseEntity.ok(users);
    }
}
