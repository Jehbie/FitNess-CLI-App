public class Booking {
    private int id;
    private Customer customer;
    private Lesson lesson;

    public Booking(int id, Customer customer, Lesson lesson) {
        this.id = id;
        this.customer = customer;
        this.lesson = lesson;
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

    public void setLesson(Lesson newLesson) {
        this.lesson = newLesson;
    }
}
