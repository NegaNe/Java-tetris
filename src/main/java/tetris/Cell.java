/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.image.BufferedImage;

/**
 *
 * @author hans
 */
public class Cell {
    public int row;
    public int col;
    protected BufferedImage image;
    

   
    public Cell() { }
    
    public Cell(int row, int col, BufferedImage img) {
        this.row = row;
        this.col = col;
        this.image = img;
    }
    
    
    public int getRow() {
    return row;
	}

    public void setRow(int row) {
    this.row = row;
	}
        
    public void setCol(int col) {
    this.col = col;
	}
    
    public int getCol() {
        return col;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
    
    public void left() {
        col--;
    }
    
    public void right() {
        col++;
    }

    public void down() {
        row++;
    }
    

}
