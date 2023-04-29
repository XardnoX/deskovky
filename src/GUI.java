import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    public static final String FILE_PATH = "deskovky.txt";
    private static final String TEXT = "text.txt";
    private List<Deskovka> listData = new ArrayList<>();
    private Deskovka current;

    private JTextArea data;
    private JPanel panel;
    private JButton loadData;
    private JButton nextButton;
    private JButton previousButton;
    private JCheckBox bought;
    private JRadioButton priority1;
    private JRadioButton priority2;
    private JRadioButton priority3;
    private JButton saveButton;

    private JFileChooser jFileChooser;

    public GUI() {
        File file = new File(FILE_PATH);

        /*      if (!file.exists()) {
            System.out.println("Cannot load file!");
            return;
        }

*/
        List<Deskovka> list = new ArrayList<>();


        saveButton.addActionListener(e -> {
            Deskovka deskovka = null;
            zapis(current);
           }  );
        jFileChooser = new JFileChooser(".");

        loadData.addActionListener(e -> readFromFile());

        nextButton.addActionListener(e -> {

            if (current == null) {
                if (listData.isEmpty()) {
                    return;
                }

                showData(listData.get(0));
                return;
            }

            Deskovka deskovka;
            try {

                deskovka = listData.get(listData.indexOf(current) + 1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "There are not next data!");
                return;
            }

            if (deskovka == null) {
                System.out.println("Not another data");
                return;
            }

            showData(deskovka);
        });

        previousButton.addActionListener(e -> {
            Deskovka deskovka;
            if (current == null) {
                if (listData.isEmpty()) {
                    return;
                }

                showData(listData.get(0));
                return;
            }

            try {
                deskovka = listData.get(listData.indexOf(current) - 1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "There are not previous data!");
                return;
            }

            if (deskovka == null) {
                System.out.println("Not another data");
                return;
            }

            showData(deskovka);
        });
    }

    public void showData(Deskovka deskovka) {
        this.current = deskovka;
        resetButtons();

        data.append(deskovka.toString());
        bought.setSelected(deskovka.isBought());

        if (deskovka.getPopularity() == 1) priority1.setSelected(true);
        else if (deskovka.getPopularity() == 2) priority2.setSelected(true);
        else if (deskovka.getPopularity() == 3) priority3.setSelected(true);


    }

    private void resetButtons() {
        data.setText("");
        bought.setSelected(false);
        priority1.setSelected(false);
        priority2.setSelected(false);
        priority3.setSelected(false);
    }

    public void readFromFile() {
        int result = jFileChooser.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION) {
            System.out.println("Invalid option, please select file again!");
            return;
        }

        System.out.println("Reading..");
        panel.remove(loadData);

        listData = FileUtils.loadData(jFileChooser.getSelectedFile());

        if (listData.isEmpty()) {
            System.out.println("Cannot load any data");
            return;
        }

        Deskovka deskovka = listData.get(0);
        showData(deskovka);
    }

    private List<Deskovka> deskovky;

    private void zapis(Deskovka deskovka) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(TEXT)))) {
            try {
                writer.println(current.getName());
                writer.println(";");
                if (current.isBought() == false){
                    writer.println("false");
                }
                writer.println(";");
                if (current.isBought() == true){
                    writer.println("true");
                }
                writer.println(";");
                if (priority1.isSelected()){
                    writer.println("1");
                }
                if (priority2.isSelected()){
                    writer.println("2");
                }
                if (priority3.isSelected()){
                    writer.println("3");
                }
                writer.println("\n");


            } catch (Exception e) {
                System.out.println("idk");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "nelze uložit deskovku!");
        }
    }


    public static void main(String[] args) {
        GUI main = new GUI();

        main.setContentPane(main.panel);
        main.setSize(500, 500);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}