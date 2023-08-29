package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand implements Command{
    private String details;

    public EventCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details == "") {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.\n");
        } else {
            String[] partFrom = details.split("/from");
            String[] partTo = partFrom[1].split("/to");
            Task curr = new Event(partFrom[0], partTo[0], partTo[1]);
            tasks.add(curr);
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                    + "Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    @Override
    public void loadTask(TaskList tasks) {
        String[] partFrom = details.split("/from");
        String[] partTo = partFrom[1].split("/to");
        Task curr = new Event(partFrom[0], partTo[0], partTo[1]);
        tasks.add(curr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}