import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lesson {
    private int id;
    private LessonType type;
    private Date date;
    private int maxParticipants;
    private List<Booking> bookings;
    private List<Review> reviews;

    public Lesson(int id, LessonType type, Date date, int maxParticipants) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.maxParticipants = maxParticipants;
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }


    public int getAvailableSlots() {
        return type.getMaxCapacity() - bookings.size();
    }
    

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public double getAverageRating() {
        if (reviews.size() == 0) {
            return 0;
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }
    
    
    public Date getDate() {
        return date;
    }

    public LessonType getType() {
        return type;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getId() {
        return id;
    }
}
