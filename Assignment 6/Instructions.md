# Assignment 6
*Start on 8 April, complete by 16 April end of day Montreal time zone.*

## Context

Pixar puts you in charge of creating a well-designed, well-documented software to write programs for a robot. That robot is WALL-E (from the movie of the same name) and is responsible for compacting trash into cubes to help clean the Earth of its garbage. To start this assignment, download the baseline code available [here](https://gitlab.cs.mcgill.ca/jguo/COMP303_Winter2021/-/tree/main/Assignments/Assignment-6/assignment6-baseline).


### Objective

Your software should allow a single `Driver` class to create *programs*, composed of a sequence of *actions*, which can then be loaded on WALL-E before being executed. You are not expected to write a user interface to create these programs, only the useful abstractions and logic to make the creation of programs as convenient as possible for client code. In particular, client code **should not** have to directly call any of the methods of the `Robot` interface.

### Robot

WALL-E can move forward a defined distance on the ground. WALL-E can also turn clockwise by a certain number of degrees. Specifying a negative number of degrees results in WALL-E turning anti-clockwise by that number of degrees. WALL-E has one *arm* that it can extend and retract. At the end of the arm is a *gripper* that can open and close in order to hold onto an object. Finally, WALL-E has a *compactor* used to reduce trash to cubes. The compactor can compact up to ten objects before it needs to be emptied. WALL-E is powered by a single battery with a maximum charge of 100 units.

Pixar provides you with an interface, `Robot`, to control each motor of the robot. Pixar also provide you with an implementation of that interface, `WallE`, to develop and test your software. **You cannot modify these files.**

## Requirements

You are presented with the following requirements that must be fulfilled *using good design techniques and principles*.

1. Start by creating abstractions for an *action* and a *program*. A program is a sequence of steps that the robot should perform. After being created, a program is transferred to the robot and executed autonomously (i.e., without additional client code). A program is composed of a sequence of actions, which represent the steps to perform. A single action can involve manipulating several motors of the robot. Client code creates programs by assembling the actions in a sequence. [2 points]

### Actions

2. Create the following set of basic actions. These basic actions are the fundamental building blocks for creating programs, and clients are not expected to have to create new basic actions [2 points]:
    1. Move forward some distance: ensure the arm is retracted, then move forward
    2. Turn 90 degrees to the right or left: ensure the arm is retracted, then turn clockwise or anti-clockwise
    3. Grab an object: extend the arm, close the gripper, retract the arm
    4. Release an object: ensure the arm is retracted, then open the gripper
    5. Compact an object: ensure the gripper holds an object, then compact the object
    6. Empty the compactor: empty the content of the compactor
3. Enforce that the execution of every basic action follows the same protocol [7.5 points]:
    1. First check the state of the battery
    2. If the charge of the battery is <= 5 units, then recharge the battery
    3. Perform the action and update the battery level
4. Provide a way for clients to design their own complex actions that perform a sequence of basic actions. For example, one such complex action could be to "move backward some distance" (WALL-E can not move in reverse, hence to move backward, he would first need to turn backward), and another could be to "grab and compact ten objects, then empty the compactor" [2.5 points].
5. Provide a way for clients to add a new action that will force the battery to be recharged before execution the action, regardless of the current charge [2.5 points].

### Programs

6. Allow clients to create and edit programs. It should also be possible to execute the entire program (i.e., execute each action within it) [2 points].
7. Violating the preconditions of the `Robot` interface can damage the robot. Design your software to prevent the execution of actions that would violate the preconditions of the `Robot` interface [2 points].
8. Implement a way for clients to perform computations related to the actions of a program. For examples of computations that can be performed: given a program, clients should be able to calculate the total distance WALL-E will move, and the total number of objects that will be compacted, without actually executing the program. Make sure to consider complex actions composed of other actions, and use polymorphism effectively [7.5 points].
9. Create a logging system that allows the Pixar development team (including you) to keep track of all the actions performed by the client. Every time an action is performed, log the statement `"X action performed, battery level is Y"` (replacing X with the type of action performed and Y with the updated battery level). Design this feature to avoid unnecessary coupling between the program and actions and the logging system, and to ensure that the way a statement is logged can be easily changed in the future (e.g., printing to the console or writing to a file) [5 points].

### Additional Requirements

10. **Tests:** You must create unit tests to test all aspects of your application. Be as thorough as possible so that you can guarantee to Pixar that the entire application will work with no trouble. Use reflections and stub objects to improve the quality and thoroughness of your tests. Use and report the statement and branch coverage of your tests to see whether there were any parts of your code that you missed. Other developers and testers at Pixar will look at your code and your unit tests to determine whether the application can be approved and is ready to be released [5 points].
11. **Documentation:** Document your code, so that developers can write client code for your software. Also, write a brief description of your software to explain the design techniques you used. Support this with three UML diagrams: (1) a class diagram representing the entire application, (2) a state diagram to represent the valid operations that WALL-E can perform without violating preconditions (requirement 7), and (3) a sequence diagram that demonstrates a computation performed on a program (requirement 8). The Pixar managers will look at this documentation of your application to give their approval for release [8 points].
12. **Overall Design:** The overall solution of your assignment should be coherent. It should also demonstrate the proper application of all design techniques learnt in this course, where applicable. [4 points]

## Deliverables
The submission must include a zip file submitted to MyCourses -> Assignment -> Assignment6:
   1. The source code of your solution in a `src` folder;
   2. A `Driver.java` file (located in the `src` folder) with a `main` method that exercises the main scenarios of the code;
   3. A pdf file of a short description of the design of your solution (major decisions, techniques used, trade-offs, etc.).



Good luck!
