import Menues.StartMenu;
import Menues.StudentMenu;
import Menues.TeacherMenu;
import Status.AbsenceStatus;
import Types.UserType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentManagementActions {
    protected static ArrayList<Student> createStudents(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(new UserCredentials("Pic Bianca", "pic.bianca", "123", UserType.STUDENT), Map.of(new Course("math"), new ArrayList<>(List.of(new Grade(8))))));
        students.add(new Student(new UserCredentials("Juca Oana", "juca.oana", "123", UserType.STUDENT), Map.of(new Course("physics"), new ArrayList<>(Arrays.asList(new Grade(10), new Grade(8))))));
        students.add(new Student(new UserCredentials("Popescu Mihai", "popescu_mihai", "123", UserType.STUDENT), Map.of(new Course("english"), new ArrayList<>(List.of(new Grade(5))))));
        return students;
    }

    static ArrayList<Student> listOfStudents = createStudents();
    protected static ArrayList<Teacher> createTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(new UserCredentials("Botez Eva", "botez.eva", "123",UserType.TEACHER), new ArrayList<>(Arrays.asList(new Course("math"), new Course("physics")))));
        teachers.add(new Teacher(new UserCredentials("Pop Ioan", "popioan", "123", UserType.TEACHER), new ArrayList<>(List.of(new Course("english")))));
        return teachers;
    }

    static ArrayList<Teacher> listOfTeachers = createTeachers();
    protected static void displayStartMenu(){
        for(StartMenu startMenu : StartMenu.values())
            System.out.println(startMenu.getOptionNumber() + "-" + startMenu.getOptionDescription());
        System.out.print("Enter your option: ");
    }

    protected static void displayStudentMenu(){
        for(StudentMenu studentMenu : StudentMenu.values())
            System.out.println(studentMenu.getOptionNumber() + "-" + studentMenu.getOptionDescription());
        System.out.print("Enter your option: ");
    }

    protected static void displayTeacherMenu(){
        for(TeacherMenu teacherMenu : TeacherMenu.values())
            System.out.println(teacherMenu.getOptionNumber() + "-" + teacherMenu.getOptionDescription());
        System.out.print("Enter your option: ");
    }

    protected static int readOptionNumberFromConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean isNumber = false;
        int optionNumber = 0;
        while (!isNumber) {
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter an option number!");
                scanner = new Scanner(System.in);
            }else {
                optionNumber = scanner.nextInt();
                isNumber = true;
            }
        }
        return optionNumber;
    }

    protected static User verifyCredentials() throws ParseException {
        Student loggedInStudent = null;
        Teacher loggedInTeacher = null;
        User loggedInUser = null;
        boolean studentExist = false;
        boolean teacherExist = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your credentials!");
        System.out.print("username: ");
        String username = scan.nextLine();
        System.out.print("password: ");
        String password = scan.nextLine();
        for(Student student : listOfStudents) {
            if (student.getUserCredentials().getUsername().equals(username) && student.getUserCredentials().getPassword().equals(password)) {
                loggedInStudent = student;
                studentExist = true;
            }
        }
        for (Teacher teacher : listOfTeachers) {
            if (teacher.getUserCredentials().getUsername().equals(username) && teacher.getUserCredentials().getPassword().equals(password)) {
                loggedInTeacher = teacher;
                teacherExist = true;
            }
        }
        if(!studentExist && !teacherExist) {
            System.out.println("Incorrect username or password!");
            displayStartMenu();
        }
        else if(studentExist){
            System.out.println("You have been logged in successfully!");
            System.out.println(loggedInStudent.getUserCredentials().toString());
            loggedInUser = loggedInStudent;
        }
        if(teacherExist) {
            System.out.println("You have been logged in successfully!");
            System.out.println(loggedInTeacher.getUserCredentials().toString());
            loggedInUser = loggedInTeacher;
        }
        return loggedInUser;
    }

    protected static void showMainScreen() throws ParseException {
        while (true) {
            int option = readOptionNumberFromConsole();
            if (option == StartMenu.Exit.getOptionNumber()) System.exit(0);
            else if (option == StartMenu.LOGIN.getOptionNumber()) {
                User user = verifyCredentials();
                if(user instanceof Teacher loggedInTeacher){
                    showTeacherScreen(loggedInTeacher);
                }
                else if(user instanceof Student loggedInStudent){
                    showStudentScreen(loggedInStudent);
                }
            } else {
                System.out.println("This option does not exist!");
                displayStartMenu();
            }
        }
    }
    protected static void showTeacherScreen(Teacher loggedInTeacher) throws ParseException {
        displayTeacherMenu();
        while (true){
            int optionNumber = readOptionNumberFromConsole();
            if(optionNumber == TeacherMenu.EXIT.getOptionNumber()) System.exit(0);
            else if(optionNumber == TeacherMenu.DISPLAY_COURSES.getOptionNumber()){
                System.out.print("COURSES YOU ARE TEACHING: ");
                displayTeacherCourses(loggedInTeacher);
                displayTeacherMenu();

            }
            else if(optionNumber == TeacherMenu.DISPLAY_STUDENTS_GRADES.getOptionNumber()){
                System.out.println("STUDENTS GRADES AT YOUR CLASSES: ");
                displayStudentsGradesAtTeacherCourses(loggedInTeacher);
                displayTeacherMenu();
            }
            else if(optionNumber == TeacherMenu.DISPLAY_STUDENTS_ATTENDANCE.getOptionNumber()){
                System.out.println("STUDENTS ABSENCES AT YOUR CLASSES: ");
                displayAttendanceAtTeacherCourses(loggedInTeacher);
                displayTeacherMenu();
            }
            else if(optionNumber == TeacherMenu.ADD_GRADES.getOptionNumber()){
                System.out.println("ADD GRADES: ");
                displayStudentsGradesAtTeacherCourses(loggedInTeacher);
                addGrades(loggedInTeacher);
                displayTeacherMenu();
            }
            else if(optionNumber == TeacherMenu.ADD_ABSENCES.getOptionNumber()){
                System.out.println("ADD ABSENCES: ");
                displayStudents(loggedInTeacher);
                addAbsences(loggedInTeacher);
                displayTeacherMenu();
            }
            else if(optionNumber == TeacherMenu.MOTIVATE_ABSENCES.getOptionNumber()){
                System.out.println("MOTIVATE ABSENCES: ");
                displayUnfoundedAbsences(loggedInTeacher);
                motivateAbsences(loggedInTeacher);
                displayTeacherMenu();
            }
            else if(optionNumber == TeacherMenu.LOGOUT.getOptionNumber()) {
                displayStartMenu();
                showMainScreen();
            }else{
                System.out.println("This option does not exist!");
                displayTeacherMenu();
            }
        }
    }

    protected static void displayTeacherCourses(Teacher loggedInTeacher){
        System.out.println(loggedInTeacher.getCourses());
    }

    protected static void displayStudents(Teacher loggedInTeacher){
        for(Student student: listOfStudents)
            for(Course c : loggedInTeacher.getCourses())
                if(student.getCoursesAndGrades().keySet().toString().contains(c.toString()))
                    System.out.println(student.getUserCredentials().getName() + " " + student.getCoursesAndGrades().keySet());
    }
    protected static void displayStudentsGradesAtTeacherCourses(Teacher loggedInTeacher){
        for(Student student: listOfStudents)
            for(Course c : loggedInTeacher.getCourses())
                if(student.getCoursesAndGrades().keySet().toString().contains(c.toString()))
                    System.out.println(student.getUserCredentials().getName() + " " + student.getCoursesAndGrades());
    }

    protected static void displayAttendanceAtTeacherCourses(Teacher loggedInTeacher) {
        boolean ok = false;
        for (Student student : listOfStudents) {
            for (Course c : loggedInTeacher.getCourses())
                if (student.getCoursesAndGrades().keySet().toString().contains(c.toString())) {
                    if (student.getAbsences().size() > 0 ) {
                        ok = true;
                        System.out.println(student.getUserCredentials().getName() + " " + student.getAbsences());
                    }
                }
            }
        if(!ok)  System.out.println("There are no absences at your courses!");
    }

    protected static void displayUnfoundedAbsences(Teacher loggedInTeacher){
        for (Student student : listOfStudents) {
            for (Course c : loggedInTeacher.getCourses()) {
                if (student.getCoursesAndGrades().keySet().toString().contains(c.toString())) {
                    if(student.getAbsences().size() > 0 ) {
                        System.out.print(student.getUserCredentials().getName() + ":");
                    }
                    for (Absence absence : student.getAbsences()) {
                        if (absence.absenceStatus.equals(AbsenceStatus.UNFOUNDED)) {
                            System.out.print(" " + absence + "; ");
                        }
                    }
                }
            }
            if(student.getAbsences().size() > 0) System.out.println();
        }
    }

    protected static void addGrades(Teacher loggedInTeacher){
        boolean studentExist = false;
        boolean firstEntry = true;
        boolean studentIsEnrolled = false;
        while (!studentExist){
            while (!studentIsEnrolled) {
                Scanner scanner = new Scanner(System.in);
                if (firstEntry)
                    System.out.println("Please enter the name of the student for which you want to add the grade: ");
                else System.out.println("Please enter a valid student!");
                firstEntry = false;
                String studentName = scanner.nextLine();
                for (Student student : listOfStudents) {
                    if (student.getUserCredentials().getName().equals(studentName)) {
                        studentExist = true;
                        System.out.println("Please enter the course name: ");
                        String courseName = scanner.nextLine();
                        System.out.println("Please enter the grade: ");
                        int grade = scanner.nextInt();
                        while (grade < 0 || grade > 10){
                            System.out.println("Please enter a valid grade!");
                            grade = scanner.nextInt();
                        }
                        if (loggedInTeacher.getCourses().toString().contains(courseName)) {
                            for (Course studentCourse : student.getCoursesAndGrades().keySet()) {
                                if (studentCourse.getCourseName().equals(courseName)) {
                                    ArrayList<Grade> grades = student.getCoursesAndGrades().get(studentCourse);
                                    grades.add(new Grade(grade));
                                    System.out.println("The grade has been added successfully!");
                                    studentIsEnrolled = true;
                                } else {
                                    System.out.println("The student is not enrolled at this course!");
                                }
                            }
                        }else System.out.println("The student is not enrolled at your courses!");
                    }
                }
            }
        }
    }
    protected static void addAbsences(Teacher loggedInTeacher) throws ParseException {
        boolean studentExist = false;
        boolean firstEntry = true;
        boolean firstEntry2 = true;
        boolean studentIsEnrolled = false;
        boolean dateIsValid = false;
        while (!studentExist || !studentIsEnrolled){
            Scanner scanner = new Scanner(System.in);
            if(firstEntry) System.out.println("Please enter the name of the student for which you want to add the absence: ");
            else System.out.println("Please enter a valid name!");
            firstEntry = false;
            String studentName = scanner.nextLine();
            for (Student student : listOfStudents) {
                if (student.getUserCredentials().getName().equals(studentName)) {
                    studentExist = true;
                    for (Course teacherCourse : loggedInTeacher.getCourses()) {
                        if (student.getCoursesAndGrades().keySet().toString().contains(teacherCourse.toString())) {
                            studentIsEnrolled = true;
                            List<SimpleDateFormat> knownPatterns = new ArrayList<>();
                            knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy"));
                            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
                            knownPatterns.add(new SimpleDateFormat("dd.MM.yyyy"));
                            Date dateOfAbsence = null;
                            while (!dateIsValid){
                                if(firstEntry2) System.out.println("Please enter the date of absence: ");
                                else System.out.println("Please enter a valid date!");
                                firstEntry2 = false;
                                String inputDateOfAbsence = scanner.nextLine();
                                boolean dateExist = false;
                                for (SimpleDateFormat pattern : knownPatterns) {
                                    try {
                                            dateOfAbsence = new Date(pattern.parse(inputDateOfAbsence).getTime());
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(dateOfAbsence);
                                            for (Absence absence : student.getAbsences()) {
                                                if (absence.getAbsences().contains(dateOfAbsence)) {
                                                    dateExist = true;
                                                    break;
                                                }
                                            }
                                            if ((calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) && dateOfAbsence.before(new Date()) && !dateExist) {
                                                dateIsValid = true;
                                            }
                                            if (dateIsValid) {
                                                student.getAbsences().add(new Absence(new ArrayList<>(List.of(dateOfAbsence)), AbsenceStatus.UNFOUNDED));
                                                System.out.println("The absence has been added successfully!");
                                            } else {
                                                System.out.println("The date you entered is either in the future or is a weekend day or already exists!");
                                            }
                                    }catch (ParseException e) {
                                      //
                                    }
                                }
                            }
                        }

                    }

                }

            }

        }
    }

    protected static void motivateAbsences(Teacher loggedInTeacher) throws ParseException {
        boolean studentExist = false;
        boolean absenceExist = false;
        boolean firstEntry = true;
        boolean firstEntry2 = true;
        boolean studentIsEnrolled = false;
        while (!studentExist || !studentIsEnrolled){
            if(firstEntry) System.out.println("Please enter the student name for which you want to motivate the absence:");
            else System.out.println("Please enter a valid student name!");
            firstEntry = false;
            Scanner scanner = new Scanner(System.in);
            String studentName = scanner.nextLine();
            for (Student student : listOfStudents) {
                if (student.getUserCredentials().getName().equals(studentName)) {
                    studentExist = true;
                    for (Course teacherCourse : loggedInTeacher.getCourses()) {
                        if (student.getCoursesAndGrades().keySet().toString().contains(teacherCourse.toString())) {
                            studentIsEnrolled = true;
                            List<SimpleDateFormat> knownPatterns = new ArrayList<>();
                            knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy"));
                            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
                            knownPatterns.add(new SimpleDateFormat("dd.MM.yyyy"));
                            if (student.getAbsences().size() != 0) {
                                while (!absenceExist) {
                                    if(firstEntry2) System.out.println("Please enter the date you want to motivate: ");
                                    else System.out.println("Please enter a valid date!");
                                    firstEntry2 = false;
                                    String inputDateOfAbsence = scanner.nextLine();
                                    Date dateOfAbsence = null;
                                    for (SimpleDateFormat pattern : knownPatterns) {
                                        try {
                                            dateOfAbsence = new Date(pattern.parse(inputDateOfAbsence).getTime());
                                            for (Absence absence : student.getAbsences()) {
                                                if (absence.getAbsences().contains(dateOfAbsence)) {
                                                    absenceExist = true;
                                                    if (absence.absenceStatus == AbsenceStatus.MOTIVATED) {
                                                        System.out.println("This absence is already motivated!");
                                                    } else {
                                                        absence.absenceStatus = AbsenceStatus.MOTIVATED;
                                                        System.out.println("The absence has been motivated successfully!");
                                                    }
                                                }
                                            }
                                        } catch (ParseException e) {
                                            //System.out.println("Please enter a valid date format");
                                        }
                                    }
                                }
                            } else System.out.println("There are no absences for this student!");
                        }
                    }

                }
            }
        }
    }


    protected static void showStudentScreen(Student loggedInStudent) throws ParseException {
        displayStudentMenu();
        while (true){
            int optionNumber = readOptionNumberFromConsole();
            if(optionNumber == StudentMenu.EXIT.getOptionNumber()) System.exit(0);
            else if(optionNumber == StudentMenu.DISPLAY_GRADES.getOptionNumber()){
                System.out.println("Your grades: ");
                displayGrades(loggedInStudent);
                displayStudentMenu();
            }else if(optionNumber == StudentMenu.DISPLAY_ATTENDANCE.getOptionNumber()){
                System.out.println("Your attendance: ");
                displayAttendance(loggedInStudent);
                displayStudentMenu();
            }else if(optionNumber == StudentMenu.LOGOUT.getOptionNumber()){
                displayStartMenu();
                showMainScreen();
            }else {
                System.out.println("This option does not exist!");
                displayStudentMenu();
            }
        }
    }

    protected static void displayGrades(Student loggedInStudent){
        loggedInStudent.getCoursesAndGrades().forEach((key, value) -> System.out.println(key + ": " + value));
    }
    protected static void displayAttendance(Student loggedInStudent){
        if(loggedInStudent.getAbsences().size() == 0) System.out.println("Congrats! You don't have absences!");
        else System.out.println(loggedInStudent.getAbsences());
    }
}
