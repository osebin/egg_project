package EEGLog;

import panel.EEG_FFT;
import panel.State_Log;
import windowbuilder.*;
import chart.*;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import fft.*;

public class EEGLog {
	static int y1 = 0;
	public static boolean EEG_CONNECT = false;
	public EEGLog(){
		Thread_EEG eeg = new Thread_EEG();
		eeg.setDaemon(true);
		eeg.start();
	}
	
	class Thread_EEG extends Thread{
		public void run(){
			Pointer eEvent				= Edk.INSTANCE.EE_EmoEngineEventCreate();
	    	Pointer eState				= Edk.INSTANCE.EE_EmoStateCreate();
	    	IntByReference userID 		= null;
			IntByReference nSamplesTaken= null;
	    	short composerPort			= 1726;
	    	int option 					= 1;
	    	int state  					= 0;
	    	float secs 					= 1;
	    	boolean readytocollect 		= false;
	    	
	    	userID 			= new IntByReference(0);
			nSamplesTaken	= new IntByReference(0);
	    	
	    	switch (option) {
			case 1:
			{
				if (Edk.INSTANCE.EE_EngineConnect("Emotiv Systems-5") != EdkErrorCode.EDK_OK.ToInt()) {
					System.out.println("Emotiv Engine start up failed.");
					EEG_Main.txtrEegDataLog.append("Emotiv Engine start up failed.\n");
					return;
				}
				break;
			}
			default:
				System.out.println("Invalid option...");
				return;
	    	}
	    	
			Pointer hData = Edk.INSTANCE.EE_DataCreate();
			Edk.INSTANCE.EE_DataSetBufferSizeInSec(secs);
	    		
	    	System.out.println("Start receiving EEG Data!");
	    	EEG_Main.txtrEegDataLog.append("Start receiving EEG Data!\n");
			while(true){
				while (true) 
				{
					state = Edk.INSTANCE.EE_EngineGetNextEvent(eEvent);

					// New event needs to be handled
					if (state == EdkErrorCode.EDK_OK.ToInt()) 
					{
						int eventType = Edk.INSTANCE.EE_EmoEngineEventGetType(eEvent);
						Edk.INSTANCE.EE_EmoEngineEventGetUserId(eEvent, userID);

						// Log the EmoState if it has been updated
						if (eventType == Edk.EE_Event_t.EE_UserAdded.ToInt()) 
						if (userID != null)
							{
								System.out.println("User added");
								EEG_Main.txtrEegDataLog.append("User added\n");
								Edk.INSTANCE.EE_DataAcquisitionEnable(userID.getValue(),true);
								readytocollect = true;
							}
						
						if(eventType == Edk.EE_Event_t.EE_EmoStateUpdated.ToInt()){
							Edk.INSTANCE.EE_EmoEngineEventGetEmoState(eEvent, eState);
							
							State_Log.emoState[0] = EmoState.INSTANCE.ES_AffectivGetExcitementShortTermScore(eState);
							State_Log.emoState[1] = EmoState.INSTANCE.ES_AffectivGetExcitementLongTermScore(eState);
							State_Log.emoState[2] = EmoState.INSTANCE.ES_AffectivGetEngagementBoredomScore(eState);
							State_Log.emoState[3] = EmoState.INSTANCE.ES_AffectivGetFrustrationScore(eState);
							State_Log.emoState[4] = EmoState.INSTANCE.ES_AffectivGetMeditationScore(eState);
						}
					}
					else if (state != EdkErrorCode.EDK_NO_EVENT.ToInt()) {
						System.out.println("Internal error in Emotiv Engine!");
						EEG_Main.txtrEegDataLog.append("Internal error in Emotiv Engine!\n");
						break;
					}
					
					if (readytocollect) 
					{
						Edk.INSTANCE.EE_DataUpdateHandle(0, hData);

						Edk.INSTANCE.EE_DataGetNumberOfSample(hData, nSamplesTaken);

						if (nSamplesTaken != null)
						{
							if (nSamplesTaken.getValue() != 0 && EEGLog.EEG_CONNECT == true) {

								double[] data = new double[nSamplesTaken.getValue()];
								
								double[] data_single_1 = new double[nSamplesTaken.getValue()];
								double[] data_single_2 = new double[nSamplesTaken.getValue()];
								double[] data_single_3 = new double[nSamplesTaken.getValue()];
								double[] data_single_4 = new double[nSamplesTaken.getValue()];
								double[] data_single_5 = new double[nSamplesTaken.getValue()];
								double[] data_single_6 = new double[nSamplesTaken.getValue()];
								double[] data_single_7 = new double[nSamplesTaken.getValue()];
								double[] data_single_8 = new double[nSamplesTaken.getValue()];
								double[] data_single_9 = new double[nSamplesTaken.getValue()];
								double[] data_single_10 = new double[nSamplesTaken.getValue()];
								double[] data_single_11 = new double[nSamplesTaken.getValue()];
								double[] data_single_12 = new double[nSamplesTaken.getValue()];
								double[] data_single_13 = new double[nSamplesTaken.getValue()];
								double[] data_single_14 = new double[nSamplesTaken.getValue()];
							
								
								for(int sampleIdx=0 ; sampleIdx<nSamplesTaken.getValue() ; ++ sampleIdx){
									double[] EEG_DATA = new double[14];
									double value=0;
									
									for (int i = 3 ; i < 17 ; i++) {
										
										Edk.INSTANCE.EE_DataGet(hData, i, data, nSamplesTaken.getValue());
										EEG_DATA[i-3] = (data[sampleIdx]-4200);
									}
									
									data_single_1[sampleIdx] = EEG_DATA[0];
									data_single_2[sampleIdx] = EEG_DATA[1];
									data_single_3[sampleIdx] = EEG_DATA[2];
									data_single_4[sampleIdx] = EEG_DATA[3];
									data_single_5[sampleIdx] = EEG_DATA[4];
									data_single_6[sampleIdx] = EEG_DATA[5];
									data_single_7[sampleIdx] = EEG_DATA[6];
									data_single_8[sampleIdx] = EEG_DATA[7];
									data_single_9[sampleIdx] = EEG_DATA[8];
									data_single_10[sampleIdx] = EEG_DATA[9];
									data_single_11[sampleIdx] = EEG_DATA[10];
									data_single_12[sampleIdx] = EEG_DATA[11];
									data_single_13[sampleIdx] = EEG_DATA[12];
									data_single_14[sampleIdx] = EEG_DATA[13];
								}
								int times = 2;
								double[] out = new double[nSamplesTaken.getValue()];
								
								AllChannel.EEGDATA[0] = HighPassFilter(data_single_1)[2] *times;
								AllChannel.EEGDATA[1] = HighPassFilter(data_single_2)[2] *times;
								AllChannel.EEGDATA[2] = HighPassFilter(data_single_3)[2] *times;
								AllChannel.EEGDATA[3] = HighPassFilter(data_single_4)[2] *times;
								AllChannel.EEGDATA[4] = HighPassFilter(data_single_5)[2] *times;
								AllChannel.EEGDATA[5] = HighPassFilter(data_single_6)[2] *times;
								AllChannel.EEGDATA[6] = HighPassFilter(data_single_7)[2] *times;
								AllChannel.EEGDATA[7] = HighPassFilter(data_single_8)[2] *times;
								AllChannel.EEGDATA[8] = HighPassFilter(data_single_9)[2] *times;
								AllChannel.EEGDATA[9] = HighPassFilter(data_single_10)[2] *times;
								AllChannel.EEGDATA[10] = HighPassFilter(data_single_11)[2] *times;
								AllChannel.EEGDATA[11] = HighPassFilter(data_single_12)[2] *times;
								AllChannel.EEGDATA[12] = HighPassFilter(data_single_13)[2] *times;
								AllChannel.EEGDATA[13] = HighPassFilter(data_single_14)[2] *times;
								
								
								if(EEG_FFT.Channel_PS > 0){
									BarChart_Line.EEG_DATA_PS.add(AllChannel.EEGDATA[EEG_FFT.Channel_PS - 1]);
								}
							}
						}
					}
				}
		    	
		    	Edk.INSTANCE.EE_EngineDisconnect();
		    	Edk.INSTANCE.EE_EmoStateFree(eState);
		    	Edk.INSTANCE.EE_EmoEngineEventFree(eEvent);
		    	System.out.println("Disconnected!");
		    	EEG_Main.txtrEegDataLog.append("Disconnected!\n");
			}
		}
		double[][] totaldata=new double[128][14];
		int c=0;
		int cnt=0;
		double[] av=new double[14];
		
        private double[] HighPassFilter(double[] input) 
        { 
            float fCut = 0.16F; 
            final float W = 2.0F * 128; 

            fCut *= 6.28318530717959F; // 2.0F * Math.Pi 
            float norm = 1.0F / (fCut + W); 
            float a0 = W * norm; 
            float a1 = -a0; 
            float b1 = (W - fCut) * norm; 

            double[] output = new double[input.length]; 

            for (int i = 0; i < input.length - 1; i++) 
            { 
                if (i - 1 > 0) 
                output[i] = input[i] * a0 + input[i - 1] * a1 + output[i - 1] * b1;
            } 

            return output;
        } 
	}
}
