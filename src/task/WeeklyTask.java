package task;

import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable {

    public WeeklyTask(String title, String description, boolean isWork, LocalDateTime dateTime) {
        super(title, description, isWork, dateTime);

    }

    public boolean checkIfSuitable(LocalDate date) {
        return date.getDayOfWeek() == dateTime.getDayOfWeek();

    }
}
