import java.util.ArrayList;

import java.util.Map;

public class Student implements User{

    private Map<Course, ArrayList<Grade>> coursesAndGrades;
    private ArrayList<Absence> absences = new ArrayList<>();
    private UserCredentials userCredentials;
    Student(UserCredentials userCredentials, Map<Course, ArrayList<Grade>> coursesAndGrades){
        this.userCredentials = userCredentials;
        this.coursesAndGrades = coursesAndGrades;
    }
    public Map<Course, ArrayList<Grade>> getCoursesAndGrades() {
        return coursesAndGrades;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public ArrayList<Absence> getAbsences() {
        return absences;
    }

    @Override
    public String toString() {
        return "Student{" +
                "coursesAndGrades=" + coursesAndGrades +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
