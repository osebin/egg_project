package panel;

import javax.swing.*;

import org.jfree.chart.*;

import EEGLog.EEGLog;

import chart.*;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.text.DecimalFormat;

public class State_Log extends JPanel {
	
	public static double emoState[] = new double[6];
	JPanel Panel;
	BarChart_State state;
	
	DecimalFormat format;
	private JTextArea txtrUserStateLog;
//	panel_1.setBounds(12, 10, 682, 608);
	
	public State_Log() {
		format = new DecimalFormat("#.##");
		init();
		
		StateThread thread = new StateThread();
		thread.setDaemon(true);
		thread.start();
	}
	
	public void set_Panel(State_Log panel){
		panel.add(Panel);
	}
	
	private void init(){
		Panel = new JPanel();
		Panel.setBackground(Color.WHITE);
		Panel.setBounds(12, 10, 682, 690);
		Panel.setVisible(true);
		Panel.setLayout(null);
		
		state = new BarChart_State();
		JFreeChart state_chart = state.createDemoPanel();
		ChartPanel state_panel = new ChartPanel(state_chart);
		
		state_panel.setBounds(12, 137, 658, 543);
		state_panel.setVisible(true);
		
		Panel.add(state_panel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(34, 10, 423, 117);
		Panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		txtrUserStateLog = new JTextArea();
		txtrUserStateLog.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		txtrUserStateLog.setWrapStyleWord(true);
		txtrUserStateLog.setText("Excitement-S : " + (int)(State_Log.emoState[0]*10) + "  Excitement-L : "+(int)(State_Log.emoState[1]*10)+"  Meditation : "+ (int)(State_Log.emoState[2]*10) + 
								"\nBoredom : "+(int)(State_Log.emoState[3]*10)+"  Frustration : "+(int)(State_Log.emoState[4]*10)+"  Concentration : 0.0\n");
		scrollPane.setViewportView(txtrUserStateLog);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		textArea.setWrapStyleWord(true);
		textArea.setBounds(469, 10, 201, 117);

		textArea.append("Excitement-S : 순간적인 흥분\n");
		textArea.append("Excitement-L : 장기적인 흥분\n");
		textArea.append("Boredom : 지루한 상태 \n");
		textArea.append("Frustration : 불쾌한 상태\n");
		textArea.append("Concentration : 집중한 상태\n");
		Panel.add(textArea);
		
	}
	
	public void Timer_Start(){
		state.Timer_Start();
	}
	
	public void Timer_Stop(){
		state.Timer_Stop();
	}
	
	class StateThread extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(EEGLog.EEG_CONNECT == true)
				txtrUserStateLog.append("Excitement-S : " + format.format(State_Log.emoState[0]) + "  Excitement-L : "+format.format(State_Log.emoState[1])+"  Meditation : "+ format.format(State_Log.emoState[2]) + 
								"\nBoredom : "+ format.format(State_Log.emoState[3])+"  Frustration : "+ format.format(State_Log.emoState[4]*10)+"  Concentration : " + (int)(State_Log.emoState[5])+ "\n");
				
				txtrUserStateLog.setCaretPosition(txtrUserStateLog.getText().length());
				txtrUserStateLog.requestFocus();
			}
		}
	}
}
