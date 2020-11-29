package Components;

import java.util.ArrayList;

import Loggers.Logger;

public class BreadMaker extends BaseComponent {
	
	 private ArrayList<String> modes;
	 private int currentMode = 1;
	
	public BreadMaker(Logger logger, String name) {
        super(logger, name);
        initModes();
    }
	
	public String getCurrMode() {
        return this.modes.get(currentMode - 1);
    }
	
	public String getChannels() {
		String result = "";
		for (String mode : this.modes) {
			result += mode + "\n";
		}
		return result;
	}
	
	@Override
	public String getState() {
		String result = super.getState();
		result += "\nCurrent mode: " + getCurrMode();
		return result;
	}
	
	public boolean setCurrMode(int currMode) {
        if (currMode <= this.modes.size() && super.isTurnedOn()){
            this.currentMode = currMode;
            this.logger.Log(super.getName() + "'s current mode changed to " +
                    currMode + "(" + getCurrMode() + ")");
            return true;
        }
        return false;
    }
	
	public boolean startWithMode(int number) {
		if (number < 0 || number > this.modes.size()) return false;
		this.setCurrMode(number);
		super.turnOnOff(true);
		return true;
	}
	
	public boolean addMode(String name) {
		if (isThereThisMode(name)) return false;
		this.modes.add(name);
		return true;
	}
	
	public boolean removeMode(int number) {
		if (number < 0 || number > this.modes.size()) return false;
    	this.modes.remove(number - 1);
    	return true;
	}
	
	private void initModes() {
		modes = new ArrayList<String>();
		
		modes.add("Basic");
	}
	
	private boolean isThereThisMode(String name) {
    	for (String mode : this.modes) {
    		if (mode == name) return true;
    	}
    	return false;
    }
}
