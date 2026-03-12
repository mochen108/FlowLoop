# Changelog

All notable changes to this project are documented in this file.

The format is based on Keep a Changelog, and this project follows Semantic Versioning.

## [Unreleased]

### Added
- Continuous integration checks for backend and frontend builds on pull requests.

## [0.4.0] - 2026-03-12

### Added
- GitHub Actions workflow for Java backend build and React frontend build.
- Centralized changelog to track release history.

### Changed
- Refined commit history wording to team-style conventional commit messages.

## [0.3.0] - 2026-03-12

### Added
- Docker Compose setup for local PostgreSQL development.
- `application.example.yaml` template for onboarding.

### Changed
- Backend configuration moved to environment-variable based defaults.
- README rewritten with clearer setup and security guidance.

## [0.2.0] - 2026-01-29

### Added
- Spring AI model integrations (DeepSeek and ZhipuAI).
- Tool-enabled agent flow with knowledge, filesystem, database, and email tools.
- SSE-based real-time chat streaming support.

### Changed
- Stabilized backend and frontend module structure (`core/` and `ui/`).

## [0.1.0] - 2025-12-15

### Added
- Initial project scaffold for FlowLoop full-stack AI agent platform.
- Core backend service bootstrap (Spring Boot, MyBatis, PostgreSQL connection).
- Frontend scaffold (React + TypeScript + Vite).
- MIT license and baseline documentation.
