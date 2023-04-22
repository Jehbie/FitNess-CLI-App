import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;
    private Lesson lesson;
    private Lesson anotherLesson;
    private Timetable timetable;
    private Booking booking;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "John Doe", "john@example.com");
        lesson = new Lesson(1, new LessonType("Guitar", 50), LocalDate.of(2023, 4, 30), new ArrayList<>());
        anotherLesson = new Lesson(2, new LessonType("Piano", 60), LocalDate.of(2023, 4, 25), new ArrayList<>());
        timetable = new Timetable(new ArrayList<>(List.of(lesson, anotherLesson)));
    }

    @Test
    void testBookLesson() {
        customer.bookLesson(lesson);
        assertTrue(lesson.getBookings().stream().anyMatch(booking -> booking.getCustomer().equals(customer)));
    }

    @Test
    void testCancelBooking() {
        booking = new Booking(1, customer, lesson);
        lesson.addBooking(booking);
        customer.cancelBooking(booking);
        assertFalse(lesson.getBookings().contains(booking));
    }

    @Test
    void testChangeBooking() {
        booking = new Booking(1, customer, lesson);
        lesson.addBooking(booking);
        customer.changeBooking(booking, anotherLesson);
        assertFalse(lesson.getBookings().contains(booking));
        assertTrue(anotherLesson.getBookings().stream().anyMatch(b -> b.getCustomer().equals(customer)));
    }

    @Test
    void testWriteReview() {
        customer.writeReview(lesson, "Great lesson!", 5);
        Review review = lesson.getReviews().stream()
                .filter(r -> r.getCustomer().equals(customer))
                .findFirst()
                .orElse(null);

        assertNotNull(review);
        assertEquals("Great lesson!", review.getContent());
        assertEquals(5, review.getRating());
    }
}
