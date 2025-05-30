module Core {
    requires Common;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.desktop;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    opens shantAFX.main to javafx.graphics, spring.core, spring.beans;
    uses shantAFX.common.services.IPostEntityProcessingService;
    uses shantAFX.common.services.IGamePluginService;
    uses shantAFX.common.services.IEntityProcessingService;

    exports shantAFX.main;
}