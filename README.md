# Product-Micro-services-Project
*This is a project of a simple microservices system, with 2 services (Product-ms and Warehouse-ms), CRUD, business rules and some unit tests for the Repository and the Services.*


# Product-ms

## Endpoints
- BaseURL: /products
- POST: create()
- GET: getAll()
- GET /{id}: getById()
- PUT /{id}: update()
- DELETE /{id}: inactive()

## Model
```json
{
    "id": 1,
    "name": "PlayStation 5",
    "description": "Sony last generation console",
    "price": 3499.90,
    "isAvailable": true
}
```

## Business Rules
- It is only possible to create products with a positive value of price;
- It's not possible to search products that aren't available;
- It's not possible to insert descriptions with less than 50 characters. (@Size)

# Warehouse-ms

## Endpoints
- BaseURL: /warehouses
- POST: create()
- GET: getAll()
- GET /{id}: getById()
- PUT /{id}: update()


## Model
```json
{
    "id": 1,
    "productid": 1,
    "quantity": 10
}
```
## Business Rules
- The warehouse is responsible for notifying if a product is available or not. So, if the quantity = 0, the warehouse should notify the "product-ms" service;
- Isn't allowed more than one line of the same product on the database.
- The communication between the services might be done via Message Brokers such as RabbitMQ or Kafka, still to be defined.

## UPDATE 1.0.1 - 22/05/2023
- Swagger 3 added to the project. Basic documentation of endpoints.
- PostgreSQL configured in the 'application.properties' file. The database is hosted on a docker container orchestrated via k8s minikube (kubegres).
## UPDATE 1.0.2 - 31/05/23
- Exception Handler was implemented for the FindById end-point. If the id inst found, message appears.
## UPDATE 1.0.3 - 20/07/23
- Exception Handler was implemented for all end-points.
- Activate method was implemented. it changes the value from the isAvailable field from false to true.
- Unit testing was implemented for the repository and the services.
