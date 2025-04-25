import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Teacher {
    private static int numOfTeachers = 0;
    private String name;
    private String family;
    private String id;
    private String password;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public Teacher(String name, String family,String password) {
        this.name = name;
        this.family = family;
        numOfTeachers++;
        this.id = createTeacherId();
    }

    String createTeacherId() {
        String id = "";
        for (int i = 0; i < 3 - String.valueOf(numOfTeachers).length(); i++) {
            id += "0";
        }
        return id + numOfTeachers;
    }

    public ArrayList<Student> getStudents() {

        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);


        assignment.getCourse().addAssignment(assignment);

        assignment.initialize();


        for (int i = 0; i < assignment.getCourse().getStudents().size(); i++) {
            assignment.getCourse().getStudents().get(i).addAssignments(assignment);
        }
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
        assignment.getCourse().removeAssignment(assignment);
        for (int i = 0; i < assignment.getCourse().getStudents().size(); i++) {
            assignment.getCourse().getStudents().get(i).removeAssignment(assignment);
        }
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addMark(Assignment assignment, Student student, int score) {
        assignment.addMark(student, score);
        student.updateAvgByCourse(assignment, score);
        //student.calculateAvgByCourse(assignment.getCourse());
    }

    public String getFullName() {
        return name + " " + family;
    }

    public void setDeadline(Assignment assignment, LocalDateTime localDateTime) {
        assignment.setDeadline(localDateTime);
    }

    public void printCourses() {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + 1 + "-name: " + courses.get(i).getName() + " * capacity: " + courses.get(i).getCapacity() + " * exam date: " + courses.get(i).getExam_date().format(myFormat));
        }
    }

    public boolean graphUp(Course course) {
        if (courses.contains(course)) {
            course.sortStudentByAverage();
            double max = course.getStudents().getFirst().totalAverageToCourse.get(course);
            course.addScore(20 - max);
            return true;
        } else {
            return false;
        }
    }
}
