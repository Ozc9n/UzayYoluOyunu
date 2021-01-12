import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.Timer;


 
class Ates{

    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

public class Oyun extends JPanel implements KeyListener, ActionListener{

   Timer timer =new Timer(2, this);

    private int gecen_sure=0;

    private int harcanan_ates=0;
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();

    private BufferedImage image;

    private int atesdirY=1;

    private int TopX=0;  //başlangıç noktası

    private int TopdirX=2; //zamanla 2 birim yukarı çıkar

    private int uzayGemisiX=0;

    private int diruzayGemisiX=20;  //sağa sola 20 birim

       
        public  boolean  kontrolEt(){
            for (Ates ates : atesler){
                if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(TopX,0,20,20))){
                    return true ;
                }
            }
            return false;
        }
    
        public Oyun() {

        try {
            image= ImageIO.read( new FileImageInputStream(new File("uzaygemisi.png"))); 

        } catch (IOException ex) {

            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);

           

        }
            timer.start();
            setBackground(Color.BLACK);

}


    @Override

    public void paint(Graphics g) {

        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure+=5;
       

        g.setColor(Color.red);   

       

        g.fillOval(TopX, 0, 20, 20);  //top yuvarlat

       

        g.drawImage(image, uzayGemisiX,490,image.getWidth()/10,image.getHeight()/10, this);
        
         for(Ates ates :atesler){
            if(ates.getY()<0){
                atesler.remove(ates);
            }
            g.setColor(Color.blue);
         }
            
            for(Ates ates : atesler){
              g.fillRect(ates.getX(),ates.getY(),10,20);
          
        }
            if(kontrolEt()){
                timer.stop();
                String message = "Tebrikler Vurdunuz !!! \n"
                             +"Harcanan ateş : "+harcanan_ates
                          +"\n Geçen Süre: "+gecen_sure/1000.0+" ms";
                JOptionPane.showMessageDialog(this, message);
                
                System.exit(0);
                
            }
         
            
    }


    @Override

    public void repaint() {

        super.repaint(); //To change body of generated methods, choose Tools | Templates.

    }



    @Override

    public void keyTyped(KeyEvent e) {

        

    }


    @Override

    public void keyPressed(KeyEvent e) {

       int c = e.getKeyCode();
       if(c==KeyEvent.VK_LEFT){
           if(uzayGemisiX<=0){
               uzayGemisiX=0;
           }
           else {
               uzayGemisiX-=diruzayGemisiX;
           }
       }
       else if (c==KeyEvent.VK_RIGHT){
           if(uzayGemisiX>=720){
               uzayGemisiX=720;
           }
           else{
               uzayGemisiX+=diruzayGemisiX;
           }
       }
       else if(c==KeyEvent.VK_CONTROL){
           atesler.add(new Ates(uzayGemisiX+15,470));//UZay mekiğininucundan çıkmasını sağlar
           
           harcanan_ates ++;
           
       }
      
    }


    @Override

    public void keyReleased(KeyEvent e) {

        

    }


    @Override

    public void actionPerformed(ActionEvent e) {
        for(Ates ates : atesler){
            ates.setY(ates.getY()- atesdirY);
        }
        
        TopX+=TopdirX;
        
        if(TopX>=750){
            TopdirX= -TopdirX;
        }
        if(TopX <=0){
            TopdirX=-TopdirX;
        }
        repaint();
        

    }


    private File FileImageInputStream(File file) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

   

}