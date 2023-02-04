import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class JavaSwing implements ActionListener {
    
    JFrame frame;
    JButton[] backOfCards = new JButton[20];
    //JButton[] functionalButtons = newJButton[]
    //JButton start, restart, quit, animalButton;

    JPanel startScreen, gameScreen, menuScreen;
    //Font myFont = new Font("Ink Free", Font.BOLD, 30);

    public JavaSwing() {

        // ImageIcon narwhal = new ImageIcon(new ImageIcon("elephant (1).png").getImage().getScaledInstance(190, 170, Image.SCALE_DEFAULT));     
        // narwhalButton = new JButton(narwhal);
        
        frame = new JFrame("Memory Game");
        frame.setSize(900, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen = new JPanel();
        gameScreen.setBounds(40, 65, 800, 520);
        gameScreen.setLayout(new GridLayout(4, 5, 10, 10));
        //gameScreen.setBackground(new Color(116, 131, 171));

        for (int i = 0; i < 20; i++) //make all cards one color and add to screen
        {
            backOfCards[i] = new JButton("");
            backOfCards[i].addActionListener(this);
            //backOfCards[i].setFont(myFont);
            backOfCards[i].setBackground(new Color(116, 131, 171));
            gameScreen.add(backOfCards[i]);
        }

        frame.add(gameScreen);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JavaSwing js = new JavaSwing();
    }

   @Override
    public void actionPerformed (ActionEvent e) {
        for (int i = 0; i < 20; i++)
        {
            if ( e.getSource() == backOfCards[i]) 
            {    
                
                Images cardImage = new Images();
                String imageString = cardImage.getImageString(i);
              
                ImageIcon animalImage = new ImageIcon(new ImageIcon(imageString).getImage().getScaledInstance(190, 170, Image.SCALE_DEFAULT));     
                backOfCards[i].setIcon(animalImage);
            }
        }
    }

}