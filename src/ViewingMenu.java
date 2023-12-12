import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class ViewingMenu {
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel panel;
    private JTable table1;
    private JPanel panel2;
    private JFrame frame;

    public IntervalCategoryDataset getCategoryDataset(Project project) {

        TaskSeries series1 = new TaskSeries("Estimated Date");
        for (Project.Task task: project.getTaskList()){
            series1.add(new Task(task.getName() + " " + "(" + task.getId() + ")",
                    Date.from(task.getDates()[0].atStartOfDay().toInstant(ZoneOffset.UTC)),
                    Date.from(task.getDates()[1].atStartOfDay().toInstant(ZoneOffset.UTC))));
        }
        System.out.println(project.getTaskList());

        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series1);
        return dataset;
    }
    public ViewingMenu(MenuHandler menuHandler, Project project){
        frame = new JFrame();
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(800,420);
        frame.setLocationRelativeTo(menuHandler.getFrame());
        textField1.setText(project.getName());
        textArea1.setText(project.getDescription());
        project.updateAdjMatrix();
        DefaultTableModel model = new DefaultTableModel();
        table1.setEnabled(false);
        IntervalCategoryDataset dataset = getCategoryDataset(project);

        JFreeChart chart = ChartFactory.createGanttChart(
                project.getName(), // Chart title
                "Tasks", // X-Axis Label
                "Timeline", // Y-Axis Label
                dataset);
        ChartPanel chartpanel = new ChartPanel(chart);
        panel2.removeAll();
        panel2.add(chartpanel);
        panel2.validate();
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
