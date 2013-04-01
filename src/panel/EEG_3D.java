package panel;

import javax.media.opengl.GLCapabilities;
import javax.swing.*;

import org.jfree.chart.*;

import processing.Circle;
import windowbuilder.EEG_Main;

import chart.*;
import fft.FFT_Topo;
import fft.Topo;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.swing.border.EtchedBorder;

import jogl_topo.JOGL_Topo;
import jogl_topo.JOGL_Topo_Gradient;
import jogl_topo.JOGL_Topo_Up;

import java.awt.BorderLayout;

public class EEG_3D extends JPanel {
	
	private JPanel Panel;
	public static FFT_Topo topo;
	
	private GLCapabilities 		cap;
	private GLCapabilities 		cap2;
	
	private JOGL_Topo 			canvas;
	private JOGL_Topo_Up		canvas2;
	
	private JPanel 				panel;
	private JPanel 				panel_1;
	
	private SpiderWebChart		spider;
	
	private JPanel 				text_panel;
	private JTextArea			txtlog;
	private JScrollPane			scroll;
	
//	private Brain_Data			brain_thread;
	private DATA_process		process;
	private Thread_Pearson 		pearson_thread;
	private DATA_Log			log_thread;
	
	private DecimalFormat format;
	public EEG_3D() {
		init();
		format = new DecimalFormat("#.##");
		
		process = new DATA_process();
		process.setDaemon(true);
		process.start();
		
		pearson_thread = new Thread_Pearson();
		pearson_thread.setDaemon(true);
		pearson_thread.start();
		
		log_thread = new DATA_Log();
		log_thread.setDaemon(true);
		log_thread.start();
	}
	
	public void setdata(){
		topo = new FFT_Topo(EEG_Main.filepath);
	}
	
	public void set_Panel(EEG_3D panel){
		panel.add(Panel);
	}
	
	private void init(){
		Panel = new JPanel();
		Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(12, 10, 682, 677);
		Panel.setVisible(true);
		Panel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 227, 329, 433);
		panel.setLayout(new BorderLayout(0, 0));
		
		Panel.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(341, 227, 329, 433);
		panel_1.setLayout(new BorderLayout(0, 0));
		Panel.add(panel_1);
		
		spider = new SpiderWebChart();
		JFreeChart chart = spider.createPanel();
		ChartPanel panel_spider = new ChartPanel(chart);
		panel_spider.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_spider.setBackground(Color.WHITE);
		panel_spider.setBounds(12, 10, 237, 207);
		Panel.add(panel_spider);
		
		text_panel = new JPanel();
		text_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		text_panel.setBounds(261, 10, 409, 207);
		text_panel.setLayout(new BorderLayout(0,0));
		
		txtlog = new JTextArea();
		txtlog.setWrapStyleWord(true);
		txtlog.setEditable(false);
		txtlog.setLineWrap(true);
		scroll = new JScrollPane();
		scroll.setViewportView(txtlog);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setAutoscrolls(true);
		text_panel.add(scroll, BorderLayout.CENTER);
		Panel.add(text_panel);
		
