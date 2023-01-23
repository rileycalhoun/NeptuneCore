package dev.blackcandletech.neptunecore.velocity.api;

import com.velocitypowered.api.command.SimpleCommand;

public interface ProxyCommand extends SimpleCommand {

    String getName();
    String[] getAliases();

}
