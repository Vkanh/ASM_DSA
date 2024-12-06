public class Student {
    private String id;
    private String name;
    private double score;

    // Constructor
    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    // Student ranking method
    public String getRank() {
        if (score < 5.0) return "Fail";
        else if (score < 6.5) return "Average";
        else if (score < 7.5) return "Good";
        else if (score < 9.0) return "Very Good";
        else return "Excellent";
    }
    @Override
    public String toString() {
        return String.format("%-10s %-20s %-5.1f %-10s", id, name, score, getRank());
    }
}
