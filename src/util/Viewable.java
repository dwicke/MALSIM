/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Map;
import java.util.TreeMap;

/**
 * This defines an interface that will provide data
 * about the subclass in order to construct a view
 * of the object.
 * @author drew
 */
public abstract class Viewable {
    
   
     protected TreeMap<String, Object> fieldVals;
     protected TreeMap<String, Class> fieldAlias;
     
     
     public Viewable()
     {
         fieldAlias = new TreeMap<String, Class>();
         fieldVals  = new TreeMap<String, Object>();
     }
     
     /**
      * Subclasses must implement this and
      * fill in the fieldVals and fieldAlias's
      */
     public abstract void generateViewFields();
     
    /**
     * Returns a map of the field alias to the class object
     * @return 
     */
    public Map<String, Class> getFieldAliases() {
        return fieldAlias;
    }
    /**
     * Sets the field with the alias fieldAlias to the
     * value of the Object
     * @param fieldAlias name of field
     * @param value value to set to that field
     */
    public boolean setField(String fieldAlias, Object value) {
        fieldVals.put(fieldAlias, value);
        return true;
    }
    
    /**
     * Checks to see if the string is mapped to an object.
     * @param fieldAlias
     * @return 
     */
    public boolean checkField(String fieldAlias)
    {
        if (this.fieldVals.keySet().contains(fieldAlias))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Sets the class of the field.
     * @param field field alias
     * @param classVal class
     */
    public void setFieldClass(String field, Class classVal){
        fieldAlias.put(field, classVal);
    }
    /**
     * Sets all of the class's fields.
     * @param vals A map that maps the field alias to value to assign to the field
     */
   public void setAll(Map<String, Object> vals) {
        fieldVals.putAll(vals);
    }
    /**
     * Returns the field value given the field alias
     * @param fieldAlias The alias of the field who's value is returned.
     * @return The value of the field.
     */
     public Object getField(String fieldAlias) {
        return fieldVals.get(fieldAlias);
    }
    /**
     * Returns a map of all of the field alias mapped to their current values
     * @return 
     */
    public Map<String, Object> getFieldVals() {
        return fieldVals;
    }
    
    public Class getClass(String alias)
    {
        return fieldAlias.get(alias);
    }
}
