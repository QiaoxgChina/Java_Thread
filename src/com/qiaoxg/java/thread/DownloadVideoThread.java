package com.qiaoxg.java.thread;

import com.qiaoxg.java.listener.DownloadListener;

public class DownloadVideoThread extends Thread {

	private Thread t;
	private DownloadListener mListener;
	private int mDownloadCount = 0;
	private static DownloadVideoThread mDownloadVideoThread;
	private static boolean isStart = false;
	private boolean isSuccess = false;

	public static DownloadVideoThread getInstanse() {
		if (mDownloadVideoThread == null) {
			mDownloadVideoThread = new DownloadVideoThread();
		}
		return mDownloadVideoThread;
	}

	public void run() {
		super.run();
		while (true) {
			while (mDownloadCount <= 100) {
				isSuccess = false;
				mListener.downloading(mDownloadCount);
				mDownloadCount++;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					mListener.downloadFail(e.getMessage());
				}
			}
			
			if(!isSuccess){
				mListener.onSuccess("下载成功");
			}
			isSuccess = true;
		}
	}

	public synchronized void start() {
		super.start();
		if (t == null) {
			t = new Thread();
		}
		t.start();
		isStart = true;
	}

	public void setmListener(DownloadListener mListener) {
		this.mListener = mListener;
	}

	public void setmDownloadCount(int mDownloadCount) {
		this.mDownloadCount = mDownloadCount;
		mListener.startDownload("开始下载");
	}
	
	public boolean isThreadStart() {
		return isStart;
	}


}
