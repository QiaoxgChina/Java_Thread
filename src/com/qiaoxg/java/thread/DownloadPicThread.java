package com.qiaoxg.java.thread;

import com.qiaoxg.java.listener.DownloadListener;

public class DownloadPicThread extends Thread {

	private Thread t;
	private DownloadListener mListener;
	private int mDownloadCount = 0;
	private static DownloadPicThread mDownloadPicThread;
	private static boolean isStarted = false;
	private boolean isSuccess = false;

	public static DownloadPicThread getInstanse() {
		if (mDownloadPicThread == null) {
			mDownloadPicThread = new DownloadPicThread();
		}
		return mDownloadPicThread;
	}

	public void run() {
		super.run();
		while (true) {
			while (mDownloadCount <= 100) {
				mListener.downloading(mDownloadCount);
				isSuccess = false;
				mDownloadCount++;
				try {
					Thread.sleep(10);
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
		isStarted = true;
	}

	public void setmListener(DownloadListener mListener) {
		this.mListener = mListener;
	}

	public void setmDownloadCount(int mDownloadCount) {
		this.mDownloadCount = mDownloadCount;
		mListener.startDownload("开始下载");
	}

	public boolean isThreadStart() {
		return isStarted;
	}

}
