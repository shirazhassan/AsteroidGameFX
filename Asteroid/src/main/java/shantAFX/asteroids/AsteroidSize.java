package shantAFX.asteroids;

import java.util.concurrent.ThreadLocalRandom;

public enum AsteroidSize {
    GIANT (36f, 5, 8),
    LARGE (24f, 4, 6),
    MEDIUM (18f, 3, 5),
    SMALL (10f, 2, 4);

    private final float baseRadius;
    private final int minSplit, maxSplit;
    private static final float BASE_SPEED = 600f;

    AsteroidSize (float baseRadius, int minSplit, int maxSplit) {
        this.baseRadius = baseRadius;
        this.minSplit = minSplit;
        this.maxSplit = maxSplit;
    }

    public float getBaseRadius() {
        return baseRadius;
    }
    public float getSpeed() {
        return BASE_SPEED / baseRadius;
    }
    public boolean canSplit() {
        return this != SMALL;
    }

    public AsteroidSize nextSize() {
        return switch (this) {
            case GIANT -> LARGE;
            case LARGE -> MEDIUM;
            case MEDIUM -> SMALL;
            default -> null;
        };
    }
    public int randomFragments() {
        if (maxSplit <= minSplit) return minSplit;
        return ThreadLocalRandom.current().nextInt(maxSplit - minSplit + 1) + minSplit;
    }
}
