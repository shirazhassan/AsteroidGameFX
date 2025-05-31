package shantAFX.playersystem;

import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.data.*;
import shantAFX.common.data.components.PlayerComponent;
import shantAFX.common.services.IEntityProcessingService;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PlayerControlSystem implements IEntityProcessingService {

    private final IBulletSPI bulletSPI;
    private double shootCooldown = 0f;
    private static final double DEACCELERATION_FACTOR = 0.99    ;
    private static final float FIRE_RATE = 0.1f;
    public static final float PLAYER_RADIUS = 8;


    private static final float RESPAWN_DELAY = 3.0f;
    private float respawnTimer = 0.0f;

    public PlayerControlSystem() {
        this.bulletSPI = ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).findFirst().orElse(null);
    }
    @Override
    public void process(GameData gameData, World world) {

        double dt = gameData.getDeltaTime();
        GameKeys keys = gameData.getKeys();
        for (Entity playerE : world.getEntities()) {
            PlayerComponent pc = playerE.getComponent(PlayerComponent.class);
            if (pc == null)
                continue;

            if (keys.isDown(GameKeys.LEFT)) {
                playerE.setRotation(playerE.getRotation() - Player.ROTATION_SPEED * dt);
            }
            if (keys.isDown(GameKeys.RIGHT)) {
                playerE.setRotation(playerE.getRotation() + Player.ROTATION_SPEED * dt);
            }
            if (keys.isDown(GameKeys.UP)) {
                double rad = Math.toRadians(playerE.getRotation());
                playerE.setDx(playerE.getDx() + Math.cos(rad) * Player.ACCELERATION * dt);
                playerE.setDy(playerE.getDy() + Math.sin(rad) * Player.ACCELERATION * dt);

                double speed = Math.hypot(playerE.getDx(), playerE.getDy());
                if (speed > Player.MAX_SPEED) {
                    double scale = Player.MAX_SPEED / speed;
                    playerE.setDx(playerE.getDx() * scale);
                    playerE.setDy(playerE.getDy() * scale);
                }
            } else {
                playerE.setDx(playerE.getDx() * Math.pow(DEACCELERATION_FACTOR, dt*20));
                playerE.setDy(playerE.getDy() * Math.pow(DEACCELERATION_FACTOR, dt*20));
            }

            double nx = playerE.getX() + playerE.getDx() * dt;
            double ny = playerE.getY() + playerE.getDy() * dt;
            nx = wrap(nx, gameData.getDisplayWidth());
            ny = wrap(ny, gameData.getDisplayHeight());
            playerE.setX(nx);
            playerE.setY(ny);

            shootCooldown -= dt;
            if (bulletSPI != null && keys.isDown(GameKeys.SPACE) && shootCooldown <= 0f) {
                world.addEntity(bulletSPI.createBullet(playerE, gameData));
                shootCooldown = FIRE_RATE;
            }
        }

        keys.update();

        List<Entity> playerList = world.getEntities()
                .stream()
                .filter(e -> e.getComponent(PlayerComponent.class) != null)
                .toList();
        if (playerList.isEmpty()) {
            respawnTimer += gameData.getDeltaTime();
            if (respawnTimer >= RESPAWN_DELAY) {
                Entity newPlayer = PlayerFactory.spawnPlayer(gameData, world);
                newPlayer.addComponent(new PlayerComponent());
                world.addEntity(newPlayer);
                respawnTimer = 0f;
            }
        } else {
            respawnTimer = 0f;
        }
    }
    private double wrap(double v, double max) {
        if (v < 0) return v + max;
        if (v > max) return v - max;
        return v;
    }
}
