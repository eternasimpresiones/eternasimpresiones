
package org.gteperu.erp.everest.controller;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.gteperu.erp.everest.domain._Producto;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.service._CompanyService;
import org.gteperu.erp.everest.service._ProductoService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/productos")
public class _ProductoRestController {
	@Resource(name = "productoService")
	_ProductoService productoService;

	@Resource(name = "companyService")
	_CompanyService companyService;

	@Resource(name = "parametrosService")
	ParametrosService parametrosService;

	/*
	 * registra un producto nuevo dentro de la empresa
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertaProducto", method = RequestMethod.POST)
	public ResponseWrapper insertaProducto(@RequestBody @Validated _Producto producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = productoService.insertarProducto(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Producto insertado correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertaProd. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/insertaProd" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}

	/*
	 * actualiza los datos del producto seleccionado
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/actualizarProducto", method = RequestMethod.POST)
	public ResponseWrapper actualizarProducto(@RequestBody @Validated _Producto producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;

			cat = productoService.actualizarProducto(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Producto actualizado correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarProducto. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/actualizarProducto" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizarProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}

	/*
	 * Elimina datos del proucto de la empresa
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/eliminarProducto", method = RequestMethod.POST)
	public ResponseWrapper eliminarProducto(@RequestBody @Validated _Producto producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;

			cat = productoService.eliminarProducto(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Producto eliminado correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarProducto. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminarProducto" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}
	/*
	 * Lista Productos segun la empresa
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornarProducto", method = RequestMethod.POST)
	public ResponseWrapper retornarProducto(@RequestBody @Validated _Producto producto, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = productoService.listarProducto(producto);
			if (objtList != null && objtList.size() > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(objtList);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(objtList);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornarProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return obj;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertarProducto", method = RequestMethod.POST)
	public ResponseWrapper insertarProducto(@RequestBody @Validated _Producto producto, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			Integer cat = 0;
			cat = productoService.insertarProducto(producto);

			if (objtList != null && objtList.size() > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(objtList);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(objtList);
			}
		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertarProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return obj;

	}

	/*
	 * Lista productos segun la empresa seleccionad y el estado
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornarProductoPorEstado", method = RequestMethod.POST)
	public ResponseWrapper retornarProductoPorEstado(@RequestBody @Validated _Producto producto,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = productoService.listarProductoPorEstado(producto);
			if (objtList != null && objtList.size() > 0) {
				obj.setMsg(Constantes.msgTransaccionOk);
				obj.setEstado(Constantes.valTransaccionOk);
				obj.setAaData(objtList);
			} else {
				obj.setMsg(Constantes.msgTransaccionFiltroNoEncontrada);
				obj.setEstado(Constantes.valTransaccionNoEncontro);
				obj.setAaData(objtList);
			}

		} catch (Exception e) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornarProductoPorEstado",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return obj;

	}

	/*
	 * Anexa archivos al producto seleccionado
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/cargarArchivoProducto", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody Object handleFileUpload3(@RequestPart("producto") _Producto producto,
			@RequestPart("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		MultipartFile file = null;
		Integer empresa_actualizada = 0;
		ResponseWrapper response = new ResponseWrapper();
		_Producto prod = new _Producto();
		Boolean bol = false;
		Boolean flgNull = false;
		_Parametros parametro = new _Parametros();
		List<_Parametros> lsPar = new ArrayList<>();
		String rutamatriz = "", rutaexcel = "";
		Integer cat = 0;

		try {
			if (files.length != 0) {
				file = files[0];
				file.getSize();
				byte[] bytes = file.getBytes();

				String directorio = System.getProperty("user.dir");
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
				emp.setId_empresa(producto.getId_empresa());
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
						if (A1 == null && B1 == null && C1 == null && D1 == null && E1 == null && F1 == null && G1 == null) {
							continue;
						}

						prod.setId_empresa(producto.getId_empresa());
						/*****/
						if (A1 == null || A1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda A" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							return response;
						} else if (A1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							prod.setCodigo_producto_interno(String.valueOf((int) A1.getNumericCellValue()));
						} else {
							prod.setCodigo_producto_interno(A1.getStringCellValue());
						}
						/*****/
						if (B1 == null || B1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda B" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							return response;
						} else if (B1.getCellType() == Cell.CELL_TYPE_STRING) {
							prod.setNombre_producto(B1.getStringCellValue());
						} else {
							prod.setNombre_producto(String.valueOf((int) B1.getNumericCellValue()));
						}
						/*****/
						if (C1 == null || C1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda C" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							return response;
						} else if (C1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							prod.setCodigo_sunat(String.valueOf((int) C1.getNumericCellValue()));
						} else {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("El campo codigo sunat solo debe contener caracteres numéricos, corregir celda C" + (i + 1));
							return response;
						}
						/*****/
						if (D1 == null || D1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("Por favor complete los datos de la celda D" + (i + 1)
									+ " del documento Excel y vuelva a cargarlo al sistema");
							return response;
						} else if (D1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							prod.setIgv_afecto(String.valueOf((int) D1.getNumericCellValue()));
						} else {
							response.setEstado(Constantes.valTransaccionErrornull);
							response.setMsg("El campo Igv afecto solo debe contener caracteres numéricos, corregir celda D" + (i + 1));
							return response;
						}
						/*****/
						if (E1 == null || E1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							prod.setDescripcion(String.valueOf((int) E1.getNumericCellValue()));
						} else if (E1.getCellType() == Cell.CELL_TYPE_STRING) {
							prod.setDescripcion(E1.getStringCellValue());
						} else {
							prod.setDescripcion(String.valueOf((int) E1.getNumericCellValue()));
						}
						/*****/
						if (F1 == null || F1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							prod.setValor_precio_venta(0.0);
						} else if (F1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							prod.setValor_precio_venta(F1.getNumericCellValue());
						} else {
							prod.setValor_precio_venta(0.0);
						}
						/*****/
						if (G1 == null || G1.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
							prod.setValor_precio_compra(0.0);
						} else if (G1.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							prod.setValor_precio_compra(G1.getNumericCellValue());
						} else {
							prod.setValor_precio_compra(0.0);
						}

						prod.setEstado(1);
						cat = productoService.updateProductoWithCodigoInterno(prod);
						if (cat == 0) {
							cat = productoService.insertarProducto(prod);
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
				// agregar logica para eliminar carpeta de la empresa creada
			}
		} catch (Exception e) {
			response.setMsg(Constantes.msgTransaccionCargarDocumentoProductosError);
			System.out.println(this.getClass().getSimpleName() + " cargarArchivoProducto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cargarArchivoProducto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}

		return response;
	}

}