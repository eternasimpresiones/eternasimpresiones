/*
*ˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆˆ
* Creado por     : Jose Zambrano  (08/12/2016)
* Bean = Cpe_Retencion
*/

package org.gteperu.erp.everest.domain;
import java.util.Date;
import java.util.List;

public class _Cpe_RetencionPercepcionBean {
   private String COD_TIPO_DOCUMENTO;
	private int id_retencion;
	private String FECHA_REGISTRO;
	private int ID_EMPRESA;
	private int ID__DETALLE_RETENCION;

	private int ID_CLIENTE_CPE;
	private String NRO_COMPROBANTE;
	private String FECHA_DOCUMENTO;
	private String TIPO_DOCUMENTO_EMPRESA;
	private String NRO_DOCUMENTO_EMPRESA;
	private String NOMBRE_COMERCIAL_EMPRESA;
	private String DIRECCION_EMPRESA;
	private String COD_UBIGEO_EMPRESA;
	private String ZONA_URBANIZACION_EMPRESA;
	private String DEPARTAMENTO_EMPRESA;
	private String PROVINCIA_EMPRESA;
	private String DISTRITO_EMPRESA;
    private String COD_PAIS_EMPRESA;
	private String RAZON_SOCIAL_EMPRESA;
	////
	private String TIPO_DOCUMENTO_PROVEEDOR;
	private String NRO_DOCUMENTO_PROVEEDOR;
	private String NOMBRE_COMERCIAL_PROVEEDOR;
	private String DIRECCION_PROVEEDOR;
	private String COD_UBIGEO_PROVEEDOR;
	private String ZONA_URBANIZACION_PROVEEDOR;
	private String CIUDAD_PROVEEDOR;
	private String DEPARTAMENTO_PROVEEDOR;
	private String PROVINCIA_PROVEEDOR;
	private String DISTRITO_PROVEEDOR;
	private String PAIS_PROVEEDOR;
	private String RAZON_SOCIAL_PROVEEDOR;
	////
	private String TIPO_DOCUMENTO_CLIENTE;
	private String NRO_DOCUMENTO_CLIENTE;
	private String NOMBRE_COMERCIAL_CLIENTE;
	private String DIRECCION_CLIENTE;
	private String COD_UBIGEO_CLIENTE;
	private String ZONA_URBANIZACION_CLIENTE;
	private String CIUDAD_CLIENTE;
	private String DEPARTAMENTO_CLIENTE;
	private String PROVINCIA_CLIENTE;
	private String DISTRITO_CLIENTE;
	private String PAIS_CLIENTE;
	private String RAZON_SOCIAL_CLIENTE;
	private String TIPO_PERCEPCION;
	private double PORCENTAJE_PERCEPCION;
	private double TOTAL_PERCEPCION;
	private double NETO_PERCEPCION;
	private String MONEDA_TOTAL_PERCIBIDO;
	private String MONEDA_TOTAL_COBRADO;
	////
	private String TIPO_RETENCION;
	private double PORCENTAJE_RETENCION;
	private double TOTAL_RETENCION;
	private double NETO_RETENCION;
	private String NOTA;
	private String MONEDA;
	private String COD_RESPUESTA_SUNAT;
	private String DESCRIPCION_SUNAT;
	private String HASH_CPE;
	private String HASH_CDR;
    private int TIPO_PROCESO;
	private int ESTADO;
	private int TIPO;
  	private String dirDocumentoEmpresaEmisora;
  	private String[] rpta_sunat;
  	private List<_Cpe_RetencionPercepcion_DetalleBean> lsdetalle;

    private String CORREO;
    
	/////////////////////////////////////////////
    private Integer ifacturacionPse;
        


	public List<_Cpe_RetencionPercepcion_DetalleBean> getLsdetalle() {
		return lsdetalle;
	}


	public String getTIPO_DOCUMENTO_CLIENTE() {
		return TIPO_DOCUMENTO_CLIENTE;
	}

