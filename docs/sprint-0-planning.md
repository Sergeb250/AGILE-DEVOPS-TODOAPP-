# Sprint 0 — Planning

Full write-up: [README.md](../README.md). The app is a console menu with three classes: `Todo`, `TodoList`, and `TodoApp`.

## Product vision

A small console to-do app lets a person add tasks, see the list, mark work done, and remove tasks they no longer need. The same app prints a status line and a log line for each action so a run can be checked the same way every time.

## Tech choice

Plain Java (release 17), three classes (`Todo`, `TodoList`, `TodoApp`), Maven, and JUnit 5. Todos stay in memory. That is enough for a working prototype, tests, and a CI workflow in two sprints.

## Definition of Done

A backlog item is done only when all of the following are true:

1. Every acceptance criterion for that story has been checked.
2. `mvn test` passes, including a test that covers the new behavior.
3. The change is in git as its own commit, not bundled into a single dump of the whole app.
4. The GitHub Actions workflow runs that same test command on push.
5. The sprint review names the story and shows what the user sees in the console.

Sprint 2 adds two checks, taken from the Sprint 1 retrospective:

6. Each action writes one console log line.
7. Tests drive the console menu, not only the list class.

## Product backlog

Estimates use story points (relative size). A lower priority number means do it sooner.

| ID | User story | Points | Priority | Sprint |
|----|------------|--------|----------|--------|
| US-01 | As a user, I want to add a to-do with a title so that I can remember a task. | 3 | 1 | Sprint 1 |
| US-02 | As a user, I want to see all of my to-dos so that I know what is still open. | 2 | 2 | Sprint 1 |
| US-03 | As a user, I want to mark a to-do complete so that I can track progress. | 3 | 3 | Sprint 2 |
| US-04 | As a user, I want to delete a to-do so that I can remove a task I no longer need. | 2 | 4 | Sprint 2 |
| US-05 | As an operator, I want a status line and action logs so that I can tell the app is running. | 2 | 5 | Sprint 2 |
| US-06 | As a user, I want to filter to-dos by completed status so that I can focus on open work. | 3 | 6 | Later |

Total points: 15. US-06 stays in the backlog. It is not part of these two sprints.

## Acceptance criteria

### US-01 — Add a to-do

- Given a non-blank title, when the user chooses Add and types the title, then the app prints the new to-do with an id, the title, and an open mark.
- Given a missing or blank title, when the user chooses Add, then the app prints `Title is required` and stores nothing.
- Stored titles have leading and trailing spaces removed.

### US-02 — List to-dos

- Given no to-dos, when the user chooses List, then the app prints `No todos.`
- Given several to-dos, when the user chooses List, then the app prints them in the order they were created, each as `id. [ ] title` or `id. [x] title`.

### US-03 — Complete a to-do

- Given a stored to-do, when the user chooses Complete and types its id, then the list shows `[x]` for that id.
- Completing a to-do that is already complete leaves it complete.
- Given an unknown id, when the user chooses Complete, then the app prints `Todo not found`.

### US-04 — Delete a to-do

- Given a stored to-do, when the user chooses Delete and types its id, then a later list does not include that id.
- Given an unknown id, when the user chooses Delete, then the app prints `Todo not found`.

### US-05 — Status and logs

- When the user chooses Status, the app prints `status=UP` and the number of to-dos.
- Add, list, complete, delete, status, and failed actions each print one `LOG` line.

### US-06 — Filter (not scheduled)

- A list of open to-dos shows only items that are not complete.
- A list of done to-dos shows only completed items.
- The normal list still shows every to-do.

## Sprint 1 plan

**Sprint goal:** A user can add a task and see the list.

**Selected:** US-01 (3), US-02 (2). Committed points: 5.

**Not pulled in:** US-03, US-04, US-05, US-06. Completing and deleting tasks waits until add and list work. Status and logs come after the first review.

**Tasks:**

1. Create the Maven project and the `Todo` class.
2. Implement add and list on `TodoList`, with unit tests.
3. Offer Add and List from the console menu.
4. Add a GitHub Actions workflow that runs `mvn test`.
5. Demo the menu and hold the retrospective.

## Sprint 2 plan

**Sprint goal:** A user can finish or remove a task, and an operator can see that the app is up.

**Selected:** US-03 (3), US-04 (2), US-05 (2). Committed points: 7.

Sprint 2 is larger than Sprint 1 because US-04 and US-05 are small, and Sprint 1 closed its 5 points with nothing left over. US-06 stays out so the sprint can also take the retrospective actions: tests that drive the menu, and a log line per action.

**Tasks:**

1. Add a test that runs the console menu for add and list.
2. Implement complete and delete, with tests.
3. Add Status and one `LOG` line per action.
4. Demo the new commands and hold the retrospective.
