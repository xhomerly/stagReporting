package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    @SerializedName("value")
    public String date;

    public LocalDate getDate() {
        if (date == null || date.isEmpty()) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        return LocalDate.parse(this.date, formatter);
    }
}