	public void setTIPO_DOCUMENTO_CLIENTE(String tIPO_DOCUMENTO_CLIENTE) {
		TIPO_DOCUMENTO_CLIENTE = tIPO_DOCUMENTO_CLIENTE;
	}

	public String getNRO_DOCUMENTO_CLIENTE() {
		return NRO_DOCUMENTO_CLIENTE;
	}

	public void setNRO_DOCUMENTO_CLIENTE(String nRO_DOCUMENTO_CLIENTE) {
		NRO_DOCUMENTO_CLIENTE = nRO_DOCUMENTO_CLIENTE;
	}

	public String getNOMBRE_COMERCIAL_CLIENTE() {
		return NOMBRE_COMERCIAL_CLIENTE;
	}

	public void setNOMBRE_COMERCIAL_CLIENTE(String nOMBRE_COMERCIAL_CLIENTE) {
		NOMBRE_COMERCIAL_CLIENTE = nOMBRE_COMERCIAL_CLIENTE;
	}

	public String getDIRECCION_CLIENTE() {
		return DIRECCION_CLIENTE;
	}

	public void setDIRECCION_CLIENTE(String dIRECCION_CLIENTE) {
		DIRECCION_CLIENTE = dIRECCION_CLIENTE;
	}

	public String getCOD_UBIGEO_CLIENTE() {
		return COD_UBIGEO_CLIENTE;
	}

	public void setCOD_UBIGEO_CLIENTE(String cOD_UBIGEO_CLIENTE) {
		COD_UBIGEO_CLIENTE = cOD_UBIGEO_CLIENTE;
	}

	public String getZONA_URBANIZACION_CLIENTE() {
		return ZONA_URBANIZACION_CLIENTE;
	}

	public void setZONA_URBANIZACION_CLIENTE(String zONA_URBANIZACION_CLIENTE) {
		ZONA_URBANIZACION_CLIENTE = zONA_URBANIZACION_CLIENTE;
	}

	public String getCIUDAD_CLIENTE() {
		return CIUDAD_CLIENTE;
	}

	public void setCIUDAD_CLIENTE(String cIUDAD_CLIENTE) {
		CIUDAD_CLIENTE = cIUDAD_CLIENTE;
	}

	public String getDEPARTAMENTO_CLIENTE() {
		return DEPARTAMENTO_CLIENTE;
	}

	public void setDEPARTAMENTO_CLIENTE(String dEPARTAMENTO_CLIENTE) {
		DEPARTAMENTO_CLIENTE = dEPARTAMENTO_CLIENTE;
	}

	public String getPROVINCIA_CLIENTE() {
		return PROVINCIA_CLIENTE;
	}

	public void setPROVINCIA_CLIENTE(String pROVINCIA_CLIENTE) {
		PROVINCIA_CLIENTE = pROVINCIA_CLIENTE;
	}

	public String getDISTRITO_CLIENTE() {
		return DISTRITO_CLIENTE;
	}

	public void setDISTRITO_CLIENTE(String dISTRITO_CLIENTE) {
		DISTRITO_CLIENTE = dISTRITO_CLIENTE;
	}

	public String getPAIS_CLIENTE() {
		return PAIS_CLIENTE;
	}

	public void setPAIS_CLIENTE(String pAIS_CLIENTE) {
		PAIS_CLIENTE = pAIS_CLIENTE;
	}

	public String getRAZON_SOCIAL_CLIENTE() {
		return RAZON_SOCIAL_CLIENTE;
	}

	public void setRAZON_SOCIAL_CLIENTE(String rAZON_SOCIAL_CLIENTE) {
		RAZON_SOCIAL_CLIENTE = rAZON_SOCIAL_CLIENTE;
	}

	public String getTIPO_PERCEPCION() {
		return TIPO_PERCEPCION;
	}

