package assignment5;

import java.io.File;
import java.util.NoSuchElementException;

public class Driver {
	public void main(String[] args) {
		
	//let us first create a new and empty WatchList called watchlist1
	WatchList watchlist1 = new WatchList("This is the first WatchList.");
	
	//then, try accessing the last watched item from this WatchList
	//there is nothing in the watchlist right now, so calling the lastWatched() function should throw an error
	try {
		Watchable lastWatchedItem = watchlist1.lastWatched();
		if (lastWatchedItem != null) {
			System.out.println("The lastWatched() method in WatchList is not working properly. There should not be a last watched item for this watchlist yet.");
		}
	} 
	catch (NoSuchElementException e) {
		System.out.println("This is correct! There is indeed no 'last watched' element in this WatchList.");
	}
	
	//then, we try adding a new watchable into the WatchList
	//if we call the lastWatched() function again, there should still be an error
	//because the item has not been watched
	TVShow yugiohShow = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "WarnerBrothers");
	File yugioh1 = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
	yugiohShow.createAndAddEpisode(yugioh1, "The Heart of the Cards");
	
	watchlist1.addWatchableCommand(yugiohShow).execute();
	
	try {
		Watchable lastWatchedItem = watchlist1.lastWatched();
		if (lastWatchedItem != null) {
			System.out.println("The lastWatched() method in WatchList is not working properly. There should not be a last watched item for this watchlist yet.");
		}
	} 
	catch (NoSuchElementException e) {
		System.out.println("This is correct! There is indeed no 'last watched' element in this WatchList.");
	}
	
	//now, we try to watch the show in the watchlist
	//after watching the show, the watchlist should have a last watched element
	yugiohShow.watch();
	try {
		Watchable lastWatchedItem = watchlist1.lastWatched();
		if (lastWatchedItem != null) {
			System.out.println("The lastWatched() method in WatchList is working properly!");
		}
	}
	catch (NoSuchElementException e) {
		System.out.println("The lastWatched() method in WatchList is not working properly. There should be a last watched item for this watchlist.");
	}
	
	
	
	
	//Using another watchlist, we test the undo and redo functions
	
	WatchList watchlist2 = new WatchList("This is the first WatchList.");
	CommandProcessor manager1 = new CommandProcessor();
	
	
	
