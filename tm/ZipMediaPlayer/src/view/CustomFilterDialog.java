/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.filter.IFilter;
import model.FilterDim3;

/**
 *
 * @author zenbook
 */
public class CustomFilterDialog extends javax.swing.JDialog {

    private IFilter filter;
    
    private FilterDim3 currentFilter;
    
    /**
     * Creates new form OptionsDialog
     * @param parent
     * @param modal
     * @param filter
     */
    public CustomFilterDialog(java.awt.Frame parent, boolean modal, IFilter filter) {
        super(parent, modal);
        initComponents();
        this.filter =  filter;
        
        showFilter(filter.getFilterDim3());
    }
    
    
    private void showValue(String value) {
        field1.setText(value);
        field2.setText(value);
        field3.setText(value);
        field4.setText(value);
        field5.setText(value);
        field6.setText(value);
        field7.setText(value);
        field8.setText(value);
        field9.setText(value);
    }
    
    private void enablePanel(boolean enabled) {
        field1.setEnabled(enabled);
        field2.setEnabled(enabled);
        field3.setEnabled(enabled);
        field4.setEnabled(enabled);
        field5.setEnabled(enabled);
        field6.setEnabled(enabled);
        field7.setEnabled(enabled);
        field8.setEnabled(enabled);
        field9.setEnabled(enabled);
    }
    
    private void showFilter(FilterDim3 f) {
        this.currentFilter = f;
        field1.setText((int)f.getFilter()[0][0]+"");
        field2.setText((int)f.getFilter()[0][1]+"");
        field3.setText((int)f.getFilter()[0][2]+"");
        field4.setText((int)f.getFilter()[1][0]+"");
        field5.setText((int)f.getFilter()[1][1]+"");
        field6.setText((int)f.getFilter()[1][2]+"");
        field7.setText((int)f.getFilter()[2][0]+"");
        field8.setText((int)f.getFilter()[2][1]+"");
        field9.setText((int)f.getFilter()[2][2]+"");
    }
    
    private FilterDim3 getPanelFilter() {
        return new FilterDim3(Integer.parseInt(field1.getText()), 
                Integer.parseInt(field2.getText()), 
                Integer.parseInt(field3.getText()), 
                Integer.parseInt(field4.getText()), 
                Integer.parseInt(field5.getText()), 
                Integer.parseInt(field6.getText()), 
                Integer.parseInt(field7.getText()), 
                Integer.parseInt(field8.getText()), 
                Integer.parseInt(field9.getText()));      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        field1 = new javax.swing.JTextField();
        field2 = new javax.swing.JTextField();
        field3 = new javax.swing.JTextField();
        field4 = new javax.swing.JTextField();
        field5 = new javax.swing.JTextField();
        field6 = new javax.swing.JTextField();
        field7 = new javax.swing.JTextField();
        field8 = new javax.swing.JTextField();
        field9 = new javax.swing.JTextField();
        filterbox = new javax.swing.JComboBox();
        cancelbtn = new javax.swing.JButton();
        applybtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        field1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field7.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        field9.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        filterbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Average", "Sobel X", "Sobel Y", "Low Pass", "High Pass", "Laplacian", "Custom" }));
        filterbox.setSelectedIndex(6);
        filterbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterboxItemStateChanged(evt);
            }
        });

        cancelbtn.setText("Cancel");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        applybtn.setText("Apply");
        applybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applybtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(applybtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filterbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(applybtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelbtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterboxItemStateChanged
        String item = (String) filterbox.getSelectedItem();
        switch (item) {
            case "Average":
                enablePanel(false);
                this.currentFilter = FilterDim3.AVERAGE;
                showValue("1/9");
                break;
            case "Sobel X":
                enablePanel(false);
                showFilter(FilterDim3.SOBEL_X);
                break;
            case "Sobel Y":
                enablePanel(false);
                showFilter(FilterDim3.SOBEL_Y);
                break;
            case "Low Pass":
                enablePanel(false);
                showFilter(FilterDim3.LOW_PASS);
                break;
            case "High Pass":
                enablePanel(false);
                showFilter(FilterDim3.HIGH_PASS);
                break;
            case "Laplacian":
                enablePanel(false);
                showFilter(FilterDim3.LAPLACIAN);
                break;
            case "Custom":
                enablePanel(true);
                break;
        }
    }//GEN-LAST:event_filterboxItemStateChanged

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void applybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applybtnActionPerformed
        FilterDim3 f = this.currentFilter;
        if(((String)filterbox.getSelectedItem()).equals("Custom"))
            f = getPanelFilter();
        filter.applyFilter(f);
    }//GEN-LAST:event_applybtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applybtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JTextField field1;
    private javax.swing.JTextField field2;
    private javax.swing.JTextField field3;
    private javax.swing.JTextField field4;
    private javax.swing.JTextField field5;
    private javax.swing.JTextField field6;
    private javax.swing.JTextField field7;
    private javax.swing.JTextField field8;
    private javax.swing.JTextField field9;
    private javax.swing.JComboBox filterbox;
    // End of variables declaration//GEN-END:variables
}
