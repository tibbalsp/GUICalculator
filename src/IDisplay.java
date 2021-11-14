/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface ICalculator{
    String[] getOperators();
    String[] getNumbers();
    Integer parseString(String s);
}

interface IDisplay {
    void displays();

    String[] binaryButtons = {"0", "1", "=", "+", "-", "*", "\u00F7", "."};
    String[] hexButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "=", "+", "-", "*", "\u00F7"};
    String[] bigDButtons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", "*", "\u00F7", "\u221A", "\u33A1", "!", "MOD", "LCD", "GCD"};
    JPanel mainPanel = new JPanel();


}

class MainWindow extends JFrame implements IDisplay {
    Font font1 = new Font("SansSerif", Font.BOLD, 20);
    Font font2 = new Font("SansSerif", Font.BOLD, 48);

    public void addCharacter(char c){
        System.out.println(c);
    }

//Add button loop
    public void displays() {
        for (int i = 0; i < binaryButtons.length; i++) {
            JButton button = new JButton(binaryButtons[i]);
            mainPanel.add button

        }




        //Create Window
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
        JMenuBar menuBar = new JMenuBar();
        //Create menus for bar
        JMenu calcMenu = new JMenu("Calculator");
        JMenu calcHelpMenu = new JMenu("Help");
        JMenu calcAboutMenu = new JMenu("About");
        //Add the menus to bar
        menuBar.add(calcMenu);
        menuBar.add(calcHelpMenu);
        menuBar.add(calcAboutMenu);
        //Create the items for Calc menu
        JMenuItem nCalc = new JMenuItem("Decimal Calculator");
        JMenuItem bCalc = new JMenuItem("Binary Calculator");
        JMenuItem hCalc = new JMenuItem("Hexadecimal Calculator");
        JMenuItem bigDCalc = new JMenuItem("Big Decimal Calculator");
        //Add
        calcMenu.add(nCalc);
        calcMenu.add(bCalc);
        calcMenu.add(hCalc);
        calcMenu.add(bigDCalc);
        //Create the Help menu items
        JMenuItem helpB = new JMenuItem("Binary");
        JMenuItem helpH = new JMenuItem("Hexadecimal");
        JMenuItem helpBigD = new JMenuItem("Big Decimal");
        //Add help menu items
        calcHelpMenu.add(helpB);
        calcHelpMenu.add(helpH);
        calcHelpMenu.add(helpBigD);
        //Create the About menu
        JMenuItem about = new JMenuItem("About");
        //Add the about menu items
        calcAboutMenu.add(about);
        //Create the Menu Bar
        setJMenuBar(menuBar);
        add(mainPanel);

        //Create the action listeners
        nCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DecimalDisplay().displays();
                setVisible(true);
            }
        });

        bCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BinaryDisplay().displays();
                setVisible(true);
            }
        });

        hCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new HexadecimalDisplay().displays();
                setVisible(true);
            }
        });

        bigDCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  new BigDecimalDisplay().displays();
                setVisible(true);
            }
        });
        setVisible(true);
    }
}

class DecimalDisplay extends MainWindow implements IDisplay {

    public void displays() {
        //Make Display for input and previous calculations
        JPanel displayPanel = new JPanel();

        //Split into two
        displayPanel.setLayout(new GridLayout(2, 1));

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
        inputHistoryPane.setBackground(Color.LIGHT_GRAY);
        displayPanel.add(inputHistoryPane);

        //Add buttons
        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");

        JButton bC = new JButton("C");
        JButton bEquals = new JButton("=");
        JButton bAdd = new JButton("+");
        JButton bMinus = new JButton("-");
        JButton bMultiply = new JButton("*");
        JButton bDivide = new JButton("\u00F7");

        //Add the buttons to the main panel
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(1, 2));

        //Operations
        JPanel buttonOpPanel = new JPanel();
        buttonPanel.add(buttonOpPanel);
        buttonOpPanel.setLayout(new GridLayout(5, 1));
        //Numbers
        JPanel buttonNumPanel = new JPanel();
        buttonPanel.add(buttonNumPanel);
        buttonNumPanel.setLayout(new GridLayout(5, 2));


