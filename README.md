# API - Controle de horas

Esta API permite gerenciar squads, funcionários e relatórios de horas gastas.

## 🛠️ Tecnologias
- Java 17 + Spring Boot
- PostgreSQL 14
- Postman para testes
- Para o front-end será usado Angular
## 📌 Rotas Disponíveis

### 🔹 Squad
- `POST /squad/create` → Cria um novo squad.

### 🔹 Employee
- `POST /employee/create` → Adiciona um novo funcionário.

### 🔹 Report
- `POST /report/create` → Registra um novo relatório.
- `GET /report/average-hours-by-squad` → Média de horas por squad.
- `GET /report/total-hours-by-squad` → Total de horas por squad.
- `GET /report/spent-hours-by-employee` → Horas gastas por funcionário.

## 🚀 Como usar
1. Importe o arquivo `gestao-relatorios.postman_collection.json` no Postman.
2. Configure o banco de dados e inicie o servidor Spring Boot.
3. Faça as requisições no Postman.

---
Desenvolvido por Lucas Anderi.
