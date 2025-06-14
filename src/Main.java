public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        System.out.println("=== Тестирование Task ===");
        Task task1 = new Task("Помыть посуду", "Помыть всю посуду вечером", Status.NEW);
        taskManager.createTask(task1);
        System.out.println("Создана задача: " + taskManager.getTaskById(task1.getId()));

        Task task2 = new Task("Сделать ДЗ", "Выполнить домашнее задание", Status.NEW);
        taskManager.createTask(task2);
        System.out.println("Все задачи: " + taskManager.getTasks());

        task2.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task2);
        System.out.println("Обновленная задача: " + taskManager.getTaskById(task2.getId()));

        taskManager.removeTask(task1.getId());
        System.out.println("После удаления: " + taskManager.getTasks());

        System.out.println("\n=== Тестирование Epic и SubTask ===");
        Epic epic1 = new Epic("Ремонт квартиры", "Сделать полный ремонт");
        taskManager.createEpic(epic1);
        System.out.println("Создан эпик: " + taskManager.getEpicById(epic1.getId()));

        SubTask subTask1 = new SubTask("Купить материалы", "Купить стройматериалы", Status.NEW, epic1.getId());
        taskManager.createNewSubtask(subTask1, epic1.getId());

        SubTask subTask2 = new SubTask("Сделать дизайн", "Разработать дизайн-проект", Status.IN_PROGRESS, epic1.getId());
        taskManager.createNewSubtask(subTask2, epic1.getId());

        System.out.println("Подзадачи эпика: " + taskManager.getSubTasksForEpic(epic1.getId()));
        System.out.println("Статус эпика: " + taskManager.getEpicById(epic1.getId()).getStatus());

        subTask1.setStatus(Status.DONE);
        taskManager.updateSubTask(subTask1);
        System.out.println("После обновления подзадачи. Статус эпика: " +
                taskManager.getEpicById(epic1.getId()).getStatus());

        taskManager.deleteSubTask(subTask2.getId());
        System.out.println("После удаления подзадачи: " + taskManager.getSubTasksForEpic(epic1.getId()));

        taskManager.deleteEpic(epic1.getId());
        System.out.println("После удаления эпика. Все эпики: " + taskManager.getAllEpics());

        System.out.println("\n=== Тестирование очистки ===");
        taskManager.clearAll();
        System.out.println("После clearAll. Задачи: " + taskManager.getTasks() +
                ", Эпики: " + taskManager.getAllEpics());
    }
}