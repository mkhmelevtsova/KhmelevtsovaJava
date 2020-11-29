package Console;

import java.util.ArrayList;

import Components.BaseComponent;

public class ConsoleHelper {
	
	public boolean isCommandValid(String cmd) {
        String[] cmdParts = cmd.split(" ");
        String action = cmdParts[0];

        int len = cmdParts.length;
        if (len > 3){
            return false;
        }
        switch(action){
            case "add":
            case "remove":
            case "turnon":
            case "turnoff":
            case "channellist":
                if (len != 2) {
                    return false;
                }
                return true;
            case "info":
            case "help":
                return true;
            case "setontimer":
            case "setofftimer":
            case "setchannel":
            case "setvolume":
            case "setbrightness":
            case "removechannel":
            case "removemode":
            case "setmode":
                if (len != 3){
                    return false;
                }
                try {
                    int value = Integer.parseInt(cmdParts[2]);
                    if (value < 0){
                        return false;
                    }
                }
                catch (NumberFormatException e){
                    return false;
                }
                return true;
            case "addchannel":
            case "addmode":
            	if (len != 3) {
            		return false;
            	}
            	try {
            		String value = String.valueOf(cmdParts[2]);
            	}
            	catch (NumberFormatException e){
                    return false;
                }
                return true;            	
            default:
                return false;
        }
    }

    public void printStrArr(String[] arr, String desc) {
        System.out.println(desc);
        for(String s : arr){
            System.out.println(s);
        }
    }

    public void printInfoAboutSuccess(boolean isSucceed){
        if (isSucceed){
            System.out.println("Command succeeded.");
        }
        else {
            System.out.println("Command failed.");
        }
    }

    public void printDetailedInformation(ArrayList<BaseComponent> components){
        for (BaseComponent cmp : components){
            System.out.println(cmp.getState());
        }
    }

    public void print(String description){
        System.out.println(description);
    }
}