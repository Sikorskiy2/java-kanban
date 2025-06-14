import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds;  // Список ID подзадач

    public Epic(String nameTask, String description) {
        super(nameTask, description, Status.NEW);  // Эпик всегда создается со статусом NEW
        this.subtaskIds = new ArrayList<>();
    }

    // Добавление ID подзадачи
    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    // Удаление подзадачи
    public void removeSubtask(int subtaskId) {
        subtaskIds.remove((Integer) subtaskId);
    }

    // Получение списка ID подзадач
    public ArrayList<Integer> getSubtaskIds() {
        return new ArrayList<>(subtaskIds);  // Возвращаем копию для безопасности
    }

    // Очистка списка подзадач
    public void clearSubtasks() {
        subtaskIds.clear();
    }
}