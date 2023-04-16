package CGA106G3.com.WebSocketChat.Service;

import CGA106G3.com.WebSocketChat.Entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatService {
    List<Message> getPublicHistory();

    void savePublicMessage(String key,Message message);
}
