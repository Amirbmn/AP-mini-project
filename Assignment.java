import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Assignment {
    private static int numOfAssignments = 0;
    private String title;
    private Course course;
    private String id;
    private LocalDateTime deadline;
    private boolean is_active;
    private double pointOfTotal;
    private ArrayList<Student> students = new ArrayList<>();
    private HashMap<Student, Integer> scoreToStudent = new HashMap<>();

    public Assignment(String title, Course course, LocalDateTime deadline, double pointOfTotal) {
        this.title = title;
        this.course = course;
        this.deadline = deadline;
        this.pointOfTotal = pointOfTotal;
        students = course.getStudents();
        is_active = true;
        numOfAssignments++;
        this.id = createAssignmentId();
    }


    String createAssignmentId() {
        String id = "";
        for (int i = 0; i < 3 - String.valueOf(numOfAssignments).length(); i++) {
            id += "0";
        }
        return id + numOfAssignments;
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean is_active() {
        if (LocalDateTime.now().isAfter(deadline)) {
            is_active = false;
        }
        return is_active;
    }

    public double getPointOfTotal() {
        return pointOfTotal;
    }

    public void addMark(Student student, int score) {
        scoreToStudent.put(student, score);
    }

    void printScoresByAssignment() {
        //Student[] keys = scoreToStudent.keySet().toArray(new Student[0]);
        System.out.println("course: " + course.getName() + " * title: " + title);
        int count = 1;
        for (Student i : scoreToStudent.keySet()) {
            System.out.println(count + "-Student: " + i.getFullName() + " * id:" + i.getStudent_id() + " * score: " + scoreToStudent.get(i));
            count++;
        }
    }

    void printTimeRemain() {
        long days = Duration.between(LocalDateTime.now(), deadline).toDays();
        long hours = Duration.between(LocalDateTime.now(), deadline).toHoursPart();
        long minutes = Duration.between(LocalDateTime.now(), deadline).toMinutesPart();
        long seconds = Duration.between(LocalDateTime.now(), deadline).toSecondsPart();
        System.out.println("Remaining Time: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    int getScore(Student student) {
        return scoreToStudent.get(student);
    }

    void addStudent(Student student) {
        students.add(student);
        scoreToStudent.put(student, 0);
    }

    void removeStudent(Student student) {
        students.remove(student);
        scoreToStudent.remove(student);
    }

    void initialize() {
        for (Student s : students) {
            scoreToStudent.put(s, 0);
        }
    }
}