	public void setTIPO_PERCEPCION(String tIPO_PERCEPCION) {
		TIPO_PERCEPCION = tIPO_PERCEPCION;
	}

	public double getPORCENTAJE_PERCEPCION() {
		return PORCENTAJE_PERCEPCION;
	}

	public void setPORCENTAJE_PERCEPCION(double pORCENTAJE_PERCEPCION) {
		PORCENTAJE_PERCEPCION = pORCENTAJE_PERCEPCION;
	}

	public double getTOTAL_PERCEPCION() {
		return TOTAL_PERCEPCION;
	}

	public void setTOTAL_PERCEPCION(double tOTAL_PERCEPCION) {
		TOTAL_PERCEPCION = tOTAL_PERCEPCION;
	}

	public double getNETO_PERCEPCION() {
		return NETO_PERCEPCION;
	}

	public void setNETO_PERCEPCION(double nETO_PERCEPCION) {
		NETO_PERCEPCION = nETO_PERCEPCION;
	}

	public String getMONEDA_TOTAL_PERCIBIDO() {
		return MONEDA_TOTAL_PERCIBIDO;
	}

	public void setMONEDA_TOTAL_PERCIBIDO(String mONEDA_TOTAL_PERCIBIDO) {
		MONEDA_TOTAL_PERCIBIDO = mONEDA_TOTAL_PERCIBIDO;
	}

	public String getMONEDA_TOTAL_COBRADO() {
		return MONEDA_TOTAL_COBRADO;
	}

	public void setMONEDA_TOTAL_COBRADO(String mONEDA_TOTAL_COBRADO) {
		MONEDA_TOTAL_COBRADO = mONEDA_TOTAL_COBRADO;
	}

	public void setLsdetalle(List<_Cpe_RetencionPercepcion_DetalleBean> lsdetalle) {
		this.lsdetalle = lsdetalle;
	}

	public String[] getRpta_sunat() {
		return rpta_sunat;
	}

	public void setRpta_sunat(String[] rpta_sunat) {
		this.rpta_sunat = rpta_sunat;
	}

	public String getDirDocumentoEmpresaEmisora() {
		return dirDocumentoEmpresaEmisora;
	}

	public void setDirDocumentoEmpresaEmisora(String dirDocumentoEmpresaEmisora) {
		this.dirDocumentoEmpresaEmisora = dirDocumentoEmpresaEmisora;
	}


        public int getId_retencion() {
		return id_retencion;
	}

	public void setId_retencion(int id_retencion) {
		this.id_retencion = id_retencion;
	}

	public int getID__DETALLE_RETENCION() {
		return ID__DETALLE_RETENCION;
	}

	public void setID__DETALLE_RETENCION(int iD__DETALLE_RETENCION) {
		ID__DETALLE_RETENCION = iD__DETALLE_RETENCION;
	}

