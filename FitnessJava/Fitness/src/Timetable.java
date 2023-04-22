import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;

public class Timetable {
    private List<Lesson> lessons;

    public Timetable() {
        this.lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    public List<Lesson> searchByDay(Date date) {
        return lessons.stream()
            .filter(lesson -> isSameDay(lesson.getDate(), date))
            .collect(Collectors.toList());
    }

    public List<Lesson> searchByFitnessType(String fitnessType) {
        return lessons.stream()
            .filter(lesson -> lesson.getType().getName().equalsIgnoreCase(fitnessType))
            .collect(Collectors.toList());
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
    public List<Lesson> getLessons() {
        return lessons;
    }


       public Lesson getLessonById(int id) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == id) {
                return lesson;
            }
        }
        return null;
    }

}



