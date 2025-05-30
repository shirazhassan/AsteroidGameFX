module Collision {
    exports shantAFX.collision;

    requires Common;
    requires Asteroid;
    requires Player;
    requires java.desktop;
    requires Enemy;
    requires Bullet;

    uses shantAFX.common.asteroids.IAsteroidSplitter;

    provides shantAFX.common.services.IPostEntityProcessingService
            with shantAFX.collision.CollisionDetector;
}