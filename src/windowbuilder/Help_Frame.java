package windowbuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Help_Frame extends JFrame {

	private JPanel contentPane;
	private Image image;
	private Image chartimg;
	
	public Help_Frame() {
		setTitle("Explain");
		setResizable(false);
		image = Toolkit.getDefaultToolkit().getImage("image/main_explain.jpg");
		chartimg = Toolkit.getDefaultToolkit().getImage("image/chart.png");
		
		setIconImage(chartimg);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(image, 50, 10, 650, 500, this);
			}
		};
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
