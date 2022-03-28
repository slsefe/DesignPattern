package singleton.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdGenerator01Test {

    @Test
    void should_equal_when_compare_two_instances_generated_by_singleton_class() {
        IdGenerator01 instance_1 = IdGenerator01.getInstance();
        IdGenerator01 instance_2 = IdGenerator01.getInstance();

        assertEquals(instance_1, instance_2);
    }

    @Test
    void should_increment_when_two_instance_get_id() {
        IdGenerator01 instance1 = IdGenerator01.getInstance();
        IdGenerator01 instance2 = IdGenerator01.getInstance();

        long id_1 = instance1.getId();
        long id_2 = instance2.getId();
        assertEquals(1, id_2 - id_1);
    }

}
