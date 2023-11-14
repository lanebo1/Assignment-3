import java.util.*;

public final class UniversityCourseManagementSystem {
    public static void UniversityCourseManagementSystem(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (input.equals("course")) {
                String courseName = scanner.nextLine();
                String courseLevel = scanner.nextLine();
                courseName = courseName.toLowerCase();
                if(!(courseLevel.equals("bachelor") || courseLevel.equals("master"))){
                    System.out.println("ERR_CNAME");
                }
                CourseLevel cL = CourseLevel.valueOf(courseLevel);
                Course course = new Course(courseName, cL);

            } else if (input.equals("student")) {
                String memberName = scanner.nextLine();
                if(!memberName.matches("[a-zA-Z ]+")){
                    System.out.println("ERR_SNAME");
                }
                memberName = memberName.toLowerCase();
            }
            else if (input.equals("professor")) {
                String memberName = scanner.nextLine();
                if(!(input.matches("[a-zA-Z]+(_[a-zA-Z]+)*") && input.length() > 0)){
                    System.out.println("ERR_PNAME");
                }
                memberName = memberName.toLowerCase();
            }
            else if (input.equals("enroll")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
            }
            else if (input.equals("drop")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
            }
            else if (input.equals("teach")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
            }
            else if (input.equals("exempt")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
            }

        }
        System.out.println("Program execution stopped.");
    }
    static void fillInitialData(){

    }
}
enum CourseLevel{
    BACHELOR,
    MASTER;
}
class Professor {
    static int MAX_LOAD;
    List<Course> assignedCourses;
//    public Professor(){
//        super(Course);
//    }
    Professor(String memberName){}
    boolean teach(Course course){}
    boolean exempt(Course course){}
}

class Course{
    static int CAPACITY;
    static int numberOfCourses;
    int courseId;
    String courseName;
    List<Student> enrolledStudents;
    CourseLevel courseLevel;
    Course (String courseName, CourseLevel courseLevel){}
    boolean isFull(){}
}

class Student{
    static int MAX_ENROLLMENT;
    List<Course> enrolledCourses;
    Student(String memberName){}
    boolean drop(Course course){}
    boolean enroll(Course course){}
}

interface Enrollable{
    boolean drop(Course course);
    boolean enroll(Course course);
}

abstract class UniversityMember{
    static int numberOfMembers;
    int memberId;
    String memberName;
    UniversityMember(int memberId, String memberName){}
}