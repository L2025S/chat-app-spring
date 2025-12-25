package se.sprinto.hakan.chatapp.model;
import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name="image_url", length = 200, updatable = true)
    private String imageUrl;

    @Column(length = 500, updatable = true)
    private String description;

    @OneToOne
    @JoinColumn(name ="user_id",nullable = false )
    private User user;

    public Profile() {
    }

    public Profile(String imageUrl, String description, User user) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.user = user;
    }

    public Profile(Long profileId, String imageUrl, String description, User user) {
        this.profileId = profileId;
        this.imageUrl = imageUrl;
        this.description = description;
        this.user = user;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
