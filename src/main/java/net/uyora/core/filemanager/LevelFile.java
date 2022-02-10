package net.uyora.core.filemanager;

import net.uyora.core.Core;
import net.uyora.core.leveling.ExperienceTable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LevelFile {

    private File file;
    private File directory;
    private FileConfiguration fileConfiguration;

    private final Core core;

    public LevelFile(Core core){
        this.core = core;
        this.directory = new File(core.getDataFolder(), "Developers Only");
        this.file = new File(directory, "Experience Table.yml" );
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        initializeTable();
    }



    public File ExpTable(){return file;}

    public void initializeTable(){
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();

                file.canWrite();
                for (ExperienceTable table : ExperienceTable.values()) {
                    fileConfiguration.createSection(table.getPath());
                    fileConfiguration.set(table.getPath(), table.getValue());
                    fileConfiguration.save(file);
                }
            } else {
                for (ExperienceTable table : ExperienceTable.values()) {
                    fileConfiguration.createSection(table.getPath());
                    fileConfiguration.set(table.getPath(), table.getValue());
                    fileConfiguration.save(file);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