		txtlog.append("EEG Data Log...\n");

	}
	
	public void active(){
		cap = JOGL_Topo.createGLCapabilities();
		canvas = new JOGL_Topo(cap, 317, 399);
		
		cap2 = JOGL_Topo_Up.createGLCapabilities2();
		canvas2 = new JOGL_Topo_Up(cap2, 329, 399);
		
		panel.add(canvas);
		panel_1.add(canvas2);
		
		spider.dataStart();
	}
	
	@SuppressWarnings("deprecation")
	public void deactive(){
		
		panel.removeAll();
		panel_1.removeAll();
		spider.dataStop();
	}
	
	class DATA_Log extends Thread{
		private double log[] = new double[5];
		public void run(){
			while(true){
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log = SpiderWebChart.logdata;
				txtlog.append("DELTA : " + format.format(log[0]) +
								"  THETA : " + format.format(log[1]) +
								"  ALPHA : " + format.format(log[2]) +
								"  SMR : " + format.format(log[3]) +
								"  BETA : " + format.format(log[4])+"\n");
				
				txtlog.setCaretPosition(txtlog.getText().length());
				txtlog.requestFocus();
			}
		}
	}
	class DATA_process extends Thread{
		Topo []DATA = new Topo[14];
		public void run(){
			while(true){
				try {
					Thread.sleep(1000);
					int EEG = 5;
					int []Jogldata = new int[14];
					
					if(EEG != 0 && EEG_Main.filepath != null){
						DATA = EEG_3D.topo.get_data();
						
						if(EEG == 5){
							for(int i = 0; i < 14; i++){
								int sum = CAL_Sum(DATA, EEG);

								if(i == 0 || i == 1 || i == 12 || i == 13){
									Jogldata[i] = (int)((DATA[i].BETA/sum)*100);
								}else{
									Jogldata[i] = (int)((DATA[i].BETA/sum)*100) - 5;
								}
							}
						}
						
						JOGL_Topo.Color_data = Jogldata;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private int CAL_Sum(Topo []Data, int EEG ){
		Topo []data = new Topo[14];
		data = Data;
		int sum = 0;
		
		if(EEG == 1){
			for(int i = 0; i < 14; i++){
				sum += data[i].DELTA;
			}
		}else if(EEG == 2){
			for(int i = 0; i < 14; i++){
				sum += data[i].THETA;
			}			
		}else if(EEG == 3){
			for(int i = 0; i < 14; i++){
				sum += data[i].ALPHA;
			}
		}else if(EEG == 4){
			for(int i = 0; i < 14; i++){
				sum += data[i].SMR;
			}
		}else if(EEG == 5){
			for(int i = 0; i < 14; i++){
				sum += data[i].BETA;
			}
		}
		
		return sum;
	}
	
	class Thread_Pearson extends Thread{
		double [][]channel = new double[14][14];
		int [][]channel_order = new int[14][14];
		
		double []value = new double[14];

		public void run(){
			while(true){
				try{
					Thread.sleep(1000);
					if(JOGL_Topo_Up.active != null){
						setActive();
					}				
					if(AllChannel.Pearson != null){
						value = AllChannel.Pearson;
						
						for(int i = 0; i < value.length; i++){
							double data = value[i];
							for(int j = 0; j < value.length; j++){
								channel[i][j] = Math.sqrt(Math.pow(data-value[j],2));
							}
						}
						
//						for(int i = 0; i < value.length; i++){
//							double data[] = channel[i];
//							Min_Sort(i, data, value.length);
//						}
						
						JOGL_Topo_Up.Channel_order = channel;
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
		private void Min_Sort(int order, double[] data, int num){
			int minidx = 0;
			int i,j;
			
			for(i = 0; i < num; i++){
				for(minidx = i,j = 0; j < num; j++){
					if(data[minidx] > data[j]){
						minidx = j;
					}
				}
				
				channel_order[order][i] = minidx;
				data[minidx] = 9999;
			}
		}// End of Min_Sort
		
		private void setActive(){
			channeloff();
			
			switch(EEG_FFT.Channel_PS){
			case 0:
				JOGL_Topo_Up.active.channel1 = true;
				break;
			case 1:
				JOGL_Topo_Up.active.channel2 = true;
				break;
			case 2:
				JOGL_Topo_Up.active.channel3 = true;
				break;
			case 3:
				JOGL_Topo_Up.active.channel4 = true;
				break;
			case 4:
				JOGL_Topo_Up.active.channel5 = true;
				break;
			case 5:
				JOGL_Topo_Up.active.channel6 = true;
				break;
			case 6:
				JOGL_Topo_Up.active.channel7 = true;
				break;
			case 7:
				JOGL_Topo_Up.active.channel8 = true;
				break;
			case 8:
				JOGL_Topo_Up.active.channel9 = true;
				break;
			case 9:
				JOGL_Topo_Up.active.channel10 = true;
				break;
			case 10:
				JOGL_Topo_Up.active.channel11 = true;
				break;
			case 11:
				JOGL_Topo_Up.active.channel12 = true;
				break;
			case 12:
				JOGL_Topo_Up.active.channel13 = true;
				break;
			case 13:
				JOGL_Topo_Up.active.channel14 = true;
				break;
			default :
				channeloff();
			}
		}
		
		private void channeloff(){
			if(JOGL_Topo_Up.active != null){
				JOGL_Topo_Up.active.channel1 = false;
				JOGL_Topo_Up.active.channel2 = false;
				JOGL_Topo_Up.active.channel3 = false;
				JOGL_Topo_Up.active.channel4 = false;
				JOGL_Topo_Up.active.channel5 = false;
				JOGL_Topo_Up.active.channel6 = false;
				JOGL_Topo_Up.active.channel7 = false;
				JOGL_Topo_Up.active.channel8 = false;
				JOGL_Topo_Up.active.channel9 = false;
				JOGL_Topo_Up.active.channel10 = false;
				JOGL_Topo_Up.active.channel11 = false;
				JOGL_Topo_Up.active.channel12 = false;
				JOGL_Topo_Up.active.channel13 = false;
				JOGL_Topo_Up.active.channel14 = false;
			}
		}
	}
}
