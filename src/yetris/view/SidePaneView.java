/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yetris.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;   
import yetris.model.Model;

/**
 *
 * @author lsp
 */
public class SidePaneView extends javax.swing.JPanel {

    Model model;
    NextTetriminoView nextTetriminoView;

    ImageIcon iconRestart, iconPlay;
    /**
     * Creates new form SidePaneView
     */
    public SidePaneView(Model model) {
        initComponents();
        this.model = model;

        nextTetriminoView = new NextTetriminoView(model);
        panelNext.setLayout(new FlowLayout());
        panelNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelNext.setAlignmentY(Component.CENTER_ALIGNMENT);
        panelNext.add(nextTetriminoView);
        panelNext.setVisible(model.getConfig().showNextTetrimino);

        iconRestart = new ImageIcon(this.getClass().getResource("/img/restart.png"));
        iconPlay = new ImageIcon(this.getClass().getResource("/img/play.png"));
        btnRestart.setFocusable(false);
        btnRestart.setIcon(iconPlay);
    }

    public void refresh(){
        nextTetriminoView.refresh();
    	labelScore.setText(Integer.toString(model.getScore()));
    	labelLevel.setText(Integer.toString(model.getLevel()));
    	labelLines.setText(Integer.toString(model.getLines()));
    }

    public void initStartGame(){
        btnRestart.setText("Restart");
        btnRestart.setIcon(iconRestart);
    }

	public void addActionListener(ActionListener al){
		btnRestart.addActionListener(al);
	}

    // @Override
    // public Dimension getPreferredSize(){
    //   return new Dimension(137, 350); // Should ideally never reach here
    // }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {


        jLabel5 = new JLabel();
        labelScore = new JLabel();
        jLabel8 = new JLabel();
        labelLevel = new JLabel();
        jLabel10 = new JLabel();
        labelLines = new JLabel();
        panelNext = new JPanel();
        jPanel1 = new JPanel(); 
        btnRestart = new JButton();
        btnOptions = new JButton();
        jLabel1 = new JLabel();

        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new GridLayout(3, 2, 5, 0));

        jLabel5.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setText("Score:");
        jPanel1.add(jLabel5);

        labelScore.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        labelScore.setForeground(new Color(51, 153, 0));
        labelScore.setText("100");
        labelScore.setName("labelScore"); // NOI18N
        jPanel1.add(labelScore);
        labelScore.getAccessibleContext().setAccessibleDescription("");

        jLabel8.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel8.setText("Level:");
        jPanel1.add(jLabel8);

        labelLevel.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        labelLevel.setText("100");
        jPanel1.add(labelLevel);

        jLabel10.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel10.setText("Lines:");
        jPanel1.add(jLabel10);

        labelLines.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        labelLines.setText("100");
        jPanel1.add(labelLines);

        panelNext.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Next"));
        panelNext.setLayout(null);

        btnRestart.setText("Start");
        btnRestart.setActionCommand("restart");
        btnRestart.setBorder(null);
        btnRestart.setFocusable(false);
        btnRestart.setMinimumSize(new Dimension(90, 15));

        btnOptions.setText("Options");
        btnOptions.setActionCommand("restart");
        btnOptions.setBorder(null);
        btnOptions.setFocusable(false);
        btnOptions.setMinimumSize(new Dimension(90, 15));

        jLabel1.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setForeground(new Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Press SPACE to pause");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(btnRestart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOptions, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelNext, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnOptions, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestart, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnOptions;
    private JButton btnRestart;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel5;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JLabel labelLevel;
    private JLabel labelLines;
    private JLabel labelScore;
    private JPanel panelNext;
    // End of variables declaration//GEN-END:variables
}
