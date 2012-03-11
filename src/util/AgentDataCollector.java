/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.ITracePoint2D;
import info.monitorenter.gui.chart.io.ADataCollector;
import java.util.ArrayList;

/**
 *
 * @author drew
 */
public class AgentDataCollector extends ADataCollector{

    
    
    
    /** Flag to check wether the collector is running. */
  private boolean m_isRunning = false;

  /** The interval for data collection of a single point. */
  private long m_latency = 400;

  /** This flag controls stopping / starting the thread that is used. */
  private boolean m_stop = true;

  /**
   * The thread that is created in {@link #start()}.
   * <p>
   * If someone tries to: <code>new Thread(collector).start()</code> instead
   * of <code>collector.start()</code> an exception will be thrown.
   */
  private Thread m_thread;

    @Override
    public void start() {
        if (this.m_stop) {
      this.m_thread = new Thread(this);
      this.m_thread.start();
    }
    }
    
    @Override
    public void stop() {
    this.m_stop = true;
  }
    
    public boolean isRunning() {
    return this.m_isRunning;
  }
     public long getLatency() {
    return this.m_latency;
  }
     
     @Override
  protected void finalize() throws Throwable {
    super.finalize();
    this.stop();
  }
     
     public void run() {
    if (Thread.currentThread() != this.m_thread) {
      throw new IllegalStateException(
          "You cannot start an own thread for data collectors. Use collector.start()!");
    }
    this.m_isRunning = true;
    long lasttime;
    this.m_stop = false;
    ITracePoint2D point;
    while (!this.m_stop) {
      lasttime = System.currentTimeMillis();
      point = this.collectData();
      if (point != null)
      {
        this.getTrace().addPoint(point);
      }
      try {
        Thread.sleep(Math.max(this.m_latency - System.currentTimeMillis() + lasttime, 0));
      } catch (InterruptedException e) {
        this.stop();
      }
      if (Thread.interrupted()) {
        this.stop();
      }
    }
    this.m_isRunning = false;
  }
    
    
    
    
    
    
    
    
    
    
    
    
    private ArrayList<ITracePoint2D> dataPoints;
    private int curLoc;// where i am at in the datapoints
    // the max num of data points to expect
    private int numElements;
    public AgentDataCollector(ITrace2D trace, long latency) {
        super(trace, latency);
        this.m_latency = latency;
        dataPoints = new ArrayList<ITracePoint2D>();
        curLoc = 0;
    }
    
    
    

    public void setData(ArrayList<ITracePoint2D> data, Integer numElts)
    {
        this.numElements = numElts;
        ITrace2D tr = getTrace();
       // for (ITracePoint2D pt : data)
        int numEls = data.size();
        for (int i =0 ; i < numEls; i++)
        {
            tr.addPoint(data.get(i));
            curLoc++;
        }
        
        this.dataPoints = data;
    }
    
    public boolean shouldStart()
    {
        System.err.println("Should start " + !(curLoc == (numElements)) + "  curLoc= " + curLoc + " numels" + numElements + " size of data " + dataPoints.size());
        return !(curLoc == (numElements));
    }
    
    @Override
    public ITracePoint2D collectData() {
        
        
        if (curLoc == numElements)
        {
            stop();
        }
        if (curLoc == dataPoints.size())
        {
            return null;
        }
        //System.out.println("Data is added");
        curLoc++;
        return dataPoints.get(curLoc - 1);
        
        
    }
    
}
