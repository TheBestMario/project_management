import javax.swing.*;
import javax.swing.table.TableColumn;

public class ViewingMenu {
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel panel;
    private JTable table1;
    private JFrame frame;
    public ViewingMenu(MenuHandler menuHandler, Project project){
        frame = new JFrame();
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(420,420);
        frame.setLocationRelativeTo(menuHandler.getFrame());
        textField1.setText(project.getName());
        textArea1.setText(project.getDescription());
    }
    public void addcolumnToTable(int task, Boolean connection){
        TableColumn column = new TableColumn(task);
        table1.addColumn(column);
    }
}
