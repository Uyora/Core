package net.uyora.core.filemanager;


import net.uyora.core.Core;

public class ConfigFile {

    private final Core main;
    public ConfigFile(Core main){
        this.main = main;
    }

    public void setUpFile(){
        for (ConfigFileData data : ConfigFileData.values()){
            if (main.getConfig().get(data.getPath()) == null){
                main.getConfig().set(data.getPath(), data.getDefaultValue());
                main.saveConfig();
            }
        }
    }

    public void setData(ConfigFileData data, Object value){
        main.getConfig().set(data.getPath(), value);
        main.saveConfig();
    }

    public Object getData(ConfigFileData data){
        return main.getConfig().get(data.getPath());
    }

}
