package com.srikanth.ip.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IPServiceImpl implements IPService{
	
	private BlockingQueue<Integer> available   = null;
	private BlockingQueue<Integer> assigned   = null;
	
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				 }
		return true;
	}

	private void fillAvailableIPAddresses(){
		for(int i=1;i<=500;i++){
			available.add(i);
		}
	}
}
