/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author hans
 */
public class Tetromino extends Cell {
protected Cell[] cells;

    public Tetromino() {
        cells = new Cell[4];
    }
    
    public void moveLeft() {
        for(Cell cell : cells) {
            cell.left();
        }
    }
    
    public void moveRight() {
        for(Cell cell : cells) {
            cell.right();
        }
    }

    public void softDrop() {
        for(Cell cell : cells) {
            cell.down();
        }
    }
        public Cell[] pivotRotate(){ //method rotasi
            Cell[] Cells = new Cell[4];  //mendeteksi titik pivot untuk putar
            int rRow = cells[0].getRow(); //mendeteksi letak row1 block 
            int rCol = cells[0].getCol(); //mendeteksi letak column1 block
            for (int i = 0 ; i < 4; i++) { //for looping untuk mendeteksi letak blok
                int nRow = cells[i].getRow(); //mendeteksi letak row2 block
                int nCol = cells[i].getCol(); //mendeteksi letak column2 block
                Cells[i] = new Cell (nCol+(rRow-rCol), (rRow+rCol)-nRow, cells[i].getImage());  //rumus rotasi
                  if (this.getClass().equals(new Shape_O().getClass())) return null; //default shape untuk mencegak Shape_O untuk berputar.
//                System.out.print("nCol" + nCol + "\n");
//                System.out.print("rCol" + rCol + "\n");
            }
            return Cells;
        }
    

    public static Tetromino randomOne() {
        Tetromino t = null;
        int n = (int) (Math.random() * 7);
        switch(n) {
            case 0: t = new Shape_I(); break;
            case 1: t = new Shape_J(); break;
            case 2: t = new Shape_L(); break;
            case 3: t = new Shape_O(); break;
            case 4: t = new Shape_S(); break;
            case 5: t = new Shape_Z(); break;
            case 6: t = new Shape_T(); break;
        }
        return t;
    }   
    
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage Z;
    public static BufferedImage T;
    
    static {
        try {
            I = ImageIO.read(new File("src/resources/I.png"));
            J = ImageIO.read(new File("src/resources/J.png"));
            L = ImageIO.read(new File("src/resources/L.png"));
            O = ImageIO.read(new File("src/resources/O.png"));
            S = ImageIO.read(new File("src/resources/S.png"));
            Z = ImageIO.read(new File("src/resources/Z.png"));
            T = ImageIO.read(new File("src/resources/T.png"));        

            
        } catch (IOException ex) {
            Logger.getLogger(Tetromino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
