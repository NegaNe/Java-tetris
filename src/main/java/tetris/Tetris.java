/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tetris;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
//import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author hans
 */
public class Tetris extends JPanel {
    
    public static final int fontSize = 18;
    public static final int fontColour=0x0000;
    
    private Tetromino current = Tetromino.randomOne();
    private Tetromino next = Tetromino.randomOne();

    protected int score=0;
    protected int line=0;
    protected int sec;
    boolean status=false;
    
    private final int row = 20;
    private final int col = 10;
    
    private final Cell[][] wall = new Cell[row][col];
    private static final int CELL_SIZE = 26;

    
    void drawWall(Graphics g) {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                int x = CELL_SIZE * j;
                int y = CELL_SIZE * i;
                
                Cell cell = wall[i][j];
                if (cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }
    } 
    
    void drawCurrent(Graphics g) {
        Cell[] cells = current.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
        
    }

    void drawNext(Graphics g) {
        Cell[] cells = next.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 260;
            int y = cell.getRow() * CELL_SIZE + 26;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
            
            Font f = getFont();
         Font font = new Font (f.getName(), Font.PLAIN, fontSize);
         int X,Y; X=280; Y=50;
         g.setColor(new Color(fontColour));
         g.setFont(font);
         String str = "Next : ";
         g.drawString(str, X, Y);
        }
    }
     
     void drawScore(Graphics g){

         Font f = getFont();
         Font font = new Font (f.getName(), Font.PLAIN, fontSize);
         int x,y; x=290; y=150;
         g.setColor(new Color(fontColour));
         g.setFont(font);
         String str = "Score : " + this.score;
         g.drawString(str, x, y);
     }
          void drawLine(Graphics g){
         Font f = getFont();
         Font font = new Font (f.getName(), Font.PLAIN, fontSize);
         int x,y; x=290; y=175;
         g.setColor(new Color(fontColour));
         g.setFont(font);
         String str = "Line    : " + this.line;
         g.drawString(str, x, y);
     }
     
     
         void drawOver(Graphics g){
        if(isOver()){
         int fontSize=36;
         int fontColour=0xff0000;
         Font f = getFont();
         Font font = new Font (f.getName(), Font.BOLD, fontSize);
         int x,y; x=290; y=350;
         g.setColor(new Color(fontColour));
         g.setFont(font);
         String str = "Game Over";
         g.drawString(str, x, y);
            }
         }
         
                  void drawControls(Graphics g){
   
         int fontSize=18;
         Font f = getFont();
         Font font = new Font (f.getName(), Font.PLAIN, fontSize);
         int x,y; x=290; y=225;
         g.setColor(new Color(fontColour));
         g.setFont(font);
         String str = "Controls :";
         g.drawString(str, x, y);
         y+=35;
         Font font2 = new Font (f.getName(), Font.PLAIN, fontSize);
         g.setColor(new Color(fontColour));
         g.setFont(font2);
         String str2 = " Arrow Key";
         g.drawString(str2, x, y);
         x+=75;

                           Font font3 = new Font (f.getName(), Font.PLAIN, fontSize);
         g.setColor(new Color(fontColour));
         g.setFont(font3);
         String str3 = "Spacebar to Rotate";
         x-=71;
         y+=25;
         g.drawString(str3, x, y);
         
                  }
         
         
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawWall(g);
        drawCurrent(g);
        drawNext(g);
        drawScore(g);
        drawOver(g);
        drawLine(g);
        drawControls(g);
    }
    
    boolean outOfBound() { // di bawah
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celRow = cell.getRow();
            if (celRow <= 0 || celRow >= row-1){
                return true;
            }
        }
        return false;
    }
    
    
    boolean tooLeft() { // terlalu ke kiri
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            if (celCol <= 0){
                return true;
            }
            for (int i = 0 ; i<celCol ; i++){
            if (i == 0 && i == 1){
                return true;
                    }
            }
        }
        
        return false;
    }
    
    boolean tooRight() { // terlalu ke kanan
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            if (celCol >= col-1){
                return true;
                
            }
        }
        return false;
    }
    
    boolean coincide() { // kalau ketemu bidak lainnya
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
                        if (wall[celRow][celCol] != null){ 

                return true;       
            } 

        }
        return false;
    }
    
    boolean coincideLeft(){
               Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (wall[celRow][celCol-1] != null){
                return true;
            }
        }
        return false;
    }

       boolean coincideRight(){
               Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (wall[celRow][celCol+1] != null){
            return true;
            }
        }
        return false;
    }
       
    boolean isDrop() { // pemeriksaan kesempatan turun
        Cell[] cells = current.cells;
                        if (!isOver()){ //conditional gameover
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
                        deleteLine();

            if(celRow == row-1) {
                return false;
            }
            if (wall[celRow+1][celCol] != null){

                return false;
            }
        }
        
    }
        return true;
    }
   
    boolean isOver(){ //Statement Game Over
                Cell[] cells = current.cells;
            for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
         for (int gRow = 0 ; gRow < celCol ; gRow++){
             int i = 1, j=2;
            if (wall[celRow][celCol] != null && wall[i][celCol] !=null || wall[j][celCol] != null){
                return true;
            }
    }
            
    }   
        return false;
    }

    void stopDropping () { // jika bidak sudah sampai bawah
        Cell[] cells = current.cells;
        for(Cell cell : cells) {
            int celrow = cell.getRow();
            int celcol = cell.getCol();
            wall[celrow][celcol] = cell;
 
        }
//        for (int i = 0 ; i < ; i++){
                this.score+=1;           
//        }
    }


