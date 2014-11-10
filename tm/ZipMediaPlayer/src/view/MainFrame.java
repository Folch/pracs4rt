/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.disk.IDisk;
import controller.player.IPlayer;
import controller.player.OnImageListener;
import controller.MainController;
import controller.filter.IFilter;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.config.FileType;

/**
 *
 * @author zenbook
 */
public class MainFrame extends javax.swing.JFrame {
    
    private enum State {OPEN_ZIP_PLAY, OPEN_ZIP_PAUSE, OPEN_IMAGE, EMPTY};
    private enum FilterState {CUSTOM, HSB, NEGATIVE, BINARY, ORIGINAL};
    
    private IDisk saver;
    private IPlayer player;
    private IFilter filter;
    
    private FilterState currentFilterState;
    
    private JFileChooser fc;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        changeState(State.EMPTY);
        currentFilterState = FilterState.ORIGINAL;
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        //((VideoPanel)imagepanel).loading(true);
    }

    public String showFileChooser(FileType fileType) {
        fc.setDialogTitle("Open");
        if (fileType == FileType.ZIP) {
            fc.setFileFilter(new FileNameExtensionFilter("Zip", "zip"));
        } else if (fileType == FileType.IMAGE) {
            fc.setFileFilter(new FileNameExtensionFilter("Images", new String[]{"png", "jpeg", "jpg"}));
        }

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
    
    public String showSaveFileChooser() {
        fc.setDialogTitle("Save");   
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prevbtn = new javax.swing.JButton();
        nextbtn = new javax.swing.JButton();
        playbtn = new javax.swing.JButton();
        imagepanel = new VideoPanel();
        menubar = new javax.swing.JMenuBar();
        filebar = new javax.swing.JMenu();
        openzipmenu = new javax.swing.JMenuItem();
        openimagemenu = new javax.swing.JMenuItem();
        saveimagemenu = new javax.swing.JMenuItem();
        savezipmenu = new javax.swing.JMenuItem();
        savegzipmenu = new javax.swing.JMenuItem();
        exitmenu = new javax.swing.JMenuItem();
        playerbar = new javax.swing.JMenu();
        optionsmenu = new javax.swing.JMenuItem();
        fullscreenmenu = new javax.swing.JCheckBoxMenuItem();
        filterbar = new javax.swing.JMenu();
        customfiltermenu = new javax.swing.JCheckBoxMenuItem();
        hsbmenu = new javax.swing.JCheckBoxMenuItem();
        negativemenu = new javax.swing.JCheckBoxMenuItem();
        binarymenu = new javax.swing.JCheckBoxMenuItem();
        separatonfiltermenu = new javax.swing.JPopupMenu.Separator();
        originalmenu = new javax.swing.JCheckBoxMenuItem();
        helpbar = new javax.swing.JMenu();
        aboutmenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(512, 372));

        prevbtn.setForeground(new java.awt.Color(74, 62, 254));
        prevbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resource/Rewind24.gif"))); // NOI18N
        prevbtn.setEnabled(false);
        prevbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevbtnActionPerformed(evt);
            }
        });

        nextbtn.setForeground(new java.awt.Color(74, 62, 254));
        nextbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resource/FastForward24.gif"))); // NOI18N
        nextbtn.setEnabled(false);
        nextbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextbtnActionPerformed(evt);
            }
        });

        playbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resource/Play24.gif"))); // NOI18N
        playbtn.setEnabled(false);
        playbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playbtnActionPerformed(evt);
            }
        });

        imagepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(156, 156, 156)));

        javax.swing.GroupLayout imagepanelLayout = new javax.swing.GroupLayout(imagepanel);
        imagepanel.setLayout(imagepanelLayout);
        imagepanelLayout.setHorizontalGroup(
            imagepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagepanelLayout.setVerticalGroup(
            imagepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );

        filebar.setText("File");

        openzipmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        openzipmenu.setText("Open zip...");
        openzipmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openzipmenuActionPerformed(evt);
            }
        });
        filebar.add(openzipmenu);

        openimagemenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        openimagemenu.setText("Open image ...");
        openimagemenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openimagemenuActionPerformed(evt);
            }
        });
        filebar.add(openimagemenu);

        saveimagemenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveimagemenu.setText("Save Image (png) ...");
        saveimagemenu.setEnabled(false);
        saveimagemenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveimagemenuActionPerformed(evt);
            }
        });
        filebar.add(saveimagemenu);

        savezipmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        savezipmenu.setText("Save Zip ...");
        savezipmenu.setEnabled(false);
        savezipmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savezipmenuActionPerformed(evt);
            }
        });
        filebar.add(savezipmenu);

        savegzipmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        savegzipmenu.setText("Save GZip ...");
        savegzipmenu.setEnabled(false);
        savegzipmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savegzipmenuActionPerformed(evt);
            }
        });
        filebar.add(savegzipmenu);

        exitmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        exitmenu.setText("Exit");
        exitmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitmenuActionPerformed(evt);
            }
        });
        filebar.add(exitmenu);

        menubar.add(filebar);

        playerbar.setText("Player");

        optionsmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        optionsmenu.setText("Options");
        optionsmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsmenuActionPerformed(evt);
            }
        });
        playerbar.add(optionsmenu);

        fullscreenmenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        fullscreenmenu.setText("Full screen");
        fullscreenmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullscreenmenuActionPerformed(evt);
            }
        });
        playerbar.add(fullscreenmenu);

        menubar.add(playerbar);

        filterbar.setText("Filter");

        customfiltermenu.setText("Custom Filter");
        customfiltermenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customfiltermenuActionPerformed(evt);
            }
        });
        filterbar.add(customfiltermenu);

        hsbmenu.setText("HSB");
        hsbmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsbmenuActionPerformed(evt);
            }
        });
        filterbar.add(hsbmenu);

        negativemenu.setText("Negative");
        negativemenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negativemenuActionPerformed(evt);
            }
        });
        filterbar.add(negativemenu);

        binarymenu.setText("Binary");
        binarymenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarymenuActionPerformed(evt);
            }
        });
        filterbar.add(binarymenu);

        separatonfiltermenu.setBackground(new java.awt.Color(68, 68, 68));
        filterbar.add(separatonfiltermenu);

        originalmenu.setText("Original");
        originalmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                originalmenuActionPerformed(evt);
            }
        });
        filterbar.add(originalmenu);

        menubar.add(filterbar);

        helpbar.setText("Help");

        aboutmenu.setText("About");
        helpbar.add(aboutmenu);

        menubar.add(helpbar);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imagepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(playbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(nextbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(playbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prevbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prevbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevbtnActionPerformed
        if (player != null) {
            player.previous();
        }
    }//GEN-LAST:event_prevbtnActionPerformed

    private void nextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtnActionPerformed
        if (player != null) {
            player.next();
        }
    }//GEN-LAST:event_nextbtnActionPerformed

    private void openzipmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openzipmenuActionPerformed
        openFile(FileType.ZIP);
    }//GEN-LAST:event_openzipmenuActionPerformed

    private void openimagemenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openimagemenuActionPerformed
        openFile(FileType.IMAGE);
    }//GEN-LAST:event_openimagemenuActionPerformed

    private void saveimagemenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveimagemenuActionPerformed
        saver.saveImage(showSaveFileChooser());
    }//GEN-LAST:event_saveimagemenuActionPerformed

    private void playbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playbtnActionPerformed
         if (player != null) {
            if (prevbtn.isEnabled()) { // TODO: Marrano!! crear un métode al controller per saber l'estat
                //Si està pressionat serà auto
                changeState(State.OPEN_ZIP_PLAY);
                player.play();
            } else {
                changeState(State.OPEN_ZIP_PAUSE);
                player.pause();
            }
        }
    }//GEN-LAST:event_playbtnActionPerformed

    private void savezipmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savezipmenuActionPerformed
        saver.saveZip(showSaveFileChooser());
    }//GEN-LAST:event_savezipmenuActionPerformed

    private void exitmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitmenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitmenuActionPerformed

    private void savegzipmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savegzipmenuActionPerformed
        saver.saveGZip(showSaveFileChooser());
    }//GEN-LAST:event_savegzipmenuActionPerformed

    private void fullscreenmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullscreenmenuActionPerformed
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        device.setFullScreenWindow(fullscreenmenu.isSelected() ? this : null);
    }//GEN-LAST:event_fullscreenmenuActionPerformed

    private void optionsmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsmenuActionPerformed
        if(player != null) {
            OptionsDialog dialog = new OptionsDialog(this, false, player);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_optionsmenuActionPerformed

    private void customfiltermenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customfiltermenuActionPerformed
        changeFilterState(FilterState.CUSTOM);
        CustomFilterDialog dialog = new CustomFilterDialog(this, true, filter);
        dialog.setVisible(true);
    }//GEN-LAST:event_customfiltermenuActionPerformed

    private void hsbmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsbmenuActionPerformed
        changeFilterState(FilterState.HSB);
        HSBFilterDialog dialog = new HSBFilterDialog(this, true, filter);
        dialog.setVisible(true);
    }//GEN-LAST:event_hsbmenuActionPerformed

    private void negativemenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negativemenuActionPerformed
        if(!changeFilterState(FilterState.NEGATIVE))
            return;
        filter.negativeFilter();
    }//GEN-LAST:event_negativemenuActionPerformed

    private void binarymenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarymenuActionPerformed
        changeFilterState(FilterState.BINARY);
        BinaryFilterDialog dialog = new BinaryFilterDialog(this, true, filter);
        dialog.setVisible(true);
    }//GEN-LAST:event_binarymenuActionPerformed

    private void originalmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_originalmenuActionPerformed
        if(!changeFilterState(FilterState.ORIGINAL))
            return;
        filter.removeFilter();
    }//GEN-LAST:event_originalmenuActionPerformed

    public void openFile(FileType fileType) {
        MainController controller;
        if (player == null || saver == null) {
            controller = new MainController((OnImageListener)imagepanel);
            player = controller;
            saver = controller;
            filter = controller;
        }

        String path = showFileChooser(fileType);
        if (path == null) {
            return;
        }
        
        if(fileType == FileType.IMAGE) {
            saver.openImage(path);
            changeState(State.OPEN_IMAGE);

        } else if(fileType == FileType.ZIP) {
            saver.openZip(path);
            changeState(State.OPEN_ZIP_PAUSE);
        }   
        player.first();
    }

    private boolean changeFilterState(FilterState state) {
        customfiltermenu.setState(false);
        hsbmenu.setState(false);
        negativemenu.setState(false);
        binarymenu.setState(false);
        originalmenu.setState(false);
        switch (state) {
            case CUSTOM:
                customfiltermenu.setState(true);
                break;
            case HSB:
                hsbmenu.setState(true);
                break;
            case NEGATIVE:
                negativemenu.setState(true);
                break;
            case BINARY:
                binarymenu.setState(true);
                break;
            case ORIGINAL:
                originalmenu.setState(true);
                break;
        }
        if(state == currentFilterState)
            return false;
        currentFilterState = state;
        return true;
    }
    
    private void changeState(State state) {
        changeFilterState(FilterState.ORIGINAL);
        switch (state) {
            case OPEN_IMAGE:
                //menu item
                savezipmenu.setEnabled(false);
                savegzipmenu.setEnabled(false);
                saveimagemenu.setEnabled(true);
                //player items
                optionsmenu.setEnabled(false);
                prevbtn.setEnabled(false);
                nextbtn.setEnabled(false);
                playbtn.setEnabled(false);
                //filter items
                customfiltermenu.setEnabled(false);
                hsbmenu.setEnabled(false);
                negativemenu.setEnabled(false);
                binarymenu.setEnabled(false);
                originalmenu.setEnabled(false);
                break;
            case OPEN_ZIP_PLAY:
                //menu item
                savezipmenu.setEnabled(true);
                savegzipmenu.setEnabled(true);
                saveimagemenu.setEnabled(false);
                //player items
                optionsmenu.setEnabled(true);
                prevbtn.setEnabled(false);
                nextbtn.setEnabled(false);
                playbtn.setEnabled(true);
                playbtn.setIcon(new ImageIcon(getClass().getResource("/view/resource/Pause24.gif")));
                //filter items
                customfiltermenu.setEnabled(true);
                hsbmenu.setEnabled(true);
                negativemenu.setEnabled(true);
                binarymenu.setEnabled(true);
                originalmenu.setEnabled(true);
                break;
            case OPEN_ZIP_PAUSE:
                //menu item
                savezipmenu.setEnabled(true);
                savegzipmenu.setEnabled(true);
                saveimagemenu.setEnabled(false);
                //player items
                optionsmenu.setEnabled(true);
                prevbtn.setEnabled(true);
                nextbtn.setEnabled(true);
                playbtn.setEnabled(true);
                playbtn.setIcon(new ImageIcon(getClass().getResource("/view/resource/Play24.gif")));
                //filter items
                customfiltermenu.setEnabled(true);
                hsbmenu.setEnabled(true);
                negativemenu.setEnabled(true);
                binarymenu.setEnabled(true);
                originalmenu.setEnabled(true);
                break;
            case EMPTY:
                //menu item
                savezipmenu.setEnabled(true);
                savegzipmenu.setEnabled(true);
                saveimagemenu.setEnabled(true);
                //player items
                optionsmenu.setEnabled(false);
                prevbtn.setEnabled(false);
                nextbtn.setEnabled(false);
                playbtn.setEnabled(false);
                //filter items
                customfiltermenu.setEnabled(false);
                hsbmenu.setEnabled(false);
                negativemenu.setEnabled(false);
                binarymenu.setEnabled(false);
                originalmenu.setEnabled(false);
                break;
        }
        imagepanel.repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame m = new MainFrame();
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutmenu;
    private javax.swing.JCheckBoxMenuItem binarymenu;
    private javax.swing.JCheckBoxMenuItem customfiltermenu;
    private javax.swing.JMenuItem exitmenu;
    private javax.swing.JMenu filebar;
    private javax.swing.JMenu filterbar;
    private javax.swing.JCheckBoxMenuItem fullscreenmenu;
    private javax.swing.JMenu helpbar;
    private javax.swing.JCheckBoxMenuItem hsbmenu;
    private javax.swing.JPanel imagepanel;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JCheckBoxMenuItem negativemenu;
    private javax.swing.JButton nextbtn;
    private javax.swing.JMenuItem openimagemenu;
    private javax.swing.JMenuItem openzipmenu;
    private javax.swing.JMenuItem optionsmenu;
    private javax.swing.JCheckBoxMenuItem originalmenu;
    private javax.swing.JButton playbtn;
    private javax.swing.JMenu playerbar;
    private javax.swing.JButton prevbtn;
    private javax.swing.JMenuItem savegzipmenu;
    private javax.swing.JMenuItem saveimagemenu;
    private javax.swing.JMenuItem savezipmenu;
    private javax.swing.JPopupMenu.Separator separatonfiltermenu;
    // End of variables declaration//GEN-END:variables

}
