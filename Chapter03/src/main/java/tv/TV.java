package tv;

public class TV {
	private int channel;	// 1 ~ 255
	private int volume;		// 0 ~ 100
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel(channel);
		this.volume(volume);
		this.power = power;		
	}
	
	public void power(boolean on) {
		this.power = on;
	}
	
	public void channel(int channel) {
		this.channel = (channel - 1) % 255 + 1;
	}
	
	public void channel(boolean up) {
		this.channel += up ? 1 : -1;
		this.channel(this.channel);
	}
	
	public void volume(int volume) {
		this.volume = volume % 101;
	}
	
	public void volume(boolean up) {
		this.volume += up ? 1 : -1;
		this.volume(this.volume);
	}
	
	public void status() {
		System.out.printf("TV[power=%s, channel=%d, volume=%d]\n", power ? "on" : "off", channel, volume);
	}

}
