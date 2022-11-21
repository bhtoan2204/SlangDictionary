import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Interface {
    final JFrame frame = new JFrame();
    final JPanel listEdit = new JPanel();
    final JPanel listFunc = new JPanel();
    final JPanel mainBoard = new JPanel();
    final Data data = new Data();
    public void generateMainBoard() throws IOException{
        listEdit.setLayout(new FlowLayout());
        JButton addBtn = new JButton("ADD");
        addBtn.setPreferredSize(new Dimension(100, 50));

        JButton delBtn = new JButton("DELETE");
        delBtn.setPreferredSize(new Dimension(100, 50));

        JButton editBtn = new JButton("EDIT");
        editBtn.setPreferredSize(new Dimension(100, 50));

        JButton ranBtn = new JButton("RANDOM");
        ranBtn.setPreferredSize(new Dimension(100, 50));

        JButton saveBtn = new JButton("SAVE");
        saveBtn.setPreferredSize(new Dimension(100, 50));

        listEdit.add(addBtn);
        listEdit.add(delBtn);
        listEdit.add(editBtn);
        listEdit.add(ranBtn);
        listEdit.add(saveBtn);
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

        JPanel Result1 = new JPanel();
        JPanel Result2 = new JPanel();

        JTextArea result1 = new JTextArea(8, 45);
        JTextArea result2 = new JTextArea(8, 45);

        Result1.add(result1);
        Result2.add(result2);

        JScrollPane sb = new JScrollPane(result2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        result1.setEditable(false);
        result2.setEditable(false);

        // Search by key
        panel1.add(label1);
        panel1.add(entry1);
        panel1.add(okButton1);
        panel1.add(button1);
        panel1.add(Result1);


        // Search by definition
        panel2.add(label2);
        panel2.add(entry2);
        panel2.add(okButton2);
        panel2.add(button2);
        panel2.add(Result2);
        panel2.add(sb);

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
                try {
                    data.addSearchKeyToHis(input);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if(data.checkExist(input)){
                    String result = data.searchByWord(input);
                    result1.setText(result);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Your slang word you entered doesn't exist");
                }
            }
        });

        ranBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = data.getRandom();
                entry1.setText(temp);
                String def = data.searchByWord(temp);
                result1.setText(def);
                String[] subDef = def.split("\n");
                entry2.setText(subDef[0]);
                result2.setText(temp);

            }
        });
        okButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = entry2.getText();
                try {
                    data.addSearchValueToHis(temp);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String listKey = data.searchByDef(temp);
                if(listKey == ""){
                    JOptionPane.showMessageDialog(null, "Your definition you entered doesn't exist");
                }
                else{
                    result2.setText(listKey);
                }
            }
        });

        showHis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextArea textArea = new JTextArea(data.showHistory());
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    scrollPane.setPreferredSize( new Dimension( 500, 200 ) );
                    JOptionPane.showMessageDialog(null, scrollPane, "HISTORY",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    data.saveData();
                    JOptionPane.showMessageDialog(null, "SAVE ACTION SUCCESS");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    data.resetDict();
                    JOptionPane.showMessageDialog(null, "RESET ACTION SUCCESS");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame delFrame = new JFrame("Delete word");
                delFrame.setLayout(new FlowLayout());
                JLabel delLabel = new JLabel(" Type word here");
                JTextField delEntry = new JTextField(20);
                JButton delWord = new JButton("Confirm");

                delFrame.add(delLabel);
                delFrame.add(delEntry);
                delFrame.add(delWord);

                delWord.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String temp = delEntry.getText();
                        if(data.checkExist(temp)){
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure");
                            if (result == JOptionPane.YES_NO_OPTION){
                                data.delete(temp);
                                JOptionPane.showMessageDialog(null, "DELETE ACTION SUCCESS");
                                delFrame.dispatchEvent(new WindowEvent(delFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your key you entered doesn't exist");
                        }
                    }
                });

                editBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame editFrame = new JFrame();
                        editFrame.setLayout(new FlowLayout());
                        JLabel keyLabel = new JLabel("Type Key: ");
                        JLabel valueLabel = new JLabel("Type Value: ");

                        JTextField keyEntry = new JTextField(20);
                        JTextField valueEntry = new JTextField(20);

                        editFrame.add(keyLabel);
                        editFrame.add(keyEntry);
                        editFrame.add(valueLabel);
                        editFrame.add(valueEntry);

                        editFrame.setSize(300,300);
                        editFrame.setResizable(false);
                        editFrame.setVisible(true);
                    }
                });

                delFrame.setSize(300, 150);
                delFrame.setResizable(false);
                delFrame.setVisible(true);
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editFrame = new JFrame();
                editFrame.setLayout(new FlowLayout());
                JLabel keyLabel = new JLabel("Type Key: ");
                JLabel valueLabel = new JLabel("Type Value (, for each def): ");

                JTextField keyEntry = new JTextField(20);
                JTextField valueEntry = new JTextField(20);

                JButton confirmButton = new JButton("OK");

                editFrame.add(keyLabel);
                editFrame.add(keyEntry);
                editFrame.add(valueLabel);
                editFrame.add(valueEntry);
                editFrame.add(confirmButton);

                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String key = keyEntry.getText();
                        if(data.checkExist(key)){
                            String[] defs = valueEntry.getText().split(", ");
                            ArrayList<String> def = new ArrayList<>();
                            for(int i = 0;i < defs.length;i++){
                                def.add(defs[i]);
                            }
                            data.editWord(key,def);
                            JOptionPane.showMessageDialog(null, "EDIT ACTION SUCCESS");
                            editFrame.dispatchEvent(new WindowEvent(editFrame, WindowEvent.WINDOW_CLOSING));
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Your key you entered doesn't exist");
                        }
                    }
                });

                editFrame.setSize(300,200);
                editFrame.setTitle("EDIT");
                editFrame.setResizable(false);
                editFrame.setVisible(true);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame("ADD");
                addFrame.setLayout(new FlowLayout());
                JLabel keyLabel = new JLabel("Type Key: ");
                JLabel valueLabel = new JLabel("Type Value (, for each def): ");

                JTextField keyEntry = new JTextField(20);
                JTextField valueEntry = new JTextField(20);

                JButton confirmButton = new JButton("OK");

                addFrame.add(keyLabel);
                addFrame.add(keyEntry);
                addFrame.add(valueLabel);
                addFrame.add(valueEntry);
                addFrame.add(confirmButton);

                addFrame.setSize(300,200);
                addFrame.setTitle("ADD");
                addFrame.setResizable(false);
                addFrame.setVisible(true);

                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String temp = keyEntry.getText();
                        if (data.checkExist(temp)){
                            String[] option = {"Overwrite", "Duplicate", "Cancel"};
                            int check = JOptionPane.showOptionDialog(null, "Your word you entered is already existed", "Notification", JOptionPane.DEFAULT_OPTION, 0, null, option, option[0]);
                            if (check == 0){
                                String[] defs = valueEntry.getText().split(", ");
                                ArrayList<String> def = new ArrayList<>();
                                for(int i = 0;i < defs.length;i++){
                                    def.add(defs[i]);
                                }
                                data.editWord(keyEntry.getText(), def);
                                JOptionPane.showMessageDialog(null, "ADD ACTION SUCCESS");
                                addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
                            }
                            else if (check == 1){
                                String[] defs = valueEntry.getText().split(", ");
                                ArrayList<String> def = new ArrayList<>();
                                for(int i = 0;i < defs.length;i++){
                                    def.add(defs[i]);
                                }
                                data.duplicate(keyEntry.getText(), def);
                                JOptionPane.showMessageDialog(null, "ADD ACTION SUCCESS");
                                addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
                            }
                            else if (check == 2){
                                addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                        else{
                            String[] defs = valueEntry.getText().split(", ");
                            ArrayList<String> def = new ArrayList<>();
                            for(int i = 0;i < defs.length;i++){
                                def.add(defs[i]);
                            }
                            data.editWord(keyEntry.getText(), def);
                            JOptionPane.showMessageDialog(null, "ADD ACTION SUCCESS");
                            addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
                        }
                    }
                });
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
