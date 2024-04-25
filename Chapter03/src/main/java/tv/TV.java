package tv;

public class TV {
	private int channel;	// 1 ~ 255
	private int volume;		// 0 ~ 10
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	public void power(boolean on) {
		this.power = on;
	}
	
	public void channel(int channel) {
		this.channel = channel;
	}
	
	public void channel(boolean up) {
		this.channel += up ? 1 : -1;
	}
	
	public void volume(int volume) {
		this.volume = volume;
	}
	
	public void volume(boolean up) {
		this.volume += up ? 1 : -1;
	}
	
	public void status() {
		System.out.printf("TV[power=%s, channel=%d, volume=%d]\n", power ? "on" : "off", channel, volume);
	}

}
