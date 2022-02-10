package net.uyora.core.character.races;

public enum RaceType {

    HUMAN(101, 201, "\uf022" ),
   DWARF(102, 202,"\uf023"),
    ELF(103, 203, "\uf020"),
    TIEFLING(104, 204,"\uf021"),
    GNOME(105, 205,"\uf024");

    private int dataColor;
    private int dataGrey;
    private String icon;

    RaceType(int color, int dataGrey, String icon){
        this.dataGrey = dataGrey;
        dataColor = color;
        this.icon = icon;
    }

    public int getDataColor(){
        return dataColor;
    }
    public int getDataGrey(){
        return dataGrey;
    }

    public String getName(){
        return this.toString();
    }

    public String getIcon(){
        return icon;
    }

}
