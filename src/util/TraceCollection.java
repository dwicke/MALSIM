/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DBijective;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author drew
 */
public class TraceCollection {
    
    // the name of the trace and the trace
    protected Map<String, ArrayList<ITracePoint2D>> traces;
    protected Map<String, Integer> numelts;
    //protected Map<String, Chart2D> charts;

    public TraceCollection() {
        traces = new TreeMap<String, ArrayList<ITracePoint2D>>();
        numelts = new TreeMap<String, Integer>();
        //charts = new TreeMap<String, Chart2D>();
    }
    
    public void addTrace(String name, int numElts)
    {
        //traces.put(name, new Trace2DBijective());
        traces.put(name, new ArrayList<ITracePoint2D>());
        numelts.put(name, numElts);
        
    }
    
    public AgentDataCollector getCollector(String name, ITrace2D trace)
    {
        AgentDataCollector dt = new AgentDataCollector(trace, 1000);
        dt.setData(traces.get(name), numelts.get(name));
        return dt;
    }
    
    public void addPoint(String name, double x, double y)
    {
        
        traces.get(name).add(new TracePoint2D(x, y));
        
    }
    
    
    public Iterator<ITracePoint2D> getPointIterator(String name)
    {
        return traces.get(name).iterator();
    }

    public ArrayList<String> getTraceNames() {
        return new ArrayList<String>(traces.keySet());
    }
    
    
}
