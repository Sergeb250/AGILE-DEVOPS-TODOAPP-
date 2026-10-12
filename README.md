# Agile and DevOps practice — console to-do app

Individual assessment: plan, build, and improve a small working program across two simulated sprints. The product is a console menu, not a web service. The point is the backlog, the sprint reviews, the tests, the commit history, and the CI pipeline.

Repository: https://github.com/Sergeb250/AGILE-DEVOPS-TODOAPP-

Author of the commits: SergeB250

## Product vision

A small console to-do app lets a person add tasks, see the list, mark work done, and remove tasks they no longer need. The same app prints a status line and a log line for each action so a run can be checked the same way every time.

## How to run

Java 17 or newer is required. Maven does not need to be installed. The repo includes the Maven Wrapper.

```text
mvnw.cmd test
mvnw.cmd -q exec:java
```

On macOS or Linux, use `./mvnw` instead of `mvnw.cmd`.

Stop the app by choosing `0`.

Todos stay in memory. Closing the app clears the list.

## Classes

| Class | Role |
|-------|------|
| `Todo` | One task: id, title, and whether it is complete. `markCompleted()` sets it done. `toString()` prints `1. [ ] Buy milk` or `1. [x] Buy milk`. |
| `TodoList` | The list. `add`, `all`, `complete`, `delete`, and `size`. A blank title throws `Title is required`. An unknown id throws `Todo not found`. |
| `TodoApp` | The console menu. It reads a choice, calls `TodoList`, and prints the result plus a `LOG` line. |

Files:

| Path | What it is |
|------|------------|
| `src/main/java/com/todo/Todo.java` | One to-do |
| `src/main/java/com/todo/TodoList.java` | In-memory list |
| `src/main/java/com/todo/TodoApp.java` | Menu and `main` |
| `src/test/java/com/todo/TodoListTest.java` | Tests for the list |
| `src/test/java/com/todo/TodoAppTest.java` | Tests that type into the menu |
| `pom.xml` | Maven build, Java 17, JUnit 5 |
| `.github/workflows/ci.yml` | GitHub Actions |
| `docs/` | Sprint plan, reviews, retrospectives, CI note |

## Menu

```text
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
>
```

| Choice | What the user sees |
|--------|--------------------|
| 1 Add | Asks `Title:`. Prints `Added 1. [ ] Buy milk` and `LOG add id=1`. A blank title prints `Title is required` and stores nothing. Spaces around the title are removed. |
| 2 List | Prints each to-do, oldest first, or `No todos.` Also prints `LOG list count=N`. |
| 3 Complete | Asks `Id:`. Prints `Completed 1. [x] Buy milk`. Doing it again leaves the item complete. An unknown id prints `Todo not found`. |
| 4 Delete | Asks `Id:`. Prints `Deleted 1`. That id is gone from the next list. An unknown id prints `Todo not found`. |
| 5 Status | Prints `status=UP todos=N` and `LOG status=UP todos=N`. |
| 0 Exit | Stops the loop. |
| anything else | Prints `Unknown option`. |

A number that is not a whole id prints `Id must be a number`.

### Demo

```text
mvnw.cmd -q exec:java
```

The menu is printed again after every action. This is a real session:

```text
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 1
Title: Buy milk
Added 1. [ ] Buy milk
LOG add id=1
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 2
LOG list count=1
1. [ ] Buy milk
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 3
Id: 1
Completed 1. [x] Buy milk
LOG complete id=1
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 5
status=UP todos=1
LOG status=UP todos=1
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 4
Id: 1
Deleted 1
LOG delete id=1
1) Add
2) List
3) Complete
4) Delete
5) Status
0) Exit
> 0
```

A blank title looks like this:

```text
> 1
Title:
Title is required
LOG error Title is required
```

## Definition of Done

A backlog item is done only when all of the following are true:

1. Every acceptance criterion for that story has been checked.
2. `mvn test` passes, including a test that covers the new behavior.
3. The change is in git as its own commit, not bundled into a single dump of the whole app.
4. The GitHub Actions workflow runs that same test command on push.
5. The sprint review names the story and shows what the user sees in the console.

Sprint 2 added two checks from the Sprint 1 retrospective:

6. Each action writes one console log line.
7. Tests drive the console menu, not only the list class.

## Product backlog

Estimates are story points. A lower priority number means do it sooner.

