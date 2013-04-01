package panel;

import javax.swing.*;

import org.jfree.chart.*;

import EEGLog.EEGLog;

import processing.Circle;
import windowbuilder.EEG_Main;

import chart.*;
import fft.FFT_Topo;
import fft.Topo;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;

import jogl_topo.JOGL_Topo;
import jogl_topo.JOGL_Topo_Gradient;

public class EEG_Panel_1 extends JPanel implements ItemListener{
	
	JCheckBox Hi;
	JCheckBox All;
	JCheckBox AF3;
	JCheckBox F7;
	JCheckBox F3;
	JCheckBox FC5;
	JCheckBox T7;
	JCheckBox P7;
	JCheckBox O1;
	JCheckBox O2;
	JCheckBox P8;
	JCheckBox T8; 
	JCheckBox FC6;
	JCheckBox F4; 
	JCheckBox F8; 
	JCheckBox AF4; 
	
	private JSeparator separator_2;
	
	private JPanel Panel;
	private AllChannel 			all; // 14 채널 차트
	private TimeLineChart_1 	chart_1; // 싱글 채널 차트
	
	public static boolean hi_pass = true;
//	panel_1.setBounds(12, 10, 682, 608);
	
	public EEG_Panel_1() {
		
		set_checkbox();
		init();
	}
	
	public void set_Panel(EEG_Panel_1 panel){
		panel.add(Panel);
	}
	
	public void set_data(StringTokenizer[] str){
		all.setData(str);
	}
	
	public void Timer_Start(int id){
		if(id == 0){
			all.Timer_Start();			
		}else if(id == 1){
			chart_1.Timer_Start();
		}
	}
	
	public void Timer_Stop(int id){
		if(id == 0){
			all.Timer_Stop();
		}else if(id == 1){
			chart_1.Timer_Stop();
		}
	}
	private void init(){
		Panel = new JPanel();
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(12, 10, 682, 720);
		Panel.setVisible(true);
		Panel.setLayout(null);
		
		all = new AllChannel();
		JFreeChart chart = all.getChart();
		ChartPanel panel = new ChartPanel(chart);
		panel.setBounds(113, 0, 569, 429);
		panel.setVisible(true);
		Panel.add(panel);
		
		chart_1 = new TimeLineChart_1();
		JFreeChart chart2 = chart_1.getChart();
		ChartPanel panel2 = new ChartPanel(chart2);
		panel2.setBounds(0, 456, 682, 220);
		panel2.setVisible(true);
		Panel.add(panel2);
		
		Panel.add(Hi);
		Panel.add(All);
		Panel.add(AF3);
		Panel.add(F7);
		Panel.add(F3);
		Panel.add(FC5);
		Panel.add(T7);
		Panel.add(P7);
		Panel.add(O1);
		Panel.add(O2);
		Panel.add(P8);
		Panel.add(T8);
		Panel.add(FC6);
		Panel.add(F4);
		Panel.add(F8);
		Panel.add(AF4);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setBounds(5, 32, 100, 12);
		Panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(5, 62, 100, 12);
		Panel.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(8, 400, 100, 12);
		Panel.add(separator_2);
	}
	
