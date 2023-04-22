import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookingTest {
    private Booking booking;
    private Customer customer;
    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        customer = new Customer(1, "John Doe");
        LessonType yoga = new LessonType("Yoga", 15);
        Date lessonDate = new Date();
        lesson = new Lesson(1, yoga, lessonDate, 5);
        booking = new Booking(1, customer, lesson);
    }
    @Test
    public void testGetId() {
        assertEquals(1, booking.getId());
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, booking.getCustomer());
    }

    @Test
    public void testGetLesson() {
        assertEquals(lesson, booking.getLesson());
    }

    @Test
    public void testSetLesson() {
        LessonType spin = new LessonType("Spin", 20);
        Date newLessonDate = new Date(lesson.getDate().getTime() + 86400000L); 
        Lesson newLesson = new Lesson(2, spin, newLessonDate, 5);
        booking.setLesson(newLesson);
        assertEquals(newLesson, booking.getLesson());
    }
}
