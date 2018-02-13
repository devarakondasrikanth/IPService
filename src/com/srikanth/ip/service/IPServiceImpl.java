package com.srikanth.ip.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IPServiceImpl implements IPService{
	
	private BlockingQueue<Integer> available   = null;
	private BlockingQueue<Integer> assigned   = null;
	//IP Adress Range Starts from 1 to NUMBER_IP_ADRESSES
	private int NUMBER_IP_ADRESSES = 500;
	
	public IPServiceImpl(){
		available   = new LinkedBlockingQueue<>();
		assigned   = new LinkedBlockingQueue<>();
		fillAvailableIPAddresses();
	}

	@Override
	public int getIP() {
		int temp = 0;
		try {
			 if(!available.isEmpty()){
				 temp = available.take();
				System.out.println("IP address assigned -> "+temp);
				assigned.add(temp);
			}else{
				//Add this request to waiting queue and retry after few minutes
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean closeIP(int ip) {
				 if(!assigned.isEmpty()){
					int temp;
					try {
						temp = assigned.take();
						System.out.println("IP address Released -> "+temp);
						available.add(temp);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				 }
		return true;
	}

	private void fillAvailableIPAddresses(){
		for(int i=1;i<=NUMBER_IP_ADRESSES;i++){
			available.add(i);
		}
	}
}
