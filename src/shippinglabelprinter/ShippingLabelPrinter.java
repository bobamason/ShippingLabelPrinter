/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippinglabelprinter;

import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author Bob
 */
public class ShippingLabelPrinter extends JApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                File file = null;
                if (args.length > 0) {
                    file = new File(args[0]);
                    if (!file.isFile() || !file.getName().endsWith(".pdf")) {
                        file = null;
                    }
                }
                JApplet applet = new ShippingLabelPrinter(file);
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                applet.start();
            }
        });
    }

    private final Preferences preferences = Preferences.userNodeForPackage(ShippingLabelPrinter.class);
    private final File file;
    private final PrintService[] printers;
    private PrintService printerLabel;
    private PrintService printerNormal;
    private boolean isAutoPrintEnabled;
    private UIPanel uiPanel;

    private ShippingLabelPrinter(File file) {
        this.file = file;
        printers = PrintServiceLookup.lookupPrintServices(null, null);
    }

    @Override
    public void init() {
        printerLabel = getPrinterFromName(preferences.get(Keys.LABEL_PRINTER, ""));
        printerNormal = getPrinterFromName(preferences.get(Keys.NORMAL_PRINTER, ""));
        isAutoPrintEnabled = preferences.getBoolean(Keys.AUTO_PRINT, false);
        uiPanel = new UIPanel(this);
        add(uiPanel);
        if (isAutoPrintEnabled) {
            printPdfFile();
        }
    }

    public PrintService[] getAllPrinters() {
        return printers;
    }

    private PrintService getPrinterFromName(String name) {
        System.out.println("shippinglabelprinter.ShippingLabelPrinter.getPrinterFromName(" + name + ")");
        for (PrintService printer : printers) {
            if (printer.getName().equalsIgnoreCase(name)) {
                return printer;
            }
        }
        return PrintServiceLookup.lookupDefaultPrintService();
    }

    public void setPrinterLabel(PrintService printer) {
        this.printerLabel = printer;
        preferences.put(Keys.LABEL_PRINTER, printer.getName());
    }

    public void setPrinterNormal(PrintService printer) {
        this.printerNormal = printer;
        preferences.put(Keys.NORMAL_PRINTER, printer.getName());
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public File getFile() {
        return file;
    }

    public boolean wasStartedWithFile() {
        return file != null;
    }

    public void printPdfFile() {
        if (isFileLabel()) {
            System.out.println("printing label");
            printPdfFile(printerLabel);
        } else if (isFileShippingTicket()) {
            System.out.println("printing shippping ticket");
            printPdfFile(printerNormal);
        } else {
            System.out.println("incompatible file");
            uiPanel.setDebugText("incompatible file");
            if(Desktop.isDesktopSupported() && file != null){
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    Logger.getLogger(ShippingLabelPrinter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void printPdfFile(final PrintService printer) {
        uiPanel.showProgressBar();
        uiPanel.setDebugText("printing...");
        new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() throws Exception {
                if (file == null) {
                    return "no file to print";
                }
                if (printer == null) {
                    return "no printer selected";
                }
                try {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    PDDocument document = PDDocument.load(file);
                    job.setPageable(new PDFPageable(document));
                    PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                    attributes.add(new PageRanges(0, 1));
                    attributes.add(new Copies(2));
                    job.print(attributes);
                    return "print successfull";
                } catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }

            @Override
            protected void done() {
                try {
                    final String text = get();
                    System.out.println(text);
                    uiPanel.setDebugText(text);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShippingLabelPrinter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(ShippingLabelPrinter.class.getName()).log(Level.SEVERE, null, ex);
                }
                uiPanel.hideProgressBar();
            }
        }.execute();
    }

    private boolean isFileLabel() {
        if (file == null) {
            return false;
        }
        String fileName = file.getName();
        return (fileName.contains("job") || fileName.contains("Job")) && Character.isDigit(fileName.charAt(0));
    }

    private boolean isFileShippingTicket() {
        if (file == null) {
            return false;
        }
        String fileName = file.getName();
        return (fileName.contains("job") || fileName.contains("Job")) && !Character.isDigit(fileName.charAt(0));
    }

    public PrintService getPrinterLabel() {
        return printerLabel;
    }

    public PrintService getPrinterNormal() {
        return printerNormal;
    }

    public boolean isAutoPrintEnabled() {
        return isAutoPrintEnabled;
    }

    public void setAutoPrintEnabled(boolean isAutoPrintEnabled) {
        this.isAutoPrintEnabled = isAutoPrintEnabled;
        preferences.putBoolean(Keys.AUTO_PRINT, isAutoPrintEnabled);
    }
}
