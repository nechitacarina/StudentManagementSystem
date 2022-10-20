package Menues;

public enum StudentMenu {
    DISPLAY_GRADES(1, "Display Grades"),
    DISPLAY_ATTENDANCE(2, "Display Attendance"),
    LOGOUT(3, "Logout"),
    EXIT(0, "Exit");
    private int optionNumber;
    private String optionDescription;
    StudentMenu(int optionNumber, String optionDescription){
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
