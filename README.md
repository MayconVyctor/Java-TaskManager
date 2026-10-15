# To Do List App

Aplicacao fullstack de gerenciamento de tarefas construida com **Spring Boot** no backend e **React** no frontend. Permite criar, listar, editar, excluir e organizar tarefas por categorias, com interface moderna que suporta tema claro e escuro.

## Funcionalidades

- **CRUD completo de Tarefas** — criar, listar, editar e excluir
- **Categorias** — organizar tarefas por categoria com filtro na sidebar
- **Tema claro/escuro** — alternancia salva no `localStorage`
- **Validacao de dados** — campos obrigatorios validados no backend com Bean Validation
- **API RESTful** — endpoints seguindo boas praticas REST com codigos HTTP semanticos

## Tecnologias

### Backend
| Tecnologia | Versao | Finalidade |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.5 | Framework web e injecao de dependencias |
| Spring Data JPA | — | Persistencia e acesso a dados com Hibernate |
| Bean Validation | — | Validacao de campos (`@NotBlank`, `@Valid`) |
| PostgreSQL | 15 | Banco de dados relacional |
| Lombok | — | Reducao de boilerplate (getters, setters, construtores) |
| Maven | — | Gerenciador de dependencias e build |

### Frontend
| Tecnologia | Versao | Finalidade |
|---|---|---|
| React | 19 | Biblioteca de UI com componentes funcionais e hooks |
| Vite | 8 | Build tool e dev server |
| Axios | 1.15 | Cliente HTTP para comunicacao com a API |

### Infraestrutura
| Tecnologia | Finalidade |
|---|---|
| Docker Compose | Orquestracao do banco PostgreSQL e Adminer |
| Adminer | Interface web para gerenciamento do banco de dados |

## Arquitetura do Backend

O projeto segue o padrao de **arquitetura em camadas (Layered Architecture)**:

```
Requisicao HTTP (React / Postman)
        |
        v
  [ Controller ]   ->  Recebe requisicoes, valida e define rotas HTTP
        |
        v
   [ Service ]     ->  Regras de negocio (ex: status padrao da tarefa)
        |
        v
  [ Repository ]   ->  Comunicacao com o banco via Spring Data JPA
        |
        v
  [ PostgreSQL ]   ->  Persistencia dos dados
```

## Estrutura do Projeto

```
toDoList/
├── src/main/java/com/projetoTodolist/
│   ├── controller/
│   │   └── TaskController.java        # Endpoints REST de tarefas
│   ├── model/
│   │   ├── Task.java                  # Entidade JPA - Tarefa
│   │   └── Category.java             # Entidade JPA - Categoria
│   ├── repository/
│   │   ├── TaskRepository.java        # Interface JPA para tarefas
│   │   └── CategoryRepository.java   # Interface JPA para categorias
│   ├── service/
│   │   ├── TaskService.java           # Regras de negocio de tarefas
│   │   └── CategoryService.java      # Regras de negocio de categorias
│   └── ToDoListApplication.java       # Ponto de entrada da aplicacao
├── src/main/resources/
│   └── application.properties         # Configuracoes do Spring e banco
├── frontend/
│   └── src/
│       ├── App.jsx                    # Componente principal React
│       ├── App.css                    # Estilos da aplicacao
│       └── components/               # Componentes reutilizaveis
├── docker-compose.yml                 # PostgreSQL + Adminer
└── pom.xml                            # Dependencias Maven
```

## Endpoints da API

### Tarefas (`/api/tasks`)

| Metodo | Endpoint | Descricao | Status HTTP |
|---|---|---|---|
| `GET` | `/api/tasks` | Listar todas as tarefas | `200 OK` |
| `GET` | `/api/tasks/{id}` | Buscar tarefa por ID | `200 OK` / `404 Not Found` |
| `POST` | `/api/tasks` | Criar nova tarefa | `201 Created` |
| `PUT` | `/api/tasks/{id}` | Atualizar tarefa existente | `200 OK` |
| `DELETE` | `/api/tasks/{id}` | Excluir tarefa | `204 No Content` |

## Como Executar

### Pre-requisitos
- Java 17+
- Node.js 18+
- Docker e Docker Compose

### 1. Subir o banco de dados
```bash
docker-compose up -d
```
O PostgreSQL estara disponivel na porta `5434` e o Adminer na porta `8081`.

### 2. Executar o backend
```bash
./mvnw spring-boot:run
```
A API estara disponivel em `http://localhost:8080`.

### 3. Executar o frontend
```bash
cd frontend
npm install
npm run dev
```
A aplicacao estara disponivel em `http://localhost:5173`.

## Configuracao do Banco de Dados

As credenciais padrao estao configuradas em `application.properties` e `docker-compose.yml`:

| Propriedade | Valor |
|---|---|
| Host | `localhost` |
| Porta | `5434` |
| Banco | `todo_db` |
| Usuario | `postgres` |
| Senha | `123` |

## Status do Projeto

**Em desenvolvimento** — Melhorias planejadas:
- [ ] Criar `CategoryController` com CRUD completo
- [ ] Excecao customizada (`ResourceNotFoundException`) com tratamento global de erros
- [ ] Refatorar injecao de dependencia para construtor (`@RequiredArgsConstructor`)
- [ ] Criar `Enum` para status da tarefa
- [ ] Adicionar autenticacao de usuarios

## Licenca

Este projeto e de uso pessoal e educacional.
