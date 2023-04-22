import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reports {
    private Timetable timetable;

    public Reports(Timetable timetable) {
        this.timetable = timetable;
    }

    public void generateCustomerReport() {
        for (Lesson lesson : timetable.getLessons()) {
            int numberOfCustomers = lesson.getBookings().size();
            double averageRating = lesson.getAverageRating();
            System.out.printf("Lesson: %s, Date: %s, Customers: %d, Average Rating: %.2f%n",
                lesson.getType().getName(), lesson.getDate(), numberOfCustomers, averageRating);
        }
    }

    public void generateIncomeReport() {
        Map<String, Double> incomePerLessonType = new HashMap<>();
        double maxIncome = 0;
        String highestIncomeType = "";

        for (Lesson lesson : timetable.getLessons()) {
            String lessonTypeName = lesson.getType().getName();
            double lessonIncome = lesson.getBookings().size() * lesson.getType().getPrice();
            incomePerLessonType.put(lessonTypeName, incomePerLessonType.getOrDefault(lessonTypeName, 0.0) + lessonIncome);

            if (incomePerLessonType.get(lessonTypeName) > maxIncome) {
                maxIncome = incomePerLessonType.get(lessonTypeName);
                highestIncomeType = lessonTypeName;
            }
        }

        System.out.println("Income per lesson type:");
        for (Map.Entry<String, Double> entry : incomePerLessonType.entrySet()) {
            System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue());
        }

        System.out.printf("Highest income type: %s, income: %.2f%n", highestIncomeType, maxIncome);
    }
}
