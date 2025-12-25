package se.sprinto.hakan.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sprinto.hakan.chatapp.model.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByTagName(String tagName);
    Tag save(String tagName);
    Optional<Tag> findByTagName(String tagName);
    void deleteByTagName(String tagName);

}
