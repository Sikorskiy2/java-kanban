import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Task {
    String nameTask;
    String description;
    Status status;
    int id;

    public Task(String name, String description, Status status) {
        this.nameTask = name;
        this.description = description;

        this.status = status;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id && Objects.equals(nameTask, task.nameTask) && Objects.equals(description, task.description) && status == task.status;
    }

    public int hashCode() {
        int hash = 17;

        if (nameTask != null) {
            hash = hash + nameTask.hashCode();
        }
        hash = hash * 31;
        if (description != null) {
            hash = hash + description.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Task{" +
                "nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
