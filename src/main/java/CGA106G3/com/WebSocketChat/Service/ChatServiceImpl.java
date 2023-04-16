//package CGA106G3.com.WebSocketChat.Service;
//
//import CGA106G3.com.WebSocketChat.Entity.Message;
//import com.google.gson.Gson;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.Jedis;
//
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//public class ChatServiceImpl implements ChatService{
//    @Autowired
//    Jedis jedis;
//    @Autowired
//    ModelMapper modelMapper;
//    @Autowired
//    Gson gson;
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Override
//    public List<Message> getPublicHistory() {
//
//        return jedis.lrange("public",0,-1)
//                .stream()
//                .map(this::stringToMessage)
//                .collect(Collectors.toList());
//
//    }
//
//    @Override
//    public void savePublicMessage(String key, Message message) {
//        String gsonMessage = gson.toJson(message);
//        jedis.rpush("public", gsonMessage);
//    }
//
//    private Message stringToMessage(String string){
//        return gson.fromJson(string, Message.class);
//
//    }
//}
