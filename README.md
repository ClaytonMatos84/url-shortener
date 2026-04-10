# URL Shortener

Serviço de encurtamento de URLs desenvolvido com **Spring Boot**. A aplicação permite registrar URLs longas e associá-las a uma chave curta única, que pode ser utilizada para redirecionar o usuário ao endereço original.

## 📋 Funcionalidades

- Cadastro de URLs para encurtamento
- Redirecionamento de chave curta para URL original
- Ativação e desativação de URLs cadastradas
- Migração automática do banco de dados via **Flyway**
- Documentação interativa da API via **Swagger UI** (SpringDoc OpenAPI)

## 🗄️ Estrutura do Banco de Dados

A tabela principal criada automaticamente pelo Flyway é:

```sql
CREATE TABLE urls (
    idt_url         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    des_url_original VARCHAR(4800) NOT NULL,
    des_key         VARCHAR(10)  NOT NULL UNIQUE,
    flg_active      BOOLEAN      NOT NULL,
    dat_created     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dat_modified    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## ⚙️ Requisitos

| Requisito  | Versão mínima |
|------------|---------------|
| Java (JDK) | 21            |
| Maven      | 3.9+          |
| MySQL      | 8.0+          |

## 🔧 Configuração

A aplicação utiliza variáveis de ambiente para as credenciais do banco de dados. Defina as variáveis abaixo antes de iniciar:

| Variável         | Descrição                                         | Exemplo                             |
|------------------|---------------------------------------------------|-------------------------------------|
| `DB_URL`         | URL JDBC de conexão com o banco de dados MySQL    | `jdbc:mysql://db_path:3306/db_name` |
| `DB_USER_NAME`   | Usuário do banco de dados                         | `db_user`                           |
| `DB_PASSWORD`    | Senha do banco de dados                           | `db_pass`                           |

### Exemplo de exportação no Linux/macOS

```bash
export DB_URL=jdbc:mysql://db_path:3306/db_name
export DB_USER_NAME=db_user
export DB_PASSWORD=db_pass
```

### Exemplo de exportação no Windows (PowerShell)

```powershell
$env:DB_URL="jdbc:mysql://db_path:3306/db_name"
$env:DB_USER_NAME="db_user"
$env:DB_PASSWORD="db_pass"
```

## 🚀 Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/url-shortener.git
cd url-shortener
```

### 2. Crie o banco de dados no MySQL

```sql
CREATE DATABASE db_name;
```

### 3. Defina as variáveis de ambiente (veja a seção acima)

### 4. Execute a aplicação

```bash
./mvnw spring-boot:run
```

Ou, se preferir compilar e executar o JAR:

```bash
./mvnw clean package -DskipTests
java -jar target/url-shortener-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: **http://localhost:8080**

## 📖 Documentação da API

Com a aplicação em execução, acesse a documentação Swagger UI em:

```
http://localhost:8080/swagger-ui.html
```

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.0.5**
- **Spring Web** — criação dos endpoints REST
- **Spring Data JPA** — abstração de acesso ao banco de dados
- **Spring Validation** — validação de dados de entrada
- **Flyway** — versionamento e migração automática do banco de dados
- **MySQL** — banco de dados relacional
- **Lombok** — redução de código boilerplate
- **SpringDoc OpenAPI (Swagger UI)** — documentação interativa da API

