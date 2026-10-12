# CI evidence

Workflow file: `.github/workflows/ci.yml`

On every push and pull request, GitHub Actions checks out the repo, installs Temurin 17, and runs `./mvnw -B test`.

Local run on 12 Oct 2026, after status and logging:

```text
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

The same command is what the workflow runs. A green run appears on the Actions tab after this history is pushed:

https://github.com/Sergeb250/AGILE-DEVOPS-TODOAPP-/actions
