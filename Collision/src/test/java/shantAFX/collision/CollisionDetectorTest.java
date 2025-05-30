package shantAFX.collision;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shantAFX.common.data.Entity;


public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;

    @BeforeEach
    public void setUp() {
        collisionDetector = new CollisionDetector();
    }

    private Entity createMockEntity(String id, double x, double y, float radius) {
        Entity mockEntity = mock(Entity.class);
        when(mockEntity.getID()).thenReturn(id);
        when(mockEntity.getX()).thenReturn(x);
        when(mockEntity.getY()).thenReturn(y);
        when(mockEntity.getRadius()).thenReturn(radius);
        return mockEntity;
    }

    @Test
    public void noCollisions_whenDistanceGreaterThanSumOfRadii() {
        Entity e1 = createMockEntity("e1", 0, 0, 1.0f);
        Entity e2 = createMockEntity("e2", 3.0, 0, 1.0f);

        assertFalse(collisionDetector.collides(e1, e2));
    }

    @Test
    public void collisions_whenDistanceEqualsThanSumOfRadii() {
        Entity e1 = createMockEntity("e1", 0, 0, 1.5f);
        Entity e2 = createMockEntity("e2", 3.0, 0, 1.5f);

        assertTrue(collisionDetector.collides(e1, e2));
    }

    @Test
    public void collisions_whenDistanceLessThanSumOfRadii() {
        Entity e1 = createMockEntity("e1", 0, 0, 2.0f);
        Entity e2 = createMockEntity("e2", 3.0, 0, 1.5f);

        assertTrue(collisionDetector.collides(e1, e2));
    }
}
