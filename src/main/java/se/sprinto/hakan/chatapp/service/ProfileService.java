package se.sprinto.hakan.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.ProfileRepository;
import se.sprinto.hakan.chatapp.model.Profile;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;


    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }


    public Profile createProfile(String username, String imageUrl, String description) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new NoSuchElementException("Username not found." + username));

        if (profileRepository.findByUser(user).isPresent()){
            throw new IllegalStateException("Profile already exists: " + username);
        }

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setImageUrl(imageUrl);
        profile.setDescription(description);

        return profileRepository.save(profile);

    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile findProfileById(Long profileId) {
       return profileRepository.findById(profileId)
               .orElseThrow(()-> new NoSuchElementException(
                       "The profile does not exist. Profile ID: " + profileId)
               );
    }

    public Profile updateImageUrl(Long profileId, String newImageUrl) {
       Profile profile = profileRepository.findById(profileId)
               .orElseThrow(()-> new NoSuchElementException(
                       "The profile does not exist. Profile ID: " + profileId
               ));
       profile.setImageUrl(newImageUrl);
       return profileRepository.save(profile);
    }

    public Profile updateDescription(Long profileId, String newDescription) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                ()->new NoSuchElementException("The profile does not exist. Profile ID: " + profileId));
       profile.setDescription(newDescription);
       return profileRepository.save(profile);
    }

    public void deleteProfile(Long profileId) {
        if (profileRepository.findById(profileId).isEmpty()) {
            throw new NoSuchElementException("The profile you delete does not exist. Profile ID: " + profileId);
        }
        profileRepository.deleteById(profileId);
    }


    // I would like to try to update the profile's image by using the username.
    public Profile updateImageUrlByUsername(String username, String newImageUrl) {
         User user = userRepository.findByUsername(username)
                 .orElseThrow(() -> new NoSuchElementException("User not found: " + username));

         Profile profile = profileRepository.findByUser(user)
                 .orElseThrow(() -> new NoSuchElementException("Profile not found for user: " + username));

         profile.setImageUrl(newImageUrl);

         return profileRepository.save(profile);
    }

}














