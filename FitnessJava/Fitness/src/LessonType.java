public class LessonType {
    private String name;
    private double price;
    private int maxCapacity;



    public LessonType(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    

}
