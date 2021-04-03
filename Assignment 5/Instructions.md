# Assignment 5

*Start on 25 March, complete by Saturday, 3 April end of day Montreal time zone. Covers **until Chapter 8.4** of the textbook.*

## Problem Statement

Using the principles, mechanisms, and techniques seen until Chapters 8.4 of the textbook, design and write the code necessary to enhance the movie library design from the baseline code (adapted from solutions to Assignment 4) to meet the following requirements. *Your solution should continue to respect principles of good design seen in all prior chapters.*

To start this assignment, download the baseline code available [here](https://gitlab.cs.mcgill.ca/jguo/COMP303_Winter2021/-/tree/main/Assignments/Assignment-5/assignment5-baseline).

1. Add a `lastWatched()` method to the WatchList class. This method returns a reference to the last Watchable object that has been watched from the watchlist (i.e., on which the method `watch()` from Watchable was last called). Note that you should not rely on the method `next()` from WatchList to know the last watched object (a client might call `watch()` on an arbitrary object since the last call to `next()`). Do not loop through every Watchable object in a WatchList every time the method `lastWatched()` is called. [5 points]
2. Use class inheritance to group common properties and behaviors of `Watchable` implementations to reduce code duplication. Make sure to follow good design principles for inheritance. [5 points]
3. Consider the state-modifying actions of WatchList: `setName`, `addWatchable`, `removeWatchable`, `next`, and `reset`. Add two additional methods, `undo()` and `redo()`. Like most modern software, the `undo()` method reverses the effect of the last state-modifying method call. Calling `undo()` multiple times in a row undoes the actions in the reverse order they were performed, until the first action. Calling `undo()` after all actions have been undone does nothing. Similarly, `redo()` performs again the last undone action, and calling it multiple times performs the undone actions in the reverse order they were undone, until there are no more actions to redo. After the last undone action is redone, calling `redo()` does nothing. However, calling `redo()` right after an action other than `undo()` causes the last action to be repeated. For example, calling `redo()` right after `next()` is equivalent to calling `next()` a second time. If some actions are performed after calling `undo()`, it is no longer possible to `redo()` the undone actions, as it may corrupt the state of the WatchList (and `redo()` instead repeats the last action). As an example of the expected behavior, consider the undo and redo commands in Microsoft Word. Try typing "ABC", then hit Ctrl+Z and Ctrl+Y (or Cmd+Z/Cmd+Y on Mac, not Cmd+Shift+Z) in different sequences. Finally, create a UML state diagram to demonstrate your design. [10 points: same scheme, scaled from 0-5 to 0-10]

## Deliverables

1. The submission must include a zip file of the following:
   1. The source code of your solution in a `src` folder;
   2. A `Driver.java` file (located in the `src` folder) with a `main` method that exercises the main scenarios of the code;
   3. A pdf file of a short description (max. 200 words) of the design of your solution (major decisions, techniques used, trade-offs, etc.).
2. Submit your work on MyCourses -> Content -> Assignment 5 -> PeerGrade (The submission will be open on March 31).

## Rules of Carrying Out Assignment

* The focus of the assignment is to evaluate your application of design techniques while respecting all the requirements in the problem statement. To this end, you are expected to come up with an original design when the requirements don't specify a particular design.
* The goal of this assignment is to arrive at a solution having explored the design space and to understand the trade-offs.
* Remember that there's no single optimal solution. However, you should be able to justify the design choices you made during the assignment both by your code and your description file.
* Try to think of different use cases for your code, and how your solution could accommodate them. Writing your own client code can help identify design issues. For example, you can use real world scenarios to approach the problem statements. However, you don't need to implement features other than those in the problem statement, and you are not expected to implement a perfect solution for all real world scenarios.

## Assessment

Below is the grading scheme that the TAs will use to evaluate your solution:
| Grade | Description                                                  |
| ----- | ------------------------------------------------------------ |
| 0     | No useful elements.                                          |
| 1     | Incoherent set of elements related to the problem statement. |
| 2     | Coherent set of elements towards a working solution.         |
| 3     | Either a complete solution with important design issues or an incomplete solution missing only one key element. The student demonstrates a sufficient understanding of design techniques, but with noticeable gaps. |
| 4     | Good solution with minor issues. The student demonstrates adequate knowledge of the course material. |
| 5     | Excellent solution. The student demonstrates a very good grasp of design concepts and trade-offs. |

## Policy on Code Reuse

1. All design activities are prepared so as to be completed without any need to reuse external code; **"External code" means any code not part of the course samples or Java/JavaFX class libraries;** If you are pining for the a perfect score, please note that using external code will not give you any kind of competitive advantage to help you reach this level.
2. You can reuse external code only for implementation of functionalities that clearly go beyond the requirements; All major design decisions required to fulfill the requirements should be yours and yours only. In case of doubt either refrain from using external code or clarify the situation with your teaching assistant (TA). If you reuse external code it is your responsibility to ensure it does not take away from your required contributions.
3. If you reuse external code it is your responsibility to understand how it works; External code included with a solution is considered part and parcel of the solution: any bug or design flaw caused by external code will be considered a problem with the solution.
4. If you reuse external code you must clearly identify it and locate it in a separate package named `external`, and code files must clearly bear the origin of the external code.
5. If you reuse external code you are responsible for looking up its license and respecting it (in particular any attribution clause).
6. If you reuse external code you are responsible for documenting how it works to the extent where it integrates with your solution. The TAs can be assumed to have knowledge of the Java class libraries, but not external code.
7. Use of external code must be credited anywhere it appears. For example, if you paste a code fragment from Stack Overflow into a comment, this needs to be credited even if it might not appear in the final solution.
8. Any unaccredited contribution of external code will be considered a breach of [academic integrity](https://www.mcgill.ca/students/srr/academicrights/integrity).
In brief, reusing code is a big responsibility, not to be taken lightly. Please read the article [Surviving Software Dependencies](https://cacm.acm.org/magazines/2019/9/238968-surviving-software-dependencies/fulltext) for more insights.
