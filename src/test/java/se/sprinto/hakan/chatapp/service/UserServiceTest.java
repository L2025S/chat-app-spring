package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles
public class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testLogin () {
        //Arrange
        User user = new User(1L,"Alice", "123");

        when(userRepository.findByUsernameAndPassword("Alice","123")).thenReturn(user);

        //Act
        User result = userService.login("Alice", "123");

        //Assert

        verify(userRepository,times(1)).findByUsernameAndPassword("Alice","123");
        assertEquals(result, user);

    }

    @Test
    public void testRegister() {

        // Arrange
        User user = new User(1L,"Alice", "123");

        when(userRepository.save(user)).thenReturn(user);

        //Act
        User result = userService.register(user);

        //Assert
        verify(userRepository,times(1)).save(user);
        assertEquals(result, user);
    }
}
