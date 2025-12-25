//package se.sprinto.hakan.chatapp.dao;
//
//
//import se.sprinto.hakan.chatapp.model.Message;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MessageListDAO implements MessageDAO {
//
//    private final List<Message> messages = new ArrayList<>();
//
//    @Override
//    public void saveMessage(Message message) {
//        messages.add(message);
//    }
//
//    @Override
//    public List<Message> getMessagesByUserId(Long userId) {
//        return messages.stream()
//                .filter(m -> m.getUserId() == userId)
//                .collect(Collectors.toList());
//    }
//
//
//}
//
