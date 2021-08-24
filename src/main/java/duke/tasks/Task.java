package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] :  %s", getStatusIcon(), this.description);
    }

    public String getData() {
        int isDoneBinary = isDone ? 1 : 0;
        return String.format("%d | %s", isDoneBinary, this.description);
    }
}
