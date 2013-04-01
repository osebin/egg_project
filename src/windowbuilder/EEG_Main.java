package windowbuilder;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.border.EtchedBorder;

import org.omg.CORBA.TypeCodePackage.Bounds;

import database.DB;

import EEGLog.EEGLog;

import eegdata.*;

import panel.*;
import processing.*;
import processing.core.PApplet;

public class EEG_Main{
	
	public static String username = null;
	public static DB db;
	public static boolean DB_Click = false;
	public static String filepath = null;
	public static EEG_Data eegdata;

	private Calendar cal;
	public static JFrame frame;
	private EEGLog eeg_device;
	
	processing.core.PApplet sketch;
	processing.core.PApplet circle;
	
	private JTextField		textField; 	// �섑뵆留�怨꾩닔 128 Sampling
	private JTextField		txtGood; 	// 痢≪젙湲��좏샇 媛뺣룄
	private JTextField		textField_1; // �좎� �ㅼ엫 (珥덇린 �붾㈃ �⑤꼸)
	private JTextField 		textField_2; // �좎� �ㅼ엫 (�덉そ �⑤꼸)
	public static JTextArea txtrEegDataLog; // �곗씠��濡쒓렇 �④린湲�
	private JScrollPane		scrollpane;
	
	///////// �꾨젅��硫붾돱 ////////////
	private JMenuBar 		menubar;
	
	private JMenu			file;
	private JMenu			option;
	private JMenu			help;
	private JMenu			contact;
	
	// JMenu File //
	private JMenuItem		home;
	private JMenuItem		open;
	private JMenuItem		stop;
	private JMenuItem		save;
	private JMenuItem		exit;
	
	private JMenuItem		screen;
	private JMenuItem		helpus;
	private JMenuItem		contactus;
	
	
	// 媛곴컖���⑤꼸��
	private JPanel 			panel_1; 	// 硫붿씤 珥덇린 �붾㈃(�좊땲硫붿씠��+ 寃����諛뷀깢)
	private JPanel 			panel; 		// �쇱そ �⑤꼸
	public static JPanel 	panel_2; 	// �ㅻⅨ履��⑤꼸
	private JPanel 			panel_3; 	// �ㅻⅨ履�-> EEG AllChannel Data In
	private JPanel 			panel_4;	// �ㅻⅨ履�-> FFT Data
	private JPanel 			panel_5;	// �ㅻⅨ履�-> EEG 3D
	private JPanel 			panel_9;	// �ㅻⅨ履�-> �ъ슜���곹깭 蹂댁뿬二쇨린 �⑤꼸
	
	
	private EEG_Panel_1 	eeg_panel;	// EEG 14梨꾨꼸 �⑤꼸
	private EEG_Explain		eeg_explain;
	private EEG_FFT 		fft; 		// FFT �⑤꼸
	private EEG_3D 			panel_3d;	// 3D �⑤꼸
	private State_Log 		state_log;	// �ъ슜���곹깭 蹂댁뿬二쇨린 �⑤꼸
	
	private ImageIcon start_icon = new ImageIcon("image/power_black.png");
	private ImageIcon end_icon = new ImageIcon("image/stop_red.png");
	
	private ImageIcon home_icon = new ImageIcon("image/home.png");
	private ImageIcon down_icon = new ImageIcon("image/down.png");
	private ImageIcon load_icon = new ImageIcon("image/upload.png");
	private ImageIcon stop_icon = new ImageIcon("image/eeg_stop.png");
	private ImageIcon chart_icon = new ImageIcon("image/chart.png");
	private ImageIcon help_icon = new ImageIcon("image/help.png");
	private ImageIcon contact_icon = new ImageIcon("image/contact.png");
	
	private Image chartimg;
	
