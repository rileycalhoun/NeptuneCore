package dev.blackcandletech.neptunecore.velocity.api.groups;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.permission.PermissionFunction;
import com.velocitypowered.api.permission.PermissionProvider;
import com.velocitypowered.api.permission.PermissionSubject;
import com.velocitypowered.api.permission.Tristate;
import com.velocitypowered.api.proxy.Player;
import dev.blackcandletech.neptunecore.velocity.VelocityCore;

public class PlayerPermissionProvider implements PermissionProvider, PermissionFunction {

    private final Player player;

    public PlayerPermissionProvider(Player player)
    {
        this.player = player;
    }

    @Override
    public PermissionFunction createFunction(PermissionSubject subject) {
        Preconditions.checkState(subject == this.player, "createFunction called with different argument");
        return this;
    }

    @Override
    public Tristate getPermissionValue(String permission) {
        Group defaultGroup = GroupManager.getDefaultGroup();
        return Tristate.fromBoolean(defaultGroup.getPermissions().contains(permission));
    }

}
