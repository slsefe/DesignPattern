package builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcePoolConfig03Test {
    @Test
    void should_create_instance_with_all_field() {
        ResourcePoolConfig03 config = new ResourcePoolConfig03.Builder()
                .withName("mysql")
                .withMaxTotalCount(10)
                .withMaxIdleCount(5)
                .withMinIdleCount(0)
                .build();

        assertEquals("mysql", config.getName());
        assertEquals(10, config.getMaxTotalCount());
        assertEquals(5, config.getMaxIdleCount());
        assertEquals(0, config.getMinIdleCount());
    }

}
