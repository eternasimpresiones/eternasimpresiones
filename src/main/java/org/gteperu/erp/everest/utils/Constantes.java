 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.utils;

/**
 *
 * @author jhon
 */
public class Constantes {

    public static final Integer valTransaccionOk = 1;
    public static final Integer valTransaccionError = 2;
    public static final Integer valTransaccionNoEncontro = 0;
    public static final Integer valTransaccionErrornull = 4;
    public static final Integer valTransaccionSinPermiso = 3;
    public static final Integer valParametrosNoenviados = 5;
    public static final String msgTransaccionOk = "Transacción ejecutada correctamente";
    public static final String msgParametroIGV = "El IGV no se encuentra estableció en el sistema, por favor registre su valor en el módulo de configuración, opción parámetros.";
    public static final String msgTransaccionError = "Ocurrio un error durante la transacción";
    public static final String msgTransaccionErrorForanea = "El registro no puede ser eliminado.";
    public static final String msgTransaccionNoEncontrada = "No se encontro el registro buscado";
    
    public static final String msgValorIGVNoEncontro = "No existe un valor para IGV";
    public static final String msgValorISCNoEncontro = "No existe un valor para ISC";   

    public static final String msgInsertDocumentoIngresoOk = "El documento fue registrado correctamente";
    public static final String msgInsertDocumentoIngresoError = "Error al registrar documento";
    public static final String msgReprocesarIngresoError = "Error al reprocesar ingresos";
    public static final String msgReprocesarIngresoOK = "Ingreso reprocesado correctamente";
    public static final String msgTransaccionRegistroExistente = "Registro existente";
    public static final String msgParametrosNoenviados = "No se enviaron los parametros esperados";
    public static final String msgTransaccionFiltroNoEncontrada = "No existen registros para el filtro";
    public static final String msgTransaccionDocumentoNoExiste = "El documento solicitado no existe";
    public static final String msgTransaccionCaptchaError = "Validación captcha no cumple con lo requerido, por favor refresque el catpcha";
    public static final String msgTransaccionErrorNull = "Error inesperado, en la base de datos ó valor nulo.";
    public static final String msgTransaccionErrorObteninedoId = "No fue posible registrar el ";
    public static final String accionDelet = "D";
    public static final String codigoIGV = "CIGV";
    public static final String codigocuentacompraproductos = "CCM";
    public static final String codigocuentacompraservicos = "CSC";
    public static final String codigocuentaventaproductos = "CCM";
    public static final String codigocuentaventaservicos = "CSV";
    public static final String codigoPlanGenericoCuentaFree = "PLNGEN";
    public static final String codigocuentagenericaigv = "IGV";
    public static final Integer estadoDocumentoActivo = 1;
    public static final Integer estaoDocumentoDeBaja = 2;
        
    /*Empresa*/
    public static final String msgTransaccionEmpresaEliminarOk = "La empresa fue eliminada correctamente";
    public static final String msgTransaccionEmpresaEliminarError = "La empresa no fue eliminada correctamente";
    
    public static final String msgTransaccionEmpresaActualizarOk = "La empresa fue actualizada correctamente";
    public static final String msgTransaccionEmpresaActualizarError = "La empresa no fue actualizada correctamente";
    
    public static final String msgTransaccionEmpresaInsertarOk = "La empresa fue insertada correctamente";
    public static final String msgTransaccionEmpresaInsertarError = "La empresa no fue insertada correctamente";
    
    /*Cliente*/
    public static final String msgTransaccionInsertarNroDocRepetido = "El numero del documento ingresado ya está registrado";
    public static final String msgTransaccionInsertarClienteOk = "El cliente fue insertado correctamente";
    public static final String msgTransaccionInsertarClienteError = "El cliente no fue insertado correctamente";
    public static final String msgTransaccionActualizarClienteOk = "El cliente fue actualizado correctamente";
    public static final String msgTransaccionActualizarClienteError = "El cliente no fue actualizado correctamente";
    public static final String msgTransaccionEliminarClienteOk = "El cliente fue eliminado correctamente";
    public static final String msgTransaccionEliminarClienteError = "El cliente no fue eliminado correctamente";
    
