package Main;

import java.util.ArrayList;

import Components.BaseComponent;
import Components.BreadMaker;
import Components.Dishwasher;
import Components.Light;
import Components.TV;
import Components.WashingMachine;
import Console.ConsoleHelper;
import Loggers.Logger;

public class SmartHome implements Logger {

	private int tvId = 0;
    private int washingMachineId = 0;
    private int lightId = 0;
    private int dishwasherId = 0;
    private int breadMakerId = 0;

    private ConsoleHelper consoleHelper;
    private ArrayList<BaseComponent> components;

    {
        consoleHelper =  new ConsoleHelper();
        components = new ArrayList<>();
    }

    private boolean addComponent(String component) {
        BaseComponent newComponent;
        switch (component) {
            case "TV":
                newComponent = new TV(this, "TV" + ++tvId);
                break;
            case "WashingMachine":
                newComponent = new WashingMachine(this,"WashingMachine" + ++washingMachineId);
                break;
            case "Light":
                newComponent = new Light(this,"Light" + ++lightId);
                break;
            case "Dishwasher":
                newComponent = new Dishwasher(this,"Dishwashwer" + ++dishwasherId);
                break;
            case "BreadMaker":
                newComponent = new BreadMaker(this,"BreadMaker" + ++breadMakerId);
                break;
            default:
                return false;
        }
        components.add(newComponent);
        consoleHelper.print(newComponent.getName() + " has been added");
        
        return true;
    }

    private boolean removeComponent(String name) {
        BaseComponent compToRemove = null;
        for (BaseComponent component : components) {
            if (component.getName().equals(name)) {
                compToRemove = component;
            }
        }
        if (compToRemove != null) {
            components.remove(compToRemove);
            consoleHelper.print(name + " has been removed");
            return true;
        }
        return false;
    }

    private BaseComponent findComponentByName(String name){
        for (BaseComponent cmp : components) {
            if (cmp.getName().equals(name)) {
                return cmp;
            }
        }
        return null;
    }

