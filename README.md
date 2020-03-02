# Rest-application

Esta API gerencia um banco de endereços.
Disponíveis métodos GET, PUT, POST e DELETE.

### GET

Endoint: localhost:8085/addresses para listar todos endereços.
Endoint: localhost:8085/addresses/{id} para buscar por ID.

### POST

Endoint: localhost:8085/addresses

Payload sugerido:

```
{
	"id": null,
	"streetName": "Rua Abilio Jorge Cury",
	"number": "300",
	"complement": "",
	"neighbourhood": "Jardim Nazareth",
	"city": "São José do Rio Preto",
	"state": "São Paulo",
	"country": "Brasil",
	"zipcode": "15054130",
	"latitude": null,
	"longitude": null
}
```

### PUT

Endoint: localhost:8085/addresses/{id}

### DELETE

Endoint: localhost:8085/addresses/{id}

## Como rodar a aplicação

O banco para testes é executado no h2.

O projeto foi configurado no arquivo application.properties para rodar na porta 8085. Caso seja uma porta ocupada, alterar campo
"server.port".

Para iniciar o projeto, rodar o arquivo "RestApplication" como uma aplicação Spring.

## Programas necessários

```
JDK 8
Intellij (IDE)
Lombok
Postman (ou similares)
```
