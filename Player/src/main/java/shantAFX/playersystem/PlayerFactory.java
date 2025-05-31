package shantAFX.playersystem;

import shantAFX.common.data.Entity;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.components.FactionComponent;

import static shantAFX.playersystem.PlayerControlSystem.PLAYER_RADIUS;

public class PlayerFactory {
    public static Entity spawnPlayer(GameData gameData, World world){
        Player p = new Player();
        p.setX(gameData.getDisplayWidth() / 2.0);
        p.setY(gameData.getDisplayHeight() / 2.0);
        p.setRotation(0);
        p.setRadius(PLAYER_RADIUS);

        p.addComponent(
                new FactionComponent(FactionComponent.Faction.PLAYER)
        );
        return p;
    }
}
