package org.gteperu.erp.everest.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.BaseResponseWrapper;
import org.gteperu.erp.everest.domain.EmpresaSunat;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Sutipodocumento;
import org.gteperu.erp.everest.domain.Ubigeo;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service.SutipodocumentoService;
import org.gteperu.erp.everest.service._ClienteService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/api/cliente")
public class _ClienteRestController {

	@Resource(name = "clienteService")
	_ClienteService clienteService;
	@Resource(name = "parametrosService")
	ParametrosService parametrosService;
	@Resource(name = "companyService")
	_CompanyService companyService;
	
	@Autowired
	SutipodocumentoService sutipodocumentoService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/generarReporte")
	public @ResponseBody byte[] generarReporte(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
		try {
			byte[] data = null;
			List<_Clientes> lsCliente = clienteService.listarClientePorEmpresa(cliente);
			String rutaExcel = parametrosService.retornaParametroPorCodigo(Constantes.codigoRutaExcelBase).getValor();

			String rutaExcelDesc = rutaExcel + "/REPORTE_CLIENTE.xlsx";
			
			FileInputStream input = new FileInputStream(rutaExcel + "/REPORTE_CLIENTE_BASE.xlsx");
			File file = new File(rutaExcelDesc);
			if (file.exists()) {
				file.delete();
			}
			
			List<Sutipodocumento> lsSutipodocumento = sutipodocumentoService.comboSutipodocumentoidentidad();

			XSSFWorkbook libro = new XSSFWorkbook(input);
			XSSFSheet reporte = libro.getSheetAt(0);

			int inicioRow = 3;
			int contador = 1;
			for (_Clientes cl : lsCliente) {
				Row fila = reporte.getRow(inicioRow++);
				fila.getCell(0).setCellValue(contador++);
				fila.getCell(1).setCellValue(cl.getRazon_social());
				fila.getCell(2).setCellValue(retornaTipoDoc(lsSutipodocumento, cl.getTipo_doc()));
				fila.getCell(3).setCellValue(cl.getNro_doc());
				fila.getCell(4).setCellValue(cl.getMovil());
				fila.getCell(5).setCellValue(cl.getFijo());
				fila.getCell(6).setCellValue(cl.getEmail());
				fila.getCell(7).setCellValue(cl.getDireccion_fiscal() != null ? cl.getDireccion_fiscal()
						: cl.getDireccion_fiscal2() != null ? cl.getDireccion_fiscal2() : cl.getDireccion_fiscal3());
			}

			input.close();
			FileOutputStream outPut = new FileOutputStream(rutaExcelDesc);
			libro.write(outPut);
			outPut.close();

			Path path = Paths.get(rutaExcelDesc);
			data = Files.readAllBytes(path);
			return data;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " generarReporte. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/generarReporte",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
	}

	private String retornaTipoDoc(List<Sutipodocumento> lsSutipodocumento, String tipo_doc) {
		for (Sutipodocumento sutipodocumento : lsSutipodocumento) {
			if (sutipodocumento.getCodigosunat().equals(tipo_doc)) {
				return sutipodocumento.getAbrv();
			}
		}
		return "-";
	}

	/*
	 * registra una cliente nuevo dentro de la empresa
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertaCliente", method = RequestMethod.POST)
	public ResponseWrapper insertCliente(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {

			Boolean resp = clienteService.insertarCliente(cliente);

			if (resp) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgTransaccionInsertarNroDocRepetido);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertarCliente. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaCliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return response;

	}

	/*
	 * Lista clientes segun la empresa seleccionada
	 * 
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listarCliente", method = RequestMethod.POST)
	public ResponseWrapper listarCliente(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			objtList = clienteService.listarCliente(cliente);
			if (objtList != null && objtList.size() > 0) {
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(objtList);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(objtList);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarCliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return obj;

	}

	/*
	 * Actualiza datos de clientes seleccionado
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/actualizarCliente", method = RequestMethod.POST)
	public ResponseWrapper actualizarCliente(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		String cod_emp;
		try {
			Integer cat = 0;
			cat = clienteService.actualizarCliente(cliente);
			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionActualizarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionActualizarClienteError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarCliente. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/actualizarCliente" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizarCliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return response;

	}

	/*
	 * Elimina cliente seleccionado de la aplicacion
	 * 
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/eliminarCliente", method = RequestMethod.POST)
	public ResponseWrapper eliminarCliente(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = clienteService.eliminarCliente(cliente);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionEliminarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionEliminarClienteError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarCliente. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminarCliente" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarCliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return response;

	}

	/*
	 * Lista datos clientes segun la empresa seleccionada
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listarClientePorEmpresa", method = RequestMethod.POST)
	public ResponseWrapper listarClientePorEmpresa(@RequestBody @Validated _Clientes cliente,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();

		try {
			objtList = clienteService.listarClientePorEmpresa(cliente);
			if (objtList != null && objtList.size() > 0) {
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(objtList);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(objtList);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarClientePorEmpresa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarClientePorEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return obj;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/cargarArchivoCliente", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload3(@RequestPart("cliente") _Clientes cliente,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		Integer empresa_actualizada = 0;
		ResponseWrapper response = new ResponseWrapper();
		Boolean bol = false;
		Boolean flgNull = false;
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		String rutamatriz = "", rutaexcel = "";
		Integer cat = 0;
		Boolean trans = false;

		try {
			if (files.length != 0) {
				file = files[0];
				file.getSize();
				byte[] bytes = file.getBytes();

				String separador = System.getProperty("file.separator");

				parametro.setGrupo(Constantes.codigoGrupoIniParametros);
				parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
				lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

				for (int i = 0; i < lsPar.size(); i++) {
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaMatriz)) {
						rutamatriz = lsPar.get(i).getValor();
					}
					if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaExcel)) {
						rutaexcel = lsPar.get(i).getValor();
					}
				}

				_Company emp = new _Company();
				emp.setId_empresa(cliente.getId_empresa());
				emp = companyService.retornaEmpresaPorIdempresa(emp);

				String ruta = rutamatriz + separador + rutaexcel + separador + emp.getNro_documento_empresa() + ".xlsx";

				bol = Files.deleteIfExists(Paths.get(ruta));
				bol = false;
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(ruta)));
				stream.write(bytes);
				stream.close();

				FileInputStream fsIP = new FileInputStream(ruta);

				XSSFWorkbook wb = new XSSFWorkbook(fsIP);

				XSSFSheet worksheet = wb.getSheetAt(0);
				for (int i = 2; i <= worksheet.getLastRowNum(); i++) {
					XSSFRow fil1 = worksheet.getRow(i);

					if (fil1 != null) {

						XSSFCell A1 = fil1.getCell(0);
						XSSFCell B1 = fil1.getCell(1);
						XSSFCell C1 = fil1.getCell(2);
						XSSFCell D1 = fil1.getCell(3);
						XSSFCell E1 = fil1.getCell(4);
						XSSFCell F1 = fil1.getCell(5);
						XSSFCell G1 = fil1.getCell(6);
						XSSFCell H1 = fil1.getCell(7);
						XSSFCell I1 = fil1.getCell(8);

						if (A1 == null || A1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda A" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							throw new ExceptionResponse();
						} else if (A1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cliente.setTipo_doc(String.valueOf((int) A1.getNumericCellValue()));
						} else {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("El número de documento solo debe contener digitos");
							throw new ExceptionResponse();
						}

						if (B1 == null || B1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda B" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							throw new ExceptionResponse();
						} else if (B1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cliente.setNro_doc(String.valueOf((int) B1.getNumericCellValue()));
						} else {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg(
									"El número de documento solo debe contener digitos, corregir celda B" + i + 1);
							throw new ExceptionResponse();
						}

						if (C1 == null || C1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setRazon_social("");
						} else if (C1.getCellType() == Cell.CELL_TYPE_STRING) {
							cliente.setRazon_social(C1.getStringCellValue());
						} else {
							cliente.setRazon_social("");
						}

						if (D1 == null || D1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setRazon_comercial("");
						} else if (D1.getCellType() == Cell.CELL_TYPE_STRING) {
							cliente.setRazon_comercial(D1.getStringCellValue());
						} else {
							cliente.setRazon_comercial("");
						}

						if (E1 == null || E1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setEmail("");
						} else if (E1.getCellType() == Cell.CELL_TYPE_STRING) {
							cliente.setEmail(E1.getStringCellValue());
						} else {
							cliente.setEmail("");
						}

						if (F1 == null || F1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setDireccion_fiscal("");
						} else if (F1.getCellType() == Cell.CELL_TYPE_STRING) {
							cliente.setDireccion_fiscal(F1.getStringCellValue());
						} else {
							cliente.setDireccion_fiscal("");
						}

						if (G1 == null || G1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setMovil("");
						} else if (G1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cliente.setMovil(String.valueOf((int) G1.getNumericCellValue()));
						} else {
							cliente.setMovil("");
						}

						if (H1 == null || H1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setFijo("");
						} else if (H1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cliente.setFijo(String.valueOf((int) H1.getNumericCellValue()));
						} else {
							cliente.setFijo("");
						}

						if (I1 == null || I1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							cliente.setCuenta_detraccion("");
						} else if (I1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cliente.setCuenta_detraccion(String.valueOf((int) I1.getNumericCellValue()));
						} else {
							cliente.setCuenta_detraccion("");
						}

						cat = clienteService.actualizarClientePorNumDocumento(cliente);
						if (cat == 0) {
							trans = clienteService.insertarCliente(cliente);
							if(trans == true) cat = 1;
						}

					}

				}

				bol = Files.deleteIfExists(Paths.get(ruta));

			} else {

			}

			if (cat != null && cat > 0 && bol == true) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgTransaccionInsertarClienteOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionCargarDocumentoProductosError);
				throw new Exception(Constantes.msgTransaccionError);
				// agregar logica para eliminar carpeta de la empresa creada
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " cargarArchivoCliente. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/cargarArchivoCliente" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cargarArchivoCliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}

		return response;
	}

	/*
	 * 
	 * Consulta de DNI
	 * 
	 * MAD 17/02/2020
	 * 
	 * Parametros: - nro_documento_empresa
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/consultaDni", method = RequestMethod.POST)
	public ResponseWrapper consultaRuc(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
		ResponseWrapper response = new ResponseWrapper();
		_Clientes client = new _Clientes();
		Object data = new Object();
		try {
			String url_string = "https://ww1.essalud.gob.pe/sisep/postulante/postulante/postulante_obtenerDatosPostulante.htm?strDni=";
			if (cliente.getNro_doc() != null && cliente.getNro_doc() != "") {
				url_string = url_string + cliente.getNro_doc();
			} else {
				return null;
			}
			URL url = new URL(url_string);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json;chartset=utf-8");
			connection.connect();
			BufferedReader br = null;
			if (200 <= connection.getResponseCode() && connection.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				data = new Gson().fromJson(sb.toString(), Object.class);
				System.out.print(sb.toString());
				response.setDefaultObj(data);
			} else {
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				System.err.print(sb.toString());
			}

			if (response != null && response.getDefaultObj() != null) {
				response.setDefaultObj(data);
				response.setEstado(Constantes.valTransaccionOk);
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgErrorConsultaDni);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " consultaDni. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/consultaDni",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return response;

	}

	/*
	 * Retorna ubigeo segun su codigo
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public ResponseWrapper listar(@RequestBody @Validated _Clientes cliente, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		Ubigeo ubigeo = new Ubigeo();
		ResponseWrapper obj = new ResponseWrapper();

		try {
			ubigeo = clienteService.listar(cliente);
			if (ubigeo != null) {
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setDefaultObj(ubigeo);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setDefaultObj(ubigeo);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return obj;

	}

	/*
	 * Lista datos de un cliente por su Id
	 * 
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornaClientePorIdcliente", method = RequestMethod.POST)
	public ResponseWrapper retornaClientePorIdcliente(@RequestBody @Validated _Clientes cliente,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		_Clientes clien = new _Clientes();
		ResponseWrapper obj = new ResponseWrapper();
		try {
			clien = clienteService.retornaClientePorIdcliente(cliente);
			if (clien != null) {
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setDefaultObj(clien);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setDefaultObj(clien);
			}

		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " retornaClientePorIdcliente. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornaClientePorIdcliente",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cliente);
		}
		return obj;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/consultarsunatpadron/{ruc}")
	// @RequestMapping(value = "/consultarsunatpadron", method = RequestMethod.POST)
//	public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestParam("ruc") String ruc) throws IOException {
	// public BaseResponseWrapper retornaDatosSunatDesdePadron(@RequestBody
	// @Validated _Clientes cliente, HttpServletRequest request) throws IOException
	// {
	public ResponseWrapper retornaSeriexTipoDocLocal(@PathVariable("ruc") String ruc, HttpServletRequest request)
			throws Exception {
		// String ruc = cliente.getNro_doc();
		ResponseWrapper baseResponseWrapper = new ResponseWrapper();

		EmpresaSunat empsun = new EmpresaSunat();
		BufferedReader br = null;
		String rutabase = "";
		List<_Parametros> lsPar = new ArrayList<>();
		String separador = System.getProperty("file.separator");
		_Parametros parametro = new _Parametros();
		parametro.setGrupo(Constantes.codigoGrupoIniParametros);
		parametro.setId_empresa(Constantes.parametroIdEmpresaGlobal);
		lsPar = parametrosService.retornaObjParametrosPorGrupoe(parametro);

		Map<String, Object> dataretorna = new HashMap<>();

		for (int i = 0; i < lsPar.size(); i++) {
			if (lsPar.get(i).getCodigo().equals(Constantes.codigoParametroRutaBaseSunatPadron)) {
				// String[] dir=lsPar.get(i).getValor().split("\\|");
				// rutabase = dir[0] +separador+ dir[1] +separador+ dir[2];
				rutabase = lsPar.get(i).getValor();

			}
		}

		String ruta = rutabase;
		if (ruta != null) {
			try {

				br = new BufferedReader(new FileReader(ruta));
				String line = "";

				if (true) {
					dataretorna.put("cliente", (Object) empsun);
					baseResponseWrapper.setDefaultObj(empsun);
					baseResponseWrapper.setOk(true);
					baseResponseWrapper.setMsg("Consulta correcta");
					return baseResponseWrapper;
				}

				try {
					while ((line = br.readLine()) != null) {
						if (line.contains(ruc)) {
							String[] data = line.split("\\|");
							empsun = new EmpresaSunat();
							empsun.setRuc(data[0]);
							empsun.setNombreorazonsocial(data[1]);
							empsun.setEstadodelcontribuyente(data[2]);
							empsun.setcondiciondedomicilio(data[3]);
							empsun.setTipodevia(data[5]);
							empsun.setNombredevia(data[6]);
							empsun.setCodigodezona(data[6]);
							empsun.setTipodezona(data[8]);
							empsun.setNumero(data[9]);
							empsun.setInterior(data[10]);
							empsun.setLote(data[11]);
							empsun.setDepartamento(data[12]);
							empsun.setManzana(data[13]);
							empsun.setKilometro(data[14]);
							empsun.setUbigeo(data[4]);
							/*
							 * codigoUbigeo=data[4]; if(codigoUbigeo!=null) {
							 * ubigeo=ubigeoService.retornaObjUbigeoxCodigo(codigoUbigeo); if(ubigeo!=null)
							 * { empsun.setDepartamento(ubigeo.getDepartamento());
							 * empsun.setDistrito(ubigeo.getDistrito());
							 * empsun.setProvincia(ubigeo.getProvincia()); } }
							 */
							dataretorna.put("cliente", (Object) empsun);
							// baseResponseWrapper.setData(dataretorna);
							baseResponseWrapper.setOk(true);
							// baseResponseWrapper.setMessage("Consulta correcta");
							break;
						}
					}

				} catch (FileNotFoundException ex) {
					System.out.println(ex.getMessage());
					baseResponseWrapper.setOk(false);
					// baseResponseWrapper.setMessage("Error en la Consulta");
					return baseResponseWrapper;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				baseResponseWrapper.setOk(false);
				// baseResponseWrapper.setMessage("Error en la Consulta");
				e.printStackTrace();
			}
		} else {
			baseResponseWrapper.setOk(false);
			// baseResponseWrapper.setMessage("Parametro de ubicación de padron no existe");
			return baseResponseWrapper;
		}

		return baseResponseWrapper;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/loginCliente", method = RequestMethod.POST)
	public ResponseWrapper loginCliente(@RequestBody @Validated _Clientes cl, HttpServletRequest request)
			throws Exception {
		ResponseWrapper defaukt = new ResponseWrapper();
		try {
			cl = clienteService.retornaClientePorRucContrasena(cl);
			if (cl != null) {
				defaukt.setMsg("Credenciales correctas.");
				defaukt.setDefaultObj(cl);
				defaukt.setEstado(1);
			} else {
				defaukt.setMsg("Credenciales invalidos , revise su numero de documento o contraseña.");
				defaukt.setEstado(0);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/loginEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cl);
		}

		return defaukt;

	}

}