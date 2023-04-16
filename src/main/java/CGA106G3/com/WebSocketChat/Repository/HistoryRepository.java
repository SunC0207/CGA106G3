package CGA106G3.com.WebSocketChat.Repository;

import CGA106G3.com.WebSocketChat.Entity.HistoryMessage;
import CGA106G3.com.WebSocketChat.Entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<HistoryMessage,String> {
}
