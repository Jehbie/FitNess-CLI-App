import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {
    private int id;
    private String name;
    private List<Booking> bookings;
    private List<Review> reviews;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Booking bookLesson(Lesson lesson) {
        if (lesson.getAvailableSlots() > 0) {
            Booking newBooking = new Booking(bookings.size() + 1, this, lesson);
            bookings.add(newBooking);
            lesson.addBooking(newBooking);
            return newBooking;
        }
        return null;
    }

    public boolean cancelBooking(int bookingId) {
        Optional<Booking> booking = bookings.stream()
                .filter(b -> b.getId() == bookingId)
                .findFirst();

        if (booking.isPresent()) {
            bookings.remove(booking.get());
            booking.get().getLesson().removeBooking(booking.get());
            return true;
        }
        return false;
    }

    public boolean changeBooking(int bookingId, Lesson newLesson) {
        Optional<Booking> booking = bookings.stream()
                .filter(b -> b.getId() == bookingId)
                .findFirst();

        if (booking.isPresent() && newLesson.getAvailableSlots() > 0) {
            booking.get().getLesson().removeBooking(booking.get());
            booking.get().setLesson(newLesson);
            newLesson.addBooking(booking.get());
            return true;
        }
        return false;
    }

     public Review writeReview(Lesson lesson, String reviewText, int rating) {
        Review newReview = new Review(reviews.size() + 1, this, lesson, reviewText, rating);
        reviews.add(newReview);
        lesson.addReview(newReview);
        return newReview;
    }
}
