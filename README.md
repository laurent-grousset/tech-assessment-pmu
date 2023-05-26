# tech-assessment-pmu
## Endpoint
POST `/api/v1/race/create`

`
{
"number": 0,
"name": "string",
"date": "2023-05-26",
"participants": [
{
"number": 0,
"name": "string"
}
]
}
`
## Optimisations possibles

* Aurait pu avoir une orientation RESTFul en utilisant les `@RepositoryRestResource`, avec l'ajout d'une propriété de complétion de la course
* `springdoc`est pratique pour le dev, à ne pas passer en production
* A voir si l'utilisation d'un DTO + Mapper au lieu d'exposer le modèle directement est plus pertinent.
* Avec plus temps, plus de commentaire de code.
* Réalisé avec une philosophie Clean Code, Design Pattern Spring, sans surprise.

## OpenAPI definition
`
{
"openapi":"3.0.1",
"info":{
"title":"PMU Race Service",
"description":"Technical documentation of all REST API methods.",
"version":"v1"
},
"servers":[
{
"url":"/",
"description":"Default Server URL"
}
],
"tags":[
{
"name":"Race Controller",
"description":"Endpoint used to create a race."
}
],
"paths":{
"/api/v1/race/create":{
"post":{
"tags":[
"Race Controller"
],
"operationId":"create",
"requestBody":{
"content":{
"application/json":{
"schema":{
"$ref":"#/components/schemas/Race"
}
}
},
"required":true
},
"responses":{
"500":{
"description":"Internal Server Error",
"content":{
"*/*":{
"schema":{
"type":"string"
}
}
}
},
"406":{
"description":"Not Acceptable",
"content":{
"*/*":{
"schema":{
"type":"string"
}
}
}
},
"200":{
"description":"OK"
}
}
}
}
},
"components":{
"schemas":{
"Participant":{
"required":[
"name",
"number"
],
"type":"object",
"properties":{
"number":{
"type":"integer",
"format":"int32"
},
"name":{
"type":"string"
}
}
},
"Race":{
"required":[
"date",
"name",
"number",
"participants"
],
"type":"object",
"properties":{
"number":{
"type":"integer",
"format":"int32"
},
"name":{
"type":"string"
},
"date":{
"type":"string",
"format":"date"
},
"participants":{
"uniqueItems":true,
"type":"array",
"items":{
"$ref":"#/components/schemas/Participant"
}
}
}
}
}
}
}
`