package com.liushao.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//100个线程同时跑，观察性能
		final CountDownLatch begin = new CountDownLatch(1);
		
		final CountDownLatch end = new CountDownLatch(100);
		
		final ExecutorService service = Executors.newFixedThreadPool(100);
		
		for(int i = 0 ;i < 100;i++){
			Runnable run =  new Runnable() {
				public void run() {
					try {
						begin.await();
						while(true){
							Connection connection = ConnectionFactory.getConnection();
							Thread.sleep(100);
							ConnectionFactory.closeConnection(connection);
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally {
						end.countDown();
					}
				}
			};
			service.submit(run);
		}
		
		System.out.println("start");
		begin.countDown();
		end.await();
		service.shutdown();
		
	}
	
	
}