    /*Producto*/
    public static final String msgTransaccionInsertarProductoOk = "El Producto fue insertado correctamente";
    public static final String msgTransaccionInsertarProductoError = "El Producto no fue insertado correctamente";
    public static final String msgTransaccionCargarDocumentoProductosError = "El documento no pudo ser cargado correctamente";

    
    public static final String msgTransaccionActualizarProductoOk = "El Producto fue actualizado correctamente";
    public static final String msgTransaccionActualizarProductoError = "El Producto no fue actualizado correctamente";
    
    public static final String msgTransaccionEliminarProductoOk = "El Producto fue eliminado correctamente";
    public static final String msgTransaccionEliminarProductoError = "El Producto no fue eliminado correctamente";
    
    /*Documentos*/
    public static final String msgTransaccionInsertarDocumentoOk = "El documento fue insertado correctamente";
    public static final String msgTransaccionInsertarDocumentoError = "El documento no fue insertado correctamente";
    
    public static final String msgTransaccionActualizarDocumentoOk = "El documento fue actualizado correctamente";
    public static final String msgTransaccionActualizarDocumentoError = "El documento no fue actualizado correctamente";
    
    public static final String msgTransaccionEliminarDocumentoOk = "El documento fue eliminado correctamente";
    public static final String msgTransaccionEliminarDocumentoError = "El documento no fue eliminado correctamente";
    public static final String msgTransaccionDocumentoDeBajaError = "El documento no fue dado de baja correctamente";
    
    public static final String msgTransaccionCantidadDocumentoOk = "La cantidad de Docuemento correctamente";
    public static final String msgTransaccionCantidadDocumentoError = "La cantidad de Docuemento incorrectamente";
    
    public static final String msgTransaccionCantidadRetencionPercepcionOk = "La cantidad de Rete - Per correctamente";
    public static final String msgTransaccionCantidadRetencionPercepcionError = "La cantidad de Rete - Per incorrectamente";


    
    
    /*Documentos_Detalle*/
    public static final String msgTransaccionInsertarDocumentoDetalleOk = "El detalle del documento fue insertado correctamente";
    public static final String msgTransaccionInsertarDocumentoDetalleError = "El detalle del documento no fue insertado correctamente";
    
    public static final String msgTransaccionActualizarDocumentoDetalleOk = "El detalle del documento fue actualizado correctamente";
    public static final String msgTransaccionActualizarDocumentoDetalleError = "El detalle del documento no fue actualizado correctamente";
    
    public static final String msgTransaccionEliminarDocumentoDetalleOk = "El detalle del documento fue eliminado correctamente";
    public static final String msgTransaccionEliminarDocumentoDetalleError = "El detalle del documento no fue eliminado correctamente";
    
    /*Usuario*/
    public static final String msgTransaccionUsuarioEliminarOK = "El usuario fue eliminado correctamente";
    public static final String msgTransaccionUsuarioEliminarError = "El usuario no fue eliminado correctamente";
    
    
    public static final String msgTransaccionUsuarioActualizarOK = "El usuario fue actualizado correctamente";
    public static final String msgTransaccionUsuarioActualizarError = "El usuario no fue actualizado correctamente";
    
    public static final String msgTransaccionUsuarioInsertarOk ="El usuario fue registrado correctamente";
    public static final String msgTransaccionUsuarioInsertarError = "El usuario no fue insertado correctamente";
    public static final String msgTransaccionUsuarioUsernameRepeat = "El nombre de usuario ingresado ya existe , intente con otra";
    public static final String msgTransaccionUsuarioEmailRepeat = "El correo electronico ingresado ya esta siendo usado, intente con otro";
    public static final String msgTransaccionUsuarioCantidadMax = "No se puede registrar mas usuarios, se llego al limite de usuarios de la empresa";
    public static final String msgTransaccionUsuarioCambiarPassOk ="La contraseña fue cambiada correctamente";
    public static final String msgTransaccionUsuarioCambiarPassError = "Hubo un error al cambiar la contraseña";
    //Documentos
    public static final Integer DocCostoAsociado=2;
    public static final Integer DocCompraMercederia=1;
    
    /*Saldo Historico*/
    public static final Integer estPrimerHistorico=2;
    public static final Integer estSaldoMensual=1;
    
