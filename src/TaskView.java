import javax.swing.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class TaskView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextArea textArea1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private SpinnerDateModel spinnerModel1, spinnerModel2;

    public TaskView(MenuHandler menuHandler) {
        LocalDate today = menuHandler.getProjectHandler().getDateToday();
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        Date startLimit = (Date) menuHandler.getNewProjectMenu().getSpinner1().getValue();
        Date endLimit = (Date) menuHandler.getNewProjectMenu().getSpinner2().getValue();
        //sets spinner attributes
        this.spinnerModel1 = new SpinnerDateModel(
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()),startLimit,endLimit, Calendar.DAY_OF_MONTH);
        this.spinnerModel2 = new SpinnerDateModel(
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()),startLimit,endLimit, Calendar.DAY_OF_MONTH);
        spinner1.setModel(spinnerModel1);
        spinner2.setModel(spinnerModel2);
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner1,"dd/MM/yy");
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2,"dd/MM/yy");
        spinner1.setEditor(editor1);
        spinner2.setEditor(editor2);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(textField1.getText().isEmpty())){

                    ProjectHandler projectHandler = menuHandler.getProjectHandler();
                    //makes template task for current project
                    Project.Task task = projectHandler.getProjectsList().getLast().addTask();
                    //adds task info into UI elements
                    //adds task made to temp list with attributes Name, Description, Parent(parent's ID)
                    projectHandler.getProjectsList().getLast().getTaskList().getLast().setName(nameInput().getText());
                    projectHandler.getProjectsList().getLast().getTaskList().getLast().setDesc(descInput().getText());
                    menuHandler.getNewProjectMenu().updateModel(task);

                    Date input = (Date) spinner1.getValue();
                    Date input2 = (Date) spinner2.getValue();
                    LocalDate start = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate end = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    task.setDates(start, end);
                    dispose();
                }
            }
        });

        //used intellij to generate this code
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public JTextArea descInput(){
        return textArea1;
    }
    public JTextField nameInput(){
        return textField1;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
