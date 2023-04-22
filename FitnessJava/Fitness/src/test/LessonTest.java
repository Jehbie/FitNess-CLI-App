import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LessonTest {
    private Lesson lesson;
    private Customer customer;
    private Booking booking;
    private Review review;

    @BeforeEach
    void setUp() {
        lesson = new Lesson(1, new LessonType("Guitar", 50), LocalDate.of(2023, 4, 30), new ArrayList<>());
        customer = new Customer(1, "John Doe", "john@example.com");
        booking = new Booking(1, customer, lesson);
        review = new Review(1, customer, lesson, "Great lesson!", 5);
    }

    @Test
    void testGetAvailableSlots() {
        // Test implementation
    }

    @Test
    void testAddBooking() {
        lesson.addBooking(booking);
        assertTrue(lesson.getBookings().contains(booking));
    }

    @Test
    void testRemoveBooking() {
        lesson.addBooking(booking);
        lesson.removeBooking(booking);
        assertFalse(lesson.getBookings().contains(booking));
    }

    @Test
    void testGetAverageRating() {
        lesson.addReview(review);
        assertEquals(5, lesson.getAverageRating());
    }

    @Test
    void testAddReview() {
        lesson.addReview(review);
        assertTrue(lesson.getReviews().contains(review));
    }
}
