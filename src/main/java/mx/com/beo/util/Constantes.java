package mx.com.beo.util;

public class Constantes {
	
	private Constantes() {
	}
	
	public static final String FORMATO_FECHA = "yyyyMMddHHmmss'.0Z'";
	public static final String FORMATO_FECHA_SERVICIO = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String ERROR_FORMATO_FECHA_SERVICIO = "Error en Formato fecha Ultimo Acceso";
	public static final String ERROR_FORMATO_FECHA = "Error en Formato fecha";
	
    public static final String  EXCEPTION_HEADERS = "Valida Headers exception";
    
    public static final String MAP_GENERAL = "mapGeneral";
    public static final String URLS = "urls";
    public static final String HTTPHEADERS = "httpHeaders";
    public static final String HEADERS_AUTENTICACION = "Headers autenticacion:";

	public static final String  PROTOCOLO = "PROTOCOLO";
	public static final String  HOSTNAME_BEO = "HOSTNAME_BEO";
	public static final String  PUERTO = "PUERTO";
	public static final String  BASEPATH = "BASEPATH";
	public static final String  BITACORA_URL = "BITACORA_URL";
	public static final String  URL_MODIFICA_CONTRATO = "URL_MODIFICA_CONTRATO";
	public static final String BASEPATH_AUTENTICACION = "BASEPATH_AUTENTICACION";
	public static final String BASEPATH_PERSONA = "BASEPATH_PERSONA";
	public static final String BASEPATH_FACULTAMIENTO = "BASEPATH_FACULTAMIENTO";
	
	public static final String ENDPOINT = "endpoint";
	public static final String METHOD = "method";
	public static final String CONNECT_TIMEOUT = "connectTimeout";
	public static final String READ_TIMEOUT = "readTimeout";
	public static final String CONNECT_REQUEST_TIMEOUT = "connectionRequestTimeout";
	public static final String HEADER = "headers";
	public static final String BODY = "body";
	public static final String BANDERA_ACCESO = "banderaAcceso";
	public static final String CLIENTE ="cliente";
	

	public static final String RESPONSE_STATUS = "responseStatus";
	public static final String RESPONSE_ERROR = "responseError";
	
	public static final String LOG_ENDPOINT_CAMBIO_CONTRASENA = "EndPoint /autenticacion/cambioContrasena";
	public static final String LOG_ENDPOINT_CONTRASENA = "EndPoint Contrasena"; 
	public static final String LOG_CONTRASENAS_MODIFICAR = "Las contraseñas a modificar son diferentes";
	public static final String LOG_CONTRATO_ACEPTADO = "el contrato ya esta aceptado";
	public static final String LOG_CONTRATO_NO_ACEPTADO = "el contrato no esta aceptado";
	public static final String LOG_OK_BANDERA_ACCESO = "Ok, banderaAcceso trae 1";
	public static final String LOG_OK_CONTRATO_MODIFICADO = "Ok, Se ha modificado el contrato";
	public static final String LOG_OK_MOSTRAR_CONTRATO = "OK, Se envia mostrar contrato";
	public static final String LOG_ENDPOINT_ACCESO_CLIENTES = "EndPoint accesoCliente";
	public static final String LOG_ERROR_CONTRATO = "Error, el cliente no ha aceptado el contrato";
	
	public static final String DATOS_BASICOS = "consultaDatosBasicos";
	public static final String SERVICIOS_CONTRATADOS = "consultaServiciosContratados";
	public static final String PERFIL_GENERAL = "perfilGeneral";
	public static final String ENVIO_NOTIFICACION = "envioNotificacion";
	
	
	public static final String LOG_OK = "OK";
	
	//Tipo de respuesta del servicio
	public static final String  VACIO = "";
	public static final String TICKET = "ticket";
	public static final String CANAL = "canal";
	public static final String IDPERSONA = "idPersona";
	public static final String NOMBRESISTEMA = "nombreSistema";


}
