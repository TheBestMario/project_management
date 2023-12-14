import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

public class ViewProjectsMenu implements Menu{
    private JPanel panel;
    private JButton backButton;
    private JLabel viewLabel;
    private JScrollPane jscrollPane;
    private JTable table1;
    private JButton deleteProjectButton;
    private JButton viewButton;
    private final DefaultTableModel table1Model;
    private final MenuHandler menuHandler;

    private Integer tableSelectionID, row;
    public ViewProjectsMenu(MenuHandler menuHandler){
        this.menuHandler = menuHandler;
        //menuHandler.getTableHandler().firstUpdateTable();
        //initialTableUpdate();
        String[] columnNames = new String[]{"ID", "Project Name", "Description", "Num. Tasks","Start date","End date"};
        table1Model = new DefaultTableModel(columnNames, 0);
        table1.setModel(table1Model);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //finds the row of data selected and makes a vector, then finds ID from vector.
                Vector<String> selection = new Vector<String>(table1Model.getDataVector().
                        elementAt(table1.convertRowIndexToModel(table1.getSelectedRow())));
                tableSelectionID = Integer.valueOf(selection.getFirst());
                row = table1.getSelectedRow();
                if (selection == null) return;
                else{
                    viewButton.setEnabled(true);
                    deleteProjectButton.setEnabled(true);
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Project p: menuHandler.getProjectHandler().getProjectsList()){
                    if (tableSelectionID.equals(p.getId())){
                        ViewingMenu viewMenu = new ViewingMenu(menuHandler,p);
                        break;
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuHandler.switchToMainMenu();
            }
        });
        deleteProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuHandler.getProjectHandler().removeFromList(tableSelectionID);
                table1Model.removeRow(row);
                row = null;
                tableSelectionID = null;
                viewButton.setEnabled(false);
                deleteProjectButton.setEnabled(false);
            }
        });
    }

    public void addToTable(){
        //recieves project details from the handler to update visual table in this menu
        String [] project = menuHandler.getProjectHandler().getCurrentObjectAttributes();
        System.out.println(project);
        table1Model.addRow(project);
    }

    public void updateNameTable(Integer ID, String newName){
        for (int i = 0; i<table1Model.getRowCount(); i++){
            if (Integer.valueOf((String) table1Model.getValueAt(i,0)) == ID){
                table1Model.setValueAt(newName, i, 1);
                table1Model.fireTableDataChanged();
                table1.repaint();
            }
        }
    }

    public void updateDescTable(Integer ID, String newDesc){
        for (int i = 0; i<table1Model.getRowCount(); i++){
            if (Integer.valueOf((String) table1Model.getValueAt(i,0)) == ID){
                table1Model.setValueAt(newDesc, i, 2);
                table1Model.fireTableDataChanged();
                table1.repaint();
            }
        }
    }

    @Override
    public void display() {

        menuHandler.getFrame().revalidate();
        menuHandler.getFrame().repaint();
    }

    @Override
    public void hide() {

    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public void initialTableUpdate(){
        //checks for already existing data and adds to table.
        List<Project> list= menuHandler.getProjectHandler().getProjectsList();
        int size= menuHandler.getProjectHandler().getProjectsList().size();
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

