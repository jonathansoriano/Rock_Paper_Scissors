import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

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
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;
    private Random random = new Random();

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
        setSize(700, 800);
    }

    /**
     * This method generates the panel with all the buttons options for the RPS game.
     */
    public void generateButtonPanel(){
        btnPanel = new JPanel(); // Create panel to hold the buttons
        btnPanel.setLayout(new GridLayout(1,4)); //This layout is best for buttons so they all have same size
        btnPanel.setBorder(new TitledBorder(new EtchedBorder(),"Choices")); // Setting titled etched border with title "Choices".

        imgRock = new ImageIcon("src/RockImg2.png"); // Defining imgRock ImageIcon and putting in filename
        btnRock = new JButton(imgRock);// Create a button that has the imgRock Image Icon.
        btnRock.setFocusable(false);// This gets rid of the annoying halo around the button
        btnRock.addActionListener((ActionEvent ae) -> playGame("Rock")); // We are adding an actionListening
        // and using a Lambda expression. Assigning the button to a String of "Rock" to playGame() method.

        imgPaper = new ImageIcon("src/PaperImg2.png"); // Defining imgPaper ImageIcon and putting in filename
        btnPaper = new JButton(imgPaper); //Creating a button that has the imgRock ImageIcon.
        btnPaper.setFocusable(false);//Getting rid of annoying halo around the button.
        btnPaper.addActionListener((ActionEvent ae) -> playGame("Paper"));//We are adding an actionListening
        // and using a Lambda expression. Assigning the button to a String of "Paper" to playGame() method.

        imgScissors = new ImageIcon("src/ScissorsImg2.png");// Defining imgScissors ImageIcon and putting in filename
        btnScissors = new JButton(imgScissors); // Creating a button that has imgScissors ImageIcon
        btnScissors.setFocusable(false);
        btnScissors.addActionListener((ActionEvent ae) -> playGame("Scissors")); // We are adding an actionListening
        // and using a Lambda expression. Assigning the button to a String of "Scissors" to playGame() method.

        imgQuit = new ImageIcon("src/Quit2.png");
        btnQuit = new JButton(imgQuit);
        btnQuit.setFocusable(false);
        btnQuit.addActionListener((ActionEvent ae)-> System.exit(0)); // We are adding an actionListening
        // and using a Lambda expression. "System.exit(0)" method will close the application when Quit button pressed.

        //Adding all 4 buttons to the btnPanel in order I want them in the gridlayout
        btnPanel.add(btnRock);
        btnPanel.add(btnPaper);
        btnPanel.add(btnScissors);
        btnPanel.add(btnQuit);
    }

    /**
     * This method generates the panel that contains the win tally marks between the computer, user and includes ties.
     */
    public void generateStatPanel(){
        statPanel = new JPanel(); //Creates new statPanel to contain labels and text fields.
        statPanel.setLayout(new GridLayout(3,2)); // Organizes panel and comp. better this way
        statPanel.setBorder(new TitledBorder(new EtchedBorder(),"Stats"));

        labelPlayer = new JLabel("Player Wins:"); // Defining label and giving it a title name "Player Wins:".
        playerWinsTF = new JTextField("0",75); //Create Player's stat text field and giving it a text string of "0" and a width of 75 char
        playerWinsTF.setEditable(false); // Prevents player/user from texting/editing text field.


        labelComputer= new JLabel("Computer Wins:");
        compWinsTF = new JTextField("0",75);
        compWinsTF.setEditable(false);


        labelTie = new JLabel("Ties:");
        tieWinsTF = new JTextField("0",75);
        tieWinsTF.setEditable(false);

        // Adding all labels and textfields comp. to the statPanel in order how I want them seen on screen
        statPanel.add(labelPlayer);
        statPanel.add(playerWinsTF);
        statPanel.add(labelComputer);
        statPanel.add(compWinsTF);
        statPanel.add(labelTie);
        statPanel.add(tieWinsTF);
    }

    /**
     * This method generates the panel that shows who all has won the games in previous games in String format.
     */
    public void generateResultsPanel(){
        resultPanel = new JPanel();// Defining the resultPanel
        resultPanel.setBorder(new TitledBorder(new EtchedBorder(),"Results"));

        resultsTA = new JTextArea(6,35);// Defining and creating a JTextArea of 6 rows and width of 35 char
        resultsTA.setEditable(false);// Makes sure user can't edit the JTextField

        scroller = new JScrollPane(resultsTA); //Defining and giving the JTextField ability to scroll if text fills up the
                                            // JTextArea. Have to add the JTextArea the ScrollPanel for it to work.
        resultPanel.add(scroller);// instead of adding JTextArea comp, we add the ScrollPane that has the JTextArea
    }

    /**
     * This method takes the action from user and generates computer's move randomly. Creates the logic of the game
     * by including method that determines the winner, updating stats in Stat panel, and send String text message of
     * who won the game in the Results Panel.
     * @param playerChoice - needs choice from the player/user.
     */
    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};//Choices in an Array so random gen can randomly select index
        String computerChoice = choices[random.nextInt(choices.length)];//Range for random gen.

        String result = determineWinner(playerChoice, computerChoice);
        updateStats(result);
        updateResultArea(result);
    }

    /**\
     * This method figures out the winner of the game based on the player's choices.
     * @param playerChoice - need users choice
     * @param computerChoice -- need computer's random choice from Array
     * @return - returns the results of the game in String format
     */
    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) { // we use "equals()" method to compare two Strings
            return "It's a tie!"; // returning tie msg if player's choice and computer's choice are the same.
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) || // Here we are defining all win possibilities for the user
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return playerChoice + " beats " + computerChoice + " (Player wins)"; //uses player's choice and returns a String msg of what was picked for each player and who won
        } else {
            return computerChoice + " beats " + playerChoice + " (Computer wins)";// Dont need to hardcode win possibilities for computer. This works just fine.
        }
    }

    /**
     * This method updates the statPanel's JTextFields. Keeps track of wins numerically.
     * @param result - needs result String to know who to give a tally to their Win tally JTextfield.
     */
    private void updateStats(String result) {
        if (result.contains("Player wins")) { //contains() looks in a String to finds matching String
            playerWins++;
            playerWinsTF.setText(String.valueOf(playerWins));// Converts int value to String since Texfield takes Strings only.
        } else if (result.contains("Computer wins")) {
            computerWins++;
            compWinsTF.setText(String.valueOf(computerWins));
        } else {
            ties++;
            tieWinsTF.setText(String.valueOf(ties));
        }
    }

    /**
     * This method updates the JTextAres of who the winner is.
     * @param result - takes result String from determineWinner() method and appends it (adds it) to the JTextArea.
     */
    private void updateResultArea(String result) {
        resultsTA.append(result + "\n");
    }
}
