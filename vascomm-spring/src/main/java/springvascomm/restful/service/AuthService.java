package springvascomm.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import springvascomm.restful.entity.User;
import springvascomm.restful.model.AccessTokenRequest;
import springvascomm.restful.model.AccessTokenResponse;
import springvascomm.restful.model.LoginUserRequest;
import springvascomm.restful.model.TokenResponse;
import springvascomm.restful.repository.UserRepository;
import springvascomm.restful.security.BCrypt;
import springvascomm.restful.util.JwtUtil;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            String accessToken = JwtUtil.accessToken(request.getEmail());
            String refreshToken = JwtUtil.refreshToken(request.getEmail());
            user.setAccessToken(accessToken);
            user.setRefreshToken(refreshToken);
            user.setTokenExpiredAt(next1Hour());
            userRepository.save(user);

            return TokenResponse.builder()
                    .accessToken(user.getAccessToken())
                    .refreshToken(user.getRefreshToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    @Transactional
    public AccessTokenResponse accessToken(AccessTokenRequest request) {
        validationService.validate(request);

        User user = userRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token not found"));

        if (JwtUtil.validateToken(request.getRefreshToken())) {
            String accessToken = JwtUtil.accessToken(user.getEmail());
            user.setAccessToken(accessToken);
            user.setTokenExpiredAt(next1Hour());
            userRepository.save(user);

            return AccessTokenResponse.builder()
                    .accessToken(user.getAccessToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    private Long next1Hour() {
        return System.currentTimeMillis() + (1000 * 60 * 60);
    }

    @Transactional
    public void logout(User user) {
        user.setAccessToken(null);
        user.setRefreshToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
