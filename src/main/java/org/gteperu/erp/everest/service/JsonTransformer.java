/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.service;

/**
 *
 * @author jhon
 */
public interface JsonTransformer {
      String toJson(Object data);
    
    Object fromJson(String json, Class clazz);
}
