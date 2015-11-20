package dad.games;

public abstract class BackgroundTask implements Runnable {

	private Thread thread;
	private boolean finish;

	public void start() {
		start(false);
	}

	public void start(boolean wait) {
		thread = new Thread(this);
		thread.start();
		if (wait) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		finish = true;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract void run();

}
