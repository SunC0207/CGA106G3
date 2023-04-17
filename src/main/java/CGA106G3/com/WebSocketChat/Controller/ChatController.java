package CGA106G3.com.WebSocketChat.Controller;

import CGA106G3.com.WebSocketChat.Entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    RedisTemplate redisTemplate;


    @MessageMapping("/message")// /app/message
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        redisTemplate.opsForList().rightPush("public",message);
        return message;
    }

    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId().toString(),"/private",message);
        return message;
    }

    @RequestMapping("webrtc/{username}.html")
    public ModelAndView socketChartPage(@PathVariable String username) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("webrtc.html");
        modelAndView.addObject("username",username);
        return modelAndView;
    }

    @GetMapping("/getPublicHistory")
    @ResponseBody
    public List<Message> getPublicHistory(){
        return redisTemplate.opsForList().range("public",0,-1);
    }

}