        //Add Operation Button Components
        buttonOpPanel.add(bAdd);
        buttonOpPanel.add(bMinus);
        buttonOpPanel.add(bMultiply);
        buttonOpPanel.add(bDivide);
        buttonOpPanel.add(bC);
        buttonOpPanel.add(bEquals);
        //Add number button components
        buttonNumPanel.add(b0);
        buttonNumPanel.add(b1);
        buttonNumPanel.add(b2);
        buttonNumPanel.add(b3);
        buttonNumPanel.add(b4);
        buttonNumPanel.add(b5);
        buttonNumPanel.add(b6);
        buttonNumPanel.add(b7);
        buttonNumPanel.add(b8);
        buttonNumPanel.add(b9);

        // Implement action listeners
        bEquals.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          String answer = "";
                                          String previousText = display.getText();

                                          if (previousText.contains("+")) {
                                              String[] input = previousText.split(" \\+ ");

                                              answer = ;

                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains(" - ")) {
                                              String[] input = previousText.split(" \\- ");
                                              answer = Integer.toString(Integer.parseInt(input[0]) - Integer.parseInt(input[1]));
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains("*")) {
                                              String[] input = previousText.split(" \\* ");
                                              answer = Integer.toString(Integer.parseInt(input[0]) * Integer.parseInt(input[1]));
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains("\u00F7")) {
                                              String[] input = previousText.split(" \u00F7 ");
                                              String tempAnswer = Integer.toString(Integer.parseInt(input[0]) / Integer.parseInt(input[1]));
                                              String remainder = Integer.toString(Integer.parseInt(input[0]) % Integer.parseInt(input[1]));
                                              answer = tempAnswer + " R " + remainder;
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else {
                                              display.setText(previousText);
                                          }
                                          display.setText(answer);
                                      }
                                  }
        );
        bC.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     display.setText("");
                                 }
                             }
        );
        bAdd.addActionListener(new ActionListener() {
                                   @Override
                                   public void actionPerformed(ActionEvent e) {
                                       String previousText = display.getText();
                                       display.setText(previousText + " + ");
                                   }
                               }
        );
        bMinus.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent e) {
                                         String previousText = display.getText();
                                         display.setText(previousText + " - ");

                                     }
                                 }
        );
        bMultiply.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String previousText = display.getText();
                                            display.setText(previousText + " * ");
                                        }
                                    }
        );
        bDivide.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          String previousText = display.getText();
                                          display.setText(previousText + " \u00F7 ");
                                      }
                                  }
        );
// 1 - 9 Action Listeners
        b0.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "0");
                                 }
                             }
        );
        b1.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "1");
                                 }
                             }
        );
        b2.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "2");
                                 }
                             }
        );
        b3.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "3");
                                 }
                             }
        );
        b4.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "4");
                                 }
                             }
        );
        b5.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "5");
                                 }
                             }
        );
        b6.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "6");
                                 }
                             }
        );
        b7.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "7");
                                 }
                             }
        );
        b8.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "8");
                                 }
                             }
        );
        b9.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                     String previousText = display.getText();
                                     display.setText(previousText + "9");
                                 }
                             }
        );
    }
}

