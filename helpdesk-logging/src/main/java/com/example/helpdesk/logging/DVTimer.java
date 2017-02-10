package com.example.helpdesk.logging;

public class DVTimer {
	private long	t0;
	private long	diff;

	private DVTimer() {
		restart();
	}

	public static DVTimer start() {
		return new DVTimer();
	}

	public void restart() {
		this.t0 = System.currentTimeMillis();
		diff = -1;
	}

	/**
	 * Stops timer and returns difference in milliseconds
	 * @return milliseconds passed by
	 */
	public long stop() {
		diff = System.currentTimeMillis() - t0;
		return diff;
	}

	public long getTimeInMilliSeconds() {
		if( diff == -1)
			return stop();
		return diff;
	}

	public long getTimeInSeconds() {
		if( diff == -1)
			return stop() / 1000;
		return diff / 1000;
	}

	public String getTimeInMilliSecondsString() {
		return String.valueOf( diff) + "ms";
	}
}
