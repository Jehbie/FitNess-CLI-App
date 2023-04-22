import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {
    private Timetable timetable;
    private Lesson lesson1;
    private Lesson lesson2;

    @BeforeEach
    void setUp() {
        timetable = new Timetable();
        lesson1 = new Lesson(1, new LessonType("Guitar", 50), LocalDate.of(2023, 4, 30), new ArrayList<>());
        lesson2 = new Lesson(2, new LessonType("Piano", 60), LocalDate.of(2023, 4, 30), new ArrayList<>());
    }

    @Test
    void testAddLesson() {
        timetable.addLesson(lesson1);
        assertTrue(timetable.getLessons().contains(lesson1));
    }

    @Test
    void testRemoveLesson() {
        timetable.addLesson(lesson1);
        timetable.removeLesson(lesson1);
        assertFalse(timetable.getLessons().contains(lesson1));
    }

    @Test
    void testSearchByDay() {
        timetable.addLesson(lesson1);
        timetable.addLesson(lesson2);
        List<Lesson> lessons = timetable.searchByDay(LocalDate.of(2023, 4, 30));
        assertTrue(lessons.contains(lesson1) && lessons.contains(lesson2));
    }

    @Test
    void testSearchByFitnessType() {
        timetable.addLesson(lesson1);
        timetable.addLesson(lesson2);
        List<Lesson> lessons = timetable.searchByFitnessType("Guitar");
        assertTrue(lessons.contains(lesson1) && !lessons.contains(lesson2));
    }

    @Test
    void testGetLessonById() {
        timetable.addLesson(lesson1);
        timetable.addLesson(lesson2);
        Lesson foundLesson = timetable.getLessonById(1);
        assertEquals(lesson1, foundLesson);
    }
}
