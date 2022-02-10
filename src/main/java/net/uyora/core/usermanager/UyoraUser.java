package net.uyora.core.usermanager;

import net.uyora.core.Core;
import net.uyora.core.character.CharacterData;
import net.uyora.core.character.UyoraCharacter;
import net.uyora.core.filemanager.PlayerFile;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class UyoraUser {
    private Player p;
    private Map<Integer, UyoraCharacter> characterList;
    private PlayerFile file;
    private Core main;



    public UyoraUser(Player p, Core main){
        this.main = main;
        this.p = p;
        //loads the characters of a player
        main.getCharacterManager().loadCharacters(p);
        this.characterList = main.getCharacterManager().getCharacterMap(p);
        file = new PlayerFile(p, main);
        setUpFile();
        setActiveCharacter(0);
    }

    public void setUpFile(){
        for(UserData data : UserData.values()){
            if (getData(data) == null){
                setData(data, data.getDefaultValue());
            }
        }
    }

    public Player getPlayer(){
        return p;
    }

    public boolean hasActiveCharacter(){
        if (getActiveCharacterNumber() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Object getData(UserData data){
        return file.getFile().get(data.getPath());
    }
    public void setData(UserData data, Object value){
        file.getFile().set(data.getPath(), value);
        file.saveFile();
    }

    public void setActiveCharacter(int number){
        if (number != 0) {
            main.getCharacterManager().saveActiveCharacter(p);
        }
        file.getFile().set(UserData.ACTIVE_CHARACTER.getPath(), number);
        file.saveFile();
        if (number != 0) {
            if (getActiveCharacter().getData(CharacterData.LOCATION) instanceof Location) {
                p.teleport((Location) getActiveCharacter().getData(CharacterData.LOCATION));
            }
            if (getActiveCharacter().getData(CharacterData.INVENTORY) instanceof ItemStack[]) {
                p.getInventory().setContents((ItemStack[]) getActiveCharacter().getData(CharacterData.INVENTORY));
            }
        }
    }

    public Map<Integer, UyoraCharacter> getCharacterMap(){
        return characterList;
    }
    public boolean hasCharacter(int number){
        if (getCharacterMap().containsKey(number)){
            return true;
        }
        return false;
    }

    public UyoraCharacter getActiveCharacter(){
        return characterList.get(getActiveCharacterNumber());
    }

    public int getActiveCharacterNumber(){
        return file.getFile().getInt(UserData.ACTIVE_CHARACTER.getPath());
    }

}
