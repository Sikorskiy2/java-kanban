public class SubTask extends Task {
    private int epicId;  // ID эпика, к которому относится подзадача

    public SubTask(String nameTask, String description, Status status, int epicId) {
        super(nameTask, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getNameTask() + '\'' +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}