| ID | User story | Points | Priority | Sprint |
|----|------------|--------|----------|--------|
| US-01 | As a user, I want to add a to-do with a title so that I can remember a task. | 3 | 1 | Sprint 1 |
| US-02 | As a user, I want to see all of my to-dos so that I know what is still open. | 2 | 2 | Sprint 1 |
| US-03 | As a user, I want to mark a to-do complete so that I can track progress. | 3 | 3 | Sprint 2 |
| US-04 | As a user, I want to delete a to-do so that I can remove a task I no longer need. | 2 | 4 | Sprint 2 |
| US-05 | As an operator, I want a status line and action logs so that I can tell the app is running. | 2 | 5 | Sprint 2 |
| US-06 | As a user, I want to filter to-dos by completed status so that I can focus on open work. | 3 | 6 | Later |

Total points: 15. US-06 is still open.

### Acceptance criteria

**US-01 — Add a to-do**

- Given a non-blank title, when the user chooses Add and types the title, then the app prints the new to-do with an id, the title, and an open mark.
- Given a missing or blank title, when the user chooses Add, then the app prints `Title is required` and stores nothing.
- Stored titles have leading and trailing spaces removed.

**US-02 — List to-dos**

- Given no to-dos, when the user chooses List, then the app prints `No todos.`
- Given several to-dos, when the user chooses List, then the app prints them in the order they were created, each as `id. [ ] title` or `id. [x] title`.

**US-03 — Complete a to-do**

- Given a stored to-do, when the user chooses Complete and types its id, then the list shows `[x]` for that id.
- Completing a to-do that is already complete leaves it complete.
- Given an unknown id, when the user chooses Complete, then the app prints `Todo not found`.

**US-04 — Delete a to-do**

- Given a stored to-do, when the user chooses Delete and types its id, then a later list does not include that id.
- Given an unknown id, when the user chooses Delete, then the app prints `Todo not found`.

**US-05 — Status and logs**

- When the user chooses Status, the app prints `status=UP` and the number of to-dos.
- Add, list, complete, delete, status, and failed actions each print one `LOG` line.

**US-06 — Filter (not built)**

- A list of open to-dos shows only items that are not complete.
- A list of done to-dos shows only completed items.
- The normal list still shows every to-do.

## Sprint 0 — Planning

Tech choice: plain Java 17, three classes, Maven, and JUnit 5. No database and no web server.

### Sprint 1 plan

**Sprint goal:** A user can add a task and see the list.

**Selected:** US-01 (3), US-02 (2). Committed points: 5.

**Left in the backlog:** US-03, US-04, US-05, US-06.

Tasks:

1. Create the Maven project and the `Todo` class.
2. Implement add and list on `TodoList`, with unit tests.
3. Offer Add and List from the console menu.
4. Add a GitHub Actions workflow that runs the tests.
5. Demo the menu and hold the retrospective.

### Sprint 2 plan

**Sprint goal:** A user can finish or remove a task, and an operator can see that the app is up.

**Selected:** US-03 (3), US-04 (2), US-05 (2). Committed points: 7.

US-06 stays out so the sprint can also take the retrospective actions: tests that drive the menu, and a log line per action.

Tasks:

1. Add a test that runs the console menu for add and list.
2. Implement complete and delete, with tests.
3. Add Status and one `LOG` line per action.
4. Demo the new commands and hold the retrospective.

## Sprint 1 — Review

**Result:** Goal met. US-01 and US-02 are done. 5 points committed, 5 points completed.

| Story | Points | Evidence |
|-------|--------|----------|
| US-01 Add a to-do | 3 | Option 1 prints `Added 1. [ ] Buy milk`. A blank title prints `Title is required` and is not stored. |
| US-02 List to-dos | 2 | Option 2 prints `No todos.` when empty, otherwise each item in creation order. |

Test run during Sprint 1, list class only:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Still open after Sprint 1: complete, delete, status and logs, filter.

### Sprint 1 retrospective

What went well:

- The goal stayed small. Both stories finished with no spillover.
- Blank titles and an empty list were acceptance criteria before coding, and both have tests.
- Local tests and CI use the same command: `./mvnw -B test`.

What Sprint 2 changed:

