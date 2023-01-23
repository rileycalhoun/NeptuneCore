package dev.blackcandletech.neptunecore.velocity.api.yaml;

import dev.blackcandletech.neptunecore.velocity.VelocityCore;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings({"ResultOfMethodCallIgnored"})
public class YAMLFile {

    private File file;
    private ConfigurationSection configuration;

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
            this.configuration = new ConfigurationSection(yaml.load(inputStream));
        } catch (FileNotFoundException e)
        {
            VelocityCore.shutdown(e.getMessage());
        }
    }

    public File getFile()
    {
        return file;
    }

    public ConfigurationSection getConfiguration() {
        return configuration;
    }

}
