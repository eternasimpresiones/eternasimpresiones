package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Metodopago;

public interface MetodopagoMapper {

	@Select( "select * from metodo_pago")
    public List<Metodopago> listarMetodopago();
}
