package com.farata.course.mwd.auction.security;

import com.sun.security.auth.UserPrincipal;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;


/**
 * Enables {@link org.springframework.messaging.simp.annotation.SendToUser} annotation to send
 * direct messages to * anonymous users. Overrides {@link DefaultHandshakeHandler#determineUser} and
 * for anonymous users creates {@link Principal} with name initialized with unique {@link UUID}.
 */
public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {

  @Override
  protected Principal determineUser(final ServerHttpRequest request,
                                    final WebSocketHandler wsHandler,
                                    final Map<String, Object> attributes) {
    Principal principal = super.determineUser(request, wsHandler, attributes);
    return principal != null ? principal : new UserPrincipal(UUID.randomUUID().toString());
  }
}
