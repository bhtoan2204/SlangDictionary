import javax.swing.*;
import java.awt.*;

public class Interface {
    final JFrame frame = new JFrame();
    final JPanel listEdit = new JPanel();
    final JPanel listFunc = new JPanel();
    final JPanel mainBoard = new JPanel();
    public void generateListEdit(){
        listEdit.setLayout(new FlowLayout());
        JButton addBtn = new JButton("ADD");
        addBtn.setPreferredSize(new Dimension(150, 50));
        JButton delBtn = new JButton("DELETE");
        delBtn.setPreferredSize(new Dimension(150, 50));
        JButton editBtn = new JButton("EDIT");
        editBtn.setPreferredSize(new Dimension(150, 50));
        JButton ranBtn = new JButton("RANDOM");
        ranBtn.setPreferredSize(new Dimension(150, 50));
        listEdit.add(addBtn);
        listEdit.add(delBtn);
        listEdit.add(editBtn);
        listEdit.add(ranBtn);
        listEdit.setVisible(true);
    }
    public void generateListFunc(){
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
    }
    public void generateMainBoard(){
        mainBoard.setVisible(true);
    }
    public void generateAllPanel(){
        generateListEdit();
        generateListFunc();
        generateMainBoard();
    }
    public void addAll(){
        frame.add(listEdit, BorderLayout.SOUTH);
        frame.add(listFunc, BorderLayout.EAST);
        frame.add(mainBoard, BorderLayout.CENTER);
    }
    public void createGUI(){
        frame.setTitle("Slang Dictionary");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        generateAllPanel();
        addAll();
        frame.setVisible(true);
    }
    public void start(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                createGUI();
            }
        });
    }
    public static void main(String[] args){
        Interface gui = new Interface();
        gui.start();
    }
}
