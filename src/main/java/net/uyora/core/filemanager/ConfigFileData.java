package net.uyora.core.filemanager;

import java.util.Arrays;

public enum ConfigFileData {

    MAX_CHARACTERS("Max_Characters" , 5),
    CREATOR_LOCATION("Creator.Location", "none"),
    MOTD_VALUE("MOTD.Values", Arrays.asList("none")),
    SERVER_ICON("MOTD.Icon", "server-icon.png"),
    REALM_NAME("Realm.Name", "Gideon");

    private String path;
    private Object defaultValue;

    ConfigFileData(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public String getPath(){
        return path;
    }
    public Object getDefaultValue(){
        return defaultValue;
    }

}
