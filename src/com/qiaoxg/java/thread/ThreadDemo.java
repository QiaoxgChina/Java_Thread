package com.qiaoxg.java.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadDemo extends Thread {

	public interface onChangedListener {
		void isEnd(boolean isEnd);
	}

	private static ThreadDemo mThreadDemo;

	private Thread t;
	private String threadName;
	private int i = 4;
	private onChangedListener mListener;

	public static ThreadDemo getInstanse() {
		if (mThreadDemo == null) {
			mThreadDemo = new ThreadDemo("");
		}
		return mThreadDemo;
	}

	public void setI(int i) {
		this.i = i;
	}

	public ThreadDemo(String name) {
		threadName = name;
	}

	public void setListener(onChangedListener mListener) {
		this.mListener = mListener;
	}

	public void setThreadName(String name) {
		this.threadName = name;
	}

	public void run() {
		try {
			while (true) {
				while (i >= 0) {
					Date currTime = new Date();
					SimpleDateFormat SDF = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss.SSS");
					System.out.println(SDF.format(currTime) + "===i is " + i);
					i--;
					// 让线程睡醒一会
					Thread.sleep(500);
				}
				System.out.println("\n");
				mListener.isEnd(true);
			}

		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.\n\n\n");
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
