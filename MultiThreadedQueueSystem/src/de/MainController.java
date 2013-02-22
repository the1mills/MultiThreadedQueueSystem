package de;
import java.util.Vector;

import Views.MultiServerAnimation;

public class MainController {
	
	public static int numberOfServers = 12;
	public static volatile Vector<Server> serverVector;
	public static volatile Vector<Thread> serverThreadVector;
	public static volatile Thread queueThread;
	public static volatile Queue queue;
	public static volatile Vector<QueueEntity> servedEntities;
	public static Integer numberOutLimit = 5000;
	public static Integer numberThroughQueue = 0;
	public static boolean keepLooping = true;
	public static MultiServerAnimation msa = null;
	public static Stats stats = null;
	
	public synchronized static boolean isKeepLooping() {
		return keepLooping;
	}

	public synchronized static void setKeepLooping(boolean keepLooping) {
		MainController.keepLooping = keepLooping;
	}

	public synchronized static Integer getNumberThroughQueue() {
		return numberThroughQueue;
	}

	public synchronized static void setNumberThroughQueue(Integer numberThroughQueue) {
		MainController.numberThroughQueue = numberThroughQueue;
	}
	
	public static Integer getNumberOutLimit() {
		return numberOutLimit;
	}

	public void setNumberOutLimit(Integer numberOutLimit) {
		MainController.numberOutLimit = numberOutLimit;
	}
	
	public synchronized static Vector<QueueEntity> getServedEntities() {
		return servedEntities;
	}

	public synchronized void setServedEntities(Vector<QueueEntity> servedEntities) {
		MainController.servedEntities = servedEntities;
	}
	
	public synchronized static QueueEntity getFirstItem(){
		if(MainController.getQueue().getQueue().size() > 0){
		QueueEntity	qe = MainController.getQueue().getQueue().get(0);
		qe.setTimeReachedService(System.nanoTime()/1000000);
		qe.setInQueue(false);
		qe.setInService(true);
		MainController.getQueue().getQueue().remove(qe);
		return qe;
		}
		else{
		return null;
		}
	}
	
	public MainController() throws InterruptedException {

		queue = new Queue(this);
		servedEntities = new Vector<QueueEntity>();
		serverVector = new Vector<Server>();
		serverThreadVector = new Vector<Thread>();
		queueThread = new Thread(queue);
		
		msa = new MultiServerAnimation();
		msa.setSize(600, 600);
		msa.setVisible(true);
		
		Thread.sleep(500);
		
		stats = new Stats();

		for (Integer i = 0; i < numberOfServers; i++) {

			this.getServerVector().add(new Server(i));
			this.getServerThreadVector().add(
					new Thread(this.getServerVector().get(i)));

		}

		for (int i = 0; i < numberOfServers; i++) {

			this.getServerThreadVector().get(i).start();

		}

		queueThread.start();

	}

	public int getNumberOfServers() {
		return numberOfServers;
	}

	public void setNumberOfServers(int numberOfServers) {
		MainController.numberOfServers = numberOfServers;
	}

	public synchronized Vector<Server> getServerVector() {
		return serverVector;
	}

	public synchronized Vector<Thread> getServerThreadVector() {
		return serverThreadVector;
	}

	public synchronized void setServerThreadVector(
			Vector<Thread> serverThreadVector) {
		MainController.serverThreadVector = serverThreadVector;
	}

	public synchronized void setServerVector(Vector<Server> serverVector) {
		MainController.serverVector = serverVector;
	}

	public synchronized Thread getQueueThread() {
		return queueThread;
	}

	public synchronized void setQueueThread(Thread queueThread) {
		MainController.queueThread = queueThread;
	}

	public synchronized static Queue getQueue() {
		return queue;
	}

	public synchronized void setQueue(Queue queue) {
		MainController.queue = queue;
	}

}
