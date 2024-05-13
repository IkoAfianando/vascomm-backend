# Product API Spec

## Create Product API

Endpoint : POST /api/products

Headers :
- X-API-KEY : token

Request Body :

```json
{
  "product_name" : "Product 1",
    "price" : 10000,
    "stock" : 10
}
```

Response Body Success :

```json
{
  "code": 201,
  "message": "Created",
  "data" : {     
    "product_id" : "925b0eb2-6a3a-4bd0-91a0-12049d53a00d",
    "product_name" : "Product 1",
    "price" : 10000,
    "stock" : 10,
    "created_at" : "2021-08-01T00:00",
    "updated_at" : "2021-08-01T00:00",
    "deleted_at" : null,
    "created_by" : "iko",
    "updated_by" : "iko",
    "deleted_by" : null
  }
}
```

Response Body Error :

```json
{
  "errors" : "Product name is required"
}
```

## Update Product API

Endpoint : PUT /api/products/:id

Headers :
- X-API-KEY : token 

Request Body :

```json
{
    "product_name" : "Product 1",
    "price" : 10000,
    "stock" : 10
}
```

Response Body Success :

```json
{
  "code": 200,
  "message": "OK",
  "data" : {
    "product_id" : "925b0eb2-6a3a-4bd0-91a0-12049d53a00d",
    "product_name" : "Product 1",
    "price" : 10000,
    "stock" : 10,
    "created_at" : "2021-08-01T00:00",
    "updated_at" : "2021-08-01T00:00",
    "deleted_at" : null,
    "created_by" : "iko",
    "updated_by" : "iko",
    "deleted_by" : null
  }
}
```

Response Body Error :

```json
{
  "errors" : "Product is not found"
}
```

## Get Product API

Endpoint : GET /api/products/:id

Headers :
- X-API-KEY : token

Response Body Success :

```json
{
  "code": 200,
    "message": "OK",
  "data" : {
    "product_id" : "925b0eb2-6a3a-4bd0-91a0-12049d53a00d",
    "product_name" : "Product 1",
    "price" : 10000,
    "stock" : 10,
    "created_at" : "2021-08-01T00:00",
    "updated_at" : "2021-08-01T00:00",
    "deleted_at" : null,
    "created_by" : "iko",
    "updated_by" : "iko",
    "deleted_by" : null
  }
}
```

Response Body Error :

```json
{
  "errors" : "Product is not found"
}
```

## Search Product API

Endpoint : GET /api/products

Headers :
- X-API-KEY : token

Query params :
- product_name : Search by product name using like, optional
- take : Limit data, optional
- skip : Skip data, optional

Response Body Success :

```json
{
  "code": 200,
    "message": "OK",
  "data" : [
    {
        "product_id" : "925b0eb2-6a3a-4bd0-91a0-12049d53a00d",
        "product_name" : "Product 1",
        "price" : 10000,
        "stock" : 10
    },
    {
        "product_id" : "925b0eb2-6a3a-4bd0-91a0-12049d53a10d",
        "product_name" : "Product 2",
        "price" : 20000,
        "stock" : 20
    }
  ],
  "paging" : {
    "total" : 100,
    "take" : 10,
    "skip" : 0
  }
}
```

Response Body Error :

## Remove Product API

Endpoint : DELETE /api/products/:id

Headers :
- X-API-KEY : token

Response Body Success :

```json
{
    "code": 200,
    "message": "OK",
    "data" : "Product is deleted"
}
```

Response Body Error :

```json
{
  "errors": "Products is not found"
}
```
