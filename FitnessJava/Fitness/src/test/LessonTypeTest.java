import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class LessonTypeTest {
    private LessonType lessonType;

    @BeforeEach
    void setUp() {
        lessonType = new LessonType("Guitar", 50);
    }

    @Test
    void testGetName() {
        assertEquals("Guitar", lessonType.getName());
    }

    @Test
    void testGetPrice() {
        assertEquals(50, lessonType.getPrice());
    }

    @Test
    void testGetMaxCapacity() {
        // Test implementation
    }
}