public void deleteLine() { //mengecek dan menghapus baris
     boolean full=false;
        for (int rRow = 0; rRow < row; rRow++) {
            for (int rCol = 0; rCol < col; rCol++) {
        if (wall[rRow][rCol] == null) { //mengecek apakah baris sudah penuh
            full=false;
            } }
            if(full==true){
            for (int rCol = 0 ; rCol < col ; rCol++){
                wall[rRow][rCol] = null; //proses menghapus baris 
//                  System.out.print(rRow + " ");
//                  System.out.print(rCol + " ");
                
            }
    this.line=line+1;
    this.score+=score=8;
    //                for (int i = 0 ; i < line ; ) //combo
//                if()
//penurunan
	for (int celRow = rRow; celRow > 0; celRow--) { //array copy berfungsi untuk menurunkan blok apabila sudah di hapus
                System.arraycopy(wall[celRow-1], 0, wall[celRow], 0, col);
    }
        }else{
            full = true; 
            }
           }
          }
      
    protected void softDrop() {
            if (!isOver())    
        if(isDrop()) {
            current.softDrop();
        } else {
            stopDropping();
            current = next;
            next = Tetromino.randomOne();
        }        
    }
    
    protected void moveLeft() {
       if(!tooLeft() && !outOfBound() && !coincide() && !isOver() && !coincideLeft()){
        current.moveLeft();
        }
    }

    protected void moveRight() {
        if(!tooRight() && !outOfBound() && !coincide() && !isOver() && !coincideRight())
            current.moveRight();
    }
    


    public void setRotation() { //untuk pengecekan daerah sekitar apakah null atau tidak
        if (!coincide() && !isOver()){
            Cell[] Cells = current.pivotRotate(); 
            if (Cells == null) return; // apabila cell sekitar = null maka akan return ke method rotate
            for (Cell Cell : Cells) {
                int nRow = Cell.getRow();
                int nCol = Cell.getCol();
                if ( nCol >= col || nRow >=row|| nRow < 0 || nCol < 0 || wall[nRow][nCol] != null ) return;
                repaint();
            }
         current.cells = Cells;
            }
    }

  
    public void start() {
        KeyListener keylist = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent args) {
                int key = args.getKeyCode();
                
                switch(key) {
                    case KeyEvent.VK_DOWN : {
                        softDrop();
                        break;
                    } case KeyEvent.VK_LEFT : {
                        moveLeft();
                        break;
                    } case KeyEvent.VK_RIGHT : {
                        moveRight();
                        break;
                    }
                    case KeyEvent.VK_SPACE : {
                        setRotation();
                        break;
                    }
                }
                repaint();
            }
        };
        
        this.addKeyListener(keylist);
        this.requestFocus();
        

        new Thread() {
            @Override
            public void run() {

            
                while(true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    if (isDrop()){
                        softDrop();
                        
                    } else {
                        stopDropping();
                        current = next;
                        next = Tetromino.randomOne();
                    }
                    repaint();
                }
            }
        }.start();
    }
    
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Tetris");
        frame.setSize(530, 580);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Tetris tetrisPanel = new Tetris();
        frame.add(tetrisPanel);
        frame.setVisible(true);
        tetrisPanel.start();
        

        
        
    }
    
}
   
    




