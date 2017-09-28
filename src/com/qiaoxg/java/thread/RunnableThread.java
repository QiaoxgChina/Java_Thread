package com.qiaoxg.java.thread;

/**
 * ͨ��ʵ��Runnable����������Thread
 * 
 * @author admin
 * 
 */
public class RunnableThread implements Runnable {

	String threadName;
	Thread thread;

	public RunnableThread(String threadName) {
		this.threadName = threadName;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				// ���߳�˯��һ��
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}
