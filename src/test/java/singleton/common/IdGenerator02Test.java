package singleton.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdGenerator02Test {
    @Test
    void should_equal_when_compare_two_instances_generated_by_singleton_class() {
        IdGenerator02 instance_1 = IdGenerator02.getInstance();
        IdGenerator02 instance_2 = IdGenerator02.getInstance();

        assertEquals(instance_1, instance_2);
    }

    @Test
    void should_increment_when_two_instance_get_id() {
        IdGenerator02 instance1 = IdGenerator02.getInstance();
        IdGenerator02 instance2 = IdGenerator02.getInstance();

        long id_1 = instance1.getId();
        long id_2 = instance2.getId();
        assertEquals(1, id_2 - id_1);
    }

}