    /*Guia ingreso*/
    public static final String msgTransaccionGuiaIngresoOk ="Guía insertada correctamente";
    public static final String msgTransaccionGuiaIngresoError ="Error al insertar la guía";
    public static final Integer EstadoProvision=1;
    public static final String msgProvisionOk="Guia provisionada correctamente";
    public static final String msgProvisionError="Error al provisionar la guia";
    public static final Integer idCompras=1;
    
    /*Guia egreso*/
    public static final String msgInsertGuiaEgresoOk = "La guia de salida fue registrada correctamente";
    public static final String msgInsertGuiaEgresoError = "Error al registrar guia de salida";
    
    /*Parametros*/
    public static final String grupoGlobales = "PARMGLOBAL";
    public static final String grupoEmpresa = "PARMEMPRESA";
    public static final String codigoEmpresa = "EMPRESA";
    public static final String codigoGlobal = "GLOBAL";
    public static final String msgGrupoParametros = "El parametro ha sido guardado correctamente";
    public static final String msgGrupoParametrosErr = "El parametro no ha sido guardado correctamente";
    
    /*Reportes*/
    //public static final String  grupoReportes="REPORTES";
    //public static final String  codigoRaizReportes="RUTRZREPO";
    public static final String  grupoReportes="INI";
    public static final String  codigoRaizReportes="DIRBASREP";
    
    /*Excel*/
    public static final String grupoExcel="EXCEL";
    public static final String codigoRaizExcel="RUTRZEXCEL";
    
    /*Producto*/
    public static final String msgTransaccionListarError = "Error al Listar producto";
    
    
    /*Guia*/
    public static final String msgTransaccionGuiaError = "Error al Listar Guias";
    public static final String msgTransaccionGuiaVacia = "No existen guias pendientes";
    public static final String msgGuiaEliminada="Guia eliminada correctamente";

    public static final String INGR = "TOPEINGR";
    public static final String EGRE = "TOPEEGRE";
    public static final String msgAgrupProductoError = "Ocurrio un error al agrupar los productos de las guias";
    public static final Integer IdTipoMovimientoSalida=2;
    public static final Integer IdTipoMovimientoIngreso=1;
    
    /*Ingreso*/
    public static final String msgTransaccionIngresoSinValorizarError = "Error al Listar Ingresos sin valorizar";
    public static final String msgTransaccionIngresoSinValorizarVacio = "No existen Ingresos sin valorizar pendientes";
    public static final Integer CodNotaCredito=7;
    public static final Integer estadoNotaCredito=3;
     public static final String msgIngresoValorizado = "El ingreso fue valorizado correctamente";
    public static final String msgIngresoNoValorizado = "El ingreso aun no fue valorizado ";

    
     public static final Integer valEstadoAsignadoParcial=2;
 //    
//    public static final String rutaFiles = "C:/Files/Migracion/";
//    public static final String rutaFilesPlantilas = "C:/Files/Plantillas/";
    
//	 public static final String rutaFiles ="/opt/files/migracion/";
//	 public static final String rutaFilesPlantilas ="/opt/files/Plantillas/";
//    //codigos de la tabla pareamtros
    
    public static final String codigoempleadocuentaadministrador = "EMPADM";
     public static final String codigoGrupoPlantillasEmailsParametros = "TXEM";
    public static final String codigoParametroRutaCargaAutomatica = "PARAUT";
    public static final String codigoParametroRutaGenericaReportes= "DIRGEN";
    public static final String codigoParametroEmailGenerico= "EMADE";
    public static final String codigoParametroEmailContacto= "EMACN";
    public static final String codigoParametroEmailLogistica= "EMEMP";
    public static final String codigoParametroEmailPresupuesto= "EMEMPR";
    public static final String codigoParametroEmailCompras= "EMOCO";
    public static final String codigoParametroEmailVentas= "EMCOT";
    public static final String codigoParametroRutaPorEmpresaReportes = "DIREMP";
    public static final String codigoParametroRutaLogoGenerico = "DIRLOG";
    public static final String codigoParametroRutaFiles = "DIRFILES";
    public static final String codigoParametroRutaServer = "URLSER";
    public static final String codigoParametroRutaServer2 = "URLSER2";
    public static final String codigoParametroRutaServer3 = "URLSER3";
 

    public static final String msgBienvenida = "MSGACT";
    
