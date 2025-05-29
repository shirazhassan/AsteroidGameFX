module Common {
    requires java.desktop;

    exports shantAFX.common.data;
    exports shantAFX.common.asteroids;
    exports shantAFX.common.services;
    exports shantAFX.common.bullet;
    exports shantAFX.common.util;

    uses shantAFX.common.services.IEntityProcessingService;
    uses shantAFX.common.services.IGamePluginService;
    uses shantAFX.common.services.IPostEntityProcessingService;

    uses shantAFX.common.bullet.IBulletSPI;
}