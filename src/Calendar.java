import task.Repeatable;
import task.Task;

import java.time.LocalDate;
import java.util.*;

public class Calendar {
    private final Map<Integer, Task> taskMap;

    public Calendar() {
        taskMap = new HashMap<>();
    }

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public boolean deleteTask(int id) {
        if(taskMap.containsKey(id)){
            taskMap.get(id).setWork(false);
            return true;
        }
        return false;
    }

    public List<Task> getTasksForOneDay(LocalDate date){
        List<Task> result = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            Task task = entry.getValue();
            if (task instanceof Repeatable && ((Repeatable) task).checkIfSuitable(date)
                || !(task instanceof Repeatable) &&
                task.getDateTime().toLocalDate().equals(date)) {
                result.add(task);
            }
        }
        result.sort(Comparator.comparing(Task::getDateTime));
        return result;
    }

}