	private void set_checkbox(){
		Hi = new JCheckBox("H-Pass Filter");
		Hi.setBackground(Color.WHITE);
		Hi.setBounds(8, 6, 100, 20);
		Hi.addItemListener(this);
		
		All = new JCheckBox("All Channel");
		All.setBackground(Color.WHITE);
		All.setBounds(8, 36, 100, 20);
		All.addItemListener(this);
		
		AF3 = new JCheckBox(" AF3"); // 1
		AF3.setBackground(Color.WHITE);
		AF3.setBounds(8, 76, 100, 20);
		AF3.addItemListener(this);
		
		F7 = new JCheckBox(" F7"); // 2
		F7.setBackground(Color.WHITE);
		F7.setBounds(8, 98, 100, 20);
		F7.addItemListener(this);
		
		F3 = new JCheckBox(" F3"); // 3
		F3.setBackground(Color.WHITE);
		F3.setBounds(8, 120, 100, 20);
		F3.addItemListener(this);
		
		FC5 = new JCheckBox(" FC5"); // 4
		FC5.setBackground(Color.WHITE);
		FC5.setBounds(8, 142, 100, 20);
		FC5.addItemListener(this);
		
		T7 = new JCheckBox(" T7"); // 5
		T7.setBackground(Color.WHITE);
		T7.setBounds(8, 164, 100, 20);
		T7.addItemListener(this);
		
		P7 = new JCheckBox(" P7"); // 6
		P7.setBackground(Color.WHITE);
		P7.setBounds(8, 186, 100, 20);
		P7.addItemListener(this);
		
		O1 = new JCheckBox(" O1"); // 7
		O1.setBackground(Color.WHITE);
		O1.setBounds(8, 208, 100, 20);
		O1.addItemListener(this);
		
		O2 = new JCheckBox(" O2"); // 8
		O2.setBackground(Color.WHITE);
		O2.setBounds(8, 230, 100, 20);
		O2.addItemListener(this);
		
		P8 = new JCheckBox(" P8"); // 9
		P8.setBackground(Color.WHITE);
		P8.setBounds(8, 252, 100, 20);
		P8.addItemListener(this);
		
		T8 = new JCheckBox(" T8"); // 10
		T8.setBackground(Color.WHITE);
		T8.setBounds(8, 273, 100, 20);
		T8.addItemListener(this);
		
		FC6 = new JCheckBox(" FC6"); // 11
		FC6.setBackground(Color.WHITE);
		FC6.setBounds(8, 295, 100, 20);
		FC6.addItemListener(this);
		
		F4 = new JCheckBox(" F4"); // 12
		F4.setBackground(Color.WHITE);
		F4.setBounds(8, 317, 100, 20);
		F4.addItemListener(this);
		
		F8 = new JCheckBox(" F8"); // 13
		F8.setBackground(Color.WHITE);
		F8.setBounds(8, 339, 100, 20);
		F8.addItemListener(this);
		
		AF4 = new JCheckBox(" AF4"); // 14
		AF4.setBackground(Color.WHITE);
		AF4.setBounds(8, 361, 100, 20);
		AF4.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getItem() == All){
			if(EEGLog.EEG_CONNECT == true){
				set_on();
			}
			if(e.getStateChange() == ItemEvent.SELECTED){
				Timer_Start(0);
				Timer_Start(1);
				Hi.setSelected(true);
				Hi.setEnabled(false);
			}else{
				Timer_Stop(0);
				Timer_Stop(1);
				Hi.setEnabled(true);
				Hi.setSelected(false);
				
				Circle.P_g = 0;
				Circle.P_b = 0;
				Circle.P_r = 0;
			}
		}else if(e.getItem() == Hi){
			if(e.getStateChange() == ItemEvent.SELECTED){
				EEG_Panel_1.hi_pass = true;
			}else{
				EEG_Panel_1.hi_pass = false;
			}
		}else if(e.getItem() == AF3){
			if(e.getStateChange() == ItemEvent.SELECTED){
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(0));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 0;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == F7){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(1));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 1;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == F3){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(2));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 2;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == FC5){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(3));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 3;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == T7){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(4));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 4;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == P7){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(5));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 5;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == O1){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(6));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 6;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == O2){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(7));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 7;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == P8){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(8));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 8;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == T8){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(9));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 9;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == FC6){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(10));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 10;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == F4){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F8.setSelected(false); 
				AF4.setSelected(false);
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(11));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 11;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == F8){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				AF4.setSelected(false);
				
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(12));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 12;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}else if(e.getItem() == AF4){
			if(e.getStateChange() == ItemEvent.SELECTED){
				AF3.setSelected(false);
				F7.setSelected(false);
				F3.setSelected(false);
				FC5.setSelected(false);
				T7.setSelected(false);
				P7.setSelected(false);
				O1.setSelected(false);
				O2.setSelected(false);
				P8.setSelected(false);
				T8.setSelected(false);
				FC6.setSelected(false);
				F4.setSelected(false); 
				F8.setSelected(false);
				
				if(EEGLog.EEG_CONNECT == false)
				chart_1.setData(EEG_Main.eegdata.getData(13));
				
				if(EEGLog.EEG_CONNECT == true)
				TimeLineChart_1.EEG_Channel = 13;
			}else{
				chart_1.setData(null);
				TimeLineChart_1.EEG_Channel = 99;
			}
		}
	}
	
	public void check_set_off(){
		AF3.setSelected(false);
		F7.setSelected(false);
		F3.setSelected(false);
		FC5.setSelected(false);
		T7.setSelected(false);
		P7.setSelected(false);
		O1.setSelected(false);
		O2.setSelected(false);
		P8.setSelected(false);
		T8.setSelected(false); 
		FC6.setSelected(false);
		F4.setSelected(false); 
		F8.setSelected(false); 
		AF4.setSelected(false); 
	}
	
	public void set_dis(){
		AF3.setEnabled(false);
		F7.setEnabled(false);
		F3.setEnabled(false);
		FC5.setEnabled(false);
		T7.setEnabled(false);
		P7.setEnabled(false);
		O1.setEnabled(false);
		O2.setEnabled(false);
		P8.setEnabled(false);
		T8.setEnabled(false);
		FC6.setEnabled(false);
		F4.setEnabled(false); 
		F8.setEnabled(false); 
		AF4.setEnabled(false);
	}
	
	public void set_on(){
		AF3.setEnabled(true);
		F7.setEnabled(true);
		F3.setEnabled(true);
		FC5.setEnabled(true);
		T7.setEnabled(true);
		P7.setEnabled(true);
		O1.setEnabled(true);
		O2.setEnabled(true);
		P8.setEnabled(true);
		T8.setEnabled(true);
		FC6.setEnabled(true);
		F4.setEnabled(true); 
		F8.setEnabled(true); 
		AF4.setEnabled(true);
	}
}
