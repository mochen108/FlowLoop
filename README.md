# FlowLoop

FlowLoop is a full-stack AI agent platform built with Java (Spring AI) and React. This project focuses on practical AI agent workflows, including multi-model orchestration, tool integration, streaming conversations, and a full-stack architecture for iterative product development.

## Highlights

- Multi-model LLM support (`DeepSeek` + `ZhipuAI/GLM`)
- Tool-enabled agent workflow (knowledge, database, filesystem, email, direct answer)
- Real-time response streaming with Server-Sent Events (SSE)
- Knowledge base + document lifecycle APIs
- Clean split architecture: `core` backend + `ui` frontend

## Stack

- Backend: Java 17, Spring Boot 3, Spring AI, MyBatis, PostgreSQL
- Frontend: React 19, TypeScript, Vite, Ant Design
- Infra: Docker Compose (local Postgres)

## Repository Structure

```text
.
├─ core/               # Spring Boot API + agent runtime
├─ ui/                 # React web app
├─ docker-compose.yml  # Local Postgres service
└─ README.md
```

## Local Setup

### 1. Start database

```bash
docker compose up -d
```

### 2. Configure backend env

Use environment variables (recommended), or copy:

- `core/src/main/resources/application.example.yaml`
- to `core/src/main/resources/application-local.yaml`

Then set your API keys and credentials.

### 3. Run backend

```bash
cd core
./mvnw spring-boot:run
```

### 4. Run frontend

```bash
cd ui
copy .env.example .env
npm install
npm run dev
```

Frontend default URL: `http://localhost:5173`

### 5. Verify backend health

After backend startup, verify health endpoint:

```bash
curl http://localhost:8080/actuator/health
```

## Notes

- `application.yaml` is parameterized with environment variables.

## License

MIT. See [LICENSE](LICENSE).
