package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > tasks.size() - 1) {
            throw DukeException.invalidIndex();
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("This item will be removed:\n" +
                    tasks.get(index).toString() + "\n");
            tasks.remove(index);
            System.out.println(String.format("You have %d task(s) at the moment!\n", tasks.size()));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            storage.write(tasks);
        }
    }
}
