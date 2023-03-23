import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    public static final String FILE_PATH = "deskovky.txt";

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

    private JFileChooser jFileChooser;

    public GUI() {
        File file = new File(FILE_PATH);

        /*      if (!file.exists()) {
            System.out.println("Cannot load file!");
            return;
        }
*/
        jFileChooser = new JFileChooser(".");

        loadData.addActionListener(e -> readFromFile());

        nextButton.addActionListener(e -> {
            Deskovka deskovka;
         /*   if (current == null) {
                if (listData.isEmpty()) {
                    return;
                }

                showData(listData.get(0));
                return;
            }
 */

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
            if (current == null) {
                if (listData.isEmpty()) {
                    return;
                }

                showData(listData.get(0));
                return;
            }

            Deskovka deskovka;
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

/*        if (result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION) {
            System.out.println("Invalid option, please select file again!");
            return;
        }
*/
        System.out.println("Reading..");
   //     panel.remove(loadData);

        listData = FileUtils.loadData(jFileChooser.getSelectedFile());

        if (listData.isEmpty()) {
            System.out.println("Cannot load any data");
            return;
        }

        Deskovka deskovka = listData.get(0);
        showData(deskovka);
    }

    public static void main(String[] args) {
        GUI main = new GUI();

        main.setContentPane(main.panel);
        main.setSize(500, 500);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}