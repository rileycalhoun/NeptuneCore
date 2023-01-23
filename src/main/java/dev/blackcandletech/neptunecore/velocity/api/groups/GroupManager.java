package dev.blackcandletech.neptunecore.velocity.api;

public class GroupManager {

    private YAMLFile groupConfig;

    public GroupManager ()
    {
        this.groupConfig = new YAMLFile("groups.yml");
    }

}
