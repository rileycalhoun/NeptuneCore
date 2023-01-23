package dev.blackcandletech.neptunecore.velocity.api.yaml;

import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class ConfigurationSection {

    private final Map<String, Object> data;
    private final Map<String, ConfigurationSection> configurationSections = new HashMap<>();

    public ConfigurationSection (Map<String, Object> data)
    {
        this.data = data;
        for(String key : data.keySet())
        {
            Object obj = data.get(key);
            if(obj instanceof Map)
            {
                Map<String, Object> section = (Map<String, Object>) obj;
                this.configurationSections.put(key, new ConfigurationSection(section));
            }
        }
    }

    public Map<String, Object> getData()
    {
        return data;
    }

    public String getString(String path)
    {
        return (String) data.get(path);
    }

    public int getInteger(String path)
    {
        return (int) data.get(path);
    }

    public boolean getBoolean(String path)
    {
        return (boolean) data.get(path);
    }

    public float getFloat(String path)
    {
        return (float) data.get(path);
    }

    public double getDouble(String path)
    {
        return (double) data.get(path);
    }

    public ArrayList<String> getStringList(String path) {
        VelocityCore.getLogger().info(data.get(path).toString());
        return (ArrayList<String>) data.get(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return getConfigurationSections().get(path);
    }

    public Map<String, ConfigurationSection> getConfigurationSections() {
        return configurationSections;
    }
}
