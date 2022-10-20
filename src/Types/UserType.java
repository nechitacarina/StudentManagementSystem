package Types;

public enum UserType {

    STUDENT(1, "Student"),
    TEACHER(2, "Teacher");

    private int typeNumber;
    private String typeDescription;

    UserType(int typeNumber, String typeDescription){
        this.typeNumber = typeNumber;
        this.typeDescription = typeDescription;
    }
}
