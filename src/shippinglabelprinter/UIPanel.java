/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippinglabelprinter;

import javax.print.PrintService;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Bob
 */
public class UIPanel extends javax.swing.JPanel {
    private final ShippingLabelPrinter applet;

    /**
     * Creates new form UIPanel
     * @param applet
     */
    public UIPanel(ShippingLabelPrinter applet) {
        this.applet = applet;
        initComponents();
        hideProgressBar();
        checkBoxAutoPrint.setSelected(applet.isAutoPrintEnabled());
        buttonPrint.setEnabled(!checkBoxAutoPrint.isSelected());
        comboBoxPrinterLabel.setSelectedItem(applet.getPrinterLabel());
        comboBoxPrinterNormal.setSelectedItem(applet.getPrinterNormal());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLabels = new javax.swing.JLabel();
        comboBoxPrinterLabel = new javax.swing.JComboBox<>();
        labelNormal = new javax.swing.JLabel();
        comboBoxPrinterNormal = new javax.swing.JComboBox<>();
        checkBoxAutoPrint = new javax.swing.JCheckBox();
        buttonPrint = new javax.swing.JButton();
        debugLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        labelLabels.setText("label printer");

        comboBoxPrinterLabel.setModel(new DefaultComboBoxModel<PrintService>(applet.getAllPrinters())
        );
        comboBoxPrinterLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPrinterLabelActionPerformed(evt);
            }
        });

        labelNormal.setText("normal printer");

        comboBoxPrinterNormal.setModel(new DefaultComboBoxModel<PrintService>(applet.getAllPrinters())
        );
        comboBoxPrinterNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPrinterNormalActionPerformed(evt);
            }
        });

        checkBoxAutoPrint.setText("auto print");
        checkBoxAutoPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAutoPrintActionPerformed(evt);
            }
        });

        buttonPrint.setText("print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        debugLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        progressBar.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debugLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBoxAutoPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNormal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLabels, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxPrinterLabel, 0, 585, Short.MAX_VALUE)
                            .addComponent(comboBoxPrinterNormal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLabels)
                    .addComponent(comboBoxPrinterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNormal)
                    .addComponent(comboBoxPrinterNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(debugLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkBoxAutoPrint)
                        .addComponent(buttonPrint))
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxAutoPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAutoPrintActionPerformed
        buttonPrint.setEnabled(!checkBoxAutoPrint.isSelected());
        applet.setAutoPrintEnabled(checkBoxAutoPrint.isSelected());
    }//GEN-LAST:event_checkBoxAutoPrintActionPerformed

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        applet.printPdfFile();
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void comboBoxPrinterLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPrinterLabelActionPerformed
        applet.setPrinterLabel((PrintService) comboBoxPrinterLabel.getSelectedItem());
    }//GEN-LAST:event_comboBoxPrinterLabelActionPerformed

    private void comboBoxPrinterNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPrinterNormalActionPerformed
        applet.setPrinterNormal((PrintService) comboBoxPrinterNormal.getSelectedItem());
    }//GEN-LAST:event_comboBoxPrinterNormalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPrint;
    private javax.swing.JCheckBox checkBoxAutoPrint;
    private javax.swing.JComboBox<PrintService> comboBoxPrinterLabel;
    private javax.swing.JComboBox<PrintService> comboBoxPrinterNormal;
    private javax.swing.JLabel debugLabel;
    private javax.swing.JLabel labelLabels;
    private javax.swing.JLabel labelNormal;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    void showProgressBar() {
        progressBar.setVisible(true);
    }

    void hideProgressBar() {
        progressBar.setVisible(false);
    }
    
    void setDebugText(String text){
        debugLabel.setText(text);
    }
}