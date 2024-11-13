import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class EmployeeUI implements ActionListener {

    public static String div = "------------------------------------------";
    public static List<Employee> employees = new ArrayList<Employee>();
    public static JTextArea display;
    public static JButton[] buttons = new JButton[3];
    public static JLabel[] subTitles = new JLabel[6];
    public static JTextField[] inputs = new JTextField[6];

    public static void main(String[] args) {

        // Fonts
        Font titleFont = new Font("Courier New", 1, 24);
        Font subFont = new Font("Courier New", 1, 16);

        // Frame
        JFrame frame = new JFrame("Журнал работников");
        frame.setSize(550, 703);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Container
        JPanel container = new JPanel();
        container.setLayout(null);
        frame.setContentPane(container);

        // Title
        JLabel title = new JLabel("Список работников");
        title.setFont(titleFont);
        title.setForeground(Color.blue);
        title.setBounds(160, 10, 350, 24);

        // Lablels and text fields
        for (int i = 0; i < subTitles.length; i++) {
            subTitles[i] = new JLabel();
            subTitles[i].setFont(subFont);
            subTitles[i].setBounds(5, 50 + (i * 35), 190, 16);
        }
        subTitles[0].setText("Табельный номер ID#: ");
        subTitles[1].setText("Имя: ");
        subTitles[2].setText("Фамилия: ");
        subTitles[3].setText("Отчество: ");
        subTitles[4].setText("Отдел: ");
        subTitles[5].setText("Зарплата: ");

        for (int i = 0; i < subTitles.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(160, 47 + (35 * i), 150, 22);
        }

        // Buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(new EmployeeUI());
            buttons[i].setBounds(330, 47 + (35 * i), 200, 20);
        }
        buttons[0].setText("Добавить нового работника");
        buttons[1].setText("Удалить (по номеру ID#)");
        buttons[2].setText("Показать всех");

        // Text area
        display = new JTextArea();
        display.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setBounds(5, 255, 525, 410);

        // Adding everything
        container.add(title);
        container.add(scrollPane);
        // Since # of textfields will always equal # of subtitles, we can use the
        // max value of subtitles for the loop
        for (int i = 0; i < subTitles.length; i++) {
            container.add(subTitles[i]);
            container.add(inputs[i]);
        }
        for (int i = 0; i < buttons.length; i++) {
            container.add(buttons[i]);
        }

        // Extras
        frame.toFront();
        frame.setVisible(true);
        try {
            FileWriter fileWriter = new FileWriter("EmployeeBase.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            for (int i = 0; i < employees.size(); i++)
            bufferedWriter.write(div + "\nID#: " + employees.get(i).getId() + "\nИмя: " + employees.get(i).getFirstName()
                    + "\nФамилия: " + employees.get(i).getLastName() + "\nОтчество: " + employees.get(i).getSurName()
                    + "\nОтдел: Руб. " + employees.get(i).getDepartment()
                    + "\nЗарплата: Руб. " + employees.get(i).getSalary() + "\n");
            
            bufferedWriter.newLine();
            bufferedWriter.write("Еще одна строка");


            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(buttons[0])) {
            // Pass boolean to check if the program should continue or not
            boolean pass = true;
            // Loop to check if all textfields have data
            for (int i = 0; i < inputs.length; i++) {
                if (inputs[i].getText().equals("")) {
                    display.setText("Ошибка: необходимо заполнить все данные.");
                    pass = false;
                }
            }

            // If the user passed, the program continues
            if (pass == true) {
                // Checking if ID# already exists
                if (employees.contains(Integer.parseInt(inputs[0].getText()))) {
                    // Displaying error message if entered ID# exists
                    display.setText("Ошибка: с таким ID# уже есть, используйте другой ID#.");
                    // If not, it adds all the data
                } else {
                    // Adding all the info to the array
                    employees.add(new Employee(Integer.parseInt(inputs[0].getText()),//id
                            inputs[1].getText(),                //firstname
                            inputs[2].getText(),                //last name
                            inputs[3].getText(),                //фамилия
                            Integer.parseInt(inputs[4].getText()), //Отдел
                            Integer.parseInt(inputs[5].getText()) //salary
                    ));
                    display.setText("Работник #" + inputs[0].getText() + " Запись добавлена.");
                    // Loop to set all textfields to empty
                    for (int i = 0; i < inputs.length; i++) {
                        inputs[i].setText(null);
                    }

                }
            }
        } else if (event.getSource().equals(buttons[1])) {
            // Loop to search list for requested removal
            for (int i = employees.size() - 1; i >= 0; i--) {
                // If the request is found, it removes all data
                if (Integer.parseInt(inputs[0].getText()) == employees.get(i).getId()) {
                    display.setText("Работник #" + employees.get(i).getId() + " запись была удалена.");
                    employees.remove(i);
                    break;
                    // If not, the ID# does not exist
                } else {
                    display.setText("Ошибка: работника с таким ID# нет в списке.");
                }
            }
        } else {
            // Resets text area and lists all the data
            display.setText(null);
            for (int i = 0; i < employees.size(); i++) {
                display.append(div + "\nID#: " + employees.get(i).getId() + "\nИмя: " + employees.get(i).getFirstName()
                        + "\nФамилия: " + employees.get(i).getLastName() + "\nОтчество: " + employees.get(i).getSurName()
                        + "\nОтдел: Руб. " + employees.get(i).getDepartment()
                        + "\nЗарплата: Руб. " + employees.get(i).getSalary() + "\n");
            }

        }

    }
}
