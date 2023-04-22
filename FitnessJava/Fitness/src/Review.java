public class Review {
    private int id;
    private Customer customer;
    private Lesson lesson;
    private String reviewText;
    private int rating;

    public Review(int id, Customer customer, Lesson lesson, String reviewText, int rating) {
        this.id = id;
        this.customer = customer;
        this.lesson = lesson;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

}
