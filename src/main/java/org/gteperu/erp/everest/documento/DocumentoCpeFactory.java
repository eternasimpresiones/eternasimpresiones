package org.gteperu.erp.everest.documento;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DocumentoCpeFactory {
	
	private final Map<String, ProcesadorDocumento> implementationMap = new HashMap<>();
	 
	private ProcesadorDocumento builder;
	
	@Autowired
	ApplicationContext context;

	public void createBuilder(DocumentoDTO dto) throws Exception {
		if (dto.getDocumentoCpe() != null) {
			switch (dto.getDocumentoCpe().getCod_tipo_documento()) {
			case Constantes.tipoDocFactura:
				builder = implementationMap.get(FacturaBuilder.class.getName());	
				break;
			case Constantes.tipoDocBoleta:
				builder = implementationMap.get(BoletaBuilder.class.getName());
				break;
			case Constantes.tipoDocNotaCredito:
				builder = implementationMap.get(NotaCreditoBuilder.class.getName());
				break;
			case Constantes.tipoDocNotaDebito:
				builder = implementationMap.get(NotaDebitoBuilder.class.getName());
				break;
			case Constantes.tipoDocGuiaRemisionRemitente:
			case Constantes.tipoDocGuiaRemisionTransportista:
				builder = implementationMap.get(GuiaRemisionBuilder.class.getName());
				break;
			default:
				throw new Exception("El tipo de documentoCPE no es valido");
			}
			builder.setDocumentoCpe(dto.getDocumentoCpe());
		} else {
			switch (dto.getRetencion().getCod_tipo_documento()) {
			case Constantes.tipoDocPercepcion:
				builder = implementationMap.get(PercepcionBuilder.class.getName());
				break;
			case Constantes.tipoDocRetencion:
				builder = implementationMap.get(RetencionBuilder.class.getName());
				break;
			default:
				throw new Exception("El tipo de retencion no es valido");
			}
			builder.setRetencion(dto.getRetencion());
		}
		builder.initialize();
	}
	
	@PostConstruct
	public void initialize() {
		populateDataMapperMap(context.getBeansOfType(ProcesadorDocumento.class).values().iterator());
	}
	
	  private void populateDataMapperMap(final Iterator<ProcesadorDocumento> classIterator) {
	        while (classIterator.hasNext()) {
	            ProcesadorDocumento abstractClassImpl = (ProcesadorDocumento) classIterator.next();
	            implementationMap.put(abstractClassImpl.getClass().getName(), abstractClassImpl);

	        }
	    }

	public Integer procesarDocumento() throws Exception {
		return builder.procesarDocumento();
	}
	
	public Integer procesarDocumentoBorrador() throws Exception {
		return builder.procesarDocumentoBorrador();
	}
}
