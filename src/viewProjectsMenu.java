import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewProjectsMenu{
    private JPanel panel;
    private JButton backButton;
    private JLabel viewLabel;
    private JScrollPane jscrollPane;

    private JTable table1;
    private JButton deleteProjectButton;

    private final Builder builder;
    public viewProjectsMenu(Builder builder){
        this.builder = builder;
        builder.getTableHandler().firstUpdateTable();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                builder.getFrame().setContentPane(builder.getMainMenuGUI().getPanel());
            }
        });

    }

    private void updateTable(){
        //recieves project details from the handler to update visual table in this menu
        table1.setModel(builder.getTableHandler().getmodel());
        System.out.println("updating table...");
    }

    public JTable getTable1(){
        return table1;
    }

    public JPanel getPanel(){
        updateTable();
        return panel;
    }
}