    public static final String GrpCuentas = "CUENTAS";
    public static final String CodIGVGlob = "GLOBAL-1";
    public static final String CodISCGlob = "GLOBAL-2";
    
    public static final Integer cteIdTipoMovimientoIngreso = 1;

    
    //Periodo
    
    public static final String msgTransaccionPeriodoActualizarOK ="El periodo fue actualizado correctamente";
    public static final String msgTransaccionPeriodoActualizarError = "El periodo no fue actualizado correctamente";

    
    
    public static final String  CuentasFlete = "CTACOMPRSFLT";
    public static final String  CuentasSeguro = "CTACOMPRSSEG";
    public static final String  CuentasManipuleo = "CTACOMPRSMAN";
    public static final String  CuentasAlmacenaje = "CTACOMPRSALMCNJ";
    public static final String  CuentasGastosAduaneros = "CTACOMPRSGSADUA";
    
    public static final Integer CodigoTipoOperacionGuiaCompra = 1;
    public static final Integer CodigoTipoOperacionGuiaDevolucion = 2;
    public static final Integer CodigoTipoOperacionGuiaIngresoTransferancia = 3;
    public static final Integer CodigoTipoOperacionGuiaIngresoTransformacion = 4;
    public static final Integer CodigoTipoOperacionGuiaIngresoDonacion = 5;
    public static final Integer CodigoTipoOperacionGuiaIngresoOtros = 6;

    public static final Integer CodigoTipoOperacionGuiaEgresoVentas = 7;
    public static final Integer CodigoTipoOperacionGuiaEgresoDevoliucion = 8;
    public static final Integer CodigoTipoOperacionGuiaEgresoTransformacion = 9;
    public static final Integer CodigoTipoOperacionGuiaEgresoTransferencia = 10;
    public static final Integer CodigoTipoOperacionGuiaEgresoConsumoInterno =11;
    public static final Integer CodigoTipoOperacionGuiaEgresoObsolescencia =12;
    public static final Integer CodigoTipoOperacionGuiaEgresoOtros =13;
  //Tipo de cambio Sunat
    public static final String msgTipoDeCambioSunatError="Error no hay conexion con la SUNAT";

    
    public static final String idPerfilAlmacen="295";
    public static final String idPerfilFinanzas="294";
    public static final String idPerfilEficaz="296";
    public static final String idPerfilAdministradorEficaz="293";
    public static final String idPerfilAdministradorPartner="2";

    
    
    
// CONSTANTES FACTURACION ELECTRONICA
     public static final String tipoDocFactura="01";
    public static final String tipoDocBoleta="03";
    public static final String tipoDocNotaCredito="07";
    public static final String tipoDocNotaDebito="08";
    public static final String tipoDocGuiaRemisionRemitente="09";
    public static final String tipoDocGuiaRemisionTransportista="31";
    public static final String tipoDocPercepcion="40";
    public static final String tipoDocRetencion="20";

    public static final String comunicaciónBajaSunat="RA";
    public static final String resumenDiarioSunat="RC";

    public static final String codigoGrupoIniParametros = "INI";
    public static final String codigoGrupoEMPParametros = "EMP";

    public static final String codigoRutaReportes = "DIRBASREP";
    
    public static final String codigoRutaExcelBase = "DIREXCEBAS";
    public static final double porcentajeIGV = 18.00;

    public static final String codigoParametroRutaJasper = "JASPER";
    public static final String codigoParametroRutaExcel = "EXC";

    public static final String codigoParametroRutaMatriz = "DIRMATRIZ";

    public static final String codigoParametroRutaBase = "DIRBASE";
    public static final String codigoParametroRutaBaseSunatPadron = "DIRBASSUPA";

    
    public static final String codigoParametroPorcentajeRetencion = "POR-RET";

    public static final String codigoParametroEmailFact = "CORREOFACT";

    public static final String msgTransaccionDescargaError = "Error, el archivo no se pudo encontrar";
    public static final String codigoComprobantePagoElectronico ="CPE";
    public static final String codigoConstanciaRecepcion ="CDR";
    
    //ESTADO DOCUMENTO
    public static final String estadoSinEnvio ="0";
    public static final String estadoDeclaradoAceptado ="1";
    public static final String estadoRechazadoParcial ="2";
    public static final String estadoRechazadoTotal ="3";
    public static final String estadoSinRespuesta ="4";
    public static final String estadoDocBorrador ="5";
    public static final String estadoDocAnulado ="6";

