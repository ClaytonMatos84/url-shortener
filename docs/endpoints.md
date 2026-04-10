# Endpoints da API

Esta documentacao descreve os endpoints disponiveis no servico de encurtamento de URLs.

## Resumo

| Metodo  | Endpoint          | Descricao                                    |
|---------|-------------------|-----------------------------------------------|
| `POST`  | `/urls`           | Cria uma nova URL curta                       |
| `GET`   | `/urls/{key}`     | Redireciona para a URL original (HTTP 302)    |
| `GET`   | `/urls/active`    | Lista URLs ativas com paginacao               |
| `GET`   | `/urls/key/{key}` | Retorna os dados de uma URL pela chave        |
| `PUT`   | `/urls/key/{key}` | Desativa uma URL pela chave                   |
| `PATCH` | `/urls/key/{key}` | Atualiza a URL original e/ou status           |

## `POST /urls`

Cria uma nova URL curta a partir de uma URL original.

**Request body** (`text/plain`):
```
https://www.exemplo.com/uma-url-muito-longa
```

**Response** (`200 OK`, `text/plain`):
```
http://localhost:8080/urls/AB12CD34EF
```

## `GET /urls/{key}`

Redireciona o cliente para a URL original associada a chave.

**Response**: `302 Found` com header `Location` apontando para a URL original.

> Retorna `400 Bad Request` se a URL estiver inativa.

## `GET /urls/active`

Lista todas as URLs ativas com suporte a paginacao via query params `page`, `size` e `sort`.

**Exemplo**:
```
GET /urls/active?page=0&size=10
```

**Response** (`200 OK`):
```json
{
  "content": [
    {
      "id": 1,
      "originalUrl": "https://www.exemplo.com/uma-url-longa",
      "key": "AB12CD34EF",
      "active": true,
      "created": "2026-01-01T00:00:00.000+00:00",
      "modified": "2026-01-01T00:00:00.000+00:00"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

## `GET /urls/key/{key}`

Retorna os dados completos de uma URL pela chave.

**Response** (`200 OK`):
```json
{
  "id": 1,
  "originalUrl": "https://www.exemplo.com/uma-url-longa",
  "key": "AB12CD34EF",
  "active": true,
  "created": "2026-01-01T00:00:00.000+00:00",
  "modified": "2026-01-01T00:00:00.000+00:00"
}
```

## `PUT /urls/key/{key}`

Desativa a URL associada a chave. Nao requer body.

**Response**: `200 OK` (sem corpo).

## `PATCH /urls/key/{key}`

Atualiza parcialmente uma URL. Todos os campos do body sao opcionais.

**Request body** (`application/json`):
```json
{
  "originalUrl": "https://www.novo-destino.com",
  "active": true
}
```

**Response** (`200 OK`): objeto `Url` atualizado.

## Erros comuns

| Situacao                           | HTTP Status |
|------------------------------------|-------------|
| Chave nao encontrada               | `404`       |
| URL inativa ao tentar redirecionar | `400`       |
| Erro inesperado                    | `500`       |

## Swagger UI

Com a aplicacao em execucao:

```
http://localhost:8080/swagger-ui.html
```

