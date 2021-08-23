package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CompleteCommand extends Command {
    private int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > tasks.size() - 1) {
            throw DukeException.invalidIndex();
        } else {
            tasks.get(index).markAsDone();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Awesome! I marked this as done:\n" +
                    tasks.get(index).toString() + "\n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            storage.write(tasks);
        }
    }
}
