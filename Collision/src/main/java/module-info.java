import shantAFX.common.services.IPostEntityProcessingService;

module Collision {
    exports shantAFX.collision;

    requires Common;
    requires Asteroid;
    requires Player;
    requires java.desktop;
    requires Enemy;
    requires Bullet;

    uses shantAFX.common.asteroids.IAsteroidSplitter;

    provides IPostEntityProcessingService with shantAFX.collision.CollisionDetector;

}