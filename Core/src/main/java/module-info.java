module Core {
    requires Common;
    requires javafx.controls;
    requires javafx.graphics;
    opens shantAFX.main to javafx.graphics;
    uses shantAFX.common.services.IPostEntityProcessingService;
    uses shantAFX.common.services.IGamePluginService;
    uses shantAFX.common.services.IEntityProcessingService;
}