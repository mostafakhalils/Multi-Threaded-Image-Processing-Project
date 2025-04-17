import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

public class MainApp extends JFrame {
    private JTextField inputPathField, outputPathField, threadCountField, widthField, heightField;
    private JCheckBox grayCheck, binaryCheck, negativeCheck, logCheck, powerCheck;
    private JButton processButton, browseInputBtn, browseOutputBtn;
    private JProgressBar progressBar;
    private JTextArea statusArea;

    public MainApp() {
        setTitle("Image Filter Processor - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 3, 5, 5));

        inputPathField = new JTextField();
        outputPathField = new JTextField();
        threadCountField = new JTextField("4");
        widthField = new JTextField("256");
        heightField = new JTextField("256");

        browseInputBtn = new JButton("Browse");
        browseInputBtn.addActionListener(e -> selectFolder(inputPathField));

        browseOutputBtn = new JButton("Browse");
        browseOutputBtn.addActionListener(e -> selectFolder(outputPathField));

        inputPanel.add(new JLabel("Input Folder:"));
        inputPanel.add(inputPathField);
        inputPanel.add(browseInputBtn);

        inputPanel.add(new JLabel("Output Folder:"));
        inputPanel.add(outputPathField);
        inputPanel.add(browseOutputBtn);

        inputPanel.add(new JLabel("Number of Threads:"));
        inputPanel.add(threadCountField);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Resize Width:"));
        inputPanel.add(widthField);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Resize Height:"));
        inputPanel.add(heightField);
        inputPanel.add(new JLabel());

        grayCheck = new JCheckBox("Grayscale");
        binaryCheck = new JCheckBox("Binary");
        negativeCheck = new JCheckBox("Negative");
        logCheck = new JCheckBox("Log Transform");
        powerCheck = new JCheckBox("Power Law");

        inputPanel.add(grayCheck);
        inputPanel.add(binaryCheck);
        inputPanel.add(negativeCheck);
        inputPanel.add(logCheck);
        inputPanel.add(powerCheck);

        processButton = new JButton("Process Now");
        inputPanel.add(processButton);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        statusArea = new JTextArea();
        statusArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(statusArea);

        add(inputPanel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        processButton.addActionListener(e -> processImages());
    }

    private void selectFolder(JTextField field) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            field.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void processImages() {
        File inputFolder = new File(inputPathField.getText().trim());
        File[] images = inputFolder.listFiles(f -> f.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png)"));
        if (images == null || images.length == 0) {
            JOptionPane.showMessageDialog(this, "No images found in input folder!");
            return;
        }

        int threadCount = Integer.parseInt(threadCountField.getText().trim());
        int width = Integer.parseInt(widthField.getText().trim());
        int height = Integer.parseInt(heightField.getText().trim());
        File outputFolder = outputPathField.getText().trim().isEmpty()
                ? new File("Output")
                : new File(outputPathField.getText().trim());
        outputFolder.mkdirs();

        String[] folders = {
            "Resized_Images", "Grayscale_Images", "Binary_Images",
            "Negative_Images", "Log_Images", "PowerLaw_Images"
        };
        for (String f : folders) new File(outputFolder, f).mkdirs();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        progressBar.setMaximum(images.length);
        progressBar.setValue(0);
        statusArea.setText("");

        for (File img : images) {
            executor.submit(() -> {
                try {
                    BufferedImage original = ImageIO.read(img);
                    BufferedImage resized = ImageFilters.resizeImage(original, width, height);
                    ImageIO.write(resized, "jpg", new File(outputFolder, "Resized_Images/" + img.getName()));

                    if (grayCheck.isSelected()) {
                        BufferedImage gray = ImageFilters.applyGrayscale(resized);
                        ImageIO.write(gray, "jpg", new File(outputFolder, "Grayscale_Images/" + img.getName()));
                    }
                    if (binaryCheck.isSelected()) {
                        BufferedImage binary = ImageFilters.applyBinary(resized);
                        ImageIO.write(binary, "jpg", new File(outputFolder, "Binary_Images/" + img.getName()));
                    }
                    if (negativeCheck.isSelected()) {
                        BufferedImage neg = ImageFilters.applyNegative(resized);
                        ImageIO.write(neg, "jpg", new File(outputFolder, "Negative_Images/" + img.getName()));
                    }
                    if (logCheck.isSelected()) {
                        BufferedImage log = ImageFilters.applyLogTransform(resized);
                        ImageIO.write(log, "jpg", new File(outputFolder, "Log_Images/" + img.getName()));
                    }
                    if (powerCheck.isSelected()) {
                        BufferedImage power = ImageFilters.applyPowerLawTransform(resized, 0.6);
                        ImageIO.write(power, "jpg", new File(outputFolder, "PowerLaw_Images/" + img.getName()));
                    }

                    SwingUtilities.invokeLater(() -> {
                        statusArea.append("Processed: " + img.getName() + "\n");
                        progressBar.setValue(progressBar.getValue() + 1);
                        if (progressBar.getValue() == images.length) {
                            JOptionPane.showMessageDialog(this, "الشغل خلص يا إدارة 🚀");
                        }
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> 
                        statusArea.append("Error processing: " + img.getName() + "\n"));
                }
            });
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
