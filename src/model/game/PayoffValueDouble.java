/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import util.MersenneTwister;

/**
 * 
 * @author drew
 */
public class PayoffValueDouble implements PayoffValue{

    private Double payoff;
    private static MersenneTwister rand = new MersenneTwister(System.nanoTime());
    
    public PayoffValueDouble()
    {
        
    }
    
    public PayoffValueDouble(Double min, Double max)
    {
        payoff = min + rand.nextDouble() * (max - min);
    }
    
    /**
     * Gets the value of the payoff
     * @return 
     */
    @Override
    public Object getValue() {
        return payoff;
    }

    /**
     * val must be a Double object.
     * @param val 
     */
    @Override
    public void setValue(Object val) {
        payoff = (Double)val;
    }
    
}
