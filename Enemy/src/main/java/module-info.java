module Enemy {
    requires Common;
    requires java.desktop;
    requires javafx.graphics;
    requires spring.context;

    uses shantAFX.common.bullet.IBulletSPI;

    provides shantAFX.common.services.IEntityProcessingService
            with shantAFX.Enemy.EnemyControlSystem;
    provides shantAFX.common.services.IGamePluginService
            with shantAFX.Enemy.EnemyPlugin;

    exports shantAFX.Enemy;
}