package Menues;

public enum TeacherMenu {
    DISPLAY_COURSES(1, "Display Courses"),
    DISPLAY_STUDENTS_GRADES(2, "Display Students Grade"),
    DISPLAY_STUDENTS_ATTENDANCE(3, "Display Students Attendance"),
    ADD_GRADES(4, "Add Grade"),
    ADD_ABSENCES(5, "Add Absences"),
    MOTIVATE_ABSENCES(6, "Motivate Absences"),
    LOGOUT(7, "Logout"),
    EXIT(0, "Exit");

    private int optionNumber;
    private String optionDescription;
    TeacherMenu(int optionNumber, String optionDescription){
        this.optionNumber = optionNumber;
        this.optionDescription = optionDescription;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public String getOptionDescription() {
        return optionDescription;
    }
}
