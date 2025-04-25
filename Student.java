import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Student {
    private static int numOfStudents = 0;
    private String name;
    private String family;
    private LocalDate birthDate;
    private int age;
    private String id;
    private String password;
    private int entering_year = LocalDateTime.now().getYear();
    private ArrayList<Course> courses = new ArrayList<>();
    private int units = 0;
    HashMap<Course, Double> totalAverageToCourse = new HashMap<>();
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private double total_avg;

    Student(String name, String family, LocalDate birthDate,String password) {
        this.name = name;
        this.family = family;
        this.birthDate = birthDate;
        numOfStudents++;
        this.id = createStudentId();

    }

    String createStudentId() {
        String id = "";
        for (int i = 0; i < 3 - String.valueOf(numOfStudents).length(); i++) {
            id += "0";
        }
        return entering_year + id + numOfStudents;
    }


    public String choose_course(Course course) {

        if (course.getCapacity() > course.getStudents().size()) {
            course.addStudents(this);
            course.getTeacher().addStudent(this);
            courses.add(course);
            totalAverageToCourse.put(course, 0.0);
            units += course.getUnit();
            course.setCapacity(course.getCapacity() - 1);
            assignments.addAll(course.getAssignments());
            for (Assignment a : course.getAssignments()) {
                a.addStudent(this);
            }
            calculateTotalAverage();
            return "choose success";
        } else {
            return "choose course failed";
        }
    }

    public String delete_course(Course course) {
        if (this.courses.contains(course)) {
            courses.remove(course);
            totalAverageToCourse.remove(course);
            course.removeStudent(this);
            course.getTeacher().removeStudent(this);
            course.setCapacity(course.getCapacity() + 1);
            units -= course.getUnit();
            for (Assignment a : course.getAssignments()) {
                a.removeStudent(this);
            }
            for (int i = 0; i < assignments.size(); i++) {

                if (assignments.get(i).getCourse() == course) {
                    assignments.remove(i);
                    i--;
                }
            }
            calculateTotalAverage();
            return "delete course success";
        } else {
            return "this student doesnt have this course";
        }
    }

    public String getFullName() {
        return name + " " + family;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addAssignments(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public int getUnits() {
        return units;
    }

    public int getAge() {
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        return age;
    }

    public void printAssignmentsByCourse(Course course) {
        System.out.println("name: " + getFullName() + " * id:" + id);
        System.out.println("course: " + course.getName());
        if (this.courses.contains(course)) {
            int size = course.getAssignments().size();
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + "-title: " + course.getAssignments().get(i).getTitle() + "  score:" + scoreByAssignment(course.getAssignments().get(i)) + "  " + scoreByAssignment(course.getAssignments().get(i)) * course.getAssignments().get(i).getPointOfTotal() / 100);
            }
            System.out.printf("%s%.2f\n", "total: ", totalAverageToCourse.get(course));
        } else {
            System.out.println("Student Doesn't choose this course yet");
        }
    }

    int scoreByAssignment(Assignment assignment) {
        return assignment.getScore(this);
    }

    public void printAverages() {
        System.out.println("name: " + getFullName() + " * id: " + id);
        int count = 1;
        for (Course c : totalAverageToCourse.keySet()) {
            System.out.printf("%d%s%s%s%.2f\n", count, "-course: ", c.getName(), " * score: ", totalAverageToCourse.get(c));
            count++;
        }
        System.out.printf("%s%.2f\n", "total: ", total_avg);
    }

    void printAssignmentsByDeadline() {

        sortAssignmentsByDeadline();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd  HH:mm");
        System.out.println("name: " + getFullName() + " * id:" + id);
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + "-course: " + assignments.get(i).getCourse().getName() + " ** title: " + assignments.get(i).getTitle() + " ** deadline: " + assignments.get(i).getDeadline().format(myFormat));
        }
    }

    void printCourses() {
        System.out.println("name: " + getFullName() + " * id:" + id);
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + 1 + "-name: " + courses.get(i).getName() + " *  Teacher: " + courses.get(i).getTeacher().getFullName() + " * unit: " + courses.get(i).getUnit() + " *  Exam date: " + courses.get(i).getExam_date().format(myFormat));
        }
    }

    public String getStudent_id() {
        return id;
    }

    void updateAvgByCourse(Assignment assignment, int score) {
        Course course = assignment.getCourse();
        double avg = 0;
        if (totalAverageToCourse.get(course) != null) {
            avg = totalAverageToCourse.get(course);
        }
        avg += score * assignment.getPointOfTotal() / 100;
        totalAverageToCourse.put(course, avg);
        calculateTotalAverage();
    }

    void calculateTotalAverage() {
        total_avg = 0;
        for (Course c : totalAverageToCourse.keySet()) {
            total_avg += totalAverageToCourse.get(c) * c.getUnit();
        }
        total_avg /= units;

    }

    void sortAssignmentsByDeadline() {
        Comparator<Assignment> comparator = Comparator.comparing(Assignment::getDeadline);
        assignments.sort(comparator);
    }

    double getScoreByCourse(Course course) {
        return totalAverageToCourse.get(course);
    }
}
