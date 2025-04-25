import javax.management.relation.Role;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Teacher t1 = new Teacher("mojtaba", "vahidi", "4321");
        Teacher t2 = new Teacher("reza", "mahdiani", "4321");

        Course c1 = new Course("AP", t1, 3, LocalDateTime.of(2024, 6, 10, 10, 0), 25);
        Course c2 = new Course("Madar", t2, 3, LocalDateTime.of(2024, 6, 14, 12, 0), 35);
        Course c3 = new Course("Zaban", t1, 2, LocalDateTime.of(2024, 6, 12, 14, 0), 15);
        Course c4 = new Course("Zaban", t1, 2, LocalDateTime.of(2024, 6, 12, 14, 0), 15);


        Student s1 = new Student("ali", "bakhtiari", LocalDate.of(2005, 4, 9), "1234");
        Student s2 = new Student("Akbar", "Akbari", LocalDate.of(2005, 4, 11), "1234");
        Student s3 = new Student("mahdi", "heydari", LocalDate.of(2004, 5, 16), "1234");
       


        Assignment hw1 = new Assignment("OOP", c1, LocalDateTime.of(2024, 4, 20, 23, 59), 2);
        Assignment hw2 = new Assignment("Regex", c1, LocalDateTime.of(2024, 4, 18, 23, 59), 3);
        Assignment midTerm_Ap = new Assignment("MidTermAp", c1, LocalDateTime.of(2024, 12, 12, 10, 0), 7);
        Assignment lastTerm_Ap = new Assignment("LastTermAp", c1, LocalDateTime.of(2024, 2, 5, 10, 0), 8);


        Assignment hw3 = new Assignment("Pmos", c2, LocalDateTime.of(2024, 4, 16, 23, 59), 1);
        Assignment hw4 = new Assignment("Cmos", c2, LocalDateTime.of(2024, 4, 20, 23, 59), 5);
        Assignment midTerm_madar = new Assignment("midTermMadar", c2, LocalDateTime.of(2024, 6, 12, 14, 0), 5);
        Assignment lastTerm_madar = new Assignment("lastTermMadar", c2, LocalDateTime.of(2024, 12, 15, 12, 0), 9);


        Assignment hw5 = new Assignment("Grammer", c3, LocalDateTime.of(2024, 4, 21, 12, 0), 2);
        Assignment hw6 = new Assignment("Vocabulary", c3, LocalDateTime.of(2024, 4, 13, 22, 0), 5);
        Assignment midTerm_zaban = new Assignment("midterm_zaban", c3, LocalDateTime.of(2024, 5, 12, 12, 0), 6);
        Assignment lastTeem_zaban = new Assignment("lastTerm_zaban", c3, LocalDateTime.of(2024, 11, 12, 15, 0), 10);




        s1.choose_course(c1);
        s1.choose_course(c2);
        s2.choose_course(c2);
        s2.choose_course(c3);
        s3.choose_course(c1);
        s3.choose_course(c2);
        s3.choose_course(c3);
        t1.addAssignment(hw1);
        t1.addAssignment(hw2);
        t1.addAssignment(midTerm_Ap);


        t1.addAssignment(lastTerm_Ap);
        t2.addAssignment(hw3);
        t2.addAssignment(hw4);
        t2.addAssignment(midTerm_madar);
        t2.addAssignment(lastTerm_madar);
        t2.addAssignment(hw5);
        t2.addAssignment(hw6);
        t2.addAssignment(midTerm_zaban);
        t2.addAssignment(lastTeem_zaban);


        t1.addMark(hw1, s1, 75);
        t1.addMark(midTerm_Ap, s1, 90);
        t1.addMark(hw2, s1, 80);
        t1.addMark(lastTerm_Ap, s1, 45);
        t2.addMark(hw4, s1, 80);
        t2.addMark(hw3, s1, 70);
        t2.addMark(midTerm_madar, s1, 60);
        t2.addMark(lastTerm_madar, s1, 80);


        t2.addMark(hw3, s2, 100);
        t2.addMark(hw4, s2, 50);
        t2.addMark(midTerm_madar, s2, 40);
        t2.addMark(lastTerm_madar, s2, 70);
        t2.addMark(hw5, s2, 50);
        t2.addMark(hw6, s2, 70);
        t2.addMark(midTerm_zaban, s2, 90);
        t2.addMark(lastTeem_zaban, s2, 80);


        t1.addMark(hw1, s3, 40);
        t1.addMark(hw2, s3, 40);
        t1.addMark(midTerm_Ap, s3, 65);
        t1.addMark(lastTerm_Ap, s3, 75);
        t2.addMark(hw3, s3, 50);
        t2.addMark(hw4, s3, 50);
        t2.addMark(midTerm_madar, s3, 50);
        t2.addMark(lastTerm_madar, s3, 50);
        t2.addMark(hw5, s3, 45);
        t2.addMark(hw6, s3, 80);
        t2.addMark(midTerm_zaban, s3, 90);
        t2.addMark(lastTeem_zaban, s3, 50);


        s1.printAverages();
        s1.printAssignmentsByCourse(c1);
        s1.printAssignmentsByDeadline();
        c1.printAssignments();
        c1.printStudentsTotalAverage();
        s1.delete_course(c1);
        c1.printStudentsTotalAverage();

        c1.removeAssignment(hw1);
        s1.printAssignmentsByCourse(c1);
        c1.printAssignments();
         t1.graphUp(c1);

        c1.printStudentsTotalAverage();


        s1.delete_course(c1);

        s1.printCourses();
        s1.printAverages();
        s1.printAssignmentsByCourse(c1);
        s1.printAssignmentsByDeadline();


        hw1.printScoresByAssignment();


    }

}
