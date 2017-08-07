# Autenticación

Proyecto que a cualquier peticion del front le agrega los headers necesarios para la validación

- iv-user
- iv-creds
- iv-groups
- numero-cliente
- nombre-completo
- tipo-authenticacion
- contratoAceptado
- fechaUltimoAcceso
- Tipocanal
- mail


# Descripción

Este proyecto contiene dos servicios el servicio de accesoCliente y cambioContraseña.
 

# Variables de ambiente

Previo a la ejecucion del programa es necesario configurar dos variables de ambiente

## PROTOCOLO

 PROTOCOLO=http

## PUERTO

 PUERTO=3002

## HOSTNAME

 HOSTNAME=localhost

##  BASEPATH

 BASEPATH=BEO

## Ejecucion
 
```  
mvn install
mvn spring-boot:run
```

