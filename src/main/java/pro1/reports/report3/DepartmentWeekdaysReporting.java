package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.DepartmentWeekdays;

import java.util.Arrays;

public class DepartmentWeekdaysReporting {
    public static Object[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);

        return WeekdaysActions(days, actionsList);
    }

    private static DepartmentWeekdays[] WeekdaysActions(String[] weekdays, ActionsList actionsList) {
        return Arrays.stream(weekdays)
                .map(day -> {
                    long count = actionsList.items.stream()
                            .filter(item -> day.equals(item.getWeekday()))
                            .count();

                    return new DepartmentWeekdays(day, count);
                })
                .toArray(DepartmentWeekdays[]::new);
    }
}
