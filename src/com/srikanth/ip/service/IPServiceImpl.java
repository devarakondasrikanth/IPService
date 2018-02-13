package com.srikanth.ip.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IPServiceImpl implements IPService {

	private BlockingQueue<Integer> available = null;
	private BlockingQueue<Integer> assigned = null;
	// IP Adress Range Starts from 1 to NUMBER_IP_ADRESSES
	private int NUMBER_IP_ADRESSES = 650;

	public IPServiceImpl() {
		available = new LinkedBlockingQueue<>();
		assigned = new LinkedBlockingQueue<>();
		fillAvailableIPAddresses();
	}

	@Override
	public int getIP() {
		int temp = 0;
		try {

			temp = available.take();
			System.out.println("IP address assigned -> " + temp);
			assigned.add(temp);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean closeIP(int ip) {
		if (ip < 1 && ip > NUMBER_IP_ADRESSES) {
			return false;
		}

		int temp;
		try {
			temp = assigned.take();
			System.out.println("IP address Released -> " + temp);
			available.add(temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	private void fillAvailableIPAddresses() {
		for (int i = 1; i <= NUMBER_IP_ADRESSES; i++) {
			available.add(i);
		}
	}
}
