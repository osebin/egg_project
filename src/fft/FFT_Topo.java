package fft;

import java.util.*;

import eegdata.*;

public class FFT_Topo {
	EEG_Data topo_eeg_data;
	StringTokenizer []str;
	FFT Topo_fft;
	Topo []topo = new Topo[14];
	
	public FFT_Topo(String topo_path){
		
		topo_eeg_data = new EEG_Data(topo_path);
		str = new StringTokenizer[14];
		
		Topo_fft = new FFT();
		
		for(int i = 0; i < str.length; i++){
			str[i] = topo_eeg_data.getData(i);
		}	
	}
	
	public Topo[] get_data(){
		
		int Number = 128;
		Complex[] x = new Complex[Number];
		Complex[] x_out = new Complex[Number * 8];
		ArrayList<Double> data = new ArrayList<Double>();
			
		for(int i = 0; i < str.length; i++){ // 채널 1번 부터 14번 까지.
				
			for(int j = 0; j < Number; j++){
				x[j] = new Complex(j, 0);
				
				if(str[i].hasMoreTokens()){
					x[j] = new Complex(Double.valueOf(str[i].nextToken()), 0);
				}
			}
			
			int inn = 0;
			for (int j = 0; j < Number*8; j++){
				x_out[j] = x[inn];
				if(j != 0 && j % 8 == 0){
					inn++;
				}
			}
			
			Complex[] y = Topo_fft.fft(x_out);
			data = (Topo_fft.Calculator(y));
			
			topo[i] = new Topo();
			
			for(int in = 0; in < 4; in++){// 0 1 2 3 >> 1 ~ 4
				topo[i].DELTA += data.get(in);
			}
			for(int in = 3; in < 8; in++){// 3 4 5 6 7 >> 4 ~ 8 
				topo[i].THETA += data.get(in);
			}
			for(int in = 7; in < 12; in++){// 7 8 9 10 11 >> 8 ~ 12 
				topo[i].ALPHA += data.get(in);
			}
			for(int in = 11; in < 15; in++){// 11 12 13 14 >> 12 ~ 15 
				topo[i].SMR += data.get(in);
			}
			for(int in = 14; in < 18; in++){// 14 15 16 17 >> 15 ~ 18 
				topo[i].BETA += data.get(in);
			}
		}	
		return topo;
		
	} // Topo[] get_data() End
}


