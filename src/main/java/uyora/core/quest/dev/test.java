package uyora.core.quest.dev;

import org.bukkit.util.Vector;
import uyora.core.quest.Quest;

public class test extends Quest {

    public test(String id, String name, Vector location) {
        super(id, name, location);
        setStartStage("test");
        new killPigs("test", this);
    }



}
