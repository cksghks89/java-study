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
		if (channel < 0) this.channel = 0;
		else if (channel > 255) this.channel = 255;
		else this.channel = channel;
	}
	
	public void channel(boolean up) {
		this.channel(this.channel + (up ? 1 : -1));
	}
	
	public void volume(int volume) {
		if (volume < 0) this.volume = 0;
		else if (volume > 100) this.volume = 100;
		else this.volume = volume;
	}
	
	public void volume(boolean up) {
		this.volume(this.volume + (up ? 1 : -1));
	}
	
	public void status() {
		System.out.printf("TV[power=%s, channel=%d, volume=%d]\n", power ? "on" : "off", channel, volume);
	}

}
