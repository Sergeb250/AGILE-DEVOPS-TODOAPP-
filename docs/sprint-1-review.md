# Sprint 1 Review

**Sprint goal:** A user can add a task and see the list.

**Result:** Goal met. US-01 and US-02 are done. US-03, US-04, US-05, and US-06 stay in the backlog.

## Done this sprint

| Story | Points | Evidence |
|-------|--------|----------|
| US-01 Add a to-do | 3 | Menu option 1 asks for a title and prints `Added 1. [ ] Buy milk`. A blank title prints `Title is required` and is not stored. |
| US-02 List to-dos | 2 | Menu option 2 prints `No todos.` when the list is empty, otherwise each item in creation order. |

Committed points: 5. Completed points: 5.

## Demo

```text
mvnw.cmd -q exec:java
```

```text
1) Add
2) List
0) Exit
> 1
Title: Buy milk
Added 1. [ ] Buy milk
1) Add
2) List
0) Exit
> 2
1. [ ] Buy milk
1) Add
2) List
0) Exit
> 0
```

Blank title:

```text
> 1
Title:
Title is required
```

## Tests

`mvn test` for the list class:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Covered cases: add an open to-do, trim the title, reject a blank or missing title, and keep creation order.

## CI

`.github/workflows/ci.yml` checks out the repo, installs Temurin 17, and runs `./mvnw -B test` on every push and pull request. The Maven Wrapper is in the repo so the pipeline does not depend on a preinstalled Maven.

## Not done

- Mark complete (US-03)
- Delete (US-04)
- Status and action logs (US-05)
- Filter by status (US-06)

Todos live in memory. Closing the app clears them.
