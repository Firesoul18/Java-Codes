import javax.swing.*;

// import javafx.scene.paint.Color;

import java.awt.GridLayout;
class Main{
    private JFrame frame;
    Main(){
        frame=new JFrame();
        frame.setLayout(new GridLayout(64,64,2,2));

        for(int i=0; i<63; i++){
            for(int j=0; j<63; j++){
                frame.add(new JPanel());
            }
        }

        frame.setBounds(150,150,600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
public static void main(String[] args) {
    Main x =new Main();
}
}