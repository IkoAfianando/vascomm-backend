package springvascomm.restful.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_users")
public class User {

  @Id
  @Column(name = "user_id")
  private String userId;

  private String email;

  private String password;

  private String name;

  @Column(name = "access_token")
  private String accessToken;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private RoleType role = RoleType.USER;


  public enum RoleType {
    ADMIN,
    USER
  }



  @Column(name = "token_expired_at")
  private Long tokenExpiredAt;

}
