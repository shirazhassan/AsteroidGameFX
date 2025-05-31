module Collision {
    exports shantAFX.collision;

    requires Common;
    requires java.desktop;

    uses shantAFX.common.asteroids.IAsteroidSplitter;

    provides shantAFX.common.services.IPostEntityProcessingService
            with shantAFX.collision.CollisionDetector;
}