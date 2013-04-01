package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;

public class tet {

	private JFrame frame;
	private JTextField textField;
	String name=null;
	protected DB db;
	private JPanel panel;
	private JTextPane tp;
	private JPanel panel_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tet window = new tet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public tet() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 419, 184);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		db =new DB();
		
		panel = new JPanel();
		panel.setBounds(12, 10, 173, 31);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(34, 51, 126, 60);
		frame.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("생성");
		JButton loadButton = new JButton("불러오기");
		panel_1.add(btnNewButton);
		panel_1.add(loadButton);
		
		panel_2 = new JPanel();
		panel_2.setBounds(208, 10, 186, 122);
		frame.getContentPane().add(panel_2);
		db.createFolder("test");

		tp = new JTextPane();
		tp.setText(TextFile());
		panel_2.add(tp);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name=textField.getText();
				if(name!=null){
				//	db.createFile(name);
					db.createFolder(name);
					tp.setText(TextFile());
					panel_2.add(tp);
				}
				else
					System.out.println(name);
			}
		});
	}
	
	public String TextFile()
	{
		String aa = "";
		for(int i=0;i<db.getFileCount();i++){
			aa+=db.getFileList(i)+"\n";
		    }
		return aa;
	}
}
