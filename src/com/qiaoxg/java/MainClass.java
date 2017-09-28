package com.qiaoxg.java;

import com.qiaoxg.java.listener.DownloadListener;
import com.qiaoxg.java.thread.DownloadPicThread;
import com.qiaoxg.java.thread.DownloadVideoThread;
import com.qiaoxg.java.thread.RunnableThread;
import com.qiaoxg.java.thread.ThreadDemo;
import com.qiaoxg.java.thread.ThreadDemo.onChangedListener;

public class MainClass {

	private static DownloadVideoThread videoThread;
	private static DownloadPicThread picThread;
	private static int mTotalCount = 4;

	public static void main(String[] agrs) {

		// 第一种创建方式：实现runnable
		// runnableThread();

		// 第二种创建方式：继承thread
		// threadDemo();

		// 线程自唤醒
		// circelThread();

		videoThread = DownloadVideoThread.getInstanse();
		picThread = DownloadPicThread.getInstanse();
		startDownload();

	}

	private static void startDownload() {
		videoThread.setmListener(new DownloadListener() {

			public void startDownload(String msg) {
				System.out.println("第--" + mTotalCount + "--视频 ， " + msg);

			}

			public void onSuccess(String msg) {
				System.out.println("第--" + mTotalCount + "--视频 ， " + msg);
				picThread.setmListener(new DownloadListener() {

					public void startDownload(String msg) {
						System.out.println("第--" + mTotalCount + "--图片 ， "
								+ msg);
					}

					public void onSuccess(String msg) {
						System.out.println("第--" + mTotalCount + "--图片 ， "
								+ msg);

						if (mTotalCount > 0) {
							mTotalCount--;
							System.out
									.println("\n\n-----------------------还剩=="
											+ mTotalCount + "==完成 \n\n");
							videoThread.setmDownloadCount(0);
						} else {
							System.out
									.println("\n\n------------------全部下载完成----------------------- \n\n");
						}
					}

					public void downloading(int progress) {
						if (progress % 10 == 0) {
							System.out.println("第--" + mTotalCount + "--图片 ， "
									+ "下载进度：" + progress);
						}

					}

					public void downloadFail(String msg) {
						System.out.println("第--" + mTotalCount + "--图片 ， "
								+ msg);
					}
				});
				picThread.setmDownloadCount(0);
				if (!picThread.isThreadStart()) {
					picThread.start();
				}

			}

			public void downloading(int progress) {
				if (progress % 10 == 0) {
					System.out.println("第--" + mTotalCount + "--视频 ， "
							+ "下载进度：" + progress);
				}
			}

			public void downloadFail(String msg) {
				System.out.println("第--" + mTotalCount + "--视频 ， " + msg);
			}
		});
		videoThread.setmDownloadCount(0);
		if (!videoThread.isThreadStart()) {
			videoThread.start();
		}
	}

	private static void threadDemo() {
		ThreadDemo t3 = new ThreadDemo("Thread_03");
		t3.start();
		ThreadDemo t4 = new ThreadDemo("Thread_04");
		t4.start();

	}

	private static void runnableThread() {
		RunnableThread thread1 = new RunnableThread("Thread_01");
		thread1.start();
		RunnableThread thread2 = new RunnableThread("Thread_02");
		thread2.start();

	}

	private static void circelThread() {
		final ThreadDemo t3 = ThreadDemo.getInstanse();
		t3.start();
		t3.setListener(new onChangedListener() {

			public void isEnd(boolean isEnd) {
				t3.setI(5);
			}
		});
	}

}
