package windowbuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

public class Contact extends JFrame {

	private JPanel contentPane;
	private Image image;
	private Image img_log;
	private Image chartimg;

	public Contact() {
		setTitle("Contact Us");
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		
		image = Toolkit.getDefaultToolkit().getImage("image/chartbig.png");
		img_log = Toolkit.getDefaultToolkit().getImage("image/ssm.gif");
		chartimg = Toolkit.getDefaultToolkit().getImage("image/chart.png");
		
		setIconImage(chartimg);
		setBounds(200, 200, 600, 287);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(image, 0, 0, 128, 128, this);
			}
		};
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 20, 128, 128);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setText("Program Developers");
		lblNewLabel.setBounds(10, 158, 274, 25);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		textArea.setBounds(150, 45, 432, 128);
		textArea.setText("This Analysis Program would provide Researchers with information about Human Brain Electroencephalogram. " +
						"and The Program is sponsored by Busan Samsung Software Membership.\n" +
						"If you have any Questions or Technical Problems, Please don't hesitate to contact us.\n");
		contentPane.add(textArea);
		
		JPanel panel_1 = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(img_log, 0, 0, 130, 59, this);
			}
		};
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(435, 176, 147, 72);
		contentPane.add(panel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textArea_1.setBounds(10, 184, 115, 64);
		textArea_1.setText("Oh, Se Bin\n" +
							"Park, Eun Sung\n" +
							"Baek, Seung Hun");
		contentPane.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textArea_2.setBounds(137, 183, 207, 64);
		textArea_2.setText("E-mail : osebin.ssm@gmail.com\n" +
				"E-mail : dmstjd76@naver.com\n" +
				"E-mail : rlqks132@nate.com\n");
		contentPane.add(textArea_2);
		
		JLabel lblEegAnalysisSystem = new JLabel("EEG Analysis System for Reserch");
		lblEegAnalysisSystem.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblEegAnalysisSystem.setBounds(150, 20, 432, 25);
		contentPane.add(lblEegAnalysisSystem);
	}
}
