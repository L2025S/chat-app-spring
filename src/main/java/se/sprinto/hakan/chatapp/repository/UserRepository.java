package se.sprinto.hakan.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.sprinto.hakan.chatapp.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.messages WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
    Optional<User>findByUsername(String username);

}

