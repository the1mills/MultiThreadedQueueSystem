package de;
import java.util.Vector;

public class Queue implements Runnable {

	private MainController mc;
	private Vector<QueueEntity> queue;
	private boolean keepLooping = true;
	private QueueEntity qe;
	

	public synchronized MainController getMc() {
		return mc;
	}

	public synchronized void setMc(MainController mc) {
		this.mc = mc;
	}

	public synchronized QueueEntity getQe() {
		return qe;
	}

	public synchronized void setQe(QueueEntity qe) {
		this.qe = qe;
	}

	public Queue(MainController mc) {

		this.mc = mc;
		queue = new Vector<QueueEntity>();
	}

	private void loop() {

		//here is the arrival process
		
		double timeTilNextArrival = GenerateRandomVariates
				.generateExponentialRandomVariable(700);
		int numberInGroup = GenerateRandomVariates.getNextGroupSize();

		try {
			Thread.sleep((long) timeTilNextArrival);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		qe = new QueueEntity(System.nanoTime()/1000000, numberInGroup);
		qe.setInQueue(true);

		this.queue.add(qe);

		System.out.println("Number in Queue (Arrival): " + this.queue.size());
	}

	public Vector<QueueEntity> getQueue() {
		return queue;
	}

	public void setQueue(Vector<QueueEntity> queue) {
		this.queue = queue;
	}

	public boolean isKeepLooping() {
		return keepLooping;
	}

	public void setKeepLooping(boolean keepLooping) {
		this.keepLooping = keepLooping;
	}

	@Override
	public void run() {

		while (this.getMc().isKeepLooping()) {
			loop();
		}
	}
}