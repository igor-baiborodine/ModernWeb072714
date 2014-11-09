package com.farata.course.mwd.auction.config;

import com.farata.course.mwd.auction.security.WebSocketHandshakeHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;


/**
 * Keeps custom WebSocket and STOMP configuration.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry
        .addEndpoint("/ws")
        .setHandshakeHandler(handshakeHandler());
  }

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/api");
      // Configuration below is added automatically by Spring:
      // .enableSimpleBroker("/topic", "/queue");
  }

  @Override
  public boolean configureMessageConverters(final List<MessageConverter> messageConverters) {
    return true;
  }

  @Override
  public void configureWebSocketTransport(final WebSocketTransportRegistration registry) {}

  @Override
  public void configureClientInboundChannel(final ChannelRegistration registration) {}

  @Override
  public void configureClientOutboundChannel(final ChannelRegistration registration) {}

  /**
   * Returns custom handshake handler to enable
   * {@link org.springframework.messaging.simp.annotation.SendToUser} functionality for anonymous
   * users.
   */
  @Bean
  public WebSocketHandshakeHandler handshakeHandler() {
    return new WebSocketHandshakeHandler();
  }
}
