import java.util.*;



public final class UniversityCourseManagementSystem {
    public static void main(String[] args) {
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

    public void error(String code) {
        switch (code){
            case "CE":
                System.out.println("Course exists");
            case "StdEn":
                System.out.println("Student is already enrolled in this course");
            case "MaxEnStd":
                System.out.println("Maximum enrollment is reached for the student");
            case "CF":
                System.out.println("Course is full");
            case "StdNotEn":
                System.out.println("Student is not enrolled in this course");
            case "PfLoad":
                System.out.println("Professor's load is complete");
            case "PfT":
                System.out.println("Professor is already teaching this course");
            case "PfNotT":
                System.out.println("Professor is not teaching this course");
            case "WI":
                System.out.println("Wrong inputs");
        }
        return;
    }
    public void checkCourse(Course course){
        if(!(course.getCourseLevel() == CourseLevel.BACHELOR || course.getCourseLevel() == CourseLevel.MASTER)){
            error("WI");
        }
        for (Course i: Course.getListOfCourses()) {
            if(i.getCourseName().equals(course.getCourseName())){
                error("CE");
            }
        }
    }
}
enum CourseLevel{
    BACHELOR,
    MASTER;
}
class Professor {
    private static int MAX_LOAD;
    private List<Course> assignedCourses;
//    public Professor(){
//        super(Course);
//    }
    public Professor(String memberName){}
    public boolean teach(Course course){}
    public boolean exempt(Course course){}

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}

class Course{
    private static int CAPACITY;
    private static int numberOfCourses;
    private int courseId;
    private String courseName;
    private List<Student> enrolledStudents;
    private CourseLevel courseLevel;
    private static List<Course> listOfCourses = new ArrayList<>();
    public Course (String courseName, CourseLevel courseLevel){
        this.courseName = courseName;
        listOfCourses.add(this);
    }
    public boolean isFull(){}

    public static List<Course> getListOfCourses() {
        return listOfCourses;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

class Student{
    private static int MAX_ENROLLMENT;
    private List<Course> enrolledCourses;
    public Student(String memberName){}
    public boolean drop(Course course){}
    public boolean enroll(Course course){}

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}

interface Enrollable{
    public boolean drop(Course course);
    public boolean enroll(Course course);
}

abstract class UniversityMember{
    private static int numberOfMembers;
    private int memberId;
    private String memberName;
    public UniversityMember(int memberId, String memberName){}

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}