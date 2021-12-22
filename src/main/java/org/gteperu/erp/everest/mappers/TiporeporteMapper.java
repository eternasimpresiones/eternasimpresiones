package org.gteperu.erp.everest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.gteperu.erp.everest.domain.Tiporeporte;

public interface TiporeporteMapper {

	@Select("SELECT * FROM public.tiporeporte")
	List<Tiporeporte> findAll();
}
