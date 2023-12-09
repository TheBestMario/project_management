import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class viewProjectsMenu{
    private JPanel panel;
    private JButton backButton;
    private JLabel viewLabel;
    private JScrollPane jscrollPane;
    private JTable table1;
    private JButton deleteProjectButton;
    private DefaultTableModel table1Model;
    private final menuHandler menuHandler;
    private String[] columnNames;
    public viewProjectsMenu(menuHandler menuHandler){
        this.menuHandler = menuHandler;
        //menuHandler.getTableHandler().firstUpdateTable();
        //initialTableUpdate();
        columnNames = new String[]{"Project Name", "Description", "Num. Tasks"};
        table1Model = new DefaultTableModel(columnNames, 0);
        table1.setModel(table1Model);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuHandler.getFrame().setContentPane(menuHandler.getMainMenuGUI().getPanel());
            }
        });
    }

    public void updateTable(){
        //recieves project details from the handler to update visual table in this menu
        String [] project = menuHandler.getProjectHandler().getCurrentObjectAttributes();
        table1Model.addRow(project);
        System.out.println("updating project table...");
    }

    public JTable getTable1(){
        return table1;
    }

    public JPanel getPanel(){
        return panel;
    }
    public DefaultTableModel getModel(){
        return table1Model;
    }
    public void initialTableUpdate(){
        //checks for already existing data and adds to table.
        List<Project> list= menuHandler.getProjectHandler().getProjectsList();
        int size= menuHandler.getProjectHandler().countProjects();
        Project[] array = (Project[]) list.toArray();
        for (int item = 0; item < list.size(); item++){
            //System.out.println(list)
            String[] data = array[item].getAttributesArrayForm();

            if (size != 0) {
                table1Model.addRow(data);
                //System.out.println(data[item]);
            }

        }
    }
}

