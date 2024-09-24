import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RockPaperScissorsFrame extends JFrame {
    // Panels
    JPanel mainPanel;
    JPanel btnPanel;
    JPanel statPanel;
    JPanel resultPanel;
    //Buttons
    JButton btnRock;
    JButton btnPaper;
    JButton btnScissors;
    JButton btnQuit;
    //ImageIcon
    ImageIcon imgRock;
    ImageIcon imgPaper;
    ImageIcon imgScissors;
    ImageIcon imgQuit;
    //Labels
    JLabel labelPlayer;
    JLabel labelComputer;
    JLabel labelTie;
    //TextField
    JTextField playerWinsTF;
    JTextField compWinsTF;
    JTextField tieWinsTF;
    //TextArea
    JTextArea resultsTA;
    //Scroller
    JScrollPane scroller;
    //More Variables
    int numOfWins = 0;
    int gameCount = 0;
    String gameMsg = "";

    public RockPaperScissorsFrame(){
        //Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        //Button Panel method
        generateButtonPanel();
        mainPanel.add(btnPanel);
        //Stats Panel method
        generateStatPanel();
        mainPanel.add(statPanel);
        //Results Panel method
        generateResultsPanel();
        mainPanel.add(resultPanel);

        //Frame
        add(mainPanel);
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        setSize(700, 800);

    }

    public void generateButtonPanel(){
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,4));
        btnPanel.setBorder(new TitledBorder(new EtchedBorder(),"Choices"));

        imgRock = new ImageIcon("src/RockImg.png");
        btnRock = new JButton(imgRock);
        imgPaper = new ImageIcon("src/PaperImg.png");
        btnPaper = new JButton(imgPaper);
        imgScissors = new ImageIcon("src/ScissorsImg.png");
        btnScissors = new JButton(imgScissors);
        imgQuit = new ImageIcon("src/Quit.png");
        btnQuit = new JButton(imgQuit);


        btnPanel.add(btnRock);
        btnPanel.add(btnPaper);
        btnPanel.add(btnScissors);
        btnPanel.add(btnQuit);
    }

    public void generateStatPanel(){
        statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(3,1));
        statPanel.setBorder(new TitledBorder(new EtchedBorder(),"Stats"));

        labelPlayer = new JLabel("Player Wins:");
        playerWinsTF = new JTextField();
        playerWinsTF.setEditable(false);
        playerWinsTF.setPreferredSize(new Dimension(75,10));

        labelComputer= new JLabel("Computer Wins:");
        compWinsTF = new JTextField();
        compWinsTF.setEditable(false);
        compWinsTF.setPreferredSize(new Dimension(75,10));

        labelTie = new JLabel("Ties:");
        tieWinsTF = new JTextField();
        tieWinsTF.setEditable(false);
        tieWinsTF.setPreferredSize(new Dimension(75,10));

        statPanel.add(labelPlayer);
        statPanel.add(playerWinsTF);
        statPanel.add(labelComputer);
        statPanel.add(compWinsTF);
        statPanel.add(labelTie);
        statPanel.add(tieWinsTF);

    }

    public void generateResultsPanel(){
        resultPanel = new JPanel();
        resultPanel.setBorder(new TitledBorder(new EtchedBorder(),"Results"));

        resultsTA = new JTextArea(6,50);
        resultsTA.setEditable(false);

        scroller = new JScrollPane(resultsTA);
        resultPanel.add(scroller);

    }


}