	static EEG_Main window;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new EEG_Main();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EEG_Main() {
		db = new DB();
		chartimg = Toolkit.getDefaultToolkit().getImage("image/chart.png");
		
		menubar = new JMenuBar();
		file = new JMenu("   File  ");
		option = new JMenu("  Option  ");
		help = new JMenu("  Help  ");
		contact = new JMenu("  Contact  ");
		
		menubar.add(file);
		menubar.add(option);
		menubar.add(help);
		menubar.add(contact);
		
		home = new JMenuItem("     Home", home_icon);
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							window = new EEG_Main();
							window.frame.setVisible(true);
							window.frame.setResizable(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		open = new JMenuItem("     Open", load_icon);
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load Data");
				FileDialog dialog = new FileDialog(frame);
				dialog.setSize(300, 200);
				dialog.setVisible(true);
				
				if(dialog.getDirectory() != null){
					StringTokenizer str = new StringTokenizer(dialog.getFile(), ".");
					ArrayList<String> list = new ArrayList<String>();
					while(str.hasMoreTokens()){
						list.add(str.nextToken());
					}
					int last = list.size() - 1;
					if(list.get(last).equals("eeg")){
						String path = dialog.getDirectory() + dialog.getFile();
						EEG_Main.filepath = path;
						txtrEegDataLog.append(path + "\n");
						eegdata = new EEG_Data(dialog.getDirectory() + dialog.getFile());
						eeg_panel.set_data(eegdata.getDate_all());
						panel_3d.setdata();
						fft.fft_on();
						eeg_panel.set_on();
						
					}else{
						final Dialog dialog_confirm = new Dialog(frame, "Confirm");
						dialog_confirm.setLayout(new BorderLayout(0, 0));
						dialog_confirm.setBackground(Color.WHITE);
						dialog_confirm.add(new Label("Check your File, Please Select \".eeg\" File!!", Label.CENTER));
						txtrEegDataLog.append("Check your File, Please Select \".eeg\" File!!\n");
						dialog_confirm.setSize(400, 100);
						dialog_confirm.setLocation(100, 100);
						dialog_confirm.setVisible(true);
						dialog_confirm.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent e){
								dialog_confirm.dispose();
							}
						});
					}
				}
			}
		});

		stop = new JMenuItem("     Stop", stop_icon);
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eegdata = null;
				eeg_panel.set_data(null);
				EEG_Main.filepath = null;
				txtrEegDataLog.append("Data loading Stop..\n");
				fft.fft_not();
				eeg_panel.check_set_off();
				fft.desetData();
			}
		});
		
		save = new JMenuItem("     Save", down_icon);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving File");
			}
		});
		
		exit = new JMenuItem("     Exit", end_icon);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		screen = new JMenuItem("     Capture Chart", chart_icon);
		screen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cal = new GregorianCalendar();
				int hour, min, sec;
				hour = cal.get(Calendar.HOUR_OF_DAY);
				min = cal.get(Calendar.MINUTE);
				sec = cal.get(Calendar.SECOND);
				
				db.createFolder2(username);
				txtrEegDataLog.append("Capture Chart " +hour+ ":" +min+ ":" +sec+"\n");
			}
		});
		
		helpus = new JMenuItem("     Help Screen", help_icon);
		helpus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Help_Frame frame = new Help_Frame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		contactus = new JMenuItem("     About & Contact", contact_icon);
		contactus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Contact frame = new Contact();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		file.add(home);
		file.add(open);
		file.add(stop);
		file.add(save);
		file.add(exit);

		help.add(helpus);
		contact.add(contactus);
		option.add(screen);
		
		initialize();
		
		// �쇱そ �⑤꼸
		panel.setVisible(false);
		
		// �ㅻⅨ履��⑤꼸
		panel_2.setVisible(false);
		
		// 硫붿씤珥덇린�붾㈃
		panel_1.setVisible(true);
		
		// 媛곴컖���⑤꼸���④린湲�
		eeg_panel.setVisible(false);
		fft.setVisible(false);
		state_log.setVisible(false);
		panel_3d.setVisible(false);
		
		fft.fft_not();
		eeg_panel.set_dis();
	}

	private void initialize() {
		
		frame = new JFrame(" EEG Analysis System");
		frame.setIconImage(chartimg);
		frame.setJMenuBar(menubar);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1018, 810);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		// �ㅻⅨ履��꾩껜 �⑤꼸
		panel_2 = new JPanel();
		panel_2.setBounds(285, 20, 715, 730);
		frame.getContentPane().add(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new BorderLayout(0, 0));
		
//		// �ㅻⅨ履��꾩껜 �⑤꼸 �덉뿉 ���⑤꼸
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				if(e.getX() > 10 && e.getX() < 145 && e.getY() < 24){
					// EEG Channel
					txtrEegDataLog.append("-> EEG 14 Channel\n");
					eeg_panel.setVisible(true);
					
					fft.setVisible(false);
					
					state_log.setVisible(false);
					state_log.Timer_Stop();
					
					eeg_explain.setVisible(false);
					
					panel_3d.setVisible(false);
					panel_3d.deactive();
					
				}else if(e.getX() > 150 && e.getX() < 230 && e.getY() < 24){
					// EEG FFT
					txtrEegDataLog.append("-> EEG FFT Data Chart\n");
					fft.setVisible(true);
					if(EEGLog.EEG_CONNECT == true){
						fft.fft_on();
					}
					eeg_panel.setVisible(false);
					
					state_log.setVisible(false);
					state_log.Timer_Stop();
					
					panel_3d.setVisible(false);
					panel_3d.deactive();
					
					eeg_explain.setVisible(false);
				}else if(e.getX() > 238 && e.getX() < 370 && e.getY() < 24){
					// EEG 3D Mapping 
					txtrEegDataLog.append("-> EEG 3D Brain Mapping\n");
					
					panel_3d.active();
					panel_3d.setVisible(true);
					
					eeg_panel.setVisible(false);
					
					state_log.setVisible(false);
					state_log.Timer_Stop();
					
					fft.setVisible(false);
					
					eeg_explain.setVisible(false);
				}else if(e.getX() > 385 && e.getX() < 510 && e.getY() < 24){
					// EEG STATE
					txtrEegDataLog.append("-> EEG State Data\n");
					
					state_log.setVisible(true);
					state_log.Timer_Start();
					
					eeg_panel.setVisible(false);
					
					fft.setVisible(false);
					
					panel_3d.setVisible(false);
					panel_3d.deactive();
					
					eeg_explain.setVisible(false);
				}
			}
		});
		tabbedPane.setFont(new Font("留묒� 怨좊뵓", Font.PLAIN, 14));
		panel_2.add(tabbedPane);
		
		/////////////////////// 	EEG Channel Data �⑤꼸
		panel_3 = new JPanel();
		eeg_panel = new EEG_Panel_1();
		
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab(" EEG Channel Data ", null, panel_3, null);
		panel_3.setLayout(null);
		
		eeg_panel.setBackground(Color.WHITE);
		eeg_panel.setBounds(0, 0, 700, 720);
		eeg_panel.set_Panel(eeg_panel);
		panel_3.add(eeg_panel);
		eeg_panel.setLayout(null);
		eeg_panel.setVisible(true);
		
		eeg_explain = new EEG_Explain();
		eeg_explain.setBounds(12, 10, 682, 608);
		eeg_explain.setBackground(Color.WHITE);
		eeg_explain.set_Panel(eeg_explain);
		panel_3.add(eeg_explain);
		eeg_explain.setLayout(null);
		eeg_explain.setVisible(true);
		//////////////////////////////////////////////////////////
		
		/////////////////////////		FFT Data �⑤꼸
		panel_4 = new JPanel();
		fft = new EEG_FFT();
		
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab(" FFT Data ", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		fft.set_Panel(fft);
		panel_4.add(fft);
		fft.setLayout(null);
		fft.setVisible(false);
		//////////////////////////////////////////////////////////
		
		/////////////////////////		 Brain 3D Mapping �⑤꼸
		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab(" Brain 3D Mapping ", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		panel_3d = new EEG_3D();
		panel_3d.set_Panel(panel_3d);
		panel_5.add(panel_3d);
		panel_3d.setLayout(null);
		panel_3d.setVisible(true);
		////////////////////////////////////////////////////////////
		
		
		////////////////////////// �ъ슜���곹깭 諛�媛먯젙 蹂댁뿬二쇨린 �⑤꼸
		panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		tabbedPane.addTab(" Show your State ", null, panel_9, null);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		state_log = new State_Log();
		state_log.set_Panel(state_log);
		panel_9.add(state_log);
		state_log.setLayout(null);
		state_log.setVisible(true);
		////////////////////////////////////////////////////////////
		
		// �쇱そ �곹깭李��⑤꼸
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 20, 261, 732);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		circle = new Circle();
		circle.setBounds(12, 10, 237, 268);
		panel.add(circle);
		circle.setBackground(Color.BLACK);
		circle.init();
		circle.setVisible(true);
		
		//////////////////////////////////////////// �곗씠���쎌뼱�ㅺ린!!
		
		JButton btnNewButton = new JButton("Load", load_icon);
		btnNewButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("Load Data");
				FileDialog dialog = new FileDialog(frame);
				dialog.setSize(300, 200);
				dialog.setVisible(true);
				
				if(dialog.getDirectory() != null){
					StringTokenizer str = new StringTokenizer(dialog.getFile(), ".");
					ArrayList<String> list = new ArrayList<String>();
					while(str.hasMoreTokens()){
						list.add(str.nextToken());
					}
					int last = list.size() - 1;
					if(list.get(last).equals("eeg")){
						String path = dialog.getDirectory() + dialog.getFile();
						EEG_Main.filepath = path;
						txtrEegDataLog.append(path + "\n");
						eegdata = new EEG_Data(dialog.getDirectory() + dialog.getFile());
						eeg_panel.set_data(eegdata.getDate_all());
						panel_3d.setdata();
						fft.fft_on();
						eeg_panel.set_on();
						
					}else{
						final Dialog dialog_confirm = new Dialog(frame, "Confirm");
						dialog_confirm.setLayout(new BorderLayout(0, 0));
						dialog_confirm.setBackground(Color.WHITE);
						dialog_confirm.add(new Label("Check your File, Please Select \".eeg\" File!!", Label.CENTER));
						txtrEegDataLog.append("Check your File, Please Select \".eeg\" File!!\n");
						dialog_confirm.setSize(400, 100);
						dialog_confirm.setLocation(100, 100);
						dialog_confirm.setVisible(true);
						dialog_confirm.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent e){
								dialog_confirm.dispose();
							}
						});
					}
				}
			}
		});
		btnNewButton.setBounds(12, 666, 115, 42);
		panel.add(btnNewButton);
		
		//////////////////////////////////////////// Data load stop
		JButton btnstop = new JButton("Stop", end_icon);
		btnstop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				eegdata = null;
				eeg_panel.set_data(null);
				EEG_Main.filepath = null;
				txtrEegDataLog.append("Data loading Stop..\n");
				fft.fft_not();
				eeg_panel.check_set_off();
				fft.desetData();
			}
		});
		btnstop.setBounds(134, 666, 115, 42);
		panel.add(btnstop);
		///////////////////////////////////	SAVE EEG DATA /////////
		final JButton btnSavingData = new JButton("Save Data", down_icon);
		btnSavingData.setBounds(12, 614, 237, 42);
		btnSavingData.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				cal = new GregorianCalendar();
				
				if(EEG_Main.DB_Click == false){
					EEG_Main.DB_Click = true;
					btnSavingData.setText("Recording...");
					txtrEegDataLog.append("Recording EEG Data...\n");
				}else if(EEG_Main.DB_Click == true){
					EEG_Main.DB_Click = false;
					btnSavingData.setText("Save Data");
					db.createFolder(username);
					
					txtrEegDataLog.append("EEG Data Saved!!\n");
					int hour, min, sec;
					hour = cal.get(Calendar.HOUR_OF_DAY);
					min = cal.get(Calendar.MINUTE);
					sec = cal.get(Calendar.SECOND);
					
					txtrEegDataLog.append("Time - " + hour + ":" + min + ":" + sec + "\n");
					
				}
			}
		});
		panel.add(btnSavingData);
		
		///////////// - �곗씠��濡쒓렇 �④린湲��⑤꼸
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(10, 374, 239, 230);
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		txtrEegDataLog = new JTextArea();
		txtrEegDataLog.setEditable(false);
		txtrEegDataLog.setLineWrap(true);
		scrollpane = new JScrollPane(txtrEegDataLog);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel_7.add(scrollpane, BorderLayout.CENTER);
		txtrEegDataLog.setText("EEG Data Log.....\n");
		//////////////////////////////////////////////

		JLabel lblSamplingRate = new JLabel("Sampling Rate");
		lblSamplingRate.setBounds(12, 318, 120, 21);
		panel.add(lblSamplingRate);
		
		JLabel lblEegSignalQuality = new JLabel("EEG Signal Quality");
		lblEegSignalQuality.setBounds(12, 343, 120, 21);
		panel.add(lblEegSignalQuality);
		
		textField = new JTextField();
		textField.setText("128");
		textField.setBounds(139, 318, 110, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		txtGood = new JTextField();
		txtGood.setText("Good");
		txtGood.setColumns(10);
		txtGood.setBounds(139, 343, 110, 21);
		panel.add(txtGood);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(12, 288, 120, 21);
		panel.add(lblUserName);
		
		textField_2 = new JTextField();
		textField_2.setText(EEG_Main.username);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(139, 288, 110, 21);
		panel.add(textField_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1012, 762);
		panel_1.setBackground(Color.BLACK);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				txtrEegDataLog.append("-> Program Start\n");
				EEG_Main.username = textField_1.getText();
				textField_2.setText(username);
				panel_1.setVisible(false);
				panel.setVisible(true);
				panel_2.setVisible(true);
			}
		});
		btnStart.setBounds(440, 367, 159, 34);
		panel_1.add(btnStart);
		
		JLabel lblEegAnalysisSystem = new JLabel("EEG Analysis System  ver 1.01 beta");
		lblEegAnalysisSystem.setFont(new Font("留묒� 怨좊뵓", Font.BOLD, 25));
		lblEegAnalysisSystem.setHorizontalAlignment(SwingConstants.LEFT);
		lblEegAnalysisSystem.setForeground(Color.WHITE);
		lblEegAnalysisSystem.setBounds(410, 267, 435, 34);
		lblEegAnalysisSystem.setOpaque(false);
		panel_1.add(lblEegAnalysisSystem);
		
		textField_1 = new JTextField();
		textField_1.setText("UserName");
		textField_1.setBounds(506, 324, 264, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NAME :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(440, 324, 44, 21);
		panel_1.add(lblNewLabel);
		
		JButton btnEnd = new JButton("Device Cennect");
		btnEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eeg_device = new EEGLog();
				EEGLog.EEG_CONNECT = true;
				
				EEG_Main.username = textField_1.getText();
				textField_2.setText(username);
				
				txtrEegDataLog.append("-> Program Start\n");
				panel_1.setVisible(false);
				panel_1.removeAll();
				panel.setVisible(true);
				panel_2.setVisible(true);
			}
		});
		btnEnd.setBounds(611, 367, 159, 34);
		panel_1.add(btnEnd);
		
		// 硫붿씤�붾㈃ �꾨줈�몄떛 �⑤꼸 �쎌엯
		sketch = new Neurons();
		sketch.setBounds(50, 100, 533, 565);
		panel_1.add(sketch);
		sketch.setBackground(Color.BLACK);
		sketch.init();
	}
}
