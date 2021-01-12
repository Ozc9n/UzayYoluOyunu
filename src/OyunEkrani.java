
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame {

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) throws IOException {
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);//Klavyeeden işlem alma 
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);//Panele klavyeden işlem yapmak için gerekli
        
        ekran.add(oyun);
        ekran.setVisible(true);
                
        
    }
}