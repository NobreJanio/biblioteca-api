# API de Gerenciamento de Biblioteca

## Visão Geral

RESTful API para gerenciamento de biblioteca construída em Java 17 com Spring Boot 3, baseada na estrutura do projeto Santander Dev Week 2023. Esta API permite o gerenciamento completo de uma biblioteca, oferecendo funcionalidades para controle de livros, usuários, empréstimos e categorias, com uma interface de documentação interativa através do Swagger UI.

## Funcionalidades

- Cadastro e gerenciamento de livros
- Cadastro e gerenciamento de usuários
- Controle de empréstimos e devoluções
- Gerenciamento de categorias de livros
- Documentação interativa com Swagger/OpenAPI

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.1
- Spring Data JPA
- Spring Web
- Banco de dados H2 (ambiente de desenvolvimento)
- PostgreSQL (ambiente de produção)
- Swagger/OpenAPI para documentação (springdoc-openapi)
- Gradle para gerenciamento de dependências

## Requisitos

- JDK 17 ou superior
- Gradle 7.x ou superior
- PostgreSQL (para ambiente de produção)

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

- **Controller**: Responsável por receber as requisições HTTP e direcioná-las para os serviços apropriados
  - Implementa os endpoints RESTful da API
  - Utiliza anotações Swagger para documentação
- **Service**: Contém a lógica de negócios da aplicação
  - Interface `CrudService` define operações básicas de CRUD
  - Implementações específicas para cada entidade
- **Repository**: Responsável pela comunicação com o banco de dados
  - Utiliza Spring Data JPA para operações de persistência
- **Model**: Contém as entidades que representam os objetos do domínio
  - Entidades principais: Livro, Usuario, Emprestimo, Categoria
- **DTO**: Objetos de transferência de dados utilizados para comunicação entre camadas
  - Separa a representação externa da representação interna dos dados
- **Exception**: Classes para tratamento de exceções personalizadas

## Instalação e Execução

### Clonando o Repositório

```bash
git clone [URL_DO_REPOSITÓRIO]
cd biblioteca-api
```

### Executando a Aplicação

```bash
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8081`

### Compilando o Projeto

```bash
./gradlew build
```

## Endpoints da API

### Livros
- `GET /livros`: Lista todos os livros
- `GET /livros/{id}`: Busca um livro pelo ID
- `POST /livros`: Cadastra um novo livro
- `PUT /livros/{id}`: Atualiza um livro existente
- `DELETE /livros/{id}`: Remove um livro

### Usuários
- `GET /usuarios`: Lista todos os usuários
- `GET /usuarios/{id}`: Busca um usuário pelo ID
- `POST /usuarios`: Cadastra um novo usuário
- `PUT /usuarios/{id}`: Atualiza um usuário existente
- `DELETE /usuarios/{id}`: Remove um usuário

### Empréstimos
- `GET /emprestimos`: Lista todos os empréstimos
- `GET /emprestimos/{id}`: Busca um empréstimo pelo ID
- `POST /emprestimos`: Registra um novo empréstimo
- `PUT /emprestimos/{id}`: Atualiza um empréstimo existente
- `DELETE /emprestimos/{id}`: Remove um empréstimo

### Categorias
- `GET /categorias`: Lista todas as categorias
- `GET /categorias/{id}`: Busca uma categoria pelo ID
- `POST /categorias`: Cadastra uma nova categoria
- `PUT /categorias/{id}`: Atualiza uma categoria existente
- `DELETE /categorias/{id}`: Remove uma categoria

## Exemplos de Uso

### Cadastrar um Livro

```bash
curl -X POST http://localhost:8081/livros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Dom Casmurro",
    "autor": "Machado de Assis",
    "isbn": "9788574801414",
    "anoPublicacao": 1899,
    "quantidadeDisponivel": 5,
    "categoriaId": 1
  }'
```

### Registrar um Empréstimo

```bash
curl -X POST http://localhost:8081/emprestimos \
  -H "Content-Type: application/json" \
  -d '{
    "livroId": 1,
    "usuarioId": 1,
    "dataEmprestimo": "2023-07-15",
    "dataPrevistaDevolucao": "2023-07-30"
  }'
```

## Modelos de Dados

### Livro
```json
{
  "id": 1,
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "isbn": "9788574801414",
  "anoPublicacao": 1899,
  "quantidadeDisponivel": 5,
  "categoria": {
    "id": 1,
    "nome": "Literatura Brasileira"
  }
}
```

### Usuário
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "(11) 98765-4321",
  "endereco": "Rua das Flores, 123"
}
```

### Empréstimo
```json
{
  "id": 1,
  "livro": {
    "id": 1,
    "titulo": "Dom Casmurro"
  },
  "usuario": {
    "id": 1,
    "nome": "João Silva"
  },
  "dataEmprestimo": "2023-07-15",
  "dataPrevistaDevolucao": "2023-07-30",
  "dataRealDevolucao": null,
  "status": "ATIVO"
}
```

## Documentação da API

A documentação completa da API está disponível através do Swagger UI quando a aplicação está em execução:

```
http://localhost:8081/swagger-ui/index.html
```

Através da interface do Swagger, você pode:
- Visualizar todos os endpoints disponíveis
- Testar as operações diretamente pelo navegador
- Ver os modelos de dados e esquemas
- Entender os possíveis códigos de resposta de cada operação

## Ambientes

A aplicação possui configurações para dois ambientes:

- **Desenvolvimento**: Utiliza banco de dados H2 em memória
  - Console H2 disponível em: `http://localhost:8081/h2-console`
  - JDBC URL: `jdbc:h2:mem:biblioteca`
  - Usuário: `biblioteca`
  - Senha: (em branco)
- **Produção**: Utiliza banco de dados PostgreSQL

O ambiente ativo pode ser configurado no arquivo `application.yml` através da propriedade `spring.profiles.active`.

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request


## Contato

Para dúvidas ou sugestões, entre em contato através de [email ou outro meio de contato].