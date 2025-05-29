import shantAFX.common.services.IEntityProcessingService;
import shantAFX.common.services.IGamePluginService;


module Player {
    requires Common;
    requires javafx.graphics;
    requires java.desktop;

    uses shantAFX.common.bullet.IBulletSPI;

    provides IGamePluginService with shantAFX.playersystem.PlayerPlugin;
    provides IEntityProcessingService with shantAFX.playersystem.PlayerControlSystem;
}