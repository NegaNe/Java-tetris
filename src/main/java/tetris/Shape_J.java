/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author hans
 */
public class Shape_J extends Tetromino{
    public Shape_J() {
        cells[0] = new Cell(0,4, Tetromino.J);
        cells[1] = new Cell(0,3, Tetromino.J);
        cells[2] = new Cell(0,5, Tetromino.J);
        cells[3] = new Cell(1,5, Tetromino.J);
    }
}
