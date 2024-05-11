package springvascomm.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springvascomm.restful.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAccessToken(String accessToken);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByRefreshToken(String refreshToken);
}