class BinaryDisplay extends MainWindow implements IDisplay {
    public void displays() {
        //Make Display for input and previous calculations
        JPanel displayPanel = new JPanel();

        //Split into two
        displayPanel.setLayout(new GridLayout(2, 1));

        //Add the Display to the main panel
        mainPanel.add(displayPanel);

        //Create the panel for input and history
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 3));
        inputPanel.setBackground(Color.BLACK);
        displayPanel.add(inputPanel);
        JTextField display = new JTextField();
        //Input Entry Display

        JTextField display1 = new JTextField(20);
        JTextField display2 = new JTextField(20);
        //display1.setFont(font1);
        //display2.setFont(font1);
        display1.setEditable(false);
        display2.setEditable(false);
        inputPanel.add(display1);
        inputPanel.add(display2);
        String[] operations = {"+", "-", "*", "\u00F7"};
        JComboBox<String> op = new JComboBox<String>(operations);
        op.setFont(font2);
        //Add Entry Display
        inputPanel.add(display1);
        inputPanel.add(op);
        inputPanel.add(display2);

        String left = display1.getText();
        String right = display2.getText();


        //Input History Display
        JTextPane inputHistoryPane = new JTextPane();
        inputHistoryPane.setBackground(Color.LIGHT_GRAY);
        displayPanel.add(inputHistoryPane);


        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton bC = new JButton("C");
        JButton bEquals = new JButton("=");


        //Add the buttons to the main panel
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(2, 1));

        //Numbers
        JPanel buttonNumPanel = new JPanel();
        buttonPanel.add(buttonNumPanel);
        buttonNumPanel.setLayout(new GridLayout(1, 2));

        //Operations
        JPanel buttonOpPanel = new JPanel();
        buttonPanel.add(buttonOpPanel);
        buttonOpPanel.setLayout(new GridLayout(1, 2));

        //Add Operation Button Components

        buttonOpPanel.add(bC);
        buttonOpPanel.add(bEquals);
        //Add number button components
        buttonNumPanel.add(b0);
        buttonNumPanel.add(b1);

// Implement action listeners
        display2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                bC.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             display2.setText("");
                                         }
                                     }
                );

// 1 - 9 Action Listeners
                b0.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String previousText = display2.getText();
                                             display2.setText(previousText + "0");
                                         }
                                     }
                );
                b1.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String previousText = display2.getText();
                                             display2.setText(previousText + "1");
                                         }
                                     }
                );

            }


            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        display1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // Your code here
                bC.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             display1.setText("");
                                         }
                                     }
                );

// 1 - 9 Action Listeners
                b0.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String previousText = display1.getText();
                                             display1.setText(previousText + "0");
                                         }
                                     }
                );
                b1.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             String previousText = display1.getText();
                                             display1.setText(previousText + "1");
                                         }
                                     }
                );

            }


            @Override
            public void focusLost(FocusEvent e) {
                // Your code here
                display1.setText("");
            }
        });

        bEquals.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          String answer = "";
                                          int negCount = 0;
                                          String previousText = display.getText();
                                          for (int i = 0; i < previousText.length(); i++) {
                                              if (previousText.charAt(i) == '-') {
                                                  negCount++;
                                              }

                                          }
                                          String[] tokens = previousText.split("\\+| \\- |\\*|\\\u00F7|\\=");
                                          String left = tokens[0];
                                          String right = tokens[1];

                                          System.out.println(left.replaceAll("-", "").replaceAll("\\s", ""));
                                          System.out.println(right.replaceAll("-", "").replaceAll("\\s", ""));

                                          if (previousText.contains("+")) {
                                              String[] input = previousText.split("\\+");

                                              answer = Integer.toString(Integer.valueOf(input[0]) + Integer.valueOf(input[1]));

                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains("-")) {
                                              String[] input = previousText.split("\\-");
                                              answer = Integer.toString(Integer.parseInt(input[0]) - Integer.parseInt(input[1]));
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains("*")) {
                                              String[] input = previousText.split(" \\* ");
                                              answer = Integer.toString(Integer.parseInt(input[0]) * Integer.parseInt(input[1]));
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else if (previousText.contains("\u00F7")) {
                                              String[] input = previousText.split(" \u00F7 ");
                                              String tempAnswer = Integer.toString(Integer.parseInt(input[0]) / Integer.parseInt(input[1]));
                                              String remainder = Integer.toString(Integer.parseInt(input[0]) % Integer.parseInt(input[1]));
                                              answer = tempAnswer + " R " + remainder;
                                              display.setText(previousText + " = " + answer);
                                              String textComp = inputHistoryPane.getText();
                                              inputHistoryPane.setText(previousText + " = " + answer + "\n" + textComp);
                                          } else {
                                              display.setText(previousText);
                                          }
                                          display.setText(answer);
                                      }
                                  }
        );

    }
}
class HexadecimalDisplay extends JFrame implements IDisplay {
    public void displays() {
    }
}

class BigDecimalDisplay extends JFrame implements IDisplay {
    public void displays() {

    }
}


 */