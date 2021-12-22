
package org.gteperu.erp.everest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.Bancoempresa;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.BancoempresaService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bancoempresa")
public class BancoempresaRestController {
	@Resource(name = "bancoempresaService")
	BancoempresaService bancoempresaService;

  	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/insertaBancoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper insertaBancoEmpresa(@RequestBody @Validated Bancoempresa producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;
			cat = bancoempresaService.insertaBancoEmpresa(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Cuenta insertada correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " insertaBancoEmpresa. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/insertaBancoEmpresa" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/insertaBancoEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}
	
	 

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/actualizarBancoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper actualizarBancoEmpresa(@RequestBody @Validated Bancoempresa producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;

			cat = bancoempresaService.updateBancoEmpresa(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Producto actualizado correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " updateBancoEmpresa. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/updateBancoEmpresa" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/updateBancoEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/eliminarBancoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper eliminarBancoEmpresa(@RequestBody @Validated Bancoempresa producto, HttpServletRequest request)
			throws Exception {

		ResponseWrapper response = new ResponseWrapper();
		try {
			Integer cat = 0;

			cat = bancoempresaService.eliminaBancoEmpresa(producto);

			if (cat != null && cat > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg("Cuenta eliminada correctamente");
			} else {
				response.setEstado(Constantes.valTransaccionErrornull);
				response.setMsg(Constantes.msgTransaccionError);
				throw new Exception(Constantes.msgTransaccionError);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarBancoEmpresa. ERROR : " + e.getMessage());
			System.out.println(this.getClass().getSimpleName() + "/eliminarBancoEmpresa" + "Linea nro : "
					+ e.getStackTrace()[0].getLineNumber());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarBancoEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return response;

	}
	 

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@RequestMapping(value = "/retornarBancoEmpresa", method = RequestMethod.POST)
	public ResponseWrapper retornarBancoEmpresa(@RequestBody @Validated Bancoempresa producto, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		List objtList = new ArrayList();
		ResponseWrapper obj = new ResponseWrapper();
		try {

			objtList = bancoempresaService.retornaBancoEmpresaxEmpresaxEstado(producto);
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
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/retornarBancoEmpresa",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					producto);
		}
		return obj;

	}

	 
}