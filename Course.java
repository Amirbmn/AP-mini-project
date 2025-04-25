import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Course {
    private int numOfCourses = 0;
    private String name;
    private Teacher teacher;
    private int unit;
    private String id;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private boolean is_active;
    private LocalDateTime exam_date;
    private int capacity;
    private double studentsAverage;


    public Course(String name, Teacher teacher, int unit, LocalDateTime date, int capacity) {
        this.name = name;
        this.teacher = teacher;
        this.unit = unit;
        exam_date = date;
        is_active = true;
        this.capacity = capacity;
        teacher.addCourse(this);
        numOfCourses++;
        this.id = createCourseId();
    }

    String createCourseId() {
        String id = "";
        for (int i = 0; i < 3 - String.valueOf(numOfCourses).length(); i++) {
            id += "0";
        }
        return id + numOfCourses;
    }

    public int getCapacity() {
        return capacity;
    }


    public void addStudents(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUnit() {
        return unit;
    }

    public LocalDateTime getExam_date() {
        return exam_date;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    int getScore(Assignment assignment, Student student) {
        return assignment.getScore(student);
    }

    public void printStudentsTotalAverage() {
        calculateTotalAvg();
        sortStudentByAverage();

        System.out.println("course: " + this.name);
        int count = 1;
        for (Student s : students) {
            System.out.printf("%d%s%s%s%s%s%.2f\n", count, "-student: ", s.getFullName(), " * id: ", s.getStudent_id(),
                    " * score: ", s.totalAverageToCourse.get(this));
            count++;
        }
        System.out.printf("%s%.2f\n", "average: ", studentsAverage);
    }

    double getScoreByCourse(Student student) {
        return student.getScoreByCourse(this);
    }

    void printAssignments() {
        sortAssignmentsByDeadline();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd  HH:mm");
        System.out.println("course: " + this.getName());
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + "-title: " + assignments.get(i).getTitle() + " * deadline:" + assignments.get(i).getDeadline().format(myFormat));
        }
    }

    void sortStudentByAverage() {
        Comparator<Student> comp = Comparator.comparing(s -> s.totalAverageToCourse.get(this));
        students.sort(comp.reversed());
    }

    void sortAssignmentsByDeadline() {
        Comparator<Assignment> comparator = Comparator.comparing(Assignment::getDeadline);
        assignments.sort(comparator);
    }

    void calculateTotalAvg() {
        double sum = 0;
        for (Student s : students) {
            sum += s.totalAverageToCourse.get(this);
        }
        studentsAverage = sum / students.size();
    }


    void addScore(double score) {
        for (Student s : students) {
            double newAvg = s.totalAverageToCourse.get(this) + score;
            s.totalAverageToCourse.put(this, newAvg);
        }
    }
}
