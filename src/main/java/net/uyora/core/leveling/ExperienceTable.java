package net.uyora.core.leveling;

public enum ExperienceTable {
    Level_1("Level.1", 0),
    Level_2("Level.2", 400),
    Level_3("Level.3", 1300 ),
    Level_4("Level.4", 2700),
    Level_5("Level.5", 4800),
    Level_6("Level.6", 7600),
    Level_7("Level.7", 11200),
    Level_8("Level.8", 15700),
    Level_9("Level.9", 21100),
    Level_10("Level.10", 27600),

    Level_11("Level.11", 35200),
    Level_12("Level.12", 44000),
    Level_13("Level.13", 54100 ),
    Level_14("Level.14", 65500),
    Level_15("Level.15", 78400),
    Level_16("Level.16", 92800),
    Level_17("Level.17", 108800),
    Level_18("Level.18", 126500),
    Level_19("Level.19", 145900),
    Level_20("Level.20", 167200),

    Level_21("Level.21", 190400),
    Level_22("Level.22", 215600),
    Level_23("Level.23", 242900),
    Level_24("Level.24", 272300),
    Level_25("Level.25", 304000),
    Level_26("Level.26", 338000),
    Level_27("Level.27", 374400),
    Level_28("Level.28", 413300),
    Level_29("Level.29", 454700),
    Level_30("Level.30", 499000),

    Level_31("Level.31", 546400),
    Level_32("Level.32", 597200),
    Level_33("Level.33", 651700),
    Level_34("Level.34", 710300),
    Level_35("Level.35", 773100),
    Level_36("Level.36", 840200),
    Level_37("Level.37", 911800),
    Level_38("Level.38", 987900),
    Level_39("Level.39", 1068700),
    Level_40("Level.40", 1154400),

    Level_41("Level.41", 1245100),
    Level_42("Level.42", 1340900),
    Level_43("Level.43", 1441900),
    Level_44("Level.44", 1548200),
    Level_45("Level.45", 1660000),
    Level_46("Level.46", 1777500),
    Level_47("Level.47", 1900700),
    Level_48("Level.48", 2029800),
    Level_49("Level.49", 2164900),
    Level_50("Level.50", 2306100),

    Level_51("Level.51", 2453600),
    Level_52("Level.52", 2607500),
    Level_53("Level.53", 2767900),
    Level_54("Level.54", 2293500),
    Level_55("Level.55", 3108900),
    Level_56("Level.56", 3289700),
    Level_57("Level.57", 3477600),
    Level_58("Level.58", 3672600),
    Level_59("Level.59", 3874900),
    Level_60("Level.60", 4084700),

    Level_61("Level.61", 4302100),
    Level_62("Level.62", 4527200),
    Level_63("Level.63", 4760100),
    Level_64("Level.64", 5000900),
    Level_65("Level.65", 5249700),
    Level_66("Level.66", 5506600),
    Level_67("Level.67", 5771700),
    Level_68("Level.68", 6045100),
    Level_69("Level.69", 6326900),
    Level_70("Level.70", 6617200);




    private String path;
    private int value;
    ExperienceTable(String path, int value){
        this.path = path;
        this.value = value;
    }

    public String getPath(){return path;}
    public int getValue(){return value;}
}
