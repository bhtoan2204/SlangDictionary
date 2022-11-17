import javax.swing.*;
import java.awt.*;

public class Interface {
    final JFrame frame = new JFrame();
    public void createGUI(){
        frame.setTitle("Slang Dictionary");
        frame.setSize(800,800);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        Interface gui = new Interface();
        gui.createGUI();
    }
}
