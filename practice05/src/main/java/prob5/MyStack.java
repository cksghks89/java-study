package prob5;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
	private String[] buffer;
	private int size = 10;
	private int pointer = 0;
	
	public MyStack(int size) {
		this.buffer = new String[size];
		this.size = size;
	}
	
	private void resize() {
		this.size = this.size * 2;
		String[] newBuffer = new String[this.size];
		
		for (int i = 0; i < this.buffer.length; i++) {
			newBuffer[i] = this.buffer[i];
		}
		
		this.buffer = newBuffer;
	}
	
	public void push(String str) {
		if (this.pointer == this.size) {
			resize();
		}
		
		this.buffer[pointer++] = str;
	}
	
	public String pop() {
		if (isEmpty()) {
			throw new MyStackException();
		}
		
		return this.buffer[--pointer];
	}
	
	public boolean isEmpty() {
		return pointer == 0;
	}
}