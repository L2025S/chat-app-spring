package se.sprinto.hakan.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sprinto.hakan.chatapp.model.Profile;
import se.sprinto.hakan.chatapp.model.User;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<se.sprinto.hakan.chatapp.model.Profile, Long> {
    boolean existsById(Long id);
    Profile save(Profile profile);
    Optional<Profile> findById(Long profileId);
    Optional<Profile> findByUser(User user);

}