    //FORMA DE PAGO
    public static final String codPagoContado ="CONT";
    public static final String codPagoCredito ="CRED";
    
    //TIPO OPERACION SUNAT
    public static final String codigoDeclaracionFactura ="D-FAC";
    public static final String codigoAnulacionFactura ="A-FAC";
    public static final String codigoDeclaracionBoleta ="D-BOL";
    public static final String codigoAnulacionBoleta ="A-BOL";
    public static final String codigoDeclaracionNotaCredito ="D-NCR";
    public static final String codigoAnulacionNotaCredito ="A-NCR";
    public static final String codigoDeclaracionNotaDebito ="D-NDE";
    public static final String codigoAnulacionNotaDebito ="A-NDE";
    public static final String codigoDeclaracionGuiaRemisionRemitente ="D-GUIA-REMI";
    public static final String codigoAnulacionGuiaRemisionRemitente ="A-GUIA-REMI";
    public static final String codigoDeclaracionGuiaRemisionTransportista ="D-GUIA-TRANSP";
    public static final String codigoAnulacionGuiaRemisionTransportista ="A-GUIA-TRANSP";
    
    
    public static final String msgNombreEmpresa = "Partner Tech";
    public static final String msgNombreApp = "Facturación electrónica";
    
    public static final Integer parametroIdEmpresaGlobal = 0;
    
    public static final String msgErrorSunatPadron ="Error, no existen registros en la tabla Sunat_padron";
    public static final String msgErrorSunatPadronParametros ="Error, no se encontraron las rutas en parametros";
    public static final String nombreTablaSunatPadronA ="sunat_padron_a";
    public static final String nombreTablaSunatPadronB ="sunat_padron_b";
    
    public static final String codigoRutaSunatPadron="RUTAPADRON";
    public static final String codigoURLDescargaPadron="SUNATPADRON";
    public static final String codigoGrupoSunatPadron="PADRON";
    public static final String nombreSunatPadronZip="sunat_padron.zip";
    
    public static final String msgConsultaRucEstadoBaja="El RUC ha sido dado de baja";
    public static final String msgConsultaRUCNoEncontrada = "El ruc no existe";
    
    public static final String msgErrorConsultaDni = "Error al consultar nro de documento";
    public static final String msgTransaccionErrorInsertar = "La transaccion no se inserto correctamente";
    public static final String msgTransaccionErrorActualizar = "La transaccion no se actualizo correctamente";
    public static final String msgTransaccionErrorEliminar = "La transaccion no se elimino correctamente";
    
    public static final String rutaEncryptPass ="C:\\Users\\PT0022\\Desktop\\rutaencode.txt";
    
    //RUTAS JASPER
    public static final String rutaFactura = "factura_electronica.jasper";
    public static final String rutaFacturaII = "factura_electronicaii.jasper";
    public static final String rutaFacturaIII = "factura_electronicaiii.jasper";
    public static final String rutaSubDetalle ="detalle-factura.jasper";
    public static final String rutaSubDetalleII ="detalle-facturaii.jasper";
    public static final String rutaSubDetalleIII ="detalle-facturaiii.jasper";

    public static final String rutaSubCuentasBancos ="cuentas_banco.jasper";
    public static final String rutaSubCuentasBancosII ="cuentas_bancoii.jasper";
    public static final String rutaSubCuentasBancosIII ="cuentas_bancoiii.jasper";
    public static final String rutaSubBancoFactura ="banco_factura.jasper";
    public static final String rutaSubTotalBancos ="total_banco.jasper";
    public static final String rutaAnulada = "anulada_factura_electronica.jasper";
    public static final String rutaRemision = "remision_electronica.jasper";
    public static final String rutaRemisionII = "remision_electronicaii.jasper";
    public static final String rutaRemisionIII = "remision_electronicaiii.jasper";

    public static final String rutaNCND = "NDNC.jasper";

    public static final String rutaArchivoFacturacion = "Archivos_Facturacion";
    public static final String nombreExcelBaseProducto = "EXCEL_BASE_PRODUCTO.xlsx";
    public static final String nombreExcelBaseCliente = "EXCEL_BASE_CLIENTE.xlsx";
     
