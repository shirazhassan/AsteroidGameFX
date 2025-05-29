import shantAFX.common.asteroids.IAsteroidSplitter;
import shantAFX.common.services.*;

module Asteroid {
    requires Common;
    exports shantAFX.asteroids;
    requires javafx.graphics;

    uses shantAFX.common.asteroids.IAsteroidSplitter;

    provides IGamePluginService with shantAFX.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with shantAFX.asteroids.AsteroidProcessor;
    provides IAsteroidSplitter with shantAFX.asteroids.AsteroidSplitter;
}