# Assignment 3
​
*Start on February 18th, complete by Monday, March 1st by end of day Montreal time zone. Covers Chapter 4 of the book. The peer review will be open for 72 hours after the submission deadline.*
​
## Problem Statement

Using the principles, mechanisms, and techniques seen in Chapter 4 of the book, design and write the code necessary to enhance the movie library design from the baseline code (adapted from solutions to Assignment 2) to meet the following requirements. *Your solution should continue to respect principles of good design seen in prior chapters.*
​
To start this assignment, download the baseline code available [here](https://gitlab.cs.mcgill.ca/jguo/COMP303_Winter2021/-/tree/main/Assignments/Assignment-3/assignment3-baseline).
​
1. Allow only one Library object to ever be created in your application. Add two fields -- a Name and an EmailID -- to the library, both of which can be changed by the client. The Library must always have a Name, but the EmailID may be missing. Ensure you implement appropriate getters and setters. [5 points]
2. For any given title, there can only ever be one Movie object with that title in your application. Implement this constraint for TV Shows as well. Hint: you should not throw exceptions when the title already exists in the library. [5 points]
3. Implement an equality criteria for watchlists based on their content. Two watchlists are considered duplicates if they aggregate the same Watchable objects in the same order, regardless of the name of the watchlists. [5 points]

​
## Deliverables
​
1. The submission must include a zip file of the following:
   1. The source code of your solution in a `src` folder;
   2. A `Driver.java` file (located in the `src` folder) with a `main` method that exercises the main scenarios of the code;
   3. A pdf file of a short description (max. 200 words) of the design of your solution (major decisions, techniques used, trade-offs, etc.), supported by at least one appropriate UML diagram created with JetUML.
2. Please use this template to name your submission: *[COMP303 Assignment-3]McGillID_FirstName_LastName*   
3. Submit your work on MyCourses -> Content -> Assignment 3 -> PeerGrade (The submission will be open on Feb 25th).
​
## Rules of Carrying Out Assignment
​
* The focus of the assignment is to evaluate your application of design techniques while respecting all the requirements in the problem statement. To this end, you are expected to come up with an original design when the requirements don't specify a particular design.
* The goal of this assignment is to arrive at a solution having explored the design space and to understand the trade-offs.
* Remember that there's no single optimal solution. However, you should be able to justify the design choices you made during the assignment both by your code and your description file.
* Try to think of different use cases for your code, and how your solution could accommodate them. Writing your own client code can help identify design issues. For example, you can use real world scenarios to approach the problem statements. However, you don't need to implement features other than those in the problem statement, and you are not expected to implement a perfect solution for all real world scenarios.
​
## Assessment
​
*Both* the peer review and the TA evaluation of your solution will focus on three aspects:
​
1. How well does the delivered code satisfy the requirements in the problem statement?
2. How well are the design concepts, principles, and techniques are applied and explained in the description file (including the trade-offs of different options)? Although the focus will be on techniques from Chapter 3, you can be penalized for violating design principles from earlier chapters.
3. How readable is the source code (through its style and documentation)?
​
*Note: during peer review, you will not be giving your classmate a grade. Instead, you are reviewing and providing feedback to your classmate’s work. The TAs will grade your peer review based on the quality of your review.*
​
## Grading Scheme During TA evaluation
​
Below is the grading scheme that the TAs will use to evaluate your solution during the interactive session:
| Grade | Description                                                  |
| ----- | ------------------------------------------------------------ |
| 0     | No useful elements.                                          |
| 1     | Incoherent set of elements related to the problem statement. |
| 2     | Coherent set of elements towards a working solution.         |
| 3     | Either a complete solution with important design issues or an incomplete solution missing only one key element. The student demonstrates a sufficient understanding of design techniques, but with noticeable gaps. |
| 4     | Good solution with minor issues. The student demonstrates adequate knowledge of the course material. |
| 5     | Excellent solution. The student demonstrates a very good grasp of design concepts and trade-offs. |
​
## Policy on Code Reuse
​
1. All design activities are prepared so as to be completed without any need to reuse external code; **"External code" means any code not part of the course samples or Java/JavaFX class libraries;** If you are pining for the a perfect score, please note that using external code will not give you any kind of competitive advantage to help you reach this level.
2. You can reuse external code only for implementation of functionalities that clearly go beyond the requirements; All major design decisions required to fulfill the requirements should be yours and yours only. In case of doubt either refrain from using external code or clarify the situation with your teaching assistant (TA). If you reuse external code it is your responsibility to ensure it does not take away from your required contributions.
3. If you reuse external code it is your responsibility to understand how it works; External code included with a solution is considered part and parcel of the solution: any bug or design flaw caused by external code will be considered a problem with the solution.
4. If you reuse external code you must clearly identify it and locate it in a separate package named `external`, and code files must clearly bear the origin of the external code.
5. If you reuse external code you are responsible for looking up its license and respecting it (in particular any attribution clause).
6. If you reuse external code you are responsible for documenting how it works to the extent where it integrates with your solution. The TAs can be assumed to have knowledge of the Java class libraries, but not external code.
7. Use of external code must be credited anywhere it appears. For example, if you paste a code fragment from Stack Overflow into a comment, this needs to be credited even if it might not appear in the final solution.
8. Any unaccredited contribution of external code will be considered a breach of [academic integrity](https://www.mcgill.ca/students/srr/academicrights/integrity).
In brief, reusing code is a big responsibility, not to be taken lightly. Please read the article [Surviving Software Dependencies](https://cacm.acm.org/magazines/2019/9/238968-surviving-software-dependencies/fulltext) for more insights.
