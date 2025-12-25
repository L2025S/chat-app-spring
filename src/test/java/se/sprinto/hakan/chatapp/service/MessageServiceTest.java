package se.sprinto.hakan.chatapp.service;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class MessageServiceTest {
    @MockitoBean
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;


    @BeforeEach
    void setUp(){
        messageRepository.deleteAll();
    }

    @Test
    public void testSave(){
        // Arrange
        User user = new User ("jackson", "jackson123");
        Message message =  new Message (user, "Merry Christmas",LocalDateTime.now());
        when(messageRepository.save(message)).thenReturn(message);

        //Act
        messageService.save(message);

        //Assert
        verify(messageRepository, times(1)).save(message);

    }

    @Test
    void testGetMessages() {

        //Arrange
        User user = new User ("jackson", "jackson123");
        Message message1 = new Message(user, "Hello Java!", LocalDateTime.now());
        Message message2 = new Message(user,"Hello World!", LocalDateTime.now());

        when(messageRepository.findByUserId(1L)).thenReturn(Arrays.asList(message1,message2));

        //Act
        List<Message> result = messageService.getMessages(1L);

        //Assert

        assertEquals("Hello Java!",result.get(0).getText());
        assertEquals("Hello World!",result.get(1).getText());

        verify(messageRepository,times(1)).findByUserId(1L);

    }

}
