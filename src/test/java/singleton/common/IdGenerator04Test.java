package singleton.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdGenerator04Test {

    @Test
    void should_equal_when_compare_two_instances_generated_by_singleton_class() {
        IdGenerator04 instance_1 = IdGenerator04.getInstance();
        IdGenerator04 instance_2 = IdGenerator04.getInstance();

        assertEquals(instance_1, instance_2);
    }

    @Test
    void should_increment_when_two_instance_get_id() {
        IdGenerator04 instance1 = IdGenerator04.getInstance();
        IdGenerator04 instance2 = IdGenerator04.getInstance();

        long id_1 = instance1.getId();
        long id_2 = instance2.getId();
        assertEquals(1, id_2 - id_1);
    }

}
