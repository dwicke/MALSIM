/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.agent;

/**
 *
 * @author drew
 */
public class ADLProps extends AgentProperties{

    protected Integer histLength;
    protected Double alpha, gamma;

    public ADLProps() {
        super();
        // paper said that a history length of 6 was good
        histLength = 6;
        alpha = 0.4;
        gamma = 0.8;
    }

    public int getHistLength()
    {
        return histLength;
    }
    
    /**
     * Alpha is the learning rate which is used to
     * say how fast we want learning to take place
     * [0,1] closer to 1 the greater the learning rate.
     * @return 
     */
    public double getAlpha()
    {
        return alpha;
    }
    /**
     * Gamma is how much we weight the current q-value
     * as compared to previous.  higher means we weight future 
     * more than past. less means more weight to past than present.
     * @return 
     */
    public double getGamma()
    {
        return gamma;
    }
    
    @Override
    public boolean setField(String fieldAlias, Object value) {
        boolean wasSet = false;
        if (fieldAlias.equals("History_Length"))
        {
            histLength = Integer.valueOf((String)value);
            wasSet = true;
        }
        else if (fieldAlias.equals("Alpha"))
        {
            alpha = Double.valueOf((String) value);
            wasSet = true;
        }
        else if (fieldAlias.equals("Gamma"))
        {
            gamma = Double.valueOf((String) value);
            wasSet = true;
        }
        // this will just set the value in the map
        // not sure what that is for... been a while :)
        super.setField(fieldAlias, value);
        return wasSet;
    }
    
    
    
    
    @Override
    public void generateViewFields() {
        
        fieldVals.put("History_Length", histLength);
        fieldAlias.put("History_Length", histLength.getClass());
        
        fieldVals.put("Alpha", alpha);
        fieldAlias.put("Alpha", alpha.getClass());
        
        fieldVals.put("Gamma", gamma);
        fieldAlias.put("Gamma", gamma.getClass());
        
    }
    
}
