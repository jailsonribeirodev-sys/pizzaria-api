# Repository Guidelines & Standards

## Git Commit & Push Standards

### 1. Commit Message Convention
Always use the **Conventional Commits** specification (`v1.0.0`) in lowercase English:
```text
<type>(<scope>): <short description>
```

- **Types**:
  - `feat`: A new feature or endpoint (e.g., `feat(controller): add address endpoints`)
  - `refactor`: Code change that neither fixes a bug nor adds a feature (e.g., `refactor(domain): update entity constructors`)
  - `fix`: A bug fix
  - `chore`: Tooling, build scripts, dependencies, `.gitignore` maintenance
  - `docs`: Documentation updates or additions (e.g., `docs: update README with API spec`)
  - `test`: Adding or refactoring automated tests

- **Scopes**:
  - `domain`: JPA entities, relations, enums
  - `dto`: Request, response, or wrapper records
  - `service`: Business logic, transactional boundaries
  - `controller`: REST endpoints, mapping, Swagger annotations
  - `config`: Spring beans, OpenAPI, datasource, properties
  - `repo`: Repository interfaces

### 2. Commit Granularity
- Make atomic, logically isolated commits grouped by architectural layer.
- Never bundle unrelated changes (e.g., separate config/dependencies from domain logic and DTOs).

### 3. Verification Before Commits
- Always execute `./mvnw test-compile` or `./mvnw test` to ensure zero compilation or context-boot errors before staging.

### 4. Git Push Policy
- Never push automatically (`git push`) without explicit confirmation or instruction from the user.
