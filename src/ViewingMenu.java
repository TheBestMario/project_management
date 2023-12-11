import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        project.updateAdjMatrix();
        DefaultTableModel model = new DefaultTableModel();
        table1.setEnabled(false);
        for(int i = 0; i<=project.getAdjMatrix().size(); i++){
            if (i == 0){
                model.addColumn("Tasks");
                continue;
            }
            model.addColumn(i);
            if (i == project.getAdjMatrix().size()){
                for (int j = 1; j<= project.getAdjMatrix().size();j++){
                    ArrayList<Integer> row = project.getAdjMatrix().get(j-1);
                    row.addFirst(j);
                    model.addRow(row.toArray());
                }
            }
        }

        table1.setModel(model);
    }
}
