package net.uyora.core.usermanager;

public enum UserData {
    ACTIVE_CHARACTER("ActiveCharacter", 0);

    private String path;
    private Object defaultValue;

    UserData(String path, Object defaultValue) {
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
