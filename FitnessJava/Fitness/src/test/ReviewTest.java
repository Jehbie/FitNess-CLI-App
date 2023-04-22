import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class ReviewTest {
    private Review review;
    private Customer customer;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "John Doe", "john@example.com");
        lesson = new Lesson(1, new LessonType("Guitar", 50), LocalDate.of(2023, 4, 30), new ArrayList<>());
        review = new Review(1, customer, lesson, "Great lesson!", 5);
    }

    @Test
    void testGetId() {
        assertEquals(1, review.getId());
    }

    @Test
    void testGetCustomer() {
        assertEquals(customer, review.getCustomer());
    }

    @Test
    void testGetLesson() {
        assertEquals(lesson, review.getLesson());
    }

    @Test
    void testGetReviewText() {
        assertEquals("Great lesson!", review.getContent());
    }

    @Test
    void testGetRating() {
        assertEquals(5, review.getRating());
    }
}