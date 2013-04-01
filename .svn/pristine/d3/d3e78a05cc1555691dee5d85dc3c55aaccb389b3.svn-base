package panel;

import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import chart.AllChannel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class EEG_Explain extends JPanel{
	
	private JPanel Panel;
	private Image image;
	
	public EEG_Explain() {
		init();
		image = Toolkit.getDefaultToolkit().getImage("image/main_explain.jpg");
	}
	
	public void set_Panel(EEG_Explain panel){
		panel.add(Panel);
	}
	
	private void init(){
		Panel = new JPanel() {
			public void paintComponent(Graphics g){
				g.drawImage(image, 0, 0, 650, 500, this);
			}
		};
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(20, 50, 650, 500);
		Panel.setVisible(true);
		Panel.setLayout(null);
	}
}
