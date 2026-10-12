# Sprint 1 Retrospective

Full write-up: [README.md](../README.md).

## What went well

- The sprint goal stayed small: add a task, then see the list. Both stories finished inside the sprint with no spillover.
- Acceptance criteria were specific enough to turn into tests. Blank titles and an empty list were named before coding.
- The local test command and the CI command are the same: `./mvnw -B test`.

## What to improve

1. **Tests never ran the console menu.** `TodoListTest` checks `TodoList` only. A broken menu could still pass CI. Sprint 2 will feed the menu keystrokes and read what it prints.
2. **Nothing shows that the app is up, and actions are silent.** Sprint 2 will add a Status command that prints `status=UP`, and one `LOG` line for every action, including failures.

## Actions for Sprint 2

| Action | Where it lands |
|--------|----------------|
| Test the console menu with scripted input | First Sprint 2 commit, before new stories |
| Add Status and a log line per action | US-05, after complete and delete |

US-06 (filter by status) stays in the backlog. Pulling it in would crowd out these two actions.
