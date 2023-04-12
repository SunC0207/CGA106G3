package CGA106G3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//啟用訊息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        //客戶端向伺服端 開放聊天室 傳送訊息的前綴
        registry.enableSimpleBroker("/chatroom","/user");
        //代表可以向客戶端發送訊息的前綴(/chatroom /user 兩個)
        registry.setUserDestinationPrefix("/user");
        //指定 一對一 發送訊息前綴
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("").withSockJS();
        //註冊stomp端點 接收客戶端連線(定義連線的 url)
        //withSockJS
    }
}
