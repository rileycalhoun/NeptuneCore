package dev.blackcandletech.neptunecore.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.blackcandletech.neptunecore.velocity.api.ProxyCommand;
import dev.blackcandletech.neptunecore.velocity.api.ProxyEvent;
import dev.blackcandletech.neptunecore.velocity.api.groups.GroupManager;
import dev.blackcandletech.neptunecore.velocity.api.yaml.YAMLFile;
import net.kyori.adventure.text.Component;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;

@Plugin(id = "neptunecore", name = "NeptuneCore",
    authors = "Quizaciously", description = "The core plugin for the Neptune project.", version = "1.0.0")
public class VelocityCore {

    private static ProxyServer server;
    private static Logger logger;
    private static String version;
    private static GroupManager groupManager;

    private static YAMLFile configYaml;

    @Inject
    public VelocityCore (ProxyServer server, Logger logger)
    {
        VelocityCore.server = server;
        VelocityCore.logger = logger;
        version = this.getClass().getAnnotation(Plugin.class).version();
        configYaml = new YAMLFile("config.yml");
        groupManager = new GroupManager();
    }

    @Subscribe
    public void onInitialize (ProxyInitializeEvent event)
    {
        String version = getConfig().getConfiguration().getString("version");
        if(!(version.equals(getVersion()))) shutdown("Please update the NeptuneCore configuration before continuing!");

        registerCommands();
        registerEvents();
    }

    public void registerEvents ()
    {
        Set<Class<? extends ProxyEvent>> commands = new Reflections("dev.blackcandletech.neptunecore.velocity.events")
                .getSubTypesOf(ProxyEvent.class);

        for(Class<? extends ProxyEvent> clazz : commands)
        {
            try
            {
                ProxyEvent event = clazz.getDeclaredConstructor().newInstance();
                server.getEventManager().register(this, event);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e)
            {
                logger.severe("Unable to load event: " + e.getMessage());
            }
        }
    }

    public void registerCommands ()
    {
        Set<Class<? extends ProxyCommand>> commands = new Reflections("dev.blackcandletech.neptunecore.velocity.commands")
                .getSubTypesOf(ProxyCommand.class);

        for(Class<? extends ProxyCommand> clazz : commands)
        {
            try
            {
                ProxyCommand command = clazz.getDeclaredConstructor().newInstance();
                server.getCommandManager().register(command.getName(), command, command.getAliases());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e)
            {
                logger.severe("Unable to load command: " + e.getMessage());
            }
        }
    }

    public static void shutdown(String reason)
    {
        getServer().shutdown(Component.text("Unable to start NeptuneCore: " + reason));
    }

    public static ProxyServer getServer ()
    {
        return server;
    }

    public static Logger getLogger ()
    {
        return logger;
    }

    public static String getVersion ()
    {
        return version;
    }

    public static YAMLFile getConfig()
    {
        return configYaml;
    }

    public static GroupManager getGroupManager()
    {
        return groupManager;
    }

}
