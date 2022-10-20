package Status;

public enum AbsenceStatus {
    UNFOUNDED(1, "Unfounded"),
    MOTIVATED(2, "Motivated");
    private int statusNumber;
    private String statusDescription;
    AbsenceStatus(int statusNumber, String statusDescription){
        this.statusNumber = statusNumber;
        this.statusDescription = statusDescription;
    }

}
