package dev.blackcandletech.neptunecore.velocity.api;

import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings({"ResultOfMethodCallIgnored", "unchecked"})
public class YAMLFile {

    private File file;
    private Map<String, Object> data;
    private final ArrayList<Map<String, Object>> configurationSections = new ArrayList<>();

    public YAMLFile (String fileName)
    {
        if(!fileName.endsWith(".yml")) return;

        // Create YAML file if it does not exist
        String dir = System.getProperty("user.dir") + "\\plugins\\NeptuneCore";
        VelocityCore.getLogger().info(dir);
        this.file = new File(Path.of(dir, fileName).toUri());
        if(!(this.file.exists()))
        {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                VelocityCore.shutdown(e.getMessage());
            }

            // Write default data to file
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName))
            {
                try (OutputStream outputStream = Files.newOutputStream(this.file.toPath()))
                {
                    assert inputStream != null;
                    inputStream.transferTo(outputStream);
                }
            } catch (IOException e) {
                VelocityCore.shutdown(e.getMessage());
            }
        }

        // Read from YAML file
        Yaml yaml = new Yaml();
        try
        {
            InputStream inputStream = new FileInputStream(file);
            this.data = yaml.load(inputStream);
        } catch (FileNotFoundException e)
        {
            VelocityCore.shutdown(e.getMessage());
        }

        for(String key : data.keySet()) {
            Object obj = data.get(key);
            if(obj instanceof Map) {
                Map<String, Object> section = getConfigurationSection(key);
                this.configurationSections.add(section);
            }
        }
    }

    public File getFile()
    {
        return file;
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

    public Map<String, Object> getConfigurationSection(String path) {
        return (Map<String, Object>) data.get(path);
    }

    public ArrayList<Map<String, Object>> getConfigurationSections() {
        return configurationSections;
    }

}
