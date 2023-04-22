import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.Calendar;



public class MainApp {
    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        Reports reports = new Reports(timetable);

        populateSampleData(timetable);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        Customer customer = new Customer(1, "John Doe");

        
        while (running) {
            System.out.println("Welcome to the Weekend Fitness Club");
            System.out.println("1. Search lessons by day");
            System.out.println("2. Search lessons by fitness type");
            System.out.println("3. Generate customer report");
            System.out.println("4. Generate income report");
            System.out.println("5. Book a lesson");
            System.out.println("6. Write a review");
            System.out.println("7. Change a booking");
            System.out.println("8. Cancel a booking");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter day (Saturday or Sunday): ");
                        String day = scanner.nextLine();
                        Date date = parseDate(day);
                        List<Lesson> lessonsByDay = timetable.searchByDay(date);
                        printLessons(lessonsByDay);
                        break;
                    case 2:
                        System.out.print("Enter fitness type: ");
                        String fitnessType = scanner.nextLine();
                        List<Lesson> lessonsByType = timetable.searchByFitnessType(fitnessType);
                        printLessons(lessonsByType);
                        break;
                    case 3:
                        reports.generateCustomerReport();
                        break;
                    case 4:
                        reports.generateIncomeReport();
                        break;
                    case 5:
                        printLessons(timetable.getLessons());
                        System.out.print("Enter the ID of the lesson to book: ");
                        int lessonId = scanner.nextInt();
                        scanner.nextLine();
                        if (lessonId < 1 || lessonId > timetable.getLessons().size()) {
                            System.out.println("Invalid lesson ID.");
                        } else {
                            Lesson selectedLesson = timetable.getLessonById(lessonId);
                            Booking booking = customer.bookLesson(selectedLesson);
                            if (booking != null) {
                                System.out.println("Booking successful!");
                            } else {
                                System.out.println("Booking failed, no available slots for this lesson.");
                            }
                        }
                        break;
                    
                    
                    case 6:
                        printLessons(timetable.getLessons());
                        System.out.print("Enter the ID of the lesson to review: ");
                        int reviewLessonId = scanner.nextInt();
                        scanner.nextLine();
                        Lesson reviewLesson = timetable.getLessonById(reviewLessonId);
                        if (reviewLesson != null) {
                            System.out.print("Enter your rating (1-5): ");
                            int rating = scanner.nextInt();
                            scanner.nextLine();
                            if (rating < 1 || rating > 5) {
                                System.out.println("Invalid rating. Rating should be between 1 and 5.");
                            } else {
                                System.out.print("Enter your review comment: ");
                                String reviewText = scanner.nextLine();
                                Review review = customer.writeReview(reviewLesson, reviewText, rating);
                                if (review != null) {
                                    System.out.println("Review added successfully!");
                                } else {
                                    System.out.println("Failed to add review.");
                                }
                            }
                        } else {
                            System.out.println("Invalid lesson ID.");
                        }
                        break;
                                       
                    case 7:
                        System.out.print("Enter the ID of the booking to change: ");
                        int bookingIdToChange = scanner.nextInt();
                        scanner.nextLine();
                        printLessons(timetable.getLessons()); // Corrected line
                        System.out.print("Enter the ID of the new lesson to book: ");
                        int newLessonId = scanner.nextInt();
                        scanner.nextLine();
                        Lesson newLesson = timetable.getLessonById(newLessonId);
                        if (newLesson != null) {
                            boolean changed = customer.changeBooking(bookingIdToChange, newLesson);
                            if (changed) {
                                System.out.println("Booking changed successfully!");
                            } else {
                                System.out.println("Booking change failed, no available slots for this lesson.");
                            }
                        } else {
                            System.out.println("Invalid new lesson ID.");
                        }
                        break;                    
                    case 8:
                        System.out.print("Enter the ID of the booking to cancel: ");
                        int bookingIdToCancel = scanner.nextInt();
                        scanner.nextLine();
                        boolean cancelled = customer.cancelBooking(bookingIdToCancel);
                        if (cancelled) {
                            System.out.println("Booking cancelled successfully!");
                        } else {
                            System.out.println("Booking cancellation failed. Invalid booking ID.");
                        }
                        break;

                    case 9:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void populateSampleData(Timetable timetable) {
        LessonType yoga = new LessonType("Yoga", 15);
        LessonType spin = new LessonType("Spin", 20);
        LessonType zumba = new LessonType("Zumba", 12);
        LessonType boxFit = new LessonType("Box Fit", 18);
    
        // Populate timetable with lessons
        Calendar calendar = Calendar.getInstance();
    
        int lessonId = 1;

        for (int i = 0; i < 8; i++) { // For each weekend
            for (int j = 0; j < 2; j++) { // For each day of the weekend (Saturday and Sunday)
                    calendar.set(Calendar.DAY_OF_WEEK, j == 0 ? Calendar.SATURDAY : Calendar.SUNDAY);
                calendar.add(Calendar.WEEK_OF_YEAR, i);
                Date lessonDate = calendar.getTime();
    
                // Schedule two lessons per day
                Lesson lesson1 = new Lesson(lessonId++, j == 0 ? yoga : spin, lessonDate, 5);
                Lesson lesson2 = new Lesson(lessonId++, j == 0 ? zumba : boxFit, lessonDate, 5);
    
                timetable.addLesson(lesson1);
                timetable.addLesson(lesson2);
            }
        }
    }
    
    
    private static Date parseDate(String day) throws ParseException {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        Date inputDate = dayFormat.parse(day);
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return now.getTime();
    }

    private static void printLessons(List<Lesson> lessons) {
    if (lessons.isEmpty()) {
        System.out.println("No lessons found.");
    } else {
        for (Lesson lesson : lessons) {
            System.out.printf("ID: %d, Lesson: %s, Date: %s%n", lesson.getId(), lesson.getType().getName(), lesson.getDate());
        }
    }
}

}