1. Sprint 1 tests checked `TodoList` and never typed into the menu. A broken menu could still pass CI. Sprint 2 adds `TodoAppTest`, which feeds keystrokes and reads the printed text.
2. Nothing showed that the app was up, and actions wrote no log. Sprint 2 adds option 5 (`status=UP`) and one `LOG` line per action, including failures.

## Sprint 2 — Review

**Result:** Goal met. US-03, US-04, and US-05 are done. 7 points committed, 7 points completed. US-06 stays in the backlog.

| Story | Points | What shipped |
|-------|--------|----------------|
| US-03 Complete a to-do | 3 | Option 3 prints `Completed 1. [x] Buy milk`. Unknown id prints `Todo not found`. |
| US-04 Delete a to-do | 2 | Option 4 removes the id. Unknown id prints `Todo not found`. |
| US-05 Status and logs | 2 | Option 5 prints `status=UP todos=N`. Every action prints one `LOG` line. |

Final test run on 12 Oct 2026:

```text
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Sprint 2 retrospective

What went well:

- Menu tests and logging, the two Sprint 1 actions, landed before the sprint was called done.
- Complete and delete each have their own commit and a test.
- `Todo`, `TodoList`, and `TodoApp` were enough.

What to improve next time:

- Start with the console menu. Extra structure was not needed for this prototype.
- US-06 can wait until the list is saved and survives closing the app.

Lesson: the useful change from Sprint 1 to Sprint 2 was testing the menu the user sees and logging each action.

## Testing

| Test | What it checks |
|------|----------------|
| `addStoresAnOpenTodo` | id 1, title, not complete, text `1. [ ] Buy milk` |
| `addTrimsTheTitle` | surrounding spaces are removed |
| `addRejectsABlankTitle` | blank and null titles throw `Title is required` and the list stays empty |
| `listKeepsCreationOrder` | two adds come back in that order |
| `completeMarksTheTodoDone` | complete sets `[x]`, and completing again stays done |
| `completeRejectsAnUnknownId` | id 9 throws `Todo not found` |
| `deleteRemovesTheTodo` | delete leaves the list empty |
| `deleteRejectsAnUnknownId` | id 9 throws `Todo not found` |
| `userCanAddAndList` | menu 1 then 2 prints the added line and the list line |
| `blankTitleIsRejected` | menu add of an empty title prints `Title is required` and `No todos.` |
| `userCanCompleteATodo` | menu complete prints `[x]` |
| `userCanDeleteATodo` | menu delete then list prints `Deleted 1` and `No todos.` |
| `statusAndLogsArePrinted` | status prints `status=UP`, and add and list print `LOG` lines |
| `missingIdIsNotFound` | complete and delete of id 9 print `Todo not found` |

Run:

```text
mvnw.cmd test
```

## CI/CD

Workflow: `.github/workflows/ci.yml`

Trigger: every push and every pull request.

Steps:

1. Check out the repository (`actions/checkout@v4`).
2. Set up Temurin JDK 17 (`actions/setup-java@v4`).
3. Run `./mvnw -B test`.

The wrapper downloads Maven 3.9.11, so the runner does not need Maven installed first.

Local result on 12 Oct 2026:

```text
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

That is the command the workflow runs. The Actions page for this repository is https://github.com/Sergeb250/AGILE-DEVOPS-TODOAPP-/actions.

## Commit history

Newest first. Every commit is by SergeB250.

| Commit | What changed |
|--------|----------------|
| `773089c` | Name the documentation commit in the README |
| `01fc2e1` | Record the Sprint 2 review and this README |
| `8f5a222` | Add a status line and one log per action |
| `d562bf9` | Delete a to-do from the menu |
| `40e2b89` | Mark a to-do complete from the menu |
| `87a3f04` | Test the console menu for add and list |
| `7da3b73` | Record the Sprint 1 review and retrospective |
| `dccff19` | Run the tests in GitHub Actions |
| `6c03098` | Offer add and list from the console menu |
| `a3ddba8` | Add and list to-dos, with unit tests |
| `74ad9f8` | Add the Todo class |
| `5cf5fe1` | Add a Maven build for the console to-do app |
| `1319bbf` | Plan the backlog, estimates, and definition of done |

Sprint 1 is the plan through the first review (`1319bbf` to `7da3b73`). Sprint 2 is the menu tests, complete, delete, status, and the second review.

## Known limits

- The list is in memory. Closing the app drops every to-do.
- US-06, filter by completed status, is not built.
- There is no login and no saved file.
