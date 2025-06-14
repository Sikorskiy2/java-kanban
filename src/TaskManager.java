import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> listTask = new HashMap<>();
    HashMap<Integer, Epic> listEpic = new HashMap<>();
    HashMap<Integer, SubTask> listSubTask = new HashMap<>();
    int countTask = 0;
    int nextId = 0;

    // создание новой задачи
    public void createTask(Task task) {
        if (task == null) return;
        task.setId(nextId++);
        listTask.put(task.getId(), task);
    }
    //получение всех задач
    public HashMap<Integer, Task> getTasks() {
        return listTask;
    }

    // удаление всех задач
    public void removeAllTasks() {
        listTask.clear();

    }

    //удаление задачи по id
    void removeTask(int id) {
        if (!listTask.containsKey(id)) {
            System.out.println("Обычной задачи с таким id не существует!");
            return;
        }
        listTask.remove(id);
    }

    public void updateTask(Task updatedTask) {
        if (listTask.containsKey(updatedTask.getId())) {
            listTask.put(updatedTask.getId(), updatedTask);
        }
    }
    // поиск задачи по id
    public Task getTaskById(int id) {
        return listTask.get(id);
    }
    /*==================================EPIC==============================================================*/

    public void createEpic(Epic epic) {
        if (epic == null) return;
        epic.setId(nextId++);  // Правильный вызов
        listEpic.put(epic.getId(), epic);
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(listEpic.values());
    }

    public Epic getEpic(int id) {
        return listEpic.get(id);
    }

    public void updateEpic(Epic updatedEpic) {
        Epic existing = listEpic.get(updatedEpic.getId());
        if (existing != null) {
            existing.setNameTask(updatedEpic.getNameTask());
            existing.setDescription(updatedEpic.getDescription());
        }
    }

    public Epic getEpicById(int id) {
        return listEpic.get(id);
    }

    public void deleteEpic(int id) {
        Epic epic = listEpic.remove(id);
        if (epic != null) {
            for (int subTaskId : epic.getSubtaskIds()) {
                listSubTask.remove(subTaskId);
            }
        }
    }

/*==================================SUBTASK=========================================================*/

    public void createNewSubtask(SubTask subTask, int epicId) {
        if (subTask == null || !listEpic.containsKey(epicId)) {
            return;
        }
        subTask.setId(nextId++);
        subTask.setEpicId(epicId);
        listSubTask.put(subTask.getId(), subTask);
        listEpic.get(epicId).addSubtask(subTask.getId());
        updateEpicStatus(epicId);
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return new HashMap<>(listSubTask);
    }

    public SubTask getSubTaskById(int id) {
        return listSubTask.get(id);
    }

    public void updateSubTask(SubTask updatedSubTask) {
        if (listSubTask.containsKey(updatedSubTask.getId())) {
            listSubTask.put(updatedSubTask.getId(), updatedSubTask);
            updateEpicStatus(updatedSubTask.getEpicId());
        }
    }

    public void deleteSubTask(int id) {
        SubTask subTask = listSubTask.remove(id);
        if (subTask != null) {
            Epic epic = listEpic.get(subTask.getEpicId());
            if (epic != null) {
                epic.removeSubtask(id);
                updateEpicStatus(epic.getId());
            }
        }
    }

    public ArrayList<SubTask> getSubTasksForEpic(int epicId) {
        if (!listEpic.containsKey(epicId)) {
            return new ArrayList<>();
        }
        ArrayList<SubTask> result = new ArrayList<>();
        for (int subTaskId : listEpic.get(epicId).getSubtaskIds()) {
            SubTask subTask = listSubTask.get(subTaskId);
            if (subTask != null) {
                result.add(subTask);
            }
        }
        return result;
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = listEpic.get(epicId);
        if (epic == null) return;

        ArrayList<Integer> subTaskIds = epic.getSubtaskIds();
        if (subTaskIds.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (int subTaskId : subTaskIds) {
            SubTask subTask = listSubTask.get(subTaskId);
            if (subTask == null) continue;

            if (subTask.getStatus() != Status.DONE) {
                allDone = false;
            }
            if (subTask.getStatus() == Status.IN_PROGRESS) {
                anyInProgress = true;
            }
        }

        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (anyInProgress) {
            epic.setStatus(Status.IN_PROGRESS);
        } else {
            epic.setStatus(Status.NEW);
        }
    }

    public void clearAll() {
        listTask.clear();
        listEpic.clear();
        listSubTask.clear();
        nextId = 1;
    }
}
