package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public void power(boolean on) {
		if(on)
			this.power = true;
		else
			this.power = false;
	}
	public void channel(int channel) {
		if(!power) {
			return;
		}
		if(channel<1)
			channel=255;
		else if(255<channel)
			channel=1;
	}
	public void channel(boolean up) {
		//channel(channel + (up ? 1 : -1));
		if(up)
			channel++;
		else
			channel--;
		channel(channel);
	}
	public void volume(int volume) {
		if(volume<1)
			volume=1;
		else if(255<volume)
			volume=255;
	}
	public void volume(boolean up) {
		//volume(volume + (up ? 1 : -1));
		if(up)
			volume++;
		else
			volume--;
		channel(volume);
	}
	public void status() {
		System.out.println("TV[channel=" + channel +", volume=" + volume + ", power=" + power +"]");
	}
}
