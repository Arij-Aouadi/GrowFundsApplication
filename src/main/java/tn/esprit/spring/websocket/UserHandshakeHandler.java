package tn.esprit.spring.websocket;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import tn.esprit.spring.Services.Classes.UserService;

import java.security.Principal;
import java.util.Map;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);


    UserService userService;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        userServ  ice=new UserService();
//        final Long id = userService.getConnectedUser().getId();
        final Long id =4L;
        LOG.info("User with ID '{}' opened the page", id);

        System.out.println( new UserPrincipal(id+""));
        return new UserPrincipal(id+"");
    }
}
