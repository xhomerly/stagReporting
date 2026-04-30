package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    @SerializedName("value")
    public String date;

    public boolean isValid() {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }
        try {
            toLocalDate();
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        return LocalDate.parse(this.date, formatter);
    }
}