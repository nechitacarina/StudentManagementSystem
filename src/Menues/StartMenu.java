package Menues;

public enum StartMenu {
    LOGIN(1, "Login"),
    Exit(0, "Exit");
    private int optionNumber;
    private String optionDescription;

    StartMenu(int optionNumber, String optionDescription){
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
