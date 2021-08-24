package duke.parser;

import static duke.common.Formats.DT_INPUT_FORMAT;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.DukeException;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.CompleteCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;



public class Parser {
    public enum CommandEnum {
        EXIT("bye"),
        LIST("list"),
        DELETE("delete"),
        COMPLETE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find"),
        ERROR(null);

        public final String label;

        private CommandEnum(String label) {
            this.label = label;
        }

        public static CommandEnum valueOfLabel(String label) {
            for (CommandEnum e : values()) {
                if (e.label.equals(label)) {
                    return e;
                }
            }
            return null;
        }
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.strip().split("\\s+", 2);
        String eventType = tokens[0];
        String remainder = tokens.length == 1 ? null : tokens[1];
        int index;

        switch (CommandEnum.valueOfLabel(eventType)) { // Exit routine
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(remainder);
        case DELETE:
            index = Integer.parseInt(remainder) - 1;
            return new DeleteCommand(index);
        case COMPLETE:
            index = Integer.parseInt(remainder) - 1;
            return new CompleteCommand(index);
        case TODO:
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            Todo todo = new Todo(false, remainder);

            return new AddCommand(todo);
        case DEADLINE:
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            String[] deadlineTokens = remainder.split(" /by ", 2);
            if (tokens.length == 1) {
                throw DukeException.emptyTime();
            }
            LocalDateTime deadline = LocalDateTime.parse(deadlineTokens[1], DT_INPUT_FORMAT);
            Deadline deadlineTask = new Deadline(false, deadlineTokens[0], deadline);

            return new AddCommand(deadlineTask);
        case EVENT:
            if (remainder == null) {
                throw DukeException.emptyDesc();
            }
            String[] eventTokens = remainder.split(" /at ", 2);
            LocalDateTime startTime = LocalDateTime.parse(eventTokens[1], DT_INPUT_FORMAT);
            Event event = new Event(false, eventTokens[0], startTime);

            return new AddCommand(event);
        default:
            throw DukeException.invalidCommand();
        }
    }
}
