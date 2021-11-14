import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

interface IDisplays {
    void displays();
    String[] operations = {"=" , "Clear"};
    String[] decimalButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "\u00F7"};
    String[] binaryButtons = {"0", "1", "+", "-", "*", "\u00F7"};
    String[] hexButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "+", "-", "*", "\u00F7"};
    String[] bigDButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", "*", "\u00F7" , "\u221A" , "\u33A1" , "!", "MOD", "LCD", "GCD"};
    String[][] calcButtonChoice ={operations,decimalButtons,binaryButtons,hexButtons,bigDButtons};
    String[][] menuBarString = {{"Calculator", "Decimal Calculator", "Binary Calculator", "Hexadecimal Calculator", "Big Decimal Calculator"},
            {"Help", "Decimal", "Binary", "Hexadecimal", "Big Decimal"}, {"About"}};
}

class MainWindow extends JFrame implements IDisplays {

    public void displays() {
        ArrayList<IConverter> methodList= new ArrayList<>();
        methodList.add(new DecimalConverter());
        methodList.add(new BinaryConverter());
        methodList.add(new HexadecimalConverter());
        methodList.add(new HexadecimalConverter());

        //List<Consumer<String>> methodList= new ArrayList<>();
        //Create Window
        JPanel mainPanel = new JPanel();
        setTitle("Pycharm Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        //Create main Panel
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridLayout(2, 1));
        add(mainPanel);
        JOptionPane.showMessageDialog(null, "Use the drop down menu to navigate the calculator." + "\n" +
                "This calculator currently cannot handle negative values. For division clear the text box before continuing.");


        //Create MenuBar
        ArrayList menuItemList = new ArrayList<JMenuItem>();
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu(menuBarString[0][0]);
        menuBar.add(menu);
        for (int j = 1; j < menuBarString[0].length; j++) {
            JMenuItem menuItem = new JMenuItem(menuBarString[0][j]);
            menu.add(menuItem);
            menuItemList.add(menuItem);
            int finalJ = j;
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = menuBarString[1][finalJ];
                    mainPanel.removeAll();
                    new calculatorView().displays(mainPanel, calcButtonChoice[finalJ], methodList.get(finalJ-1), name);
                    setVisible(true);
                }
            });
        }

        setJMenuBar(menuBar);
        setVisible(true);
    }
}
class calculatorView implements IDisplays{
    private IConverter converter;

    public void displays(JPanel mainPanel,String[] s, IConverter converter, String name) {

        this.converter = converter;


        //Make Display for input and previous calculations

        JPanel displayPanel = new JPanel();
        //Split into two
        displayPanel.setLayout(new GridLayout(2 ,1));

        //Add the Display to the main panel
        mainPanel.add(displayPanel);

        //Create the panel for input and history
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLACK);
        displayPanel.add(inputPanel);

        //Input Entry Display
        JTextField display = new JTextField(35);
        display.setEditable(false);
        inputPanel.add(display);

        //Input History Display
        JTextPane inputHistoryPane = new JTextPane();
        inputHistoryPane.setEditable(false);
        inputHistoryPane.setBackground(Color.LIGHT_GRAY);
        displayPanel.add(inputHistoryPane);



        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 2));
        ArrayList<JButton> buttonArrayList = new ArrayList<>(s.length);
        for (int i = 0; i < s.length; i++) {
            JButton button = new JButton(s[i]);
            numberPanel.add(button);
            buttonArrayList.add(button);
        }
        buttonPanel.add(numberPanel);
        //Create the action listener

        //Simple Case Action Listener.
        for (int i = 0; i < s.length; i++) {
            JButton j = buttonArrayList.get(i);
            int finalI = i;
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    display.setText(display.getText() + s[finalI]);
                }
            });
        }
        JPanel oPPanel = new JPanel();
        oPPanel.setLayout(new GridLayout(1,2));
        ArrayList<JButton> buttonOPArrayList = new ArrayList<>(operations.length);
        for (int i = 0; i < operations.length; i++) {
            JButton button = new JButton(operations[i]);
            oPPanel.add(button);
            buttonOPArrayList.add(button);
        }
//Equals

        (buttonOPArrayList.get(0)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] str = Calculation.Convert(display.getText());
                String answer = Calculation.convertEquation(converter, str[0],str[1],str[2],Integer.parseInt(str[3]),Integer.parseInt(str[4]));
                String prevAnswer = display.getText();
                display.setText(answer);
                if(name.equals("Decimal")){
                    inputHistoryPane.setText(inputHistoryPane.getText() + "\n" + name + ": " + prevAnswer + " = " + answer);
                }else {
                    inputHistoryPane.setText(inputHistoryPane.getText() + "\n" + name + ": " + prevAnswer + " = " + answer + "\n" +
                            "Decimal: " + (converter.ToDecimal(str[3])) * (converter.ToDecimal(str[0])) + str[2].replace("\\", "") + (converter.ToDecimal(str[4])) * (converter.ToDecimal(str[1])) +
                            " = " + ((converter.ToDecimal(str[3])) * (converter.ToDecimal(str[4]))) * (converter.ToDecimal(answer)));
                }
            }
        });

//Clear
        buttonOPArrayList.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("");
            }
        });
        buttonPanel.add(oPPanel);
        mainPanel.add(buttonPanel);


    }
    @Override
    public void displays() {
    }
}
