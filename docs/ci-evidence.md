# CI evidence

Full write-up: [README.md](../README.md).

Workflow file: `.github/workflows/ci.yml`

On every push and pull request the workflow does three things:

1. Check out the repository.
2. Install Temurin JDK 17.
3. Run `./mvnw -B test`.

Local run on 12 Oct 2026, after the console menu, status line, and logs were in place:

```text
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

`TodoListTest` has 8 tests. `TodoAppTest` has 6 tests. The workflow runs that same command. Actions: https://github.com/Sergeb250/AGILE-DEVOPS-TODOAPP-/actions
