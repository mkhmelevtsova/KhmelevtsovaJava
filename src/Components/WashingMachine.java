package Components;

import java.util.ArrayList;

import Loggers.Logger;

public class WashingMachine extends BaseComponent {

	private int currMode = 1;

    private ArrayList<String> modes;
    
	public WashingMachine(Logger logger, String name) {
        super(logger, name);
        initModes();
    }
	
	private void initModes(){
        modes = new ArrayList<String>();

        modes.add("Basic");
        modes.add("Delicate");
        modes.add("Fast");
        modes.add("Wool");
    }
	
	public String getCurrentMode() {
        return modes.get(currMode - 1);
    }
	
	public String getModes() {
		String result = "";
		for (String mode : this.modes) {
			result += mode + "\n";
		}
		return result;
	}
	
	@Override
	public String getState() {
		String result = super.getState();
		result += "\nCurrent mode: " + getCurrentMode();
		return result;
	}

    public boolean setCurrMode(int currMode) {
        if (currMode <= modes.size() && super.isTurnedOn()){
            this.currMode = currMode;
            this.logger.Log(super.getName() + "'s current model changed to " +
                    currMode + "(" + getCurrentMode() + ")");
            return true;
        }
        return false;
    }

    public boolean addMode(String name) {
    	if (this.isThereThisMode(name)) return false;
    	this.modes.add(name);
    	return true;
    }
    
    public boolean removeMode(int number) {
    	if (number < 0 || number > this.modes.size()) return false;
    	this.modes.remove(number - 1);
    	return true;
    }
    
    private boolean isThereThisMode(String name) {
    	for (String channel : this.modes) {
    		if (channel == name) return true;
    	}
    	return false;
    }
	
}
