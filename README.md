# FlowLoop

An end-to-end AI Agent platform built with Java + Spring AI + React.

This project includes:
- `core/`: Java backend (Spring Boot, Spring AI, MyBatis, PostgreSQL, SSE)
- `ui/`: React frontend (Vite, TypeScript, Ant Design)

## Why this project

`JChatMind Studio` is designed as a portfolio-ready AI agent system that demonstrates:
- Multi-model LLM integration (`DeepSeek` and `ZhipuAI/GLM`)
- Tool-augmented agent workflow (email, knowledge query, filesystem, database, direct answer, terminate)
- Knowledge base and document management with vector-ready data model
- Real-time streaming chat via Server-Sent Events (SSE)
- Full-stack engineering across backend APIs + modern frontend UI

## Tech Stack

- Backend: Java 17, Spring Boot 3, Spring AI, MyBatis, PostgreSQL
- Frontend: React 19, TypeScript, Vite, Ant Design 6, Tailwind CSS
- Other: SSE streaming, RESTful API, Markdown parsing, SMTP email support

## Project Structure

```text
Java AI Agent/
├─ core/   # Spring Boot backend
└─ ui/     # React frontend
```

## Quick Start

### 1. Backend

```bash
cd "Java AI Agent/core"
./mvnw spring-boot:run
```

Update configuration in:
- `core/src/main/resources/application.yaml`

Key fields to configure:
- PostgreSQL datasource (`spring.datasource.*`)
- LLM API keys (`spring.ai.deepseek.api-key`, `spring.ai.zhipuai.api-key`)
- SMTP settings (`spring.mail.*`) if email tool is used

### 2. Frontend

```bash
cd "Java AI Agent/ui"
npm install
npm run dev
```

By default, Vite runs on `http://localhost:5173`.

## Core Capabilities

- Agent management (create/update/list)
- Chat session and message management
- Knowledge base and document management
- Optional tools retrieval for dynamic agent tooling
- SSE connection endpoint for streaming responses

## Portfolio Summary

Built and shipped a full-stack AI Agent platform using Java Spring AI + React, with multi-LLM support, tool-calling workflow, knowledge-base integration, and real-time streaming chat.

## License

MIT License. See [LICENSE](LICENSE).
