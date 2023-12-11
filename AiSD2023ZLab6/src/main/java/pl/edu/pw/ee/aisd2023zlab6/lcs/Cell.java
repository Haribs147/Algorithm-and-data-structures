/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee.aisd2023zlab6.lcs;


public class Cell {
    
    
    private int value;
    private char arrow;
    
    public Cell(int value, char arrow){
        this.value = value;
        this.arrow = arrow;
    }
    public int getValue(){
        return value;
    }
    
    public char getArrow(){
        return arrow;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void setArrow(char arrow){
        this.arrow = arrow;
    }

}
