import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class JavaSwing implements ActionListener {
    
    JFrame frame;
    JLabel comparesDisplay, stopWatch;
    JButton[] setOfCards = new JButton[20];
    String[] cardImages = new String[20];
    String image1File, image2File;
    JPanel gameScreen, statsPanel;
    //StartScreen myStartScreen;
    
    int cardsTurned, cardsRemaining, cardComparesCount; //cardsTurned refers to whether one or two cards have been clicked, pairs clicked refers to how many compares have been done
    int idxOfImage1, idxOfImage2;
    
    GameTimer myGameTimer = new GameTimer();
    Font myFont = new FontUIResource("Ink Free", Font.BOLD, 20);

    public JavaSwing() {

        cardsTurned = 0; 
        cardsRemaining = 20;

        frame = new JFrame("Memory Game");
        frame.setSize(900, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // grid of cards
        gameScreen = new JPanel();
        gameScreen.setBounds(40, 65, 800, 520);
        gameScreen.setLayout(new GridLayout(4, 5, 10, 10));

        // panel to display stopwatch and number of card compares done
        statsPanel = new JPanel();
        statsPanel.setBounds(40, 10, 800, 50);
        statsPanel.setLayout(new GridLayout(1, 4, 15, 10));
        statsPanel.setBackground(new Color(252, 210, 218));//196, 151, 170
        
        // shows number of card compares
        comparesDisplay = new JLabel();
        comparesDisplay.setText("compares: " + cardComparesCount);
        comparesDisplay.setFont(myFont);

        statsPanel.add(comparesDisplay);        
        statsPanel.add(myGameTimer.getGameTimer());
       // gameStats.setBounds(50, 10, 100, 50); not working for some reason??

        for (int i = 0; i < 20; i++) //make all cards one color and add to screen
        {
            setOfCards[i] = new JButton();
            setOfCards[i].addActionListener(this);
            //setOfCards[i].setFont(myFont);
            setOfCards[i].setBackground(new Color(116, 131, 171)); //122, 163, 143
            setOfCards[i].setBorder(null);
            gameScreen.add(setOfCards[i]);
        }
        
        for (int i = 0; i < 20; i++) // create array with file names of images
        {
            cardImages[i] = StdIn.readString();
        } 

        shuffleCards(cardImages); // shuffle order in which images occur

       // myStartScreen = new StartScreen(frame, gameScreen, statsPanel);
        frame.add(gameScreen);
        frame.add(statsPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        StdIn.setFile("imagenames2.in");
        new JavaSwing();
    }

    public static void shuffleCards(String[] array) {

        int index;
        String temp;

        Random random = new Random();
    
            for (int i = array.length - 1; i > 0; i--)
            {
                index = random.nextInt(i + 1);
                temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
    }

   @Override
    public void actionPerformed (ActionEvent e) {
        forloop:
        for (int i = 0; i < 20; i++)
        {
            if ( e.getSource() == setOfCards[i]) 
            {       
                ImageIcon animalImage = new ImageIcon(new ImageIcon(cardImages[i]).getImage().getScaledInstance(155, 135, Image.SCALE_DEFAULT));     
                setOfCards[i].setIcon(animalImage);

                cardsTurned += 1;
                setOfCards[i].removeActionListener(this); // make button unclickable until card is flipped back over

                if (cardsTurned == 1) 
                { 
                    idxOfImage1 = i;
                    image1File = cardImages[idxOfImage1]; 
                    break forloop;
                }

                if (cardsTurned == 2) 
                { 
                    cardComparesCount += 1;
                    comparesDisplay.setText("# of compares: " + cardComparesCount);

                    for (int cardIdx = 0; cardIdx < 20; cardIdx++)
                    {
                        if (setOfCards[cardIdx] != null)
                        {
                            setOfCards[cardIdx].removeActionListener(this); 
                        }
                    }

                    idxOfImage2 = i;
                    image2File = cardImages[idxOfImage2];

                    try { flipCardsBack (idxOfImage1, idxOfImage2); } 
                    catch (Exception e1) { e1.printStackTrace(); }

                    break forloop;

                }
            }
        }
    }

    public boolean match () {

        if (image1File.equals(image2File)) return true; // && !(setOfCards[idxOfImage1].equals(setOfCards[idxOfImage2]))
        else return false;
    } 
    
    public void flipCardsBack (int idxOfImage1, int idxOfImage2) throws Exception {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() 
            {
                if (!match())
                {
                    setOfCards[idxOfImage1].setIcon(null);
                    setOfCards[idxOfImage2].setIcon(null);
                }
            
                else if (match())
                {
                    setOfCards[idxOfImage1].setVisible(false); // error occuring here, Cannot invoke "javax.swing.JButton.setVisible(boolean)" because "this.this$0.setOfCards[this.val$idxOfImage1]" is null
                    setOfCards[idxOfImage2].setVisible(false);
                    cardsRemaining -= 2;

                    if(cardsRemaining == 0) myGameTimer.stopTimer();
                }

                cardsTurned = 0;
                resetCardStateAfterCompare();
            }
        };

        timer.schedule(task, 600);
    }

    public void resetCardStateAfterCompare() {

        for (int cardIdx = 0; cardIdx < 20; cardIdx++)
        {
            if (setOfCards[cardIdx].isVisible()) 
            {
                setOfCards[cardIdx].addActionListener(this); 
            }
        }
    }
}
