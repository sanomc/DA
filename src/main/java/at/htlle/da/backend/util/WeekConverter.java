package at.htlle.da.backend.util;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekConverter {

    private static WeekConverter instance;

    private WeekConverter() {

    }

    public static WeekConverter getInstance() {
        if (instance == null) {
            synchronized (WeekConverter.class) {
                if (instance == null) {
                    instance = new WeekConverter();
                }
            }
        }
        return instance;
    }

    public String toYearWeek(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        int year = date.get(weekFields.weekBasedYear());
        return year + "-W" + weekNumber;
    }

    public LocalDate[] getWeekStartAndEndDate(String yearWeek) {
        String[] parts = yearWeek.split("-W");
        int year = Integer.parseInt(parts[0]);
        int week = Integer.parseInt(parts[1]);
        WeekFields weekFields = WeekFields.of(Locale.GERMANY);
        LocalDate startDate = LocalDate.of(year, 1, 1)
                .with(weekFields.weekOfWeekBasedYear(), week)
                .with(weekFields.dayOfWeek(), 1);
        LocalDate endDate = startDate.plusDays(6);
        return new LocalDate[]{startDate, endDate};
    }

    public LocalDate[] getWeekStartAndEndDateByDate(LocalDate date) {
        return getWeekStartAndEndDate(toYearWeek(date));
    }

}
