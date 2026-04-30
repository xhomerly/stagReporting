package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;
import java.util.*;

public class DepartmentExamsStatsReporting {
    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        String examsJson = dataSource.getTerminyZkousek2(katedra);
        ExamsList examsList = new Gson().fromJson(examsJson, ExamsList.class);

        long realizedCount = 0;
        Set<Long> uniqueTeacherIds = new HashSet<>();

        if (examsList != null && examsList.exams != null) {
            for (Exam exam : examsList.exams) {
                if (exam.registeredStudentsCount > 0) {
                    realizedCount++;
                    uniqueTeacherIds.add(exam.teacherId);
                }
            }
        }

        List<Long> sortedTeacherIds = new ArrayList<>(uniqueTeacherIds);
        Collections.sort(sortedTeacherIds);

        return new DepartmentExamsStats(realizedCount, sortedTeacherIds);
    }
}
