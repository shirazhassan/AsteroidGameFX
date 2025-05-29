package shantAFX.playersystem;

import shantAFX.common.data.Entity;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

public class PlayerFactory {
    public static Entity spawnPlayer(GameData gameData, World world){
        Player p = new Player();
        p.setX(gameData.getDisplayWidth() / 2.0);
        p.setY(gameData.getDisplayHeight() / 2.0);
        world.addEntity(p);
        return p;
    }
}
