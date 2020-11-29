package Components;

import java.util.ArrayList;

import Loggers.Logger;

public class TV extends BaseComponent {
	
	private int currChannel = 1;
	private int brightness = 10;
	private int volume = 5;

    private ArrayList<String> channels;
    
	public TV(Logger logger, String name) {
        super(logger, name);
        initChannels();
        setCurrChannel(1);
    }
	
	private void initChannels(){
        channels = new ArrayList<String>();

        channels.add("BBC");
        channels.add("Discovery");
        channels.add("EuroNews");
        channels.add("AMC");
        channels.add("Animal Planet");
        channels.add("HBO");
        channels.add("Random Channel");
        channels.add("HDTV");
    }
	
	public String getCurrChannel() {
        return channels.get(currChannel - 1);
    }
	
	public String getChannels() {
		String result = "";
		for (String channel : this.channels) {
			result += channel + "\n";
		}
		return result;
	}
	
	@Override
	public String getState() {
		String result = super.getState();
		result += "\nCurrent channel: " + getCurrChannel() +
				"\nVolume: " + this.volume +
				"\nBrightness: " + this.brightness;
		return result;
	}

    public boolean setCurrChannel(int currChannel) {
        if (currChannel <= channels.size() && super.isTurnedOn()){
            this.currChannel = currChannel;
            this.logger.Log(super.getName() + "'s current channel changed to " +
                    currChannel + "(" + getCurrChannel() + ")");
            return true;
        }
        return false;
    }

    public boolean addChannel(String name) {
    	if (this.isThereThisChannel(name)) return false;
    	this.channels.add(name);
    	return true;
    }
    
    public boolean removeChannel(int number) {
    	if (number < 0 || number > this.channels.size()) return false;
    	this.channels.remove(number - 1);
    	return true;
    }
    
    public boolean setBrightness(int number) {
    	if (number < 1 || number > 10) return false;
    	this.brightness = number;
    	return true;
    }
    
    public boolean setVolume(int number) {
    	if (number < 1 || number > 10) return false;
    	this.volume = number;
    	return true;
    }
    
    private boolean isThereThisChannel(String name) {
    	for (String channel : this.channels) {
    		if (channel == name) return true;
    	}
    	return false;
    }
}
