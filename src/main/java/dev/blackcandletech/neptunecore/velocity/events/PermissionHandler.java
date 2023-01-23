package dev.blackcandletech.neptunecore.velocity.events;

import com.velocitypowered.api.event.Continuation;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.permission.PermissionsSetupEvent;
import com.velocitypowered.api.proxy.Player;
import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import dev.blackcandletech.neptunecore.velocity.api.ProxyEvent;
import dev.blackcandletech.neptunecore.velocity.api.groups.PlayerPermissionProvider;

public class PermissionHandler implements ProxyEvent {

    public PermissionHandler()
    {
        VelocityCore.getLogger().info("test");
    }

    @Subscribe
    public void onPlayerPermissionSetup(PermissionsSetupEvent event, Continuation continuation)
    {
        if(!(event.getSubject() instanceof final Player player))
        {
            continuation.resume();
            return;
        }

        VelocityCore.getLogger().info("Registering permissions for user: " + player.getUsername());
        event.setProvider(new PlayerPermissionProvider(player));
        continuation.resume();
    }

}
