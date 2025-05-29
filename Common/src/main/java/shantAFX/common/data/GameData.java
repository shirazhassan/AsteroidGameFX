package shantAFX.common.data;

public class GameData {
    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private float deltaTime;
    private CollisionMode wallMode = CollisionMode.WRAP;


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }
    public float getDeltaTime() {
        return deltaTime;
    }

    public void setCollisionMode(CollisionMode mode) {
        this.wallMode = mode;
    }

    public CollisionMode getCollisionMode() {
        return wallMode;
    }
}
