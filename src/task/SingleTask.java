package task;

import task.Task;

import java.time.LocalDateTime;

public class SingleTask extends Task {
    public SingleTask(String title, String description, boolean isWork, LocalDateTime dateTime) {
        super(title, description, isWork, dateTime);
    }
}
