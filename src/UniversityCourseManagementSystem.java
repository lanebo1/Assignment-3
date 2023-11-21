import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


/**
 * The University course management system.
 */
public final class UniversityCourseManagementSystem {
    /**
     * The constant PROFESSORS.
     */
    private static final ArrayList<Professor> PROFESSORS = new ArrayList<>();
    /**
     * The constant STUDENTS.
     */
    private static final ArrayList<Student> STUDENTS = new ArrayList<>();
    /**
     * The constant COURSES.
     */
    private static final ArrayList<Course> COURSES = new ArrayList<>();


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        fillInitialData();
        try {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    break;
                }
                if (input.equals("course")) {
                    String courseName = scanner.nextLine().toLowerCase();
                    for (Course i : COURSES) {
                        if (i.getCourseName().equals(courseName)) {
                            error("CE");
                        }
                    }
                    String courseLevel = scanner.nextLine();
                    CourseLevel cL = CourseLevel.valueOf(courseLevel.toUpperCase());
                    if (!(cL == CourseLevel.BACHELOR || cL == CourseLevel.MASTER)) {
                        error("WI");
                    }
                    if (inputNameCheck(courseName, input)) {
                        Course course = new Course(courseName, cL);
                        COURSES.add(course);
                        success(input);
                    }
                } else if (input.equals("student")) {
                    String memberName = scanner.nextLine().toLowerCase();
                    if (inputNameCheck(memberName, input)) {
                        Student student = new Student(memberName);
                        STUDENTS.add(student);
                        success(input);
                    }
                } else if (input.equals("professor")) {
                    String memberName = scanner.nextLine().toLowerCase();
                    if (inputNameCheck(memberName, input)) {
                        Professor professor = new Professor(memberName);
                        PROFESSORS.add(professor);
                        success(input);
                    }
                } else if (input.equals("enroll")) {
                    boolean pass = false;
                    String memId = scanner.nextLine();
                    String coId = scanner.nextLine();
                    if (inputIdCheck(memId, coId) && checkStudent(memId) && checkCourse(coId)) {
                        int memberId = Integer.parseInt(memId);
                        int courseId = Integer.parseInt(coId);
                        for (Course c : COURSES) {
                            if (c.getCourseId() == courseId) {
                                for (Student s : STUDENTS) {
                                    if ((s.getMemberId() == memberId) && s.enroll(c)) {
                                        success(input);
                                        pass = true;
                                        break;
                                    }
                                }
                            }
                            if (pass) {
                                break;
                            }
                        }
                    } else {
                        error("WI");
                    }
                } else if (input.equals("drop")) {
                    boolean pass = false;
                    String memId = scanner.nextLine();
                    String coId = scanner.nextLine();
                    if (inputIdCheck(memId, coId) && checkStudent(memId) && checkCourse(coId)) {
                        int memberId = Integer.parseInt(memId);
                        int courseId = Integer.parseInt(coId);
                        for (Course c : COURSES) {
                            if (c.getCourseId() == courseId) {
                                for (Student s : STUDENTS) {
                                    if ((s.getMemberId() == memberId) && s.drop(c)) {
                                        success(input);
                                        pass = true;
                                        break;
                                    }
                                }
                            }
                            if (pass) {
                                break;
                            }
                        }
                    } else {
                        error("WI");
                    }
                } else if (input.equals("teach")) {
                    boolean pass = false;
                    String memId = scanner.nextLine();
                    String coId = scanner.nextLine();
                    if (inputIdCheck(memId, coId) && checkProfessor(memId) && checkCourse(coId)) {
                        int memberId = Integer.parseInt(memId);
                        int courseId = Integer.parseInt(coId);
                        for (Professor p : PROFESSORS) {
                            if (p.getMemberId() == memberId) {
                                for (Course c : COURSES) {
                                    if ((c.getCourseId() == courseId) && p.teach(c)) {
                                        success(input);
                                        pass = true;
                                        break;
                                    }
                                }
                            }
                            if (pass) {
                                break;
                            }
                        }
                    } else {
                        error("WI");
                    }
                } else if (input.equals("exempt")) {
                    boolean pass = false;
                    String memId = scanner.nextLine();
                    String coId = scanner.nextLine();
                    if (inputIdCheck(memId, coId) && checkProfessor(memId) && checkCourse(coId)) {
                        int memberId = Integer.parseInt(memId);
                        int courseId = Integer.parseInt(coId);
                        for (Professor p : PROFESSORS) {
                            if (p.getMemberId() == memberId) {
                                for (Course c : COURSES) {
                                    if ((c.getCourseId() == courseId) && p.exempt(c)) {
                                        success(input);
                                        pass = true;
                                        break;
                                    }
                                }
                            }
                            if (pass) {
                                break;
                            }
                        }
                    } else {
                        error("WI");
                    }
                } else {
                    error("WI");
                }
            }
        } catch (Exception e) {
            error("WI");
        }
    }

    /**
     * Check student id boolean.
     *
     * @param id the id of student
     * @return boolean/error
     */
    public static boolean checkStudent(String id) {
        int memberId = Integer.parseInt(id);
        for (Student s : STUDENTS) {
            if (s.getMemberId() == memberId) {
                return true;
            }
        }
        error("WI");
        return false;
    }

    /**
     * Check course id boolean.
     *
     * @param id the id of course
     * @return boolean/error
     */
    public static boolean checkCourse(String id) {
        int courseId = Integer.parseInt(id);
        for (Course c : COURSES) {
            if (c.getCourseId() == courseId) {
                return true;
            }
        }
        error("WI");
        return false;
    }

    /**
     * Check professor id boolean.
     *
     * @param id the id of professor
     * @return boolean/error
     */
    public static boolean checkProfessor(String id) {
        int memberId = Integer.parseInt(id);
        for (Professor p : PROFESSORS) {
            if (p.getMemberId() == memberId) {
                return true;
            }
        }
        error("WI");
        return false;
    }

    /**
     * Input id check boolean.
     *
     * @param memberId the member id
     * @param courseId the course id
     * @return boolean/error
     */
    public static boolean inputIdCheck(String memberId, String courseId) {
        int flag = 0;
        try {
            Integer.parseInt(memberId);
            flag += 1;
        } catch (NumberFormatException e) {
            error("WI");
        }
        try {
            Integer.parseInt(courseId);
            flag += 1;
        } catch (NumberFormatException e) {
            error("WI");
        }
        return flag == 2;
    }

    /**
     * Input name check boolean.
     *
     * @param memberName the member name
     * @param code       the code of command
     * @return boolean/error
     */
    public static boolean inputNameCheck(String memberName, String code) {
        memberName = memberName.toLowerCase();
        if (!memberName.matches("[a-zA-Z ]+") && !code.equals("course")) {
            error("WI");
            return false;
        } else if (!(memberName.matches("^[A-Za-z]+(_[A-Za-z]+)*$")) && code.equals("course")) {
            error("WI");
            return false;
        }
        if (memberName.equals("course") || memberName.equals("student") || memberName.equals("professor")) {
            error("WI");
            return false;
        } else if (memberName.equals("enroll") || memberName.equals("drop") || memberName.equals("teach")) {
            error("WI");
            return false;
        } else if (memberName.equals("exempt")) {
            error("WI");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Fill initial data.
     */
    public static void fillInitialData() {
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");
        Student s3 = new Student("Alex");
        Professor p1 = new Professor("Ali");
        Professor p2 = new Professor("Ahmed");
        Professor p3 = new Professor("Andrey");
        STUDENTS.add(s1);
        STUDENTS.add(s2);
        STUDENTS.add(s3);
        PROFESSORS.add(p1);
        PROFESSORS.add(p2);
        PROFESSORS.add(p3);
        Course javaBeginner = new Course("java_beginner", CourseLevel.BACHELOR);
        Course javaIntermediate = new Course("java_intermediate", CourseLevel.BACHELOR);
        Course pythonBasics = new Course("python_basics", CourseLevel.BACHELOR);
        Course algorithms = new Course("algorithms", CourseLevel.MASTER);
        Course advancedProgramming = new Course("advanced_programming", CourseLevel.MASTER);
        Course mathematicalAnalysis = new Course("mathematical_analysis", CourseLevel.MASTER);
        Course computerVision = new Course("computer_vision", CourseLevel.MASTER);
        COURSES.add(javaBeginner);
        COURSES.add(javaIntermediate);
        COURSES.add(pythonBasics);
        COURSES.add(algorithms);
        COURSES.add(advancedProgramming);
        COURSES.add(mathematicalAnalysis);
        COURSES.add(computerVision);
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

    /**
     * Error.
     *
     * @param code the code of the error
     */
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
        System.exit(0);
    }

    /**
     * Success.
     *
     * @param code the code of success
     */
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

/**
 * The enum Course level.
 */
enum CourseLevel {
    /**
     * Bachelor course level.
     */
    BACHELOR,
    /**
     * Master course level.
     */
    MASTER;
}

/**
 * The type Professor extends UniversityMember.
 */
class Professor extends UniversityMember {
    /**
     * The constant MAX_LOAD.
     */
    private static final int MAX_LOAD = 2;
    /**
     * The Assigned courses.
     */
    private List<Course> assignedCourses = new ArrayList<>();

    /**
     * Instantiates a new Professor.
     *
     * @param memberName the member name
     */
    public Professor(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    /**
     * Teach boolean.
     *
     * @param course the course
     * @return boolean/error
     */
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

    /**
     * Exempt boolean.
     *
     * @param course the course
     * @return boolean/error
     */
    public boolean exempt(Course course) {
        if (this.assignedCourses.contains(course)) {
            this.assignedCourses.remove(course);
            return true;
        }
        UniversityCourseManagementSystem.error("PfNotT");
        return false;
    }
}

/**
 * The type Course.
 */
class Course {
    /**
     * The constant CAPACITY.
     */
    private static final int CAPACITY = 3;
    /**
     * The constant numberOfCourses.
     */
    private static int numberOfCourses = 0;
    /**
     * The Course id.
     */
    private int courseId;
    /**
     * The Course name.
     */
    private String courseName;
    /**
     * The Enrolled students.
     */
    private final List<Student> enrolledStudents;
    /**
     * The Course level.
     */
    private final CourseLevel courseLevel;
    /**
     * The List of courses.
     */
    private static List<Course> listOfCourses;

    /**
     * Instantiates a new Course.
     *
     * @param courseName  the course name
     * @param courseLevel the course level
     */
    Course(String courseName, CourseLevel courseLevel) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        numberOfCourses += 1;
        this.courseId = numberOfCourses;
        enrolledStudents = new ArrayList<>();
        listOfCourses = new ArrayList<>();
        listOfCourses.add(this);
    }

    /**
     * Is full boolean.
     *
     * @return boolean
     */
    public boolean isFull() {
        return numberOfCourses >= CAPACITY;
    }

    /**
     * Gets course name.
     *
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets course id.
     *
     * @return course id
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Gets enrolled students.
     *
     * @return enrolled students
     */
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Gets capacity.
     *
     * @return capacity
     */
    public int getCAPACITY() {
        return CAPACITY;
    }
}

/**
 * The type Student.
 */
class Student extends UniversityMember implements Enrollable {
    /**
     * The constant MAX_ENROLLMENT.
     */
    private static final int MAX_ENROLLMENT = 3;
    /**
     * The Enrolled courses.
     */
    private List<Course> enrolledCourses = new ArrayList<>();

    /**
     * Instantiates a new Student.
     *
     * @param memberName the member name
     */
    public Student(String memberName) {
        super(UniversityMember.getNumberOfMembers(), memberName);
    }

    /**
     * Drop boolean.
     *
     * @param course the course
     * @return boolean/error
     */
    @Override
    public boolean drop(Course course) {
        if (this.enrolledCourses.contains(course)) {
            this.enrolledCourses.remove(course);
            return true;
        }
        UniversityCourseManagementSystem.error("StdNotEn");
        return false;
    }

    /**
     * Enroll boolean.
     *
     * @param course the course
     * @return boolean/error
     */
    @Override
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
}

/**
 * The interface Enrollable.
 */
interface Enrollable {
    /**
     * Drop boolean.
     *
     * @param course the course
     * @return boolean
     */
    public boolean drop(Course course);

    /**
     * Enroll boolean.
     *
     * @param course the course
     * @return boolean
     */
    public boolean enroll(Course course);
}

/**
 * The type University member.
 */
class UniversityMember {
    /**
     * The constant numberOfMembers.
     */
    private static int numberOfMembers = 0;
    /**
     * The Member id.
     */
    private int memberId;
    /**
     * The Member name.
     */
    private String memberName;

    /**
     * Instantiates a new University member.
     *
     * @param memberId   the member id
     * @param memberName the member name
     */
    public UniversityMember(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    /**
     * Gets member id.
     *
     * @return member id
     */
    public int getMemberId() {
        return memberId;

    }

    /**
     * Add number of members.
     */
    public static void addNumberOfMembers() {
        numberOfMembers += 1;
    }

    /**
     * Gets number of members.
     *
     * @return number of members
     */
    public static int getNumberOfMembers() {
        addNumberOfMembers();
        return numberOfMembers;
    }
}
