import shantAFX.bulletsystem.BulletControlSystem;
import shantAFX.bulletsystem.BulletPlugin;
import shantAFX.common.services.*;
import shantAFX.common.bullet.IBulletSPI;
import shantAFX.bulletsystem.BulletFactory;

module Bullet {
    requires Common;
    requires javafx.graphics;

    uses shantAFX.common.bullet.IBulletSPI;

    provides IBulletSPI with shantAFX.bulletsystem.BulletFactory;
    provides IEntityProcessingService with BulletControlSystem;
    provides IGamePluginService with BulletPlugin;

    exports shantAFX.bulletsystem;
}