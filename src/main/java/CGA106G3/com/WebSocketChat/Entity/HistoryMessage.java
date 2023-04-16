package CGA106G3.com.WebSocketChat.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Message")
public class HistoryMessage {
    @Id
    private String historyId;

    private List<Message> history;
}
