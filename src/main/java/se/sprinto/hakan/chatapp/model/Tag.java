package se.sprinto.hakan.chatapp.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name= "tag_name", updatable = true, length = 50, unique = true)
    private String tagName;

    @ManyToMany(mappedBy="tags", fetch=FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Tag(Long tagId, String tagName, Set<Message> messages) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.messages = messages;
    }
}
