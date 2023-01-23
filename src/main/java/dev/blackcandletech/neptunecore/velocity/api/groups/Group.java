package dev.blackcandletech.neptunecore.velocity.api.groups;

import com.google.common.collect.Lists;
import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import dev.blackcandletech.neptunecore.velocity.api.yaml.ConfigurationSection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {

    private String name, prefix, suffix;
    private List<String> permissions;

    private ConfigurationSection options;
    private boolean isDefault;

    public Group (String name, ConfigurationSection section)
    {
        this.name = name;
        this.prefix = section.getString("prefix");
        this.suffix = section.getString("suffix");
        this.permissions = section.getStringList("permissions");
        this.options = section.getConfigurationSection("options");

        if(this.options != null) {
            this.isDefault = this.options.getBoolean("default");
        }
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
