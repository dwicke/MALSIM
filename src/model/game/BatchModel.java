/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author drew
 */
public class BatchModel extends AbstractTableModel{

    public enum columns{
        Tournament,
        State,
        Number_of_Agents,
        Remaining_Agents,
        Lead_Agent
        
    }
    
    
    private Batch batch;
    private ArrayList<String> columnNames;
    public BatchModel() {
    }
    
    public BatchModel(Batch batch)
    {
        this.batch = batch;
        columnNames = new ArrayList<String>();
        for ( columns c : columns.values())
        {
            columnNames.add(c.toString());
        }
        
        // just add the name of the column you want to add
        // to the columns enum and then in the getValueAt just
        // add a case where you handle getting the data for that
        // column
    }
    
    
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public int getRowCount() {
        
        return batch.getTourn().size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);// columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        
        System.out.println("Getting value at row " + row + " col " + col + " " + columns.values()[col]);
        switch(columns.values()[col])
        {    
            case Tournament:
                // return the name of the tournament
                System.out.println("Tourn in BATCHMODEL");
                return batch.getTournList().get(row).toString();
                
            case State:
                return batch.getTournStateString(batch.getTournNames().get(row));
            case Number_of_Agents:
                return batch.getTournList().get(row).getTournProps().getAgents().size();
            case Remaining_Agents:
                return batch.getTournList().get(row).remainingAgents.size();
            case Lead_Agent:
                return batch.getTournList().get(row).getLeadAgent().toString();
                
                //return batch.getTournList().get(row).
                // so this is where I can add more columns
                // if I want to later
        }
        
        return null;
    }
    
}