    public void performCommand(String cmd){
        if (!consoleHelper.isCommandValid(cmd)){
            consoleHelper.printInfoAboutSuccess(false);
            return;
        }
        String[] cmdParts = cmd.split(" ");
        String action = cmdParts[0];

        boolean succeed = true;
        switch(action){
            case "add":
                String compType = cmdParts[1];
                succeed = addComponent(compType);
                break;
            case "remove":
                String compName = cmdParts[1];
                succeed = removeComponent(compName);
                break;
            case "turnon":
                compName = cmdParts[1];
                BaseComponent component = findComponentByName(compName);
                if (component != null){
                    succeed = component.turnOnOff(true);
                }
                else {
                    succeed = false;
                }
                break;
            case "turnoff":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    succeed = component.turnOnOff(false);
                }
                else {
                    succeed = false;
                }
                break;
            case "setontimer":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    int seconds = Integer.parseInt(cmdParts[2]);
                    succeed = component.setTimer(true, seconds);
                }
                else {
                    succeed = false;
                }
                break;
            case "setofftimer":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    int seconds = Integer.parseInt(cmdParts[2]);
                    succeed = component.setTimer(false, seconds);
                }
                else {
                    succeed = false;
                }
                break;
            case "setbrightess":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    int value = Integer.valueOf(cmdParts[2]);
                    if (component.getClass().equals(Light.class)){
                        succeed = ((Light)component).setBrightness(value);
                    }
                    else if (component.getClass().equals(TV.class)){
                        succeed = ((TV)component).setBrightness(value);
                    }
                }
                else {
                    succeed = false;
                }
                break;
            case "setvolume":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    int value = Integer.valueOf(cmdParts[2]);
                    if (component.getClass().equals(TV.class)){
                        succeed = ((TV)component).setVolume(value);
                    }
                }
                else {
                    succeed = false;
                }
                break;
            case "addchannel":
            	compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                    String value = String.valueOf(cmdParts[2]);
                    if (component.getClass().equals(TV.class)){
                        succeed = ((TV)component).addChannel(value);
                    }
                }
                else {
                    succeed = false;
                }
            	break;
            case "removechannel":
            	compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null){
                	int value = Integer.valueOf(cmdParts[2]);
                    if (component.getClass().equals(TV.class)){
                        succeed = ((TV)component).removeChannel(value);
                    }
                }
                else {
                    succeed = false;
                }
            	break;
            case "addmode" :
            	compName = cmdParts[1];
            	component = findComponentByName(compName);
            	if (component != null) {
            		String modeName = String.valueOf(cmdParts[2]);
            		if (component.getClass().equals(Light.class)) {
            			succeed = ((Light)component).addMode(modeName);
            		}
            		if (component.getClass().equals(WashingMachine.class)) {
            			succeed = ((WashingMachine)component).addMode(modeName);
            		}
            		if (component.getClass().equals(BreadMaker.class)) {
            			succeed = ((BreadMaker)component).addMode(modeName);
            		}
            	}
            	else {
            		succeed = false;
            	}
            	break;
            case "removemode" :
            	compName = cmdParts[1];
            	component = findComponentByName(compName);
            	if (component != null) {
            		int modeNumber = Integer.valueOf(cmdParts[2]);
            		if (component.getClass().equals(Light.class)) {
            			succeed = ((Light)component).removeMode(modeNumber);
            		}
            		if (component.getClass().equals(WashingMachine.class)) {
            			succeed = ((WashingMachine)component).removeMode(modeNumber);
            		}
            		if (component.getClass().equals(BreadMaker.class)) {
            			succeed = ((BreadMaker)component).removeMode(modeNumber);
            		}
            	}
            	else {
            		succeed = false;
            	}
            	break;
            case "setmode" :
            	compName = cmdParts[1];
            	component = findComponentByName(compName);
            	if (component != null && component.isTurnedOn()) {
            		int modeNumber = Integer.valueOf(cmdParts[2]);
            		if (component.getClass().equals(Light.class)) {
            			succeed = ((Light)component).setCurrMode(modeNumber);
            		}
            		if (component.getClass().equals(WashingMachine.class)) {
            			succeed = ((WashingMachine)component).setCurrMode(modeNumber);
            		}
            		if (component.getClass().equals(BreadMaker.class)) {
            			succeed = ((BreadMaker)component).setCurrMode(modeNumber);
            		}
            	}
            	else {
            		succeed = false;
            	}
            	break;
            case "channellist":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null && component.getClass().equals(TV.class) ) {
                    String channels = ((TV) component).getChannels();
                    consoleHelper.print(channels);
                }
                break;
            case "setchannel":
                compName = cmdParts[1];
                component = findComponentByName(compName);
                if (component != null && component.getClass().equals(TV.class) ) {
                    int channel = Integer.parseInt(cmdParts[2]);
                    succeed = ((TV) component).setCurrChannel(channel);
                }
                else {
                    succeed = false;
                }
                break;
            case "info":
                consoleHelper.printDetailedInformation(components);
                break;
            case "help":
            	consoleHelper.print("Commands: \n" +
                        "add <type of component> - add component of specified type\n" +
                        "remove <name of component> - remove component with specified name\n" +
                        "turnon <name of component> - turn component with specified name on\n" +
                        "turnoff <name of component> - turn component with specified name off\n" +
                        "setontimer <name of component> <time in seconds> - " +
                        "set timer to turn specified component on after specified time\n" +
                        "setofftimer <name of component> <time in seconds> - " +
                        "set timer to turn specified component off after specified time\n" +
                        "setbrightness <name of Light/TV> <value> - " +
                        "set brightness of TV or Light by specified value\n" +
                        "setvolume <name of TV> <value> - " +
                        "set volume of TV by specified value\n" +
						"addchannel <name of TV> <new channel name> - " +
						"add channel to TV\n" +
						"removechannel <name of TV> <channel number> - " +
						"remove channel from TV\n" +
						"addmode <name of Light/Washing machine/Bread maker> <new mode name> - " +
						"add mode to Light or Washing machine or Bread maker\n" +
						"removemode <name of Light/Washing machine/Bread maker> <mode number> - " +
						"remove mode from Light or Washing machine or Bread maker\n" +
						"setmode <name of Light/Washing machine/Bread maker> <mode number> - " +
						"set mode for working Light or Washing machine or Breade maker\n" +
                        "channellist <name of TV> - show channel list of specified TV" +
                        "setchannel <name of TV> <channel number> - set current channel of specified TV to specified number\n" +
                        "info - print detailed info about all of existing components");
                break;
        }
        consoleHelper.printInfoAboutSuccess(succeed);
    }
	
	@Override
	public void Log(String message) {
		consoleHelper.print(message);
	}
	
}
