import shantAFX.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with shantAFX.collision.CollisionDetector;
}