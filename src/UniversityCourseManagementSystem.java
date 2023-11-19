import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public final class UniversityCourseManagementSystem{
    private static ArrayList<Professor> professors = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fillInitialData();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            if (input.equals("course")) {
                String courseName = scanner.nextLine().toLowerCase();
                String courseLevel = scanner.nextLine();
                CourseLevel cL = CourseLevel.valueOf(courseLevel.toUpperCase());
                if (!(courseName.matches("[a-zA-Z]+(_[a-zA-Z]+)*") && !courseName.isEmpty())) {
                    error("WI");
                    return;
                }
                if (!(cL == CourseLevel.BACHELOR || cL == CourseLevel.MASTER)) {
                    error("WI");
                    return;
                }
                for (Course i : courses) {
                    if (i.getCourseName().equals(courseName)) {
                        error("CE");
                        return;
                    }
                }
                Course course = new Course(courseName, cL);
                courses.add(course);
                success(input);
            } else if (input.equals("student")) {
                String memberName = scanner.nextLine().toLowerCase();
                if(inputNameCheck(memberName)) {
                    Student student = new Student(memberName);
                    students.add(student);
                    success(input);
                }
                else {
                    return;
                }
            } else if (input.equals("professor")) {
                String memberName = scanner.nextLine().toLowerCase();
                if(inputNameCheck(memberName)) {
                    Professor professor = new Professor(memberName);
                    professors.add(professor);
                    success(input);
                }
                else {
                    return;
                }
            } else if (input.equals("enroll")) {
                boolean pass = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                if(inputMemberCheck(memberId, courseId)) {
                    for (Course c : courses) {
                        if (c.getCourseId().equals(courseId)) {
                            for (Student s : students) {
                                if (s.getMemberId().equals(memberId) && s.enroll(c)) {
                                    success(input);
                                    pass = true;
                                    break;
                                }
                            }
                        }
                        if(pass) break;
                    }
                    if(!pass){
                        error("WI");
                        return;
                    }
                } else {
                    return;
                }
            } else if (input.equals("drop")) {
                boolean pass = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                if(inputMemberCheck(memberId, courseId)) {
                    for (Course c : courses) {
                        if (c.getCourseId().equals(courseId)) {
                            for (Student s : students) {
                                if (s.getMemberId().equals(memberId) && s.drop(c)) {
                                    success(input);
                                    pass = true;
                                    break;
                                }
                            }
                        }
                        if(pass) break;
                    }
                    if(!pass){
                        error("WI");
                        return;
                    }
                } else {
                    return;
                }
            } else if (input.equals("teach")) {
                boolean pass = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                if(inputMemberCheck(memberId, courseId)) {
                    for (Professor p : professors) {
                        if (p.getMemberId().equals(memberId)) {
                            for (Course c : courses) {
                                if (c.getCourseId().equals(courseId) && p.teach(c)) {
                                    success(input);
                                    pass = true;
                                    break;
                                }
                            }
                        }
                        if(pass) break;
                    }
                    if(!pass){
                        error("WI");
                        return;
                    }
                } else {
                    return;
                }
            } else if (input.equals("exempt")) {
                boolean pass = false;
                String memberId = scanner.nextLine();
                String courseId = scanner.nextLine();
                if(inputMemberCheck(memberId, courseId)) {
                    for (Professor p : professors) {
                        if (p.getMemberId().equals(memberId)) {
                            for (Course c : courses) {
                                if (c.getCourseId().equals(courseId) && p.exempt(c)) {
                                    success(input);
                                    pass = true;
                                    break;
                                }
                            }
                        }
                        if (pass) break;
                    }
                    if(!pass){
                        error("WI");
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
    public static boolean inputMemberCheck(String memberId, String courseId){
        if (!(memberId.matches("^\\d+$") && !memberId.isEmpty())) {
            error("WI");
            return false;
        }
        else if (!(courseId.matches("^\\d+$") && !courseId.isEmpty())) {
            error("WI");
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean inputNameCheck(String memberName){
        memberName = memberName.toLowerCase();
        if (!memberName.matches("[a-zA-Z ]+")) {
            error("WI");
            return false;
        }
        else if (memberName.equals("course") || memberName.equals("student") || memberName.equals("professor")){
            error("WI");
            return false;
        }
        else if (memberName.equals("enroll") || memberName.equals("drop") || memberName.equals("teach")){
            error("WI");
            return false;
        }
        else if (memberName.equals("exempt")){
            error("WI");
            return false;
        }
        else {
            return true;
        }
    }
    public static void fillInitialData() {
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");
        Student s3 = new Student("Alex");
        Professor p1 = new Professor("Ali");
        Professor p2 = new Professor("Ahmed");
        Professor p3 = new Professor("Andrey");
        students.add(s1);
        students.add(s2);
        students.add(s3);
        professors.add(p1);
        professors.add(p2);
        professors.add(p3);
        Course javaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        Course javaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        Course pythonBasics = new Course("python_basics", CourseLevel.BACHELOR);
        Course algorithms = new Course("algorithms", CourseLevel.MASTER);
        Course advancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        Course mathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        Course computerVision = new Course("computer_vision", CourseLevel.MASTER);
        courses.add(javaBeginner);
        courses.add(javaIntermediate);
        courses.add(pythonBasics);
        courses.add(algorithms);
        courses.add(advancedProgramming);
        courses.add(mathematicalAnalysis);
        courses.add(computerVision);
        s1.enroll(javaBeginner);
        s1.enroll(javaIntermediate);
        s1.enroll(pythonBasics);
        s2.enroll(javaBeginner);
        s2.enroll(algorithms);
        s3.enroll(advancedProgramming);
        p1.teach(javaBeginner);
        p1.teach(javaIntermediate);
        p2.teach(pythonBasics);
        p2.teach(advancedProgramming);
        p3.teach(mathematicalAnalysis);
    }

    public static void error(String code) {
        switch (code) {
            case "CE":
                System.out.println("Course exists");
                break;
            case "StdEn":
                System.out.println("Student is already enrolled in this course");
                break;
            case "MaxEnStd":
                System.out.println("Maximum enrollment is reached for the student");
                break;
            case "CF":
                System.out.println("Course is full");
                break;
            case "StdNotEn":
                System.out.println("Student is not enrolled in this course");
                break;
            case "PfLoad":
                System.out.println("Professor's load is complete");
                break;
            case "PfT":
                System.out.println("Professor is already teaching this course");
                break;
            case "PfNotT":
                System.out.println("Professor is not teaching this course");
                break;
            case "WI":
                System.out.println("Wrong inputs");
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    public static void success(String code) {
        switch (code) {
            case "course":
            case "student":
            case "professor":
                System.out.println("Added successfully");
                break;
            case "enroll":
                System.out.println("Enrolled successfully");
                break;
            case "drop":
                System.out.println("Dropped successfully");
                break;
            case "exempt":
                System.out.println("Professor is exempted");
                break;
            case "teach":
                System.out.println("Professor is successfully assigned to teach this course");
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }
}
enum CourseLevel {
    BACHELOR,
    MASTER;
}

class Professor extends UniversityMember {
    private static final int MAX_LOAD = 2;
    private List<Course> assignedCourses = new ArrayList<>();

    public Professor(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    public boolean teach(Course course) {
        if (this.assignedCourses.contains(course)) {
            UniversityCourseManagementSystem.error("PfT");
            return false;
        }
        if (this.assignedCourses.size() > MAX_LOAD - 1) {
            UniversityCourseManagementSystem.error("PfLoad");
            return false;
        }
        this.assignedCourses.add(course);
        return true;
    }

    public boolean exempt(Course course) {
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

class Course {
    private static final int CAPACITY = 3;
    private static int numberOfCourses = 0;
    private int courseId;
    private String courseName;
    private final List<Student> enrolledStudents;
    private final CourseLevel courseLevel;
    private static List<Course> listOfCourses;

    Course(String courseName, CourseLevel courseLevel) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        numberOfCourses += 1;
        this.courseId = numberOfCourses;
        enrolledStudents = new ArrayList<>();
        listOfCourses = new ArrayList<>();
        listOfCourses.add(this);
    }

    public boolean isFull() {
        if (numberOfCourses == CAPACITY) {
            return true;
        } else {
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

class Student extends UniversityMember implements Enrollable {
    private static final int MAX_ENROLLMENT = 3;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    public boolean drop(Course course) {
        if (this.enrolledCourses.contains(course)) {
            this.enrolledCourses.remove(course);
            return true;
        }
        UniversityCourseManagementSystem.error("StdNotEn");
        return false;
    }

    public boolean enroll(Course course) {
        if (this.enrolledCourses.contains(course)) {
            UniversityCourseManagementSystem.error("StdEn");
            return false;
        }
        if (!(this.enrolledCourses.size() < MAX_ENROLLMENT)) {
            UniversityCourseManagementSystem.error("MaxEnStd");
            return false;
        }
        if (!(course.getEnrolledStudents().size() < course.getCAPACITY())) {
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

interface Enrollable {
    public boolean drop(Course course);

    public boolean enroll(Course course);
}

class UniversityMember {
    private static int numberOfMembers = 0;
    private int memberId;
    private String memberName;

    private List<UniversityMember> universityMembers;

    public UniversityMember(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getMemberId() {
        return Integer.toString(memberId);

    }

    public String getMemberName() {
        return memberName;
    }

    public static void addNumberOfMembers() {
        numberOfMembers += 1;
    }

    public static int getNumberOfMembers() {
        addNumberOfMembers();
        return numberOfMembers;
    }
}
