package se.sprinto.hakan.chatapp.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String text;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timestamp;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="messages_tags",
            joinColumns=@JoinColumn(name = "message_id"),
            inverseJoinColumns=@JoinColumn(name ="tag_id")
    )
    private Set<Tag> tags = new HashSet<>();


    public Message(User user, String text, LocalDateTime timestamp) {
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                ", tags=" + tags +
                '}';
    }
}
