import java.util.ArrayList;

public class Teacher implements User{
    private ArrayList<Course> courses;

    private UserCredentials userCredentials;

    Teacher(UserCredentials userCredentials, ArrayList<Course> courses){
        this.courses = courses;
        this.userCredentials = userCredentials;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    @Override
    public String toString() {
        return ""+userCredentials;
    }
}
