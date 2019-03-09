package com.example.arman.eshop;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableDynamic {
    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data = new ArrayList<String[]>();
    private TableRow tableRow;
    private TextView textCell;
    private int indexC;
    private int indexR;
    private boolean multicolor = true;
    private int firstColor;
    private int secondColor;
    private int dataSize = 1;


    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }
    public void addHeadder(String[] header){
        this.header = header;
        createHeader();
    }
    public void addData(ArrayList<String[]> data){
        this.data = data;
        createDataTable();
    }


    private void newRow(){
        tableRow = new TableRow(context);
    }

    private void newCell(){
        textCell = new TextView(context);
        textCell.setGravity(Gravity.CENTER);
        textCell.setTextSize(25);
    }
    private void createHeader(){
        indexC=0;
        newRow();
        while(indexC<header.length ){
            newCell();
            textCell.setText(header[indexC++]);
            textCell.setTextColor(Color.parseColor("#ffffff"));
            tableRow.addView(textCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }
    private void createDataTable()
    {
        String info;
        for (indexR=0;indexR<=data.size()-1;indexR++){
            newRow();
            for (indexC = 0; indexC<header.length ;indexC++){
                newCell();
                String[] columns = data.get(indexR);
                info=(indexC<columns.length)?columns[indexC]:"";
                textCell.setText(info);
                textCell.setTextColor(Color.parseColor("#000000"));
                tableRow.addView(textCell,newTableRowParams());
            }
            tableLayout.addView(tableRow,indexR+1);
        }
    }

    private TableRow getRow(int index){
        return (TableRow)tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex,int columnIndex ){
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columnIndex);
    }

    public void backgraundHeader(int color){
        indexC=0;
        while(indexC<header.length ){
            textCell = getCell(0,indexC++);
            textCell.setBackgroundColor(color);
        }

    }

    public void backgraundData(int firstColor,int secondColor){
        String info;
        for (indexR=1;indexR<=data.size();indexR++){
            multicolor = !multicolor;
            for (indexC = 0; indexC<header.length;indexC++){
                textCell = getCell(indexR,indexC);
                textCell.setBackgroundColor((multicolor)?firstColor:secondColor);
            }
        }
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }

}
