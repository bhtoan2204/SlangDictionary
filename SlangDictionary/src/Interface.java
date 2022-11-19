import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interface {
    final JFrame frame = new JFrame();
    final JPanel listEdit = new JPanel();
    final JPanel listFunc = new JPanel();
    final JPanel mainBoard = new JPanel();
    public static Data data = new Data();
    public void generateMainBoard(){
        listEdit.setLayout(new FlowLayout());
        JButton addBtn = new JButton("ADD");
        addBtn.setPreferredSize(new Dimension(100, 50));

        JButton delBtn = new JButton("DELETE");
        delBtn.setPreferredSize(new Dimension(100, 50));

        JButton editBtn = new JButton("EDIT");
        editBtn.setPreferredSize(new Dimension(100, 50));

        JButton ranBtn = new JButton("RANDOM");
        ranBtn.setPreferredSize(new Dimension(100, 50));

        listEdit.add(addBtn);
        listEdit.add(delBtn);
        listEdit.add(editBtn);
        listEdit.add(ranBtn);
        listEdit.setVisible(true);

        mainBoard.setSize(new Dimension(400, 400));
        mainBoard.setBackground(Color.gray);
        CardLayout cl = new CardLayout();
        mainBoard.setLayout(cl);

        listFunc.setLayout(new BoxLayout(listFunc, BoxLayout.Y_AXIS));

        JButton showHis = new JButton("History");

        Dimension d = new Dimension(200,50);

        showHis.setMaximumSize(new Dimension(d));

        JButton quiz1 = new JButton("Quiz 1");
        quiz1.setMaximumSize(new Dimension(d));

        JButton quiz2 = new JButton("Quiz 2");
        quiz2.setMaximumSize(new Dimension(d));

        JButton resetBtn = new JButton("Reset");
        resetBtn.setMaximumSize(new Dimension(d));

        listFunc.add(showHis);
        listFunc.add(quiz1);
        listFunc.add(quiz2);
        listFunc.add(resetBtn);
        listFunc.setVisible(true);

        JButton button1 = new JButton("Search by key");
        JButton button2 = new JButton("Search by definition");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());

        JButton okButton1 = new JButton("Confirm");
        JButton okButton2 = new JButton("Confirm");

        JTextField entry1 = new JTextField(30);
        JTextField entry2 = new JTextField(30);

        JLabel label1 = new JLabel(" Type here");
        JLabel label2 = new JLabel(" Type here");

        JTextArea result1 = new JTextArea(8, 45);
        JTextArea result2 = new JTextArea(8, 45);
        result1.setEditable(false);
        result2.setEditable(false);

        // Search by key
        panel1.add(label1);
        panel1.add(entry1);
        panel1.add(okButton1);
        panel1.add(button1);
        panel1.add(result1);


        // Search by definition
        panel2.add(label2);
        panel2.add(entry2);
        panel2.add(okButton2);
        panel2.add(button2);
        panel2.add(result2);

        mainBoard.add(panel1, "1");
        mainBoard.add(panel2, "2");

        cl.show(mainBoard, "1");


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainBoard, "2");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(mainBoard, "1");
            }
        });

        okButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = entry1.getText();
                System.out.println(input);
                if(data.checkExist(input)){
                    String[] result = data.searchByWord(input);
                    result1.setText(result[0]);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Your slang word you entered doesn't exist");
                }
            }
        });

        okButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result2.setText("");
            }
        });

        ranBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = data.getRandom();
                entry2.setText(temp);
                result1.setText(temp);
            }
        });

        frame.add(listEdit, BorderLayout.SOUTH);
        frame.add(listFunc, BorderLayout.EAST);
        frame.add(mainBoard, BorderLayout.CENTER);
    }
    public void createGUI() throws IOException{
        data.readData();
        frame.setTitle("Slang Dictionary");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateMainBoard();
        frame.pack();
        frame.setSize(600,300);
        frame.setVisible(true);
    }
    public static void main(String[] args) throws IOException{
        Interface gui = new Interface();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    gui.createGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