	/////////////////////////////////////////////
	public String getCOD_TIPO_DOCUMENTO() {
		return COD_TIPO_DOCUMENTO;
	}
	public void setCOD_TIPO_DOCUMENTO(String COD_TIPO_DOCUMENTO) {
		this.COD_TIPO_DOCUMENTO = COD_TIPO_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getFECHA_REGISTRO() {
		return FECHA_REGISTRO;
	}
	public void setFECHA_REGISTRO(String  FECHA_REGISTRO) {
		this.FECHA_REGISTRO = FECHA_REGISTRO;
	}
	/////////////////////////////////////////////
	public int getID_EMPRESA() {
		return ID_EMPRESA;
	}
	public void setID_EMPRESA(int  ID_EMPRESA) {
		this.ID_EMPRESA = ID_EMPRESA;
	}
	/////////////////////////////////////////////
	public int getID_CLIENTE_CPE() {
		return ID_CLIENTE_CPE;
	}
	public void setID_CLIENTE_CPE(int  ID_CLIENTE_CPE) {
		this.ID_CLIENTE_CPE = ID_CLIENTE_CPE;
	}
	/////////////////////////////////////////////
	public String getNRO_COMPROBANTE() {
		return NRO_COMPROBANTE;
	}
	public void setNRO_COMPROBANTE(String  NRO_COMPROBANTE) {
		this.NRO_COMPROBANTE = NRO_COMPROBANTE;
	}
	/////////////////////////////////////////////
	public String getFECHA_DOCUMENTO() {
		return FECHA_DOCUMENTO;
	}
	public void setFECHA_DOCUMENTO(String  FECHA_DOCUMENTO) {
		this.FECHA_DOCUMENTO = FECHA_DOCUMENTO;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO_EMPRESA() {
		return TIPO_DOCUMENTO_EMPRESA;
	}
	public void setTIPO_DOCUMENTO_EMPRESA(String  TIPO_DOCUMENTO_EMPRESA) {
		this.TIPO_DOCUMENTO_EMPRESA = TIPO_DOCUMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_EMPRESA() {
		return NRO_DOCUMENTO_EMPRESA;
	}
	public void setNRO_DOCUMENTO_EMPRESA(String  NRO_DOCUMENTO_EMPRESA) {
		this.NRO_DOCUMENTO_EMPRESA = NRO_DOCUMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getNOMBRE_COMERCIAL_EMPRESA() {
		return NOMBRE_COMERCIAL_EMPRESA;
	}
	public void setNOMBRE_COMERCIAL_EMPRESA(String  NOMBRE_COMERCIAL_EMPRESA) {
		this.NOMBRE_COMERCIAL_EMPRESA = NOMBRE_COMERCIAL_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDIRECCION_EMPRESA() {
		return DIRECCION_EMPRESA;
	}
	public void setDIRECCION_EMPRESA(String  DIRECCION_EMPRESA) {
		this.DIRECCION_EMPRESA = DIRECCION_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getCOD_UBIGEO_EMPRESA() {
		return COD_UBIGEO_EMPRESA;
	}
	public void setCOD_UBIGEO_EMPRESA(String  COD_UBIGEO_EMPRESA) {
		this.COD_UBIGEO_EMPRESA = COD_UBIGEO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getZONA_URBANIZACION_EMPRESA() {
		return ZONA_URBANIZACION_EMPRESA;
	}
	public void setZONA_URBANIZACION_EMPRESA(String  ZONA_URBANIZACION_EMPRESA) {
		this.ZONA_URBANIZACION_EMPRESA = ZONA_URBANIZACION_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDEPARTAMENTO_EMPRESA() {
		return DEPARTAMENTO_EMPRESA;
	}
	public void setDEPARTAMENTO_EMPRESA(String  DEPARTAMENTO_EMPRESA) {
		this.DEPARTAMENTO_EMPRESA = DEPARTAMENTO_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getPROVINCIA_EMPRESA() {
		return PROVINCIA_EMPRESA;
	}
	public void setPROVINCIA_EMPRESA(String  PROVINCIA_EMPRESA) {
		this.PROVINCIA_EMPRESA = PROVINCIA_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getDISTRITO_EMPRESA() {
		return DISTRITO_EMPRESA;
	}
	public void setDISTRITO_EMPRESA(String  DISTRITO_EMPRESA) {
		this.DISTRITO_EMPRESA = DISTRITO_EMPRESA;
	}
        /////////////////////////////////////////////
	public String getCOD_PAIS_EMPRESA() {
		return COD_PAIS_EMPRESA;
	}
	public void setCOD_PAIS_EMPRESA(String COD_PAIS_EMPRESA) {
		this.COD_PAIS_EMPRESA = COD_PAIS_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getRAZON_SOCIAL_EMPRESA() {
		return RAZON_SOCIAL_EMPRESA;
	}
	public void setRAZON_SOCIAL_EMPRESA(String  RAZON_SOCIAL_EMPRESA) {
		this.RAZON_SOCIAL_EMPRESA = RAZON_SOCIAL_EMPRESA;
	}
	/////////////////////////////////////////////
	public String getTIPO_DOCUMENTO_PROVEEDOR() {
		return TIPO_DOCUMENTO_PROVEEDOR;
	}
	public void setTIPO_DOCUMENTO_PROVEEDOR(String  TIPO_DOCUMENTO_PROVEEDOR) {
		this.TIPO_DOCUMENTO_PROVEEDOR = TIPO_DOCUMENTO_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getNRO_DOCUMENTO_PROVEEDOR() {
		return NRO_DOCUMENTO_PROVEEDOR;
	}
	public void setNRO_DOCUMENTO_PROVEEDOR(String  NRO_DOCUMENTO_PROVEEDOR) {
		this.NRO_DOCUMENTO_PROVEEDOR = NRO_DOCUMENTO_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getNOMBRE_COMERCIAL_PROVEEDOR() {
		return NOMBRE_COMERCIAL_PROVEEDOR;
	}
	public void setNOMBRE_COMERCIAL_PROVEEDOR(String  NOMBRE_COMERCIAL_PROVEEDOR) {
		this.NOMBRE_COMERCIAL_PROVEEDOR = NOMBRE_COMERCIAL_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getDIRECCION_PROVEEDOR() {
		return DIRECCION_PROVEEDOR;
	}
	public void setDIRECCION_PROVEEDOR(String  DIRECCION_PROVEEDOR) {
		this.DIRECCION_PROVEEDOR = DIRECCION_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getCOD_UBIGEO_PROVEEDOR() {
		return COD_UBIGEO_PROVEEDOR;
	}
	public void setCOD_UBIGEO_PROVEEDOR(String  COD_UBIGEO_PROVEEDOR) {
		this.COD_UBIGEO_PROVEEDOR = COD_UBIGEO_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getZONA_URBANIZACION_PROVEEDOR() {
		return ZONA_URBANIZACION_PROVEEDOR;
	}
	public void setZONA_URBANIZACION_PROVEEDOR(String  ZONA_URBANIZACION_PROVEEDOR) {
		this.ZONA_URBANIZACION_PROVEEDOR = ZONA_URBANIZACION_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getCIUDAD_PROVEEDOR() {
		return CIUDAD_PROVEEDOR;
	}
	public void setCIUDAD_PROVEEDOR(String  CIUDAD_PROVEEDOR) {
		this.CIUDAD_PROVEEDOR = CIUDAD_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getDEPARTAMENTO_PROVEEDOR() {
		return DEPARTAMENTO_PROVEEDOR;
	}
	public void setDEPARTAMENTO_PROVEEDOR(String  DEPARTAMENTO_PROVEEDOR) {
		this.DEPARTAMENTO_PROVEEDOR = DEPARTAMENTO_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getPROVINCIA_PROVEEDOR() {
		return PROVINCIA_PROVEEDOR;
	}
	public void setPROVINCIA_PROVEEDOR(String  PROVINCIA_PROVEEDOR) {
		this.PROVINCIA_PROVEEDOR = PROVINCIA_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getDISTRITO_PROVEEDOR() {
		return DISTRITO_PROVEEDOR;
	}
	public void setDISTRITO_PROVEEDOR(String  DISTRITO_PROVEEDOR) {
		this.DISTRITO_PROVEEDOR = DISTRITO_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getPAIS_PROVEEDOR() {
		return PAIS_PROVEEDOR;
	}
	public void setPAIS_PROVEEDOR(String  PAIS_PROVEEDOR) {
		this.PAIS_PROVEEDOR = PAIS_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getRAZON_SOCIAL_PROVEEDOR() {
		return RAZON_SOCIAL_PROVEEDOR;
	}
	public void setRAZON_SOCIAL_PROVEEDOR(String  RAZON_SOCIAL_PROVEEDOR) {
		this.RAZON_SOCIAL_PROVEEDOR = RAZON_SOCIAL_PROVEEDOR;
	}
	/////////////////////////////////////////////
	public String getTIPO_RETENCION() {
		return TIPO_RETENCION;
	}
	public void setTIPO_RETENCION(String  TIPO_RETENCION) {
		this.TIPO_RETENCION = TIPO_RETENCION;
	}
	/////////////////////////////////////////////
	public double getPORCENTAJE_RETENCION() {
		return PORCENTAJE_RETENCION;
	}
	public void setPORCENTAJE_RETENCION(double  PORCENTAJE_RETENCION) {
		this.PORCENTAJE_RETENCION = PORCENTAJE_RETENCION;
	}
	/////////////////////////////////////////////
	public String getNOTA() {
		return NOTA;
	}
	public void setNOTA(String  NOTA) {
		this.NOTA = NOTA;
	}
	/////////////////////////////////////////////
	public String getMONEDA() {
		return MONEDA;
	}
	public void setMONEDA(String MONEDA) {
		this.MONEDA = MONEDA;
	}
	/////////////////////////////////////////////
	public double getTOTAL_RETENCION() {
		return TOTAL_RETENCION;
	}
	public void setTOTAL_RETENCION(double  TOTAL_RETENCION) {
		this.TOTAL_RETENCION = TOTAL_RETENCION;
	}
	/////////////////////////////////////////////
	public double getNETO_RETENCION() {
		return NETO_RETENCION;
	}
	public void setNETO_RETENCION(double  NETO_RETENCION) {
		this.NETO_RETENCION = NETO_RETENCION;
	}
	/////////////////////////////////////////////
	public String getCOD_RESPUESTA_SUNAT() {
		return COD_RESPUESTA_SUNAT;
	}
	public void setCOD_RESPUESTA_SUNAT(String  COD_RESPUESTA_SUNAT) {
		this.COD_RESPUESTA_SUNAT = COD_RESPUESTA_SUNAT;
	}
	/////////////////////////////////////////////
	public String getDESCRIPCION_SUNAT() {
		return DESCRIPCION_SUNAT;
	}
	public void setDESCRIPCION_SUNAT(String  DESCRIPCION_SUNAT) {
		this.DESCRIPCION_SUNAT = DESCRIPCION_SUNAT;
	}
	/////////////////////////////////////////////
	public String getHASH_CPE() {
		return HASH_CPE;
	}
	public void setHASH_CPE(String  HASH_CPE) {
		this.HASH_CPE = HASH_CPE;
	}
	/////////////////////////////////////////////
	public String getHASH_CDR() {
		return HASH_CDR;
	}
	public void setHASH_CDR(String  HASH_CDR) {
		this.HASH_CDR = HASH_CDR;
	}
        /////////////////////////////////////////////
        public int getTIPO_PROCESO() {
		return TIPO_PROCESO;
	}
	public void setTIPO_PROCESO(int TIPO_PROCESO) {
		this.TIPO_PROCESO = TIPO_PROCESO;
	}
	/////////////////////////////////////////////
	public int getESTADO() {
		return ESTADO;
	}
	public void setESTADO(int  ESTADO) {
		this.ESTADO = ESTADO;
	}
	/////////////////////////////////////////////
	///////////////////campos aumentados//////////////////////////
	public int getTIPO() {
		return TIPO;
	}
	public void setTIPO(int TIPO) {
		this.TIPO = TIPO;
	}
        /////////////////////////////////////////////
	public String getCORREO() {
		return CORREO;
	}
	public void setCORREO(String CORREO) {
		this.CORREO = CORREO;
	}


	public Integer getIfacturacionPse() {
		return ifacturacionPse;
	}


	public void setIfacturacionPse(Integer ifacturacionPse) {
		this.ifacturacionPse = ifacturacionPse;
	}
	
}