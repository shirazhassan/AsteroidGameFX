import shantAFX.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires Asteroid;
    uses shantAFX.common.asteroids.IAsteroidSplitter;

    provides IPostEntityProcessingService with shantAFX.collision.CollisionDetector;

}