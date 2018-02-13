package com.srikanth.ip.service.test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import com.srikanth.ip.service.IPService;
import com.srikanth.ip.service.IPServiceImpl;

public class IPServiceTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		IPService ipService = new IPServiceImpl();
		ExecutorService executorService = Executors.newFixedThreadPool(5000);
		Map<Integer,Integer> map = new ConcurrentHashMap<>(); 

		Set<Callable<Integer>> callables = new HashSet<Callable<Integer>>();
		for(int i=0;i<65000;i++){
		callables.add(new Callable<Integer>() {
		    public Integer call() throws Exception {
		        int ip =  ipService.getIP();
		        map.put(ip,ip);
		        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 1000 + 1));		        
		        ipService.closeIP(ip);
		        return ip;
		    }
		});
		
		}		
		executorService.invokeAll(callables);
		executorService.shutdown();

	}

}
