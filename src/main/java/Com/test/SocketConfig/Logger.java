package Com.test.SocketConfig;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
public class Logger {

    @EventListener
    public void handleSessionConnectedEvent(SessionConnectedEvent event) {
        Principal user = event.getUser();
        System.out.println("WebSocket Connected: " + (user != null ? user.getName() : "unknown"));
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        System.out.println("WebSocket Disconnected: " + event.getSessionId());
    }
}

