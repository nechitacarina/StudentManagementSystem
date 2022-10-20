public class Grade {

    private int gradeValue;
    Grade(int gradeValue){
        this.gradeValue = gradeValue;
    }

    @Override
    public String toString() {
        return "" + gradeValue ;
    }
}
