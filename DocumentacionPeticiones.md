# Crear usuario Caso de uso (POST):

### (http://localhost:8080/crearUsuario)

### (https://pruebas-reto-final.herokuapp.com/crearUsuario)

Petición exitosa

* Código de respuesta "200".

~~~js
Request
{
  "uid": "M1PPeC5Ax5flEqSRMyzKpXAgOGD2",
  "nombres": "Perry 2",
  "email": "correo",
  "imagenUrl": "imagen"
}

Response
{
    "id": "61f855d5cc4dbb58ea3768e9",
    "uid": "M1PPeC5Ax5flEqSRMyzKpXAgOGD2",
    "nombres": "Perry 2",
    "email": "correo",
    "imagenUrl": "imagen"
}
~~~



Petición usuario existente:

* Código de respuesta "409".

~~~json
Request
{
  "uid": "M1PPeC5Ax5flEqSRMyzKpXAgOGD2",
  "nombres": "Perry 2",
  "email": "correo",
  "imagenUrl": "imagen"
}

Response
{
    "timestamp": "2022-01-31T21:34:57.387+00:00",
    "path": "/crearUsuario",
    "status": 409,
    "error": "Conflict",
    "message": null,
    "requestId": "2871c3ca-2"
}
~~~

Petición UID errada:

* Código de respuesta "409".

~~~json
Request
{
  "uid": "sdfsdfsdfsd",
  "nombres": "Perry 2",
  "email": "correo",
  "imagenUrl": "imagen"
}

Response
{
    "timestamp": "2022-01-31T21:36:19.159+00:00",
    "path": "/crearUsuario",
    "status": 409,
    "error": "Conflict",
    "message": null,
    "requestId": "2871c3ca-3"
}
~~~





# Iniciar Sesión Caso de uso (GET):

### (http://localhost:8080//M1PPeC5Ax5flEqSRMyzKpXAgOGD2)

### (https://pruebas-reto-final.herokuapp.com/inicioSesion/M1PPeC5Ax5flEqSRMyzKpXAgOGD2)

Petición exitosa

* Código de respuesta "200".

~~~js
Response
{
    "id": "61f855d5cc4dbb58ea3768e9",
    "uid": "M1PPeC5Ax5flEqSRMyzKpXAgOGD2",
    "nombres": "Perry 2",
    "email": "correo",
    "imagenUrl": "imagen"
}
~~~

Petición fallida:

* Código de respuesta "401".

~~~json

Response
{
    "timestamp": "2022-01-31T21:40:10.005+00:00",
    "path": "/inicioSesion/asdasdasddsad",
    "status": 401,
    "error": "Unauthorized",
    "message": null,
    "requestId": "2871c3ca-5"
}
~~~

