package pro1.reports.report4;

import com.google.gson.Gson;
import java.time.temporal.ChronoUnit;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDuration;

public class ThesisDurationReporting {

    public static Object[] GetReport(DataSource dataSource, String katedra, String[] years) {
        ThesisDuration[] report = new ThesisDuration[years.length];

        for (int i = 0; i < years.length; i++) {
            String year = years[i];
            String thesisListJson = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList thesisList = new Gson().fromJson(thesisListJson, ThesisList.class);

            long totalDays = 0;
            int validCount = 0;

            if (thesisList != null && thesisList.items != null) {
                for (Thesis thesis : thesisList.items) {

                    if (thesis.dateAssigned != null && thesis.dateAssigned.isValid() && thesis.dateSubmitted != null && thesis.dateSubmitted.isValid()) {
                        long days = ChronoUnit.DAYS.between(
                                thesis.dateAssigned.toLocalDate(),
                                thesis.dateSubmitted.toLocalDate()
                        );
                        totalDays += days;
                        validCount++;
                    }
                }
            }

            long averageDuration = 0;
            if (validCount > 0) {
                averageDuration = Math.round((double) totalDays / validCount);
            }

            report[i] = new ThesisDuration(year, averageDuration);
        }

        return report;
    }
}
