import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportsTest {
    private Reports reports;
    private Timetable timetable;
    private LessonType yoga;
    private LessonType spin;
    private Date date1;
    private Date date2;

    @BeforeEach
    public void setUp() {
        yoga = new LessonType("Yoga", 15);
        spin = new LessonType("Spin", 20);
        date1 = new Date();
        date2 = new Date(date1.getTime() + 86400000L);

        List<Lesson> lessons = List.of(
                new Lesson(1, yoga, date1, 5),
                new Lesson(2, spin, date1, 5),
                new Lesson(3, yoga, date2, 5),
                new Lesson(4, spin, date2, 5)
        );

        timetable = new Timetable(lessons);
        reports = new Reports(timetable);
    }

    @Test
    public void testGenerateCustomerReport() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reports.generateCustomerReport();

        String expectedOutput = "Lesson: Yoga, Date: " + date1 + ", Customers: 0, Average Rating: 0.00\n" +
                "Lesson: Spin, Date: " + date1 + ", Customers: 0, Average Rating: 0.00\n" +
                "Lesson: Yoga, Date: " + date2 + ", Customers: 0, Average Rating: 0.00\n" +
                "Lesson: Spin, Date: " + date2 + ", Customers: 0, Average Rating: 0.00\n";

        assertEquals(expectedOutput, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void testGenerateIncomeReport() {
   
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reports.generateIncomeReport();

        String expectedOutput = "Income per lesson type:\n" +
                "Yoga: 0.00\n" +
                "Spin: 0.00\n" +
                "Highest income type: Yoga, income: 0.00\n";

        assertEquals(expectedOutput, outContent.toString());

    
        System.setOut(originalOut);
    }
}
