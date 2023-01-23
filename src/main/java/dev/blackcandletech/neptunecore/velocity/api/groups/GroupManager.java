package dev.blackcandletech.neptunecore.velocity.api.groups;

import com.velocitypowered.api.permission.PermissionFunction;
import com.velocitypowered.api.permission.PermissionProvider;
import com.velocitypowered.api.permission.PermissionSubject;
import com.velocitypowered.api.proxy.Player;
import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import dev.blackcandletech.neptunecore.velocity.api.yaml.ConfigurationSection;
import dev.blackcandletech.neptunecore.velocity.api.yaml.YAMLFile;

import java.util.ArrayList;
import java.util.Map;

public class GroupManager {

    private ConfigurationSection groupSection;
    private ArrayList<Group> groups;
    private static Group defaultGroup;

    public GroupManager ()
    {
        this.groupSection = new YAMLFile("groups.yml")
                .getConfiguration()
                .getConfigurationSection("groups");

        this.groups = new ArrayList<>();
        readGroupsFromConfig ();
    }

    private void readGroupsFromConfig ()
    {
        for(String key : groupSection
                .getConfigurationSections()
                .keySet())
        {
            ConfigurationSection section = groupSection.getConfigurationSections().get(key);

            Group group = new Group(key, section);
            if(group.isDefault())
            {
                defaultGroup = group;
                VelocityCore.getLogger().info("Default group has been set to " + group.getName());
            }

            this.groups.add(group);
        }
    }

    public Group getGroup(String name)
    {
        for(Group group : groups)
        {
            if(group.getName().equals(name))
                return group;
        }

        return null;
    }

    public static Group getDefaultGroup() {
        return defaultGroup;
    }

}
