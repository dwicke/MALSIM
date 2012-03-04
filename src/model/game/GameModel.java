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
public class GameModel extends AbstractTableModel {
    
    public enum columns{
        Game,
        State
    }

    private Tournament tourn;
    private ArrayList<String> columnNames;
    public GameModel() {
    }
    
    public GameModel(Tournament tourn)
    {
        this.tourn = tourn;
        columnNames = new ArrayList<String>();
        for ( columns c : columns.values())
        {
            columnNames.add(c.toString());
        }
        for (int i = 0; i < tourn.getTournProps().getGameProps().getNumAgents(); i++)
        {
            columnNames.add("Agent" + i);
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
        
        return tourn.runningGames.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames.get(col);// columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        
        if (columnNames.get(col).contains("Agent"))
        {
            return tourn.getAgent(row, Integer.valueOf(columnNames.get(col).replace("Agent", "")));
        }
        switch(columns.values()[col])
        {
            case Game:
                return tourn.getGameName(row);
               // return tourn.g.getGameProps().toString();
            case State:
                return tourn.getGameState(row);
                //return tourn.runningGames.get(row).getGameState().getState().toString();
        }
        return "";
    }
    
}
