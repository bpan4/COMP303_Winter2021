# Assignment 2

*Start on 29 January, complete by Friday, 5 February end of day Montreal time zone. Covers Chapter 3 of the book.*

## Problem Statement

Using the principles, mechanisms, and techniques seen in Chapter 3 of the book, design and write the code necessary to enhance the movie library design realized in Assignment 1 to meet the following requirements. *Your solution should continue to respect principles of good design seen in prior chapters.*

To start this assignment, you can choose to either start from the code you submitted for Assignment 1, or from the baseline code available at [here](https://gitlab.cs.mcgill.ca/jguo/COMP303_Winter2021/-/tree/main/Assignments/Assignment-2/assignment2_baseline).

1. Add to your library support for TV shows, which are composed of episodes. Each episode corresponds to a specific file path and can capture custom information, similarly to movies. It should be possible to identify an episode by its sequential number. Then, use **interfaces** to support multiple implementations of the following domain concepts: something that can be *watched* (e.g., a movie, an episode, a whole TV show), something that can be *binged* (watchlists and TV shows), and something that can be preceded or followed by related elements (movies can have sequels and episodes are in a sequence). Add relevant methods to your interfaces to show how they differ, and design your interfaces carefully using the **Interface Segregation Principle**. [5 points]
2. Provide a way to access the elements in any *bingeable* object, using good design principles. Keep the solution as general as possible. [5 points]
3. Add a **watchlist generation feature** to your Library class. This feature allows client code to automatically generate watchlists based on algorithms specified by the client. For example, a client may want to include create a watchlist of all English movies from a set of studios, or ten random episodes from a TV show. To illustrate your design, use this feature to generate two different watchlists in your Driver code. [5 points]

## Deliverables

1. The submission must include a zip file of the following:
   1. The source code of your solution in a `src` folder;
   2. A `Driver.java` file (located in the `src` folder) with a `main` method that exercises the main scenarios of the code;
   3. An empty file named `baseline` if you used the baseline code provided as a starting point, or a folder named `baseline` that contains your own baseline code (i.e., the `src` folder from Assignment 1);
   4. A pdf file of a short description (max. 200 words) of the design of your solution (major decisions, techniques used, trade-offs, etc.), supported by at least one UML class diagram created with JetUML.
2. Submit your work on MyCourses -> Assignment -> Assignment2.

## Rules of Carrying Out Assignment

* The focus of the assignment is to evaluate your application of design techniques while respecting all the requirements in the problem statement. To this end, you are expected to come up with an original design when the requirements don't specify a particular design.
* The goal of this assignment is to arrive at a solution having explored the design space and to understand the trade-offs.
* Remember that there's no single optimal solution. However, you should be able to justify the design choices you made during the assignment both by your code and your description file.
* Try to think of different use cases for your code, and how your solution could accommodate them. Writing your own client code can help identify design issues. For example, you can use real world scenarios to approach the problem statements. However, you don't need to implement features other than those in the problem statement, and you are not expected to implement a perfect solution for all real world scenarios.

## Assessment

There will be NO peer assessment for this assignment. The TA evaluation of your solution will focus on three aspects:

1. How well does the delivered code satisfy the requirements in the problem statement?
2. How well are the design concepts, principles, and techniques are applied and explained in the description file (including the trade-offs of different options)? Although the focus will be on techniques from Chapter 3, you can be penalized for violating design principles from earlier chapters.
3. How readable is the source code (through its style and documentation)?

## Grading Scheme During TA evaluation

Below is the grading scheme that the TAs will use to evaluate your solution during the interactive session:
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
