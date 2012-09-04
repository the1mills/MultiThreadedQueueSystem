package de;

public class QueueEntity {
	
	public synchronized double getTimeArrivedAtQueue() {
		return timeArrivedAtQueue;
	}


	public synchronized void setTimeArrivedAtQueue(double timeArrivedAtQueue) {
		this.timeArrivedAtQueue = timeArrivedAtQueue;
	}


	public synchronized double getTimeReachedService() {
		return timeReachedService;
	}


	public synchronized void setTimeReachedService(double timeReachedService) {
		this.timeReachedService = timeReachedService;
	}


	public synchronized double getTimeServed() {
		return timeServed;
	}


	public synchronized void setTimeServed(double timeServed) {
		this.timeServed = timeServed;
	}


	public synchronized double getTotalWaitingTime() {
		return totalWaitingTime;
	}


	public synchronized void setTotalWaitingTime(double totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}


	public synchronized double getTotalServerTime() {
		return totalServerTime;
	}


	public synchronized void setTotalServerTime(double totalServerTime) {
		this.totalServerTime = totalServerTime;
	}


	public synchronized double getTotalTime() {
		return totalTime;
	}


	public synchronized void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}


	public synchronized int getNumberOfItemsInQueueEntity() {
		return numberOfItemsInQueueEntity;
	}


	public synchronized void setNumberOfItemsInQueueEntity(
			int numberOfItemsInQueueEntity) {
		this.numberOfItemsInQueueEntity = numberOfItemsInQueueEntity;
	}


	public synchronized boolean isInService() {
		return inService;
	}


	public synchronized void setInService(boolean inService) {
		this.inService = inService;
	}


	public synchronized boolean isInQueue() {
		return inQueue;
	}


	public synchronized void setInQueue(boolean inQueue) {
		this.inQueue = inQueue;
	}


	public synchronized boolean isServed() {
		return served;
	}


	public synchronized void setServed(boolean served) {
		this.served = served;
	}


	private boolean inService;
	private boolean inQueue;
	private boolean served = false;
	private double timeArrivedAtQueue;
	private double timeReachedService;
	private double timeServed;
	private double totalWaitingTime;
	private double totalServerTime;
	private double totalTime;
	private int numberOfItemsInQueueEntity;

	
	public QueueEntity(double timeArrivedAtQueue, int numberOfItemsInQueueEntity){
		
		this.timeArrivedAtQueue = timeArrivedAtQueue;
		this.numberOfItemsInQueueEntity = numberOfItemsInQueueEntity;
		
	}
}
