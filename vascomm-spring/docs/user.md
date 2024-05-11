# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "email" : "iko@mail.com",
  "password" : "rahasia",
  "name" : "iko afianando"
}
```

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "email must not blank, ???"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "email" : "iko@mail.com",
  "password" : "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "access_token" : "TOKEN",
    "expiredAt" : 2342342423423
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "email or password wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "email" : "iko@mail.com",
    "password" : "rahasia"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "afianando iko",
  "password" : "new password"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "email" : "iko@mail.com",
    "name" : "Iko Afianando"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```
