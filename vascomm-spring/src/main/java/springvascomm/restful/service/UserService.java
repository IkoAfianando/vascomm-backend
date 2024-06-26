package springvascomm.restful.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springvascomm.restful.entity.User;
import springvascomm.restful.model.RegisterUserRequest;
import springvascomm.restful.model.UpdateUserRequest;
import springvascomm.restful.model.UserResponse;
import springvascomm.restful.repository.UserRepository;
import springvascomm.restful.security.BCrypt;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ValidationService validationService;

  @Transactional
  public void register(RegisterUserRequest request) {
    validationService.validate(request);

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
    }

    User user = new User();

    user.setUserId(java.util.UUID.randomUUID().toString());
    user.setEmail(request.getEmail());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    user.setName(request.getName());
    user.setRole(User.RoleType.valueOf(request.getRole()));

    userRepository.save(user);
  }

  public UserResponse get(User user) {
    return UserResponse.builder()
      .email(user.getEmail())
      .name(user.getName())
      .build();
  }

  @Transactional
  public UserResponse update(User user, UpdateUserRequest request) {
    validationService.validate(request);

    log.info("REQUEST : {}", request);

    if (Objects.nonNull(request.getName())) {
      user.setName(request.getName());
    }

    if (Objects.nonNull(request.getPassword())) {
      user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    }

    userRepository.save(user);

    log.info("USER : {}", user.getName());

    return UserResponse.builder()
      .name(user.getName())
      .email(user.getEmail())
      .build();
  }
}
