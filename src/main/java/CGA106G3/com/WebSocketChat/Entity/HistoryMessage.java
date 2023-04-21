package CGA106G3.com.WebSocketChat.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryMessage {
    @Id
    private String historyId;

    private List<Message> history;
}
