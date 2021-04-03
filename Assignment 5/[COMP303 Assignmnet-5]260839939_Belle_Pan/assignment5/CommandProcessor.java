package assignment5;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandProcessor {
	private Deque<Command> aExecutedCommands = new ArrayDeque<Command>();
	private Deque<Command> aUndoneCommands = new ArrayDeque<Command>();
	
	public void execute(Command pCommand) {
		assert pCommand != null;
		pCommand.execute();
		aExecutedCommands.push(pCommand);
		aUndoneCommands.clear();
	}
	
	public void undo() {
		if (!aExecutedCommands.isEmpty()) {
			Command undoCommand = aExecutedCommands.pop();
			undoCommand.undo();
			aUndoneCommands.push(undoCommand);
		}
	}
	
	public void redo() {
		if (!aUndoneCommands.isEmpty()) {
			Command redoCommand = aUndoneCommands.pop();
			redoCommand.execute();
			aExecutedCommands.push(redoCommand);
		}
		else {
			Command redoCommand = aExecutedCommands.pop();
			execute(redoCommand);
		}
	}
}
