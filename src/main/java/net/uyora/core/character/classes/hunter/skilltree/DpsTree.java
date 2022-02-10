package net.uyora.core.character.classes.hunter.skilltree;

public enum DpsTree {

    FIREBALL(1), ICEBALL(2), WATERBALL(3);


    private int slot;

    DpsTree(int slot){
    this.slot = slot;
    }

    public int getSlot(){
        return slot;
    }

}
