/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.domain;

/**
 *
 * @author fchuquilin
 */
public class Pagination {
    private Integer offset;
    private Integer limit;
    private Integer cantidad;
    private String filter;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
}
