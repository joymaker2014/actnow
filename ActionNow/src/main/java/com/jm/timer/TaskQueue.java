package com.jm.timer;

import java.util.Arrays;

class TaskQueue {
	private TimerTask[] queue = new TimerTask[100];

	private int size = 0;

	int size() {
		return this.size;
	}

	void add(TimerTask task) {
		if (this.size + 1 == this.queue.length) {
			this.queue = ((TimerTask[]) Arrays.copyOf(this.queue,
					2 * this.queue.length));
		}
		this.queue[(++this.size)] = task;
		fixUp(this.size);
	}

	TimerTask getMin() {
		return this.queue[1];
	}

	TimerTask get(int i) {
		return this.queue[i];
	}

	void removeMin() {
		this.queue[1] = this.queue[this.size];
		this.queue[(this.size--)] = null;
		fixDown(1);
	}

	void quickRemove(int i) {
		assert (i <= this.size);

		this.queue[i] = this.queue[this.size];
		this.queue[(this.size--)] = null;
	}

	void rescheduleMin(long newTime) {
		this.queue[1].trigger.nextExecutionTime = newTime;
		fixDown(1);
	}

	boolean isEmpty() {
		return this.size == 0;
	}

	void clear() {
		for (int i = 1; i <= this.size; i++) {
			this.queue[i] = null;
		}
		this.size = 0;
	}

	private void fixUp(int k) {
		while (k > 1) {
			int j = k >> 1;
			if (this.queue[j].trigger.nextExecutionTime <= this.queue[k].trigger.nextExecutionTime) {
				break;
			}
			TimerTask tmp = this.queue[j];
			this.queue[j] = this.queue[k];
			this.queue[k] = tmp;
			k = j;
		}
	}

	private void fixDown(int k) {
		int j;
		while (((j = k << 1) <= this.size) && (j > 0)) {
			if ((j < this.size)
					&& (this.queue[j].trigger.nextExecutionTime > this.queue[(j + 1)].trigger.nextExecutionTime)) {
				j++;
			}
			if (this.queue[k].trigger.nextExecutionTime <= this.queue[j].trigger.nextExecutionTime) {
				break;
			}
			TimerTask tmp = this.queue[j];
			this.queue[j] = this.queue[k];
			this.queue[k] = tmp;
			k = j;
		}
	}

	void heapify() {
		for (int i = this.size / 2; i >= 1; i--) {
			fixDown(i);
		}
	}
}
