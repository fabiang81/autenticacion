# Nombre de microservicio

Microservicio que propociona la funcion de ...
## Uso

Instalar las dependencias mediante

En caso de que se quieran saltar las pruebas unitarias aplicar este comando con mvn 

##### Ambiente con Internet
```
mvn --settings settings_local.xml clean package -Dmaven.test.skip=true
```
##### Ambiente de desarrollo
```
mvn --settings settings_dev.xml clean package -Dmaven.test.skip=true
```

En caso de que no requiera saltar pruebas unitarias realizar este comando

##### Ambiente con Internet
```
mvn --settings settings_local.xml clean package
```
##### Ambiente de desarrollo
```
mvn --settings settings_dev.xml clean package
```


## Variables de ambiente

Previo a la ejecucion del programa es necesario configurar variables de ambiente



```
PROTOCOLO=http
PUERTO=9094
HOSTNAME_BEO=200.39.24.141
BASEPATH=
URL_MODIFICA_CONTRATO=http://localhost:9094/modificaContrato #URL Servicio de nologin

BITACORA_URL=http://historial
```

##### ENMASCARAMIENTO
En caso de ser necesario enmascarar alguna variable correspondiente al Request o Response en el archivo de bitacora Multiva sera necesario utilizar la siguiente variable de ambiente para indicar la configuración necesaria.
NOTA: en caso de no requerir enmascarar ninguna información no sera necesario configurar esta variable de ambiente
```
LOG_ESB_ENMASCARAMIENTO
Ejemplo:
LOG_ESB_ENMASCARAMIENTO={"config":[{"servicio":"cambioContrasena","request":["oldPassword","newPassword","confirmNewPassword"],"response":[]}]}
* Explicacion:
{
  "config":[
    {
      "servicio":
      "request":[],
      "response":[]
    }
  ]
}
config: arreglo de objetos, cada objeto representa la configuracion de un servicio.
servicio: nombre del endpoind del servicio que se esta configurando.
request: es un arreglo con el nombre de todos los campos que deben ser enmascarados que se encuentran en el Request que invoca al servicio.
response: es un arreglo con el nombre de todos los campos que deben ser enmascarados que se encuentran en el Response que respondio el servicio.
```

### Puerto donde se encuentra este microservicio

```
server.port=8080 
```

## Ejecucion
```
java -jar microservicio-0.0.1-SNAPSHOT.jar
```
