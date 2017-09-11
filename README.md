# Nombre de microservicio

Microservicio que propociona la funcion de ...
## Uso

Instalar las dependencias mediante

En caso de que se quieran saltar las pruebas unitarias aplicar este comando con mvn 

```
mvn clean package -Dmaven.test.skip=true
```

En caso de que no requiera saltar pruebas unitarias realizar este comando

```
mvn clean package
```


## Variables de ambiente

Previo a la ejecucion del programa es necesario configurar variables de ambiente



```
PROTOCOLO=http
PUERTO=80
HOSTNAME=200.39.24.141
BASEPATH=/BEO
```

### Puerto donde se encuentra este microservicio

```
server.port=8080 
```

## Ejecucion
```
java -jar microservicio-0.0.1-SNAPSHOT.jar
```
