package mx.com.beo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
 
import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.ValidaContratoBean;

import org.json.simple.JSONArray;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Reynaldo ivan 
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

@Service
public class AutenticacionServicioImpl implements AutenticacionServicio{
	
	private static final Logger LOGGER = Logger.getLogger( AutenticacionServicioImpl.class.getName() );



	/**
	 * @param CambioContrasenaBean: Este objeto nos trae todos los datos para consumir el servicio
	 * @return JSONObject Nos regresa el JSON que nos manda el servicio consumido.
	 * Descripcion: Este metodo consume un servicio de ESB Fuse que realiza el cambio de contraseña.
	 */

	public JSONObject servicioCredenciales(CambioContrasenaBean bean)  {

		String output=null;
		String resultado="";

		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try {

			URL url = new URL(bean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String inputs=

					"{ \"idPersona\": "+bean.getIdPersona()+","
							+ "\"ticket\": {"
							+ "\"id_user\":"+ bean.getTicketbean().getIdUser() +","
							+ " \"id_creds\": \" "+bean.getTicketbean().getIdCreds()+" \" "
							+ "},"
							+ " \"canal\": \" "+bean.getCanal()+" \","
							+ "\"otp\": \" "+bean.getOtp()+" \","
							+ "\"oldPassword\": \" "+bean.getOldPassword()+" \","
							+ " \"newPassword\": \" "+bean.getNewPassword()+"  \","
							+ "\"confirmNewPassword\": \" "+bean.getConfirmNewPassword()+" \""
							+ "}"; 


			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
 
				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}
			}

			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);

			conn.disconnect();
			return json;

		} catch (MalformedURLException e) { 
			
			LOGGER.warning(e.getMessage());
			return error403();

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());
			return error403();

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
			return error403();
		}
		
 
	}



	/**
	 * netodo que consume el servicio consultaDatosBasicos 
	 * @param ConsultaDatoBasicoBean: Este Objeto trae todos los valores que nesesarios para consumir el servicio consultado DatosBasicos
	 * que nos trae la información de los datos basicos.
	 * @return JSONObject
	 * Descripcion: Este metodo consume el servicio datosBasicos.
	 */
 
	@SuppressWarnings("unchecked")
	public JSONObject consultaDatosBasicos(ConsultaDatoBasicoBean basicoBean )  {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;
		JSONObject jsonObject2=null;

		try { 
			 
			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs= 
					" {"
							+ "\"idPersona\":"+basicoBean.getIdPersona()+","
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}

			} 
			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);



			JSONArray array=(JSONArray) json.get("listaTelefonos");

			//			Creación de JSON  que se va a regresar 

			jsonObject2=new JSONObject();


			jsonObject2.put("listaTelefonos",array.get(0));
			jsonObject2.put("nombreRazonSocial",json.get("nombre"));
			jsonObject2.put("responseStatus", json.get("responseStatus"));
			jsonObject2.put("responseError", json.get("responseError"));


			conn.disconnect();

		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
		}

		return jsonObject2;
	}



 
	/**
	 * @param ConsultaServicioContratadoBean Este objeto nos trae los datos para consumir el servicio de servicios contratados.
	 * @return JSONObject
	 * Descripción Este servicio consume un servicio que nos trae un listado de todos los servicios que tenemos contratados.
	 */
	public JSONObject consultaServiciosContratados(ConsultaServicioContratadoBean basicoBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;   


		try { 
			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\":"+basicoBean.getIdPersona()+","
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) { 
					resultado=resultado+output;
				}

			} 
			jsonParser = new JSONParser();
			json = (JSONObject) jsonParser.parse(resultado);

			json.remove("responseError");
			json.remove("responseStatus"); 


			conn.disconnect();

		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
		}

		return json;
	}
	 

	/**
	 * @param EnvioNotificacioneBean Este objeto nos trae los datos para consumir el servicio de envio de notificaciones
	 * @return Este servicio nos trae JSONObject que nos manda el servicio que se consume
	 * Descripción: Este metodo consume un servico que realiza el envio de notificaciones.
	 */
	public JSONObject envioNotificaciones(EnvioNotificacioneBean envioNotificacioneBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try {  
			URL url = new URL(envioNotificacioneBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\":"+envioNotificacioneBean.getIdPersona()+","
							+ "\"ticket\": {"
							+ "\"id_user\": \""+envioNotificacioneBean.getTicketBean().getIdUser()+"\","
							+ "\"id_creds\": \""+envioNotificacioneBean.getTicketBean().getIdCreds()+"\""
							+ " },"
							+ "\"tipoMensaje\": \""+envioNotificacioneBean.getTipoMensaje()+"\","
							+ "\"tipoNotificacion\": \""+envioNotificacioneBean.getTipoMensaje()+"\","
							+ " \"from\": \""+envioNotificacioneBean.getFrom()+"\","
							+ " \"to\": \""+envioNotificacioneBean.getTo()+"\","
							+ " \"mapParameters\": {"
							+ "\"key\":  \""+envioNotificacioneBean.getKey()+"\""
							+ " },"
							+ "  \"canal\": \"1\""
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}

			} 
			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);


			conn.disconnect();

		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
		}

		return json;
	}



	/**
	 * @param ValidaContratoBean: Este Objeto trae los datos que se van a usar para poder consumir el servicio.
	 * @return nos retorna un JSON donde nos valida que el cambio de bandera fue exitoso o surgio algun error.
	 * Descripción Este metodo consume un servicio de ESB Fuse que realiza el cambio de bandera a contrato aceptado.
	 */
	public JSONObject cambioBandera(ValidaContratoBean bean)  {

		String output=null;
		String resultado="";

		JSONParser jsonParser=null; 
		JSONObject json=null; 

		try {

			URL url = new URL(bean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String inputs=

					"{"
							+ " \"Usuario\": "+bean.getUsuario()+","
							+ " \"ContratoAceptado\": "+bean.getContratoAceptado()+""
							+ "}"; 


			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));


				while ((output = br.readLine()) != null) {

					resultado=resultado+output; 
				}
			} 

			jsonParser = new JSONParser(); 
			json = (JSONObject) jsonParser.parse(resultado);

			conn.disconnect();

		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
		}

		return json;
	}


	/**
	 * @param PerfilBean Este Objeto trae los datos que le mandamos a JSON
	 * @return Nos retorna un JSONObject donde nos trar los perfiles
	 * Descripción Este metodo consume un servicio que nos trae los perfiles
	 */
	public JSONObject consultaPerfiles(PerfilBean basicoBean ) {

		String output=null;
		String resultado="";
		JSONParser jsonParser=null; 
		JSONObject json=null;   


		try { 

			URL url = new URL(basicoBean.getUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String inputs=
					" {"
							+ "\"idPersona\":"+basicoBean.getIdPersona()+","
							+ " \"canal\": \" "+basicoBean.getCanal()+" \" ,"
							+ "\"ticket\":{"
							+ " \"id_user\": \""+basicoBean.getIdUser()+" \","
							+ "\"id_creds\": \""+basicoBean.getIdCreds()+" \""
							+ "},"
							+ " \"nombreSistema\": \" "+basicoBean.getNombreSistema()+" \" "
							+ "}"; 

			OutputStream os = conn.getOutputStream();
			os.write(inputs.getBytes());
			os.flush();

			if (conn.getResponseCode() ==200) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				while ((output = br.readLine()) != null) { 
					resultado=resultado+output;
				}

			} 
			jsonParser = new JSONParser();
			json = (JSONObject) jsonParser.parse(resultado);




			conn.disconnect();

		} catch (MalformedURLException e) {
			LOGGER.warning(e.getMessage());

		} catch (IOException e) { 
			LOGGER.warning(e.getMessage());

		} catch (ParseException e) {
			LOGGER.warning(e.getMessage());
		}

		return json;
	}



	/**
	 * @param RequestEntity<Object> requestEntity Trae los Headers 
	 * @return JSONObject nos retorna un  JSON donde nos indica si los Headers vienen con valor
	 * Descripción Este metodo valida su los Headers viene con valor nos retorna un  respuestaStatus=200 para continuar con la Operación en caso de 
	 * quen no sea asi nos trae un respuestaStatus=403 para regresar el JSON correspondiente a su error.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getValidaHeaders(RequestEntity<Object> requestEntity) {

		if(!requestEntity.getHeaders().getFirst("iv-user").equalsIgnoreCase("")){ 
			if(!requestEntity.getHeaders().getFirst("iv-creds").equalsIgnoreCase("")){ 
				if(!requestEntity.getHeaders().getFirst("iv-groups").equalsIgnoreCase("")){ 
					if(!requestEntity.getHeaders().getFirst("numero-cliente").equalsIgnoreCase("")){ 
						if(!requestEntity.getHeaders().getFirst("nombre-completo").equalsIgnoreCase("")){ 
							if(!requestEntity.getHeaders().getFirst("tipo-authenticacion").equalsIgnoreCase("")){ 
								if(!requestEntity.getHeaders().getFirst("contratoAceptado").equalsIgnoreCase("")){ 
									if(!requestEntity.getHeaders().getFirst("fechaUltimoAcceso").equalsIgnoreCase("")){ 
										if(!requestEntity.getHeaders().getFirst("Tipocanal").equalsIgnoreCase("")){ 
											if(!requestEntity.getHeaders().getFirst("mail").equalsIgnoreCase("")){  
												long resStatus=200;
												JSONObject jsonError=new JSONObject(); 
												jsonError.put("respuestaStatus",resStatus);   
												return  jsonError;
											}else{
												return error403();
											}
										}else{
											return error403();
										} 
									}else{
										return error403();
									}

								}else{
									return error403();
								}

							}else{
								return error403();
							}

						}else{
							return error403();
						}

					}else{
						return error403();
					}

				}else{
					return error403();
				}

			}else{
				return error403();
			}

		}else{
			return error403();
		}

	}



	/**
	 * 
	 * @param requestEntity
	 * @param beanEnvioNot
	 * @param basicoBean
	 * @param consultaServicioContratadoBean
	 * @param perfil
	 * @return  Merodo que se ejecuta en caso de que el contrato ya este aceptado
	 * Descripción este metodo realiza la ejecucion y validación de varios netodos donde realizan diferentes consyltas 
	 * 
	 * Se realizo un metodo para el ContratoAceptado eso queire decir que en caso de que el contrato ya este aceptado va a ejecutar estos metodos
	 *  - envioNotificaciones
	 *  - getConsultas: este metodo realiza la consulta todos los datos que se van a traer
	 */

	@SuppressWarnings("unchecked") 
	public Map<String, Object> getContratoAceptado(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot, ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean, PerfilBean perfil) {
		 
		try{
			Map<String,Object> jsonResponse=new LinkedHashMap<String,Object>();
			//				---------------------------Datos de respuesta----------------------------------------------------------
			jsonResponse.put("fechaUltimoAcceso",requestEntity.getHeaders().getFirst("fechaUltimoAcceso")); 
			jsonResponse.put("nombreUsuario",requestEntity.getHeaders().getFirst("nombre-completo"));
			jsonResponse.put("medioAcceso",requestEntity.getHeaders().getFirst("Tipocanal"));
			jsonResponse.put("mail",requestEntity.getHeaders().getFirst("mail"));
			//				----------------------------------------------------------------------------------------- 

			//				-----------------------------EJECUCION DE METODO---------------------------------------------
		 
			JSONObject jsonEnvioNot= envioNotificaciones(beanEnvioNot);
			 
 
			String envioNotStatus=jsonEnvioNot.get("responseStatus")+"";
			if(envioNotStatus.equals("200")){
				  
				JSONObject varConsultas=getConsultas(perfil,basicoBean,consultaServicioContratadoBean);
				 
				jsonResponse.putAll(varConsultas);

				return jsonResponse;

			}else{  
				LOGGER.info("");
				return  error403();

			}

		}catch (Exception e) {
			LOGGER.warning(e.getMessage());
			return  error403();
		}




	}

	/**
	 * 
	 * @param perfil datos de perfiles
	 * @param basicoBean datos de datod basicos
	 * @param consultaServicioContratadoBean datos de servicios contratados
	 * @return JSONObject JSON ya armado que se va a mostrar
	 * Descripción en este metodo ejecuta varios metodo usando el @Async que los ejecuta en paralelo
	 */
	@SuppressWarnings("unchecked")
	@Async
	private JSONObject getConsultas(PerfilBean perfil ,ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean) {
		
		System.out.println("url de perfil: "+perfil.getUrl());
		
		System.out.println("url de ConsultaDatoBasicoBean: "+basicoBean.getUrl());
		
		System.out.println("url de ConsultaServicioContratadoBean: "+consultaServicioContratadoBean.getUrl());
 
		JSONObject jsonResponse=new JSONObject(); 
		JSONObject jsonperfiles= consultaPerfiles(perfil);  
		JSONObject jsonObject= consultaDatosBasicos(basicoBean);  
		jsonResponse.put("nombreRazonSocial", jsonObject.get("nombreRazonSocial"));
		jsonResponse.put("responseStatus", jsonObject.get("responseStatus"));
		jsonResponse.put("responseError", jsonObject.get("responseError"));
		jsonResponse.put("listaTelefonos", jsonObject.get("listaTelefonos")); 

		JSONObject jsonObjectservicios= consultaServiciosContratados( consultaServicioContratadoBean );
		 
		jsonResponse.put("consultaServiciosContratados", jsonObjectservicios);

		jsonperfiles.remove("responseError");
		jsonperfiles.remove("responseStatus");

		jsonResponse.putAll(jsonperfiles);

		return jsonResponse;


	}



	/**
	 * 
	 * @param requestEntity
	 * @param beanEnvioNot
	 * @param basicoBean
	 * @param consultaServicioContratadoBean
	 * @param perfil
	 * @return 
	 * Description Metodo que se ejecuta en caso de que el contrato aun no este aceptado consume un servicio que 
	 * realiza el cambio de bandera como aceptado. 
	 */

	@SuppressWarnings("unchecked") 
	public Map<String,Object> banderaAcceso(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot,
			ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean,
			PerfilBean perfil,
			ValidaContratoBean validaContrato){


		try{

			Map<String,Object> jsonResponse=new LinkedHashMap<String,Object>();
			JSONObject jsonObject=null;
			Map<String, Object> map = new HashMap<String, Object>(); 
			map = (Map<String, Object>) requestEntity.getBody();

			if(map.get("banderaAcceso").toString().equalsIgnoreCase("1")){

				//				---------------------------Datos de respuesta----------------------------------------------------------
				jsonResponse.put("fechaUltimoAcceso",requestEntity.getHeaders().getFirst("fechaUltimoAcceso")); 
				jsonResponse.put("nombreUsuario",requestEntity.getHeaders().getFirst("nombre-completo"));
				jsonResponse.put("medioAcceso",requestEntity.getHeaders().getFirst("Tipocanal"));
				jsonResponse.put("mail",requestEntity.getHeaders().getFirst("mail"));
				//				---------------------------------------------------------------------------------------------

				//				-----------------------------EJECUCION DE METODO---------------------------------------------
				JSONObject jsonEnvioNotElse= envioNotificaciones(beanEnvioNot);
				String envioNotiStatus=jsonEnvioNotElse.get("responseStatus")+"";
				if(envioNotiStatus.equals("200")){
 
					JSONObject jsonResCambioBandera= cambioBandera(validaContrato);
					String varCambioBandera=jsonResCambioBandera.get("CodigoRespuesta")+"";
					//						 ---------------------------------------------------------------------------------------- 
					if(varCambioBandera.equals("0")){
						//-----------------------------Valida que el cambio de vandera de cambioBandera sea igual a 0---------------------


						JSONObject varConsultas=getConsultas(perfil,basicoBean,consultaServicioContratadoBean);

						jsonResponse.putAll(varConsultas);
						LOGGER.info("Acceso Cliente OK");
						return jsonResponse;

					} else{
						LOGGER.warning("Error al consumir el servicio Cambio de bandera");
						return  error403();
					}

				}else{
					LOGGER.warning("Error al consumir el servicio Envio de Notidicaciones");
					return  error403();
				}

			}else if(map.get("banderaAcceso").toString().equalsIgnoreCase("")){
				LOGGER.info("Acceso Cliente '' ");
				long resStatus=200;
				jsonObject=new JSONObject(); 
				jsonObject.put("mostrarContrato",true);
				jsonObject.put("responseStatus",resStatus);
				jsonObject.put("responseError",""); 
				return  jsonObject;
			}else {
				LOGGER.info("bandera Acceso trae otro valor ");
				long resStatus=200;
				jsonObject=new JSONObject();  
				jsonObject.put("responseStatus",resStatus);
				jsonObject.put("responseError",""); 
				return  jsonObject;
			}

		}catch (Exception e) {
			LOGGER.warning(e.getMessage());
			return  error403();
		}
	}

	/**
	 * 
	 * @return Regresa un JSON que nos trae el error 403
	 * Description Metodo donde manejamos el error 403. Este error se ejecuta en caso de que se produsca un error al consumir uno de los servicios.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject error403(){
		long resStatus=403;
		JSONObject jsonError=new JSONObject(); 
		jsonError.put("responseStatus",resStatus);
		jsonError.put("responseError","Por Definir");   
		return  jsonError;
	}



}
