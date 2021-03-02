# Transaction - API
![Version](src/main/resources/static/img/version.svg)
![License](src/main/resources/static/img/license.svg)
![Coverage](src/main/resources/static/img/coverage.svg)

## Objetivos
Desenvolver uma API para receber informações sobre transações por mensageria. Essa API deve permitir solicitar e cancelar o recebimento de mensagens, deve permitir consultar as últimas transações realizadas.
A API deve armazenar todas as informações sobre as transações em banco de dados. Deve possuir sistema Oauth2 de autenticação e autorização fornecidos pelo Keycloak.

# Tecnologias utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:

 + Java 11
 + Spring Boot 2.4.3
 + Docker
 + Docker-compose
 + MySQL
 + Keycloak
 + Prometheus
 + Jaeger
 + Grafana
 + Github

## Endpoints & Payloads
### Endpoints
#### Autenticação
**Ação** | **Endpoint** | **Método**
---------- | ------------ | ----------
Autenticar | _auth/realms/nosso-cartao/protocol/openid-connect/token_ | POST

#### Transações
**Ação** | **Endpoint** | **Método**
---------- | ------------ | ----------
Iniciar recebimento de mensagens | _/api/transactions/start_ | POST
Encerrar recebimento de mensagens | _/api/transactions/delete/{id}_ | DELETE
Listar transações por id | _/api/transactions/{id}_ | GET

### Payloads (request & response)
#### Transações
##### POST - request (authenticate user)
    {
        "grant_type": "password",
        "username": "test_user", 
        "password": "123456",
        "client_id": "transaction",
        "client_secret": "863eeb48-b665-4dfc-8a3e-9cbdd7fd3efc"
    }

##### POST - response
    {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ3cGdfWjdrYnBrM2VyOHVvSzBISDR0OUlQcjYzVjJQR1pZYnZ6dkI5bU9zIn0.eyJleHAiOjE2MTQ2NDY1NjcsImlhdCI6MTYxNDY0NjI2NywianRpIjoiYzk2MDFjOTEtMjQ3OS00NDFlLTk2NDktYjJiOGQzNjNhNjc0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9hdXRoL3JlYWxtcy9ub3Nzby1jYXJ0YW8iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiY2M4NzBmNTgtYzNmMy00NTQyLTk4MjYtZTEzYWY1YTA3NDAzIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidHJhbnNhY3Rpb24iLCJzZXNzaW9uX3N0YXRlIjoiNjEwMTNmMmEtZGU3ZS00YWY2LThhMWQtODAwZTZiZDI2NzQ3IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJ0cmFuc2FjdGlvbjpkZWxldGUgdHJhbnNhY3Rpb246d3JpdGUgaGVhbHRoOnJlYWQgcHJvZmlsZSBlbWFpbCB0cmFuc2FjdGlvbjpyZWFkIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJUZXN0IFVzZXIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0X3VzZXIiLCJnaXZlbl9uYW1lIjoiVGVzdCIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoidGVzdF91c2VyQG1haWwuY29tIn0.ivhW8dqVcPocMuNmJNqRAaCjgd5QMhkoexVWa7KrYXikdl5frzVVapAd6sEP1ozuy-BbfI6rs2KyvXe7-Ww8L2rumtGNtKZcyj2uUNB7szLQXKYMnfOaiebUc0rcQPgZXsCuQFxQkKF6tdG56M0-CTqY77r3kmt-GoLDBLN1-f0PZuvHtcUevttvtz1bLd6tMY_tGrWRxwrnhlwelsZPeLRIHFQRExS6WskfZt3hJRedgvhJBzqzzDUgv_EAmbhGqKgDLE10ehFvGXNTSzb9",
        "expires_in": 300,
        "refresh_expires_in": 1800,
        "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5ZjZkNGMxYy1jMjY3LTQ1ZTgtODU2Ny1jYjRjNWZmZTI3MzgifQ.eyJleHAiOjE2MTQ2NDgwNjcsImlhdCI6MTYxNDY0NjI2NywianRpIjoiY2Q3OGFmMzQtYTAxOC00NTQ5LWIxNzMtMzRmNTRkMzllNWZkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9hdXRoL3JlYWxtcy9ub3Nzby1jYXJ0YW8iLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjE4MDgwL2F1dGgvcmVhbG1zL25vc3NvLWNhcnRhbyIsInN1YiI6ImNjODcwZjU4LWMzZjMtNDU0Mi05ODI2LWUxM2FmNWEwNzQwMyIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJ0cmFuc2FjdGlvbiIsInNlc3Npb25fc3RhdGUiOiI2MTAxM2YyYS1kZTdlLTRhZjYtOGExZC04MDBlNmJkMjY3NDciLCJzY29wZSI6InRyYW5zYWN0aW9uOmRlbGV0ZSB0cmFuc2FjdGlvbjp3cml0ZSBoZWFsdGg6cmVhZCBwcm9maWxlIGVtYWlsIHRyYW5zYWN0aW9uOnJl",
        "token_type": "Bearer",
        "not-before-policy": 0,
        "session_state": "61013f2a-de7e-4af6-8a1d-800e6bd26747",
        "scope": "transaction:delete transaction:write health:read profile email transaction:read"
    }

#### Transações
#### POST - request (iniciar recebimento de mensagens)
    {
    "id": "863eeb48-b665-4dfc-8a3e-9cbdd7fd3efc",
    "email": "user@mail.com"
    }

##### DELETE - request (encerrar o recebimento de mensagens)
Não são passados parâmetros no corpo da requisição, apenas o id como path parameter.

##### GET - response - (List top 10 transactions by id)
    [
        {
            "id": "392265f8-9db0-4a73-a4f3-74bb83c4e500",
            "amount": 4.71,
            "store": {
                "id": 1,
                "name": "Lou Briccant",
                "city": "Reillyborough",
                "address": "4755 Gay Parks, Lake Elroy, WV 10251"
            },
            "creditCard": {
                "id": "863eeb48-b665-4dfc-8a3e-9cbdd7fd3efc",
                "email": "user@mail.com"
            },
            "createdAt": "2021-02-07T23:15:24"
        },
        {
            "id": "c213ec45-509d-44b1-9e65-2a2c64e240c6",
            "amount": 4.40,
            "store": {
                "id": 2,
                "name": "Rich Mann",
                "city": "South Elanaport",
                "address": "96928 Koepp Isle, Rogerfurt, IN 15226"
            },
            "creditCard": {
                "id": "863eeb48-b665-4dfc-8a3e-9cbdd7fd3efc",
                "email": "user@mail.com"
            },
            "createdAt": "2021-02-06T13:06:38"
        }
    ]
