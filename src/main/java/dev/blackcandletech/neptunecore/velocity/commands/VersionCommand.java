package dev.blackcandletech.neptunecore.velocity.commands;

import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import dev.blackcandletech.neptunecore.velocity.api.ProxyCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class VersionCommand implements ProxyCommand {

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public void execute(Invocation invocation) {
        invocation.source().sendMessage(
                Component.text("Currently running on Neptune Core v" + VelocityCore.getVersion())
                        .color(TextColor.color(62, 167, 40))
        );
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("neptunecore.version");
    }

}
