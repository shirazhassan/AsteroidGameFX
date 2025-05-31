module Common {
    requires java.desktop;
    requires spring.context;

    exports shantAFX.common.data;
    exports shantAFX.common.asteroids;
    exports shantAFX.common.services;
    exports shantAFX.common.bullet;
    exports shantAFX.common.util;
    exports shantAFX.common.data.components;

    uses shantAFX.common.services.IEntityProcessingService;
    uses shantAFX.common.services.IGamePluginService;
    uses shantAFX.common.services.IPostEntityProcessingService;

    uses shantAFX.common.bullet.IBulletSPI;
}