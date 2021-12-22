package org.gteperu.erp.everest.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.gteperu.erp.everest.service.TipodecambioService;
import org.gteperu.erp.everest.domain.Tipodecambio;
import org.gteperu.erp.everest.service.TipoCambioService;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.TipoCambio;
//import org.partner.efipagos.service.AuditoriaService;
import org.gteperu.erp.everest.utils.Constantes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Resource;

/**
 * Rest Controller que permite la ejecución de los Servicios Web para un Tipo de
 * Cambio de Moneda, tales como: insertar, actualizar, eliminar, listar, buscar
 * por ID y filtrar por la descripción de la Moneda del Tipo de Cambio. Para
 * resumir, este servicio proporciona la lista de los diferentes tipos de cambio
 * de moneda que existen actualmente asociados a la Empresa.
 */
@RestController
@RequestMapping(value = "/api/tipocambio") 
public class TipodecambioRestController {

	/*@Resource(name = "tipodecambioService")
	TipodecambioService tipodecambioService;*/
	 

	@Autowired
	TipoCambioService service;

	/**
	 * Servicio web que permite registrar como nuevo, actualizar o eliminar: un
	 * Tipo de Cambio de Moneda. Se desencadena desde la pestaña Catálogos\Tipo
	 * de Cambio, en el módulo de Tesorería.
	 *
	 * @(#)inserta 1.0 21/08/2016 Se agregó la documentación del servicio
	 *             [inserta] web para: un Tipo de Cambio, rrazuri@gteperu.com
	 */
	/*
	@RequestMapping(value = "/inserta", method = RequestMethod.POST)
	public ResponseWrapper insertaTipodecambio(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		this.auditoria = new Auditoria();
		ResponseWrapper response = new ResponseWrapper();
		try {

			// se verifica que el usuario haya iniciado sesión en el sistema, y
			// se registra como log en la tabla Auditoria.
			this.auditoria.setIdempleado(funciones.sacaid(request));
			Integer cat = 0;
			if (tipocambio.getAccion().equals(Constantes.accionDelet)) {
				// obtenido el parámetro, el servicio cambiará el estado del
				// objeto afectado
				if (tipocambio.getEstado() == 1) {
					tipocambio.setEstado(0); // anulado
				} else {
					tipocambio.setEstado(1); // activo
				}
				// si en el parámetro acción se envía una D, entonces la
				// operación del servicio será una eliminación
				this.auditoria.setAccion("D");
				cat = tipodecambioService.eliminaTipodecambio(tipocambio);
			} else {
				tipocambio
						.setFechapublicacion(funciones.retornaCalendarFromString(tipocambio.getFechapublicacionStr()));
				if (tipocambio.getIdtipocambio() != null && tipocambio.getIdtipocambio() > 0) {
					// si el id del objeto enviado es mayor a cero, entonces la
					// operación será una actualización
					this.auditoria.setAccion("U");
					cat = tipodecambioService.updateTipodecambio(tipocambio);
				} else {
					// si el id del registro es cero, entonces la operacion será
					// una inserción

					Tipodecambio tcte = new Tipodecambio();

					tcte = tipodecambioService.retornaTodoTipodecambioPorFechayMoneda(tipocambio);
					if (tcte != null) {
						cat = -3;
					} else {
						this.auditoria.setAccion("I");

						cat = tipodecambioService.insertaTipodecambio(tipocambio);
					}
				}
			}
			// si el registro se realiza correctamente o muestra un error, dicho
			// mensaje se guardará en la tabla de auditoria
			if (cat != null && cat > 0) {
				this.auditoria.setIdregistro(tipocambio.getIdtipocambio());
				response.setEstado(Constantes.valTransaccionOk);
				response.setDefaultObj(tipocambio);
				response.setMsg(Constantes.msgTransaccionOk);
				this.auditoria.setMensaje(Constantes.msgTransaccionOk);
			} else if (cat == -3) {
				response.setEstado(4);
				response.setMsg(Constantes.msgTransaccionRegistroExistente);
				this.auditoria.setMensaje(Constantes.msgTransaccionRegistroExistente);
			} else {

				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				this.auditoria.setMensaje(Constantes.msgTransaccionError);
			}

		} catch (Exception e) {
			// En caso de alguna excepción, esta se registrará de igual manera
			// en la tabla Auditoria.
			response.setEstado(Constantes.valTransaccionSinPermiso);
			if (e.getMessage().contains("viola la llave foránea")) {
				response.setMsg(Constantes.msgTransaccionErrorForanea);
			} else {
				response.setMsg(Constantes.msgTransaccionError);
			}
			this.auditoria.setAccion(auditoria.getAccion() + "-E");
			this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
		} finally {
			// Se registra en la tabla Auditoria, la tabla actual y el servicio
			// web, como referencia y se completa el log del registro.
			this.auditoria.setTabla("tipocambio");
			this.auditoria.setMetodo("tipocambio-inserta");
			this.auditoriaService.insertaAuditoria(auditoria);
			// Finalmente se retorma el objeto.
			return response;
		}
	}
*/
	/**
	 * Servicio web que permite retornar todos los tipos de cambio de moneda
	 * según su estado (si es 1; son los activos, si es 0; son los anulados)
	 * esta opción permitirá visualizar todos los Tipos de Cambio de Moneda
	 * creados en la aplicación desde la pestaña Catálogos\Tipo de Cambio, en el
	 * módulo de Tesorería.
	 *
	 * @(#)list 1.0 21/08/2016 Se agregó la documentación del servicio [list]
	 *          web para: Tipos de Cambio, rrazuri@gteperu.com
	 */
	/*
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseWrapper listTipodecambioActivas(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		this.auditoria = new Auditoria();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			// se verifica que el usuario haya iniciado sesión en el sistema.
			if (tipocambio.getAccion() != null) {
				// si el atributo acción es diferente de nulo, se iniciará el
				// servicio web.
				try {
					// Se obtiene el estado del objeto a través del atributo
					// acción, que se usará como parámetro en el servicio web.
					int val = Integer.parseInt(tipocambio.getAccion());
					tipocambio.setEstado(val);
					objtList = tipodecambioService.retornaTipodecambioPorEstado(tipocambio);
					if (objtList != null && objtList.size() > 0) {
						// si el registro se realizó correctamente se mostrará
						// un mensaje de transacción correcta en pantalla
						obj.setMsg(Constantes.msgTransaccionOk);
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setAaData(objtList);
						obj.setCantidad(((Tipodecambio) objtList.get(0)).getCantidad());
					} else {
						// en caso contrario se mostrará un mensaje de parámetro
						// erróneo en pantalla
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setAaData(objtList);
					}
				} catch (Exception e) {
					// en caso de alguna otra excepción se mostrará el mensaje
					// en pantalla
					obj.setMsg(Constantes.msgTransaccionErrorNull + "Convirtiendo a numero");
					obj.setEstado(Constantes.valTransaccionErrornull);
					obj.setAaData(objtList);
				}

			} else {
			}

		} catch (Exception e) {
			// En caso de alguna excepción, esta se registrará de igual manera
			// en la tabla Auditoria.
			this.auditoria.setIdempleado(funciones.sacaid(request));
			this.auditoria.setTabla("tipocambio");
			this.auditoria.setMetodo("tipocambio-list");
			this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
			this.auditoria.setAccion("L-tipocambios");
			obj.setMsg(Constantes.msgTransaccionErrorNull);
			obj.setEstado(Constantes.valTransaccionErrornull);
			obj.setAaData(objtList);

		} finally {
			// Finalmente se retorma el objeto.
			return obj;
		}

	}
*/
	/**
	 * Servicio web que permite obtener el tipo de cambio desde la url de la
	 * pagina de la sbs peru se envia la fecha actual como parametro y se
	 * ejecuta cada 20 minutos hasta contar con el tipo de cambio actual se
	 * empieza a consultar desde las 0 horas si no se encuentra publicada se in
	 * gresa el promedio de los dias anteriores hasta contar con la real de la
	 * fecha, una vez
	 */
	@RequestMapping(value = "/leersbstipocambio", method = RequestMethod.POST)
	public ResponseWrapper leerTipoCambioSBS(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			// URL url = new
			// URL("http://www.sbs.gob.pe/app/stats/tipodecambioxls.asp?fec=13/03/2017");
			// InputStream in = url.openStream();
			// HSSFWorkbook workbook = new HSSFWorkbook(in);
		  //URL urla = new URL("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias");
			URL urla = new URL("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias");
			HttpURLConnection uc = (HttpURLConnection) urla.openConnection();
			uc.setRequestProperty("User-Agent", "");
			// Read all the text returned by the server
			BufferedReader ins = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String str;
			int f = 0;
			int g = 0;
			String read = "";
			String dia = "<td width='4%' align='center' class=\"H3\">";
			int encontro = 0;
			int diatc = 0;
			double compra = 0.0;
			double venta = 0.0;
			Tipodecambio tp = new Tipodecambio();
			List<Tipodecambio> lsTp = new ArrayList<>();
			while ((str = ins.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)
				if (f == 1) {
					
					diatc = Integer.parseInt(str.replace("<strong>", "").replace("</strong>", "").trim());
					f = 0;
					encontro = 1;
				}
				read = str;
				if (read.contains(dia)) {
					f++;
					encontro = 0;
				}
				if (encontro == 1) {
					g++;
				}
				if (g == 5) {

					compra = Double.parseDouble(str.trim());
				}
				if (g == 9) {
					tp = new Tipodecambio();
					venta = Double.parseDouble(str.trim());
					encontro = 0;
					g = 0;
					tp.setPrecio(venta);
					tp.setCompra(compra);
					tp.setMonedaorigen(1);
					tp.setMonedadestino(2);
					tp.setDia(diatc);

					
					lsTp.add(tp);
					diatc = 0;
					compra = 0.0;
					venta = 0.0;

				}

			}
			
			// Fecha actual desglosada:
			Calendar fecha = Calendar.getInstance();
			int año = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH) + 1;
			int diaact = fecha.get(Calendar.DAY_OF_MONTH);
			Date fechaActual = new Date();
			for (int i = 0; i < lsTp.size(); i++) {
				if (lsTp.get(i).getDia() == diaact) {
					lsTp.get(i).setEstado(1);
				//	lsTp.get(i).setFechapublicacion(new Timestamp(fechaActual.getDate()));
				//	lsTp.get(i).setFecharegistro(new Timestamp(fechaActual.getDate()));					
				} else {
					lsTp.get(i).setEstado(2);
				}
				objtList.add(lsTp.get(i));
			}
			ins.close();
			obj.setAaData(objtList);

			return obj;

		} catch (Exception e) {
			obj.setMsg(Constantes.msgTransaccionErrorNull);
			obj.setEstado(Constantes.valTransaccionErrornull);
			obj.setAaData(objtList);

		} finally {
			// Finalmente se retorma el objeto.
			return obj;
		}

	}

	/**
	 * Servicio web que permite filtar por la descripción de una moneda
	 * (origen/destino) para un Tipo de Cambio, esta opción permitirá realizar
	 * búsquedas de tipos de Cambio, ingresando la descripción de la Moneda en
	 * la pestaña Catálogos\Tipo de Cambio, en el módulo de Tesorería.
	 *
	 * @(#)like 1.0 21/08/2016 Se agregó la documentación del servicio [like]
	 *          web para: un Tipo de Cambio, rrazuri@gteperu.com
	 */
	/*
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public ResponseWrapper listTipodecambioPorLikeNombre(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		this.auditoria = new Auditoria();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			// se verifica que el usuario haya iniciado sesión en el sistema.
			if (tipocambio.getAccion() != null) {
				// si el atributo acción es diferente de nulo, se iniciará el
				// servicio web.
				try {
					// Se envía todo el objeto incluyendo la dirección, que se
					// usará como parámetro en el servicio web.
					tipocambio.setFiltro(tipocambio.getAccion());
					objtList = tipodecambioService.retornaTipodecambioLikePorNombre(tipocambio);
					if (objtList != null && objtList.size() > 0) {
						// si el registro se realizó correctamente se mostrará
						// un mensaje de transacción correcta en pantalla
						obj.setMsg(Constantes.msgTransaccionOk);
						obj.setEstado(Constantes.valTransaccionOk);
						obj.setAaData(objtList);
					} else {
						// en caso contrario se mostrará un mensaje de parámetro
						// erróneo en pantalla
						obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
						obj.setEstado(Constantes.valTransaccionNoEncontro);
						obj.setAaData(objtList);
					}
				} catch (Exception e) {
					// en caso de alguna otra excepción se mostrará el mensaje
					// en pantalla
					obj.setMsg(Constantes.msgTransaccionErrorNull + "Convirtiendo a numero");
					obj.setEstado(Constantes.valTransaccionErrornull);
					obj.setAaData(objtList);
				}
			} else {
			}

		} catch (Exception e) {
			// En caso de alguna excepción, esta se registrará de igual manera
			// en la tabla Auditoria.
			this.auditoria.setIdempleado(funciones.sacaid(request));
			this.auditoria.setTabla("tipocambio");
			this.auditoria.setMetodo("tipocambio-like");
			this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
			this.auditoria.setAccion("LK-tipocambio");
			obj.setMsg(Constantes.msgTransaccionErrorNull);
			obj.setEstado(Constantes.valTransaccionErrornull);
			obj.setAaData(objtList);

		} finally {
			// Finalmente se retorna el Objeto.
			return obj;
		}

	}
*/
	/**
	 * Servicio web que permite encontrar un tipo de cambio de moneda, el cual
	 * utiliza como parámetro el Id para encontrar el mismo. Esta opción es
	 * utilizada antes de actualizar cualquiera de los campos o eliminar un Tipo
	 * de Cambio ya que necesito conocer el Id del mismo para realizar la
	 * acción. Se desencadena desde la pestaña Catálogos\Tipo de
	 * Cambio\Actualizar||Eliminar, en el módulo de Tesorería.
	 *
	 * @(#)finid 1.0 21/08/2016 Se agregó la documentación del servicio [finid]
	 *           web para: un Tipo de Cambio, rrazuri@gteperu.com
	 */
	/*
	@RequestMapping(value = "/tipocambiohoy", method = RequestMethod.POST)
	public ResponseWrapper retornaTodoTipodecambioActual(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ResponseWrapper obj = new ResponseWrapper();
		Tipodecambio c = new Tipodecambio();
		this.auditoria = new Auditoria();
		try {

			// se verifica que el usuario haya iniciado sesión en el sistema.
			// Se obtiene el Id del objeto, que se usará como parámetro en el
			// servicio web.
			c = tipodecambioService.retornaTodoTipodecambioActual(tipocambio);
			if (c != null) {
				// si el objeto es diferente de nulo, se mostrará un mensaje de
				// transacción correcta en pantalla
				obj.setDefaultObj(c);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setMsg(Constantes.msgTransaccionOk);
			} else {
				// en caso contrario se mostrará un mensaje de transacción
				// errónea en pantalla
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setMsg(Constantes.msgTransaccionNoEncontrada);
			}

		} catch (Exception e) {
			// En caso de alguna excepción, esta se registrará de igual manera
			// en la tabla Auditoria.
			this.auditoria.setIdempleado(funciones.sacaid(request));
			this.auditoria.setTabla("tipocambio");
			this.auditoria.setMetodo("tipocambio-finid");
			this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
			this.auditoria.setAccion("FD-tipocambio");
			obj.setEstado(Constantes.valTransaccionError);
			obj.setMsg(Constantes.msgTransaccionError);
		} finally {
			// Finalmente se retorna el Objeto.
			return obj;
		}

	}*/

	/**
	 * Servicio web que permite encontrar un tipo de cambio de moneda, el cual
	 * utiliza como parámetro el Id para encontrar el mismo. Esta opción es
	 * utilizada antes de actualizar cualquiera de los campos o eliminar un Tipo
	 * de Cambio ya que necesito conocer el Id del mismo para realizar la
	 * acción. Se desencadena desde la pestaña Catálogos\Tipo de
	 * Cambio\Actualizar||Eliminar, en el módulo de Tesorería.
	 *
	 * @(#)finid 1.0 21/08/2016 Se agregó la documentación del servicio [finid]
	 *           web para: un Tipo de Cambio, rrazuri@gteperu.com
	 */
	/*
	@RequestMapping(value = "/finid", method = RequestMethod.POST)
	public ResponseWrapper retornaTipodecambioPorId(@RequestBody @Validated Tipodecambio tipocambio,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ResponseWrapper obj = new ResponseWrapper();
		Tipodecambio c = new Tipodecambio();
		this.auditoria = new Auditoria();
		try {

			// se verifica que el usuario haya iniciado sesión en el sistema.

			// Se obtiene el Id del objeto, que se usará como parámetro en el
			// servicio web.
			c = tipodecambioService.retornaTipodecambioPorID(tipocambio);
			if (c != null) {
				// si el objeto es diferente de nulo, se mostrará un mensaje de
				// transacción correcta en pantalla
				obj.setDefaultObj(c);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setMsg(Constantes.msgTransaccionOk);
			} else {
				// en caso contrario se mostrará un mensaje de transacción
				// errónea en pantalla
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setMsg(Constantes.msgTransaccionNoEncontrada);
			}

		} catch (Exception e) {
			// En caso de alguna excepción, esta se registrará de igual manera
			// en la tabla Auditoria.
			this.auditoria.setIdempleado(funciones.sacaid(request));
			this.auditoria.setTabla("tipocambio");
			this.auditoria.setMetodo("tipocambio-finid");
			this.auditoria.setMensaje(Constantes.msgTransaccionError + e.getMessage());
			this.auditoria.setAccion("FD-tipocambio");
			obj.setEstado(Constantes.valTransaccionError);
			obj.setMsg(Constantes.msgTransaccionError);
		} finally {
			// Finalmente se retorna el Objeto.
			return obj;
		}

	}*/
	
	@RequestMapping(value = "/retornaTipodeCambio", method = RequestMethod.POST)
	public ResponseWrapper retornaTipodeCambio(@RequestBody @Validated Timestamp fecha) throws Exception {
		ResponseWrapper obj = new ResponseWrapper();
		try {
			TipoCambio tc = null;
			Calendar f = Calendar.getInstance();
			f.setTime(fecha);
			for (int i = 0; i < 30; i++) {
				tc = service.encontrarPorFecha(new Timestamp(f.getTimeInMillis()));
				if (tc != null) {
					break;
				}
				f.add(Calendar.DAY_OF_MONTH, -1);
			}
			if (tc != null ) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setTipodeCambio(String.valueOf(tc.getCompra()));
			} else {
				obj.setMsg(Constantes.msgTipoDeCambioSunatError);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setTipodeCambio("0.0");
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName()+" retornaTipodeCambio. ERROR : "+e.getMessage());
			obj.setMsg(Constantes.msgTipoDeCambioSunatError);
			obj.setEstado(Constantes.valTransaccionErrornull);
		} 
		return obj;
	}
	
	
	
	/*  Listar el tipo de cambio SUNAT
	 *  
	 *  Retorna el tipo de cambio de la sunat dependiendo de la fecha enviada
	 * 
	 * MAD
	 * 
	 * 22/08/2019
	 * tipocambio/retornaTipodeCambio
	*/
	
//	@RequestMapping(value = "/retornaTipodeCambio", method = RequestMethod.POST)
//	public ResponseWrapper retornaTipodeCambio(@RequestBody @Validated Timestamp fecha, HttpServletRequest request)
//			throws Exception {
//		HttpSession session = request.getSession();
//		List objtList = new ArrayList();
//		ResponseWrapper obj = new ResponseWrapper();
//		//tipoOperacionGuia.setGrupo(Constantes.INGR);
//		String tipodecambio = "";
//		try {
//				try {
//					tipodecambio = obtenerTipoCambio(fecha);
//					//objtList = tipoOperacionGuiaService.listarOperacionesGuiaIngreso(tipoOperacionGuia);
//					if (tipodecambio != null ) {
//						obj.setMsg(Constantes.msgTransaccionOk);
//						obj.setEstado(Constantes.valTransaccionOk);
//						obj.setTipodeCambio(tipodecambio);
//					} else {
//						obj.setMsg(Constantes.msgTipoDeCambioSunatError);
//						obj.setEstado(Constantes.valTransaccionNoEncontro);
//						obj.setTipodeCambio(tipodecambio);
//						//obj.setAaData(objtList);
//					}
//				} catch (Exception e) {
//					System.out.println(this.getClass().getSimpleName()+" retornaTipodeCambio. ERROR : "+e.getMessage());
//					obj.setMsg(Constantes.msgTipoDeCambioSunatError);
//					obj.setEstado(Constantes.valTransaccionErrornull);
//					obj.setTipodeCambio(tipodecambio);
//					//obj.setAaData(objtList);
//				}
//
//		} catch (Exception e) {
//			System.out.println(this.getClass().getSimpleName()+" retornaTipodeCambio. ERROR : "+e.getMessage());
//			obj.setMsg(Constantes.msgTipoDeCambioSunatError);
//			obj.setEstado(Constantes.valTransaccionErrornull);
//			obj.setTipodeCambio(tipodecambio);
//			//obj.setAaData(objtList);
//		} finally {
//			return obj;
//		}
//	}

	 public String obtenerTipoCambio (Timestamp fechapago) throws IOException{
		    List objtList = new ArrayList();
		    DateFormat formatoFecha_mes = new SimpleDateFormat("MM");
		    DateFormat formatoFecha_año = new SimpleDateFormat("yyyy");
		    Date fecha_pago_date = new Date(fechapago.getTime());
		    String fechapago_mes = formatoFecha_mes.format(fecha_pago_date);
		    String fechapago_año = formatoFecha_año.format(fecha_pago_date);
		    
		    //System.out.println("mes "+fechapago.getMonth() +"año "+fechapago.getYear());
			   // URL urla = new URL("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias");
     		 //     URL urla = new URL("http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes="+fechapago_mes+"&anho="+fechapago_año);
          	  URL urla = new URL("https://e-consulta.sunat.gob.pe/cl-at-ittipcam/tcS01Alias?mes="+fechapago_mes+"&anho="+fechapago_año);

		      
		      
		    HttpURLConnection uc = (HttpURLConnection) urla.openConnection();
			uc.setRequestProperty("User-Agent", "");
			// Read all the text returned by the server
			BufferedReader ins = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String str;
			int f = 0;
			int g = 0;
			String read = "";
			String dia2 = "<td width='4%' align='center' class=\"H3\">";
			int encontro = 0;
			int diatc = 0;
			double compra = 0.0;
			double venta = 0.0;
			Tipodecambio tp = new Tipodecambio();
			List<Tipodecambio> lsTp = new ArrayList<>();
			while ((str = ins.readLine()) != null) {
				// str is one line of text; readLine() strips the newline
				// character(s)
				if (f == 1) {
					
					diatc = Integer.parseInt(str.replace("<strong>", "").replace("</strong>", "").trim());
					f = 0;
					encontro = 1;
				}
				read = str;
				if (read.contains(dia2)) {
					f++;
					encontro = 0;
				}
				if (encontro == 1) {
					g++;
				}
				if (g == 5) {

					compra = Double.parseDouble(str.trim());
				}
				if (g == 9) {
					tp = new Tipodecambio();
					venta = Double.parseDouble(str.trim());
					encontro = 0;
					g = 0;
					tp.setPrecio(venta);
					tp.setCompra(compra);
					tp.setMonedaorigen(1);
					tp.setMonedadestino(2);
					tp.setDia(diatc);

					
					lsTp.add(tp);
					diatc = 0;
					compra = 0.0;
					venta = 0.0;

				}

			}
			Double tipo_cambio=0.00;
			// Fecha actual desglosada:
			Calendar fecha = Calendar.getInstance();
			fecha.setTimeInMillis(fechapago.getTime());
		 	
		 	int diaact = fecha.get(Calendar.DAY_OF_MONTH);
			Date fechaActual = new Date();

			Integer index=null;
			try{
			for (int j = 0; j < lsTp.size(); j++) {
		 		if (lsTp.get(j).getDia() == diaact) {
					lsTp.get(j).setEstado(1);
					index=j;
				 		break;
			 	} else {
			 		if (lsTp.get(j).getDia() < diaact) {
						lsTp.get(j).setEstado(3);
						index=j;
				  		}else{
				  		break;
				  	}
			 		}
		 	}
			if(index!=null){
			 tipo_cambio=lsTp.get(index).getPrecio();
			 
			}else{
			 
			tipo_cambio=lsTp.get(lsTp.size()-1).getPrecio();
			}
		 	ins.close();
			}catch(Exception e ){
				e.getMessage();
			}finally{
				return  Double.toString(tipo_cambio);
			}		 	
	 }
}
