package dev.blackcandletech.neptunecore.velocity.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import dev.blackcandletech.neptunecore.velocity.api.ProxyEvent;

public class PlayerHandler implements ProxyEvent {

    @Subscribe
    public void onLogin(LoginEvent event) {

    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {

    }

}
