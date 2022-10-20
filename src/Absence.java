import Status.AbsenceStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Absence {
    private ArrayList<Date> absences;
    AbsenceStatus absenceStatus;

    Absence(ArrayList<Date> absences, AbsenceStatus absenceStatus){
        this.absences = absences;
        this.absenceStatus = absenceStatus;
    }
    public ArrayList<Date> getAbsences() {
        return absences;
    }


    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateOfAbsence = null;
        for (Date absence : absences){
            dateOfAbsence = simpleDateFormat.format(absence);
        }
        return dateOfAbsence + ", " + absenceStatus;
    }
}
