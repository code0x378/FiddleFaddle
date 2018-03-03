package com.fattysmurff.fiddlefaddle.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class MainFrame extends JFrame {
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenuItem exitMenuItem;
    private final JMenu helpMenu;
    private final JMenuItem aboutMenuItem;
    private final JLabel levelLabel;
    private final JLabel scoreLabel;
    private final JTextArea questionTextArea;
    private final JPanel answerPanel;
    private final JRadioButton answerOneRadioButton;
    private final JRadioButton answerTwoRadioButton;
    private final JRadioButton answerThreeRadioButton;
    private final JRadioButton answerFourRadioButton;
    private final RoundButton resetButton;
    private final RoundButton nextButton;
    private final ButtonGroup group;

    Game game = new Game();

    public MainFrame() throws HeadlessException {
        setTitle("Fiddle Faddle");
        setBackground(Color.white);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException ignored) {
        }
        UIManager.put("MenuBar.background", Color.decode("#2aa39b"));
        UIManager.put("Menu.foreground", Color.white);

        menuBar = new JMenuBar();
        menuBar.setBorder(new EmptyBorder(5,5,5,5));
        fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                exitApp();
            }
        });
        //exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);

        helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Fiddle Faddle\n\nCopyright 2017 FattySmurff");
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        MainPanel mainPanel = new MainPanel();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Fiddle Faddle");
        titleLabel.setFont(new Font(titleLabel.getName(), Font.BOLD, 16));
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);
        titlePanel.setPreferredSize(new Dimension(300, 35));

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel scorePanel = new JPanel();
        scorePanel.setOpaque(false);
        scorePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setMaximumSize(new Dimension(100000, 45));
        levelLabel = new JLabel("Level 1");
        levelLabel.setHorizontalAlignment(SwingConstants.LEFT);
        scoreLabel = new JLabel("Score 0 / 1");
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scorePanel.add(levelLabel, BorderLayout.WEST);
        scorePanel.add(scoreLabel, BorderLayout.EAST);
        centerPanel.add(scorePanel);

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setOpaque(false);
        questionTextArea = new JTextArea("Question");
        questionTextArea.setEditable(false);
        questionTextArea.setLineWrap(true);
        questionTextArea.setFont(new Font(titleLabel.getName(), Font.BOLD, 12));
        questionPanel.add(questionTextArea);
        questionTextArea.setMinimumSize(new Dimension(300, 70));
        centerPanel.add(questionPanel);

        answerPanel = new JPanel();
        answerPanel.setOpaque(false);
        answerPanel.setBorder(BorderFactory.createTitledBorder("Answers"));
        answerPanel.setLayout(new GridLayout(4, 0));

        RadioButtonActionListener radioButtonActionListener = new RadioButtonActionListener();
        group = new ButtonGroup();
        answerOneRadioButton = new JRadioButton("Answer 1");
        answerOneRadioButton.setOpaque(false);
        answerOneRadioButton.setFont(new Font(titleLabel.getName(), Font.PLAIN, 12));
        answerOneRadioButton.addActionListener(radioButtonActionListener);
        group.add(answerOneRadioButton);

        answerTwoRadioButton = new JRadioButton("Answer 2");
        answerTwoRadioButton.setFont(new Font(titleLabel.getName(), Font.PLAIN, 12));
        answerTwoRadioButton.setOpaque(false);
        answerTwoRadioButton.addActionListener(radioButtonActionListener);
        group.add(answerTwoRadioButton);

        answerThreeRadioButton = new JRadioButton("Answer 3");
        answerThreeRadioButton.setFont(new Font(titleLabel.getName(), Font.PLAIN, 12));
        answerThreeRadioButton.setOpaque(false);
        answerThreeRadioButton.addActionListener(radioButtonActionListener);
        group.add(answerThreeRadioButton);

        answerFourRadioButton = new JRadioButton("Answer 4");
        answerFourRadioButton.setFont(new Font(titleLabel.getName(), Font.PLAIN, 12));
        answerFourRadioButton.setOpaque(false);
        answerFourRadioButton.addActionListener(radioButtonActionListener);
        group.add(answerFourRadioButton);

        answerPanel.add(answerOneRadioButton);
        answerPanel.add(answerTwoRadioButton);
        answerPanel.add(answerThreeRadioButton);
        answerPanel.add(answerFourRadioButton);
        answerPanel.setPreferredSize(new Dimension(300, 50000));
        centerPanel.add(answerPanel);

        mainPanel.add(centerPanel);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        resetButton = new RoundButton("Reset", 10,20);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                resetGame();
            }
        });
        nextButton = new RoundButton("Next", 13, 20);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                nextQuestion();
            }
        });

        buttonPanel.add(resetButton, BorderLayout.WEST);
        buttonPanel.add(nextButton, BorderLayout.EAST);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);

        displayQuestion(game.getQuestion());
    }

    private void nextQuestion() {
        updateScore();
        displayQuestion(game.getNextQuestion());
    }

    private void resetGame() {
        game = new Game();
        levelLabel.setText( String.format("Level: %s", game.getLevel()));
        updateScore();
        displayQuestion(game.getQuestion());
    }

    private void displayQuestion(Question question) {
        questionTextArea.setText(question.getQuestion());
        answerOneRadioButton.setText(question.getAnswers().get(0));
        answerTwoRadioButton.setText(question.getAnswers().get(1));
        answerThreeRadioButton.setText(question.getAnswers().get(2));
        answerFourRadioButton.setText(question.getAnswers().get(3));

        Enumeration<AbstractButton> enumeration = group.getElements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().setEnabled(true);
        }
        group.clearSelection();
    }

    private void updateScore() {
        scoreLabel.setText( String.format("Score: %s / %s", game.getCorrect(), game.getTotal()));
    }

    public void exitApp(){
        setVisible(false);
        dispose();
        System.exit(0);
    }

    class RadioButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JRadioButton button = (JRadioButton) event.getSource();

            if (button == answerOneRadioButton) {
                displayResult(game.isCorrectAnswer(0));
            } else if (button == answerTwoRadioButton) {
                displayResult(game.isCorrectAnswer(1));
            } else if (button == answerThreeRadioButton) {
                displayResult(game.isCorrectAnswer(2));
            } else if (button == answerFourRadioButton) {
                displayResult(game.isCorrectAnswer(3));
            }
        }
    }

    private void displayResult(boolean result)
    {
        if (result) {
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Correct");
            answerPanel.setBorder(titledBorder);
        } else {
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Wrong");
            answerPanel.setBorder(titledBorder);
        }
        updateScore();
        Enumeration<AbstractButton> enumeration = group.getElements();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().setEnabled(false);
        }
        //answerPanel.setEnabled(false);
    }

    /************************************************
     * UI COMPONENTS
     ************************************************/

    private class MainPanel extends JPanel {

        public MainPanel() {
            setMinimumSize(new Dimension(300, 450));
            setBackground(Color.white);
        }
    }

    private class RoundButton extends JButton{

        private String text;
        int x, y;

        public RoundButton(String text, int x, int y) {
            this.x = x;
            this.y = y;
            this.text= text;
            setPreferredSize(new Dimension(60, 30));
            setForeground(Color.WHITE);
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(10,10);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(Color.decode("#2aa39b"));
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            graphics.setPaint(Color.white);
            graphics.drawString(this.text, this.x, this.y);
        }
    }


    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
    
}
