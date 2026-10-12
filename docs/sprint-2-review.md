# Sprint 2 Review

Full write-up: [README.md](../README.md).

**Sprint goal:** A user can finish or remove a task, and an operator can see that the app is up.

**Result:** Goal met. US-03, US-04, and US-05 are done. US-06 stays in the backlog.

## Done this sprint

| Story | Points | What shipped |
|-------|--------|----------------|
| US-03 Complete a to-do | 3 | Menu option 3 asks for an id and prints `Completed 1. [x] Buy milk`. An unknown id prints `Todo not found`. |
| US-04 Delete a to-do | 2 | Menu option 4 removes that id. A later list prints `No todos.` when it was the last one. An unknown id prints `Todo not found`. |
| US-05 Status and logs | 2 | Menu option 5 prints `status=UP todos=N`. Every action prints one `LOG` line. |

Committed points: 7. Completed points: 7.

The Sprint 1 retrospective asked for menu tests and for logs. Both are in place. The app is three classes: `Todo`, `TodoList`, and `TodoApp`.

## Demo

```text
mvnw.cmd -q exec:java
```

The menu is printed before every choice. A full session is in the README. The lines the user sees for each action are:

```text
> 1
Title: Buy milk
Added 1. [ ] Buy milk
LOG add id=1
> 3
Id: 1
Completed 1. [x] Buy milk
LOG complete id=1
> 4
Id: 1
Deleted 1
LOG delete id=1
> 5
status=UP todos=0
LOG status=UP todos=0
> 0
```

## Tests

`mvn test` on 12 Oct 2026:

```text
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

`TodoListTest` has 8 tests. `TodoAppTest` has 6 tests that type menu choices and read the printed lines. CI runs `./mvnw -B test`.

## Not done

US-06, filter by completed status, is still in the backlog. Closing the app clears the list.