    //PLANTILLA DOCUMENTO 
    public static final String msgTransaccionInsertarPlantillaDocumentoOk = "Pantilla documento insertada correctamente";
    public static final String msgTransaccionInsertarPlantillaDocumentoError = "Plantilla documento no fue insertada";
    public static final String msgTransaccionActualizarPlantillaDocumentoOk = "Pantilla documento actualizada correctamente";
    public static final String msgTransaccionActualizarPlantillaDocumentoError = "Plantilla documento no fue actualizada";
    public static final String msgTransaccionEliminarPlantillaDocumentoOk = "Plantilla documento eliminada correctamente";
    public static final String msgTransaccionEliminarPlantillaDocumentoError = "Plantilla documento no fue eliminada ";
    public static final String msgTransaccionListarPlantillaDocumentoOk = "Plantilla documentos listada correctamente";
    public static final String msgTransaccionFiltroPlantillaNoEncontrada = "No existen plantillas para ese filtro";

    public static final String padronreduidoruc = "padron_reducido_ruc.txt";
    public static final String codigoParametroRutaPadronSunat = "PADSUN";
    public static final String nombreExcelAyudaDocumentoFac = "estructura_fv_bv_nd_nd.xlsx";
    public static final String plantillaExcelDocumentoFac = "plantilla_de_documentos.xlsx";
    public static final String reporteExcelDocumentoFac = "reporteVentas.xlsx";
    public static final String reporteExcelDocumentoFacOutput = "reporteVentasOutput.xlsx";

    //COD TIPO OPERACION(CATALOGO 07)
    public static final String Codigogravadooperaciónonerosa = "10";
    public static final String Codigogravadooperaciónporpremio = "11";
    public static final String Codigogravadoretiropordonacion = "12";
    public static final String Codigogravadoretiro = "13";
    public static final String Codigogravadoretiroporpublicidad = "14";
    public static final String Codigogravadobonificaciones = "15";

    public static final String Codigogravadoretiroporentregaatrabajadores = "16";
    public static final String CodigogravadoIVAP = "17";
    public static final String Codigoexoneradooperaciónonerosa = "20";
    public static final String Codigoexoneradotransferenciagratuita = "21";
    public static final String Codigoinafectooperaciónonerosa ="30";
    public static final String Codigoinafectoretiroporbonificacion ="31";
    public static final String Codigoinafectoretiro ="32";
    
    public static final String Codigoinafectoretiropormuestrasmedicas = "33";
    public static final String Codigoinafectoretiroporconveniocolectivo = "34";
    public static final String CodigoinafectoRetiroporpremio ="35";
    public static final String Codigoinafectoretiroporpublicidad ="36";
    public static final String Codigoexportacion ="40";

    public static final String src = "src";
    public static final String main = "main";
    public static final String resources = "resources";

    public static final String rucPSE = "20523799623";
    
   public static final String codWSCPE_PD = "WSCPE_PD";
   public static final String codWSCPE_BE = "WSCPE_BE";
   public static final String codWSCPE_HO = "WSCPE_HO";
   public static final String codWSRTPC_PD = "WSRTPC_PD";
   public static final String codWSRTPC_BE = "WSRTPC_BE";
   public static final String codWSCPE_CONFAC = "WSCPE_CONFAC";
   public static final String codWSCPE_VED = "WSCPE_VED";
   public static final String codWSGUIA_PD = "rutaWSGUIA_PD";
   public static final String codWSGUIA_BE = "rutaWSGUIA_BE";


   public static final Integer CodUbigeoGenerico = 1249;
   
   
   public static final Integer codFacturacionPse =1;
   public static final Integer codFacturacionPropia =0;
   public static final String carpetaPSE = "PSE";
   public static final String codParametroAccesosPSE = "ACCESOPSE";

   public static final String msgSerieRepetida = "Ya existe una serie registrada para ese documento";
   public static final String msgSerieNoEncontrada = "No existe una serie registrada para este tipo de documento";

   public static final String codParametroLimiteEnvio = "DIALIMITENVIO";
   public static final String msgFechaValida = "Fecha valida";
 
   public static final Integer idPerfilAccesoApi =4;
   public static final Integer idPerfilAdminCliente =2;

}