	//we first test the changeNameCommand
	Command changeNameCommand1 = watchlist2.changeNameCommand("We have changed the name of this watchlist!");
	if (watchlist2.getName() == "This is the first WatchList.") {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	
	//then we try executing this command
	manager1.execute(changeNameCommand1);
	if (watchlist2.getName() == "We have changed the name of this watchlist!") {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	 
	//now, we try undoing the command
	manager1.undo();
	if (watchlist2.getName() == "This is the first WatchList.") {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	
	//now, we try undoing the command again. This should not do anything.
	manager1.undo();
	if (watchlist1.getName() == "This is the first WatchList.") {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	
	//now we try redoing the command
	manager1.redo();
	if (watchlist2.getName() == "We have changed the name of this watchlist!") {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	
	//and now we try redoing the action again. This should not change anything.
	manager1.redo();
	if (watchlist2.getName() == "We have changed the name of this watchlist!") {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand is not working properly.");
	}
	
	//we then try to execute another commmand and test if the redo action repeats it
	Command changeNameCommand2 = watchlist2.changeNameCommand("The name has been changed a second time!");
	manager1.execute(changeNameCommand2);
	
	//now, we try redoing an action without calling undo. This should repeat the action.
	manager1.redo();
	if (watchlist2.getName() == "The name has been changed a second time!") {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The changeNameCommand and/or redo is not working properly.");
	}
	
	
	
	WatchList watchlist3 = new WatchList("This is the third WatchList.");
	CommandProcessor manager2 = new CommandProcessor();
	
	//we now test the addWatchableCommand
	Command addWatchableCommand1 = watchlist3.addWatchableCommand(yugiohShow);
	if (watchlist3.getTotalCount() == 0) {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	
	//then we try executing this command
	manager2.execute(addWatchableCommand1);
	if (watchlist3.getTotalCount() == 1) {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	 
	//now, we try undoing the command
	manager2.undo();
	if (watchlist3.getTotalCount() == 0) {
		System.out.println("We have un-executed the command. This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	
	//now, we try undoing the command again. This should not do anything.
	manager2.undo();
	if (watchlist3.getTotalCount() == 0) {
		System.out.println("The number of watchables stayed the same. This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand and/or undo function is not working properly.");
	}
	
	//now we try redoing the command
	manager2.redo();
	if (watchlist3.getTotalCount() == 1) {
		System.out.println("We have re-executed the command! This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	
	//and now we try redoing the action again. This should not change anything.
	manager2.redo();
	if (watchlist3.getTotalCount() == 1) {
		System.out.println("The number of watchables stayed the same! This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	
	//we then try to execute another commmand and test if the redo action repeats it
	File yugioh2 = new File("C:\\Users\\user\\Downloads\\YuGiOh2.MP4");
	Movie movie1 = new Movie(yugioh2, "Yu-Gi-Oh Movie", Language.ENGLISH, "WarnerBrothers");
	Command addWatchableCommand2 = watchlist3.addWatchableCommand(movie1);
	
	manager2.execute(addWatchableCommand2);
	
	//now, we try redoing an action without calling undo. This should repeat the action.
	manager2.redo();
	if (watchlist3.getTotalCount() == 2) {
		System.out.println("The number of watchables stayed the same! This is working so far.");
	}
	else {
		System.out.println("The addWatchableCommand is not working properly.");
	}
	
	
	
	
	//we now test the removeWatchableCommand
	WatchList watchlist4 = new WatchList("This is the fourth WatchList.");
	CommandProcessor manager3 = new CommandProcessor();
	
	Command removeWatchableCommand1 = watchlist4.removeWatchableCommand(0);
	if (watchlist4.getTotalCount() == 0) {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	
	manager3.execute(watchlist4.addWatchableCommand(yugiohShow));
	
	//then we try executing this command
	manager3.execute(removeWatchableCommand1);
	if (watchlist4.getTotalCount() == 0) {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	 
	//now, we try undoing the command
	manager3.undo();
	if (watchlist4.getTotalCount() == 1) {
		System.out.println("We have un-executed the command. This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	
	//now, we try undoing the command again
	manager3.undo();
	if (watchlist4.getTotalCount() == 0) {
		System.out.println("This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand and/or undo function is not working properly.");
	}
	
	//now, we try undoing the command again. This should remain the same.
	manager3.undo();
	if (watchlist4.getTotalCount() == 0) {
		System.out.println("This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand and/or undo function is not working properly.");
	}
	
	//now we try redoing the command
	manager3.redo();
	if (watchlist4.getTotalCount() == 1) {
		System.out.println("We have re-executed the command! This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	
	//and now we try redoing the action again.
	manager3.redo();
	if (watchlist4.getTotalCount() == 0) {
		System.out.println("The number of watchables stayed the same! This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	
	
	
	//we now test the nextCommand
	WatchList watchlist5 = new WatchList("This is the fifth WatchList.");
	CommandProcessor manager4 = new CommandProcessor();
	Command addWatchable = watchlist5.addWatchableCommand(movie1);
	manager4.execute(addWatchable);
	
	Command nextCommand1 = watchlist5.nextCommand();
	if (watchlist5.getRemainingCount() == 1) {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	//then we try executing this command
	manager4.execute(nextCommand1);
	if (watchlist5.getRemainingCount() == 0) {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	 
	//now, we try undoing the command
	manager4.undo();
	if (watchlist5.getRemainingCount() == 1) {
		System.out.println("We have un-executed the command. This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	//now, we try undoing the command again. 
	manager4.undo();
	if (watchlist5.getRemainingCount() == 0) {
		System.out.println("This is working so far.");
	}
	else {
		System.out.println("The nextCommand and/or undo function is not working properly.");
	}
	
	//now, we try undoing the command again. This should remain the same.
	manager4.undo();
	if (watchlist5.getRemainingCount() == 0) {
		System.out.println("This is working so far.");
	}
	else {
		System.out.println("The nextCommand and/or undo function is not working properly.");
	}
	
	//now we try redoing the command
	manager4.redo();
	if (watchlist5.getRemainingCount() == 1) {
		System.out.println("We have re-executed the command! This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	//and now we try redoing the action again.
	manager4.redo();
	if (watchlist5.getRemainingCount() == 0) {
		System.out.println("The number of watchables stayed the same! This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	//now we try redoing the command again, which should do nothing
	manager4.redo();
	if (watchlist5.getRemainingCount() == 0) {
		System.out.println("We have re-executed the command! This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	
	
	
	//we now test the nextCommand
	WatchList watchlist6 = new WatchList("This is the sixth WatchList.");
	CommandProcessor manager5 = new CommandProcessor();
	Command addWatchable1 = watchlist6.addWatchableCommand(movie1);
	manager5.execute(addWatchable1);
	Command nextWatchable1 = watchlist6.nextCommand();
	manager5.execute(nextWatchable1);
	
	Command resetCommand1 = watchlist5.resetCommand();
	if (watchlist6.getRemainingCount() == 0) {
		System.out.println("We have not executed the command yet. This is working so far.");
	}
	else {
		System.out.println("The nextCommand is not working properly.");
	}
	
	//then we try executing this command
	manager5.execute(resetCommand1);
	if (watchlist6.getRemainingCount() == 1) {
		System.out.println("We have executed the command! This is working so far.");
	}
	else {
		System.out.println("The resetCommand is not working properly.");
	}
	 
	//now, we try undoing the command
	manager5.undo();
	if (watchlist6.getRemainingCount() == 0) {
		System.out.println("We have un-executed the command. This is working so far.");
	}
	else {
		System.out.println("The removeWatchableCommand is not working properly.");
	}
	
	//now we try redoing the command
	manager5.redo();
	if (watchlist6.getRemainingCount() == 1) {
		System.out.println("We have re-executed the command! This is working so far.");
	}
	else {
		System.out.println("The resetCommand is not working properly.");
	}
	
	//and now we try redoing the action again. This should not do anything
	manager5.redo();
	if (watchlist6.getRemainingCount() == 1) {
		System.out.println("The number of watchables stayed the same! This is working so far.");
	}
	else {
		System.out.println("The resetCommand is not working properly.");
	}
	
	}
}