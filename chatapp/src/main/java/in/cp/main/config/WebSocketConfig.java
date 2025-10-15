package in.cp.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	
	// /chat endpoint re connection establish haba
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat")
		.setAllowedOrigins(AppConstant.FRONT_END_BASE_URL)
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// In memory message broker 
		config.enableSimpleBroker("/topic");
		// /topic/message -> server send message to this start with topic
		config.setApplicationDestinationPrefixes("/app");
		// /app/chat
	}
	
}
