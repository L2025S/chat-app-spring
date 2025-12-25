package se.sprinto.hakan.chatapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sprinto.hakan.chatapp.model.Tag;
import se.sprinto.hakan.chatapp.repository.TagRepository;


import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public Tag createTag(String tagName) {
           tagRepository.findByTagName(tagName)
                   .ifPresent(t->{
                       throw new IllegalStateException("Tag already exists: " + tagName);
                   });
           Tag tag = new Tag(tagName);
           return tagRepository.save(tag);
    }

    public List<Tag>findAllTags () {
      return  tagRepository.findAll();
    }

    public Tag findByTagName(String tagName) {

        return tagRepository.findByTagName(tagName)
                .orElseThrow(()-> new NoSuchElementException("No such tag in the database."));

    }

    public Tag updateTag(String tagName, String newTagName) {
        Tag tag =tagRepository.findByTagName(tagName).orElseThrow(()
        -> new NoSuchElementException ("The tag does not exist. It cannot be updated."));

        tag.setTagName(newTagName);
        return tagRepository.save(tag);
    }

    public void deleteTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName)
                .orElseThrow(()-> new NoSuchElementException("No such tag in the database"));
        tagRepository.delete(tag);
    }


}
