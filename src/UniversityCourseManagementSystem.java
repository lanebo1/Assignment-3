import java.util.*;

public final class UniversityCourseManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fillInitialData();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (input.equals("course")) {
                String courseName = scanner.nextLine();
                String courseLevel = scanner.nextLine();
                CourseLevel cL = CourseLevel.valueOf(courseLevel);
                if(!(courseName.matches("[a-zA-Z]+(_[a-zA-Z]+)*") && !courseName.isEmpty())){
                    error("WI");
                    return;
                }
                if(!(cL == CourseLevel.BACHELOR || cL == CourseLevel.MASTER)){
                    error("WI");
                    return;
                }
                for (Course i: Course.getListOfCourses()) {
                    if(i.getCourseName().equals(courseName)){
                        error("CE");
                        return;
                    }
                }
                courseName = courseName.toLowerCase();
                Course course = new Course(courseName, cL);
            } else if (input.equals("student")) {
                String memberName = scanner.nextLine();
                memberName = memberName.toLowerCase();
                if(!memberName.matches("[a-zA-Z ]+")){
                    error("WI");
                    return;
                }
                Student student = new Student(memberName);
                success(input);
            }
            else if (input.equals("professor")) {
                String memberName = scanner.nextLine();
                memberName = memberName.toLowerCase();
                if(!memberName.matches("[a-zA-Z ]+")){
                    error("WI");
                    return;
                }
                Professor professor = new Professor(memberName);
                success(input);
            }
            else if (input.equals("enroll")) {
                boolean PASS = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                for(Course c : Course.getListOfCourses()){
                    if(c.getCourseId().equals(courseId)) {
                        for (Student s : c.getEnrolledStudents()) {
                            if (s.getMemberId().equals(memberId)) {
                                s.enroll(c);
                                success(input);
                                PASS = true;
                            }
                        }
                    }
                }
            }
            else if (input.equals("drop")) {
                boolean PASS = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                for(Course c : Course.getListOfCourses()){
                    if(c.getCourseId().equals(courseId)) {
                        for (Student s : c.getEnrolledStudents()) {
                            if (s.getMemberId().equals(memberId)) {
                                s.drop(c);
                                success(input);
                                PASS = true;
                            }
                        }
                    }
                }
            }
            else if (input.equals("teach")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();

                success(input);
            }
            else if (input.equals("exempt")) {
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();

                success(input);
            }

        }
    }
    static void fillInitialData(){
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");
        Student s3 = new Student("Alex");
        Professor p1 = new Professor("Ali");
        Professor p2 = new Professor("Ahmed");
        Professor p3 = new Professor("Andrey");
        Course java_beginner = new Course("java_beginner", CourseLevel.BACHELOR);
        Course java_intermediate = new Course("java_beginner", CourseLevel.BACHELOR);
        Course python_basics = new Course("java_beginner", CourseLevel.BACHELOR);
        Course algorithms = new Course("java_beginner", CourseLevel.MASTER);
        Course advanced_programming = new Course("java_beginner", CourseLevel.MASTER);
        Course mathematical_analysis = new Course("java_beginner", CourseLevel.MASTER);
        Course computer_vision = new Course("java_beginner", CourseLevel.MASTER);
        s1.enroll(java_beginner);
        s1.enroll(java_intermediate);
        s1.enroll(python_basics);
        s2.enroll(java_beginner);
        s2.enroll(algorithms);
        s3.enroll(advanced_programming);
        p1.teach(java_beginner);
        p1.teach(java_intermediate);
        p2.teach(python_basics);
        p2.teach(advanced_programming);
        p3.teach(mathematical_analysis);
    }

    public static void error(String code) {
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
    }
    public static void success(String code) {
        switch (code){
            case "course":
                System.out.println("Added successfully");
            case "student":
                System.out.println("Added successfully");
            case "professor":
                System.out.println("Added successfully");
            case "enroll":
                System.out.println("Enrolled successfully");
            case "drop":
                System.out.println("Dropped successfully");
            case "exempt":
                System.out.println("Professor is exempted");
            case "teach":
                System.out.println("Professor is successfully assigned to teach this course");
        }
    }
}
enum CourseLevel{
    BACHELOR,
    MASTER;
}
class Professor extends UniversityMember{
    private static int MAX_LOAD = 2;
    private List<Course> assignedCourses = new ArrayList<>();

    public Professor(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    public boolean teach(Course course){
        if(this.assignedCourses.contains(course)){
            UniversityCourseManagementSystem.error("PfT");
            return false;
        }
        if(this.assignedCourses.size() < 2) {
            UniversityCourseManagementSystem.error("PfLoad");
            return false;
        }
        this.assignedCourses.add(course);
        return true;
    }
    public boolean exempt(Course course){
        if (this.assignedCourses.contains(course)) {
            this.assignedCourses.remove(course);
            return true;
        }
        UniversityCourseManagementSystem.error("PfNotT");
        return false;
    }

    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
}

class Course{
    private static final int CAPACITY = 3;
    private static int numberOfCourses = 0;
    private int courseId;
    private String courseName;
    private final List<Student> enrolledStudents;
    private final CourseLevel courseLevel;
    private static List<Course> listOfCourses;
    public Course (String courseName, CourseLevel courseLevel){
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        numberOfCourses += 1;
        this.courseId = numberOfCourses;
        enrolledStudents = new ArrayList<>();
        listOfCourses = new ArrayList<>();
        listOfCourses.add(this);
    }
    public boolean isFull(){
        if(numberOfCourses == CAPACITY) {
            return true;
        }
        else{
            UniversityCourseManagementSystem.error("CF");
            return false;
        }
    }

    public static List<Course> getListOfCourses() {
        return listOfCourses;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return Integer.toString(courseId);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }
}

class Student extends UniversityMember{
    private static final int MAX_ENROLLMENT = 3;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    public boolean drop(Course course){
        if (this.enrolledCourses.contains(course)) {
            this.enrolledCourses.remove(course);
            return true;
        }
        UniversityCourseManagementSystem.error("StdNotEn");
        return false;
    }
    public boolean enroll(Course course){
        if(this.enrolledCourses.contains(course)){
            UniversityCourseManagementSystem.error("StdEn");
            return false;
        }
        if(!(this.enrolledCourses.size() < MAX_ENROLLMENT)){
            UniversityCourseManagementSystem.error("MaxEnStd");
            return false;
        }
        if(!(course.getEnrolledStudents().size() < course.getCAPACITY())){
            UniversityCourseManagementSystem.error("CF");
            return false;
        }
        this.enrolledCourses.add(course);
        return true;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}

interface Enrollable{
    public boolean drop(Course course);
    public boolean enroll(Course course);
}

abstract class UniversityMember{
    private static int numberOfMembers = 0;
    private int memberId;
    private String memberName;
    public UniversityMember(int memberId, String memberName){}

    public String getMemberId() {
        return Integer.toString(memberId);

    }
    public String getMemberName() {
        return memberName;
    }
    public static void addNumberOfMembers(){
        numberOfMembers += 1;
    }
    public static int getNumberOfMembers() {
        addNumberOfMembers();
        return numberOfMembers;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}