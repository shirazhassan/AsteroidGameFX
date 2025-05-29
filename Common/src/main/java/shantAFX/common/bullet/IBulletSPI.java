package shantAFX.common.bullet;

import shantAFX.common.data.GameData;
import shantAFX.common.data.Entity;
public interface IBulletSPI {
    Entity createBullet(Entity e, GameData gameData);

}
