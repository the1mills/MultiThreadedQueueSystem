package de;

import java.util.Observable;

public class Server extends Observable implements Runnable {


//	private MainController mc;
	private Integer serverId;
	private boolean keepLooping = true;
	private QueueEntity qe;
	private boolean idle = true;
	private double totalIdleTime = 0;
	private double idleTimeStart = System.nanoTime()/1000000;
	
	
	public Server(Integer i) {

		this.serverId = i;
		this.addObserver(MainController.msa);
		this.addObserver(MainController.stats);
	}

	
	public synchronized Integer getServerId() {
		return serverId;
	}

	public synchronized void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public synchronized boolean isIdle() {
		return idle;
	}

	public synchronized void setIdle(boolean idle) {
		this.idle = idle;
		
		this.setChanged();
		this.notifyObservers(idle);
		
	}

	public synchronized double getTotalIdleTime() {
		return totalIdleTime;
	}

	public synchronized void setTotalIdleTime(double totalIdleTime) {
		this.totalIdleTime = totalIdleTime;
	}

	public synchronized double getIdleTimeStart() {
		return idleTimeStart;
	}

	public synchronized void setIdleTimeStart(double idleTimeStart) {
		this.idleTimeStart = idleTimeStart;
	}

	public QueueEntity getQe() {
		return qe;
	}

	public void setQe(QueueEntity qe) {
		this.qe = qe;
	}


	public boolean isKeepLooping() {
		return keepLooping;
	}

	public void setKeepLooping(boolean keepLooping) {
		this.keepLooping = keepLooping;
	}

	

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (MainController.isKeepLooping()) {
			loop();
		}
	}

	private void loop() {

		
		qe = MainController.getFirstItem();
		if(qe == null){
			this.setIdle(true);
			double timeNow = System.nanoTime()/1000000;
			this.setTotalIdleTime(this.getTotalIdleTime() + (timeNow - this.getIdleTimeStart())); 
			this.setIdleTimeStart(timeNow);
			return;
		}
		System.out.println("Server # " + this.serverId + "  - idle time: " + this.getTotalIdleTime());
		this.setIdle(false);
		double timeToServe = GenerateRandomVariates
				.generateExponentialRandomVariable(3000);
		try {
			Thread.sleep((long) timeToServe);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qe.setTotalServerTime(System.nanoTime()/1000000 - qe.getTimeReachedService());
		qe.setInService(false);
		qe.setServed(true);
		MainController.getServedEntities().add(qe);
		MainController.setNumberThroughQueue(MainController.getNumberThroughQueue()+1);
		System.out.println(MainController.getNumberThroughQueue());
		if(MainController.getNumberThroughQueue() >= MainController.getNumberOutLimit()){
			MainController.setKeepLooping(false);
		}

		System.out.println("Number in queue (service): "
				+ MainController.getQueue().getQueue().size());
	}
}
