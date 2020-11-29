package Components;

import java.util.Timer;
import java.util.TimerTask;

import Loggers.Logger;

public class BaseComponent {
	
	private long _startTime;
    private long _stopTime;
    private long _timeLeft;
    private long _currentTime;
    private Timer _timer;
    private String _name;
    public Logger logger;

    private boolean _isTurnedOn = false;
    private boolean _currTimerMode = false;

    public BaseComponent(Logger logger, String name){
        this.logger = logger;
        this._name = name;
    }
    
    public boolean turnOnOff(boolean on) {
    	if (_isTurnedOn == on) return false;
    	_isTurnedOn = on;
    	logger.Log(_name + " has turned " + (on ? "on" : "off"));
    	return true;
    }

    public boolean isTurnedOn() {
        return _isTurnedOn;
    }

    public String getName() {
        return _name;
    }

    public boolean isTimerModeOn() {
        return _currTimerMode;
    }
    
    public String getState() {
    	String state = "Name: " + this._name +
    			"\nTurned on: " + (this._isTurnedOn ? "yes" : "no") +
    			"\nTimer: " + (this.isTimerOn() ? "yes. " + this.getTimeLeft() : "no");
    	return state;
    }

    public boolean setTimer(boolean turnTimerOn, long seconds) {

        if (_isTurnedOn && turnTimerOn || !_isTurnedOn && !turnTimerOn) {
            return false;
        }

        updateTimeLeft();
        _currTimerMode = turnTimerOn;
        _startTime = System.currentTimeMillis();
        _stopTime = _startTime + seconds * 1000;

        _timer = new Timer();
        _timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTimeLeft();
                if (_timeLeft <= 0) {
                    stopTimer();
                    if (turnTimerOn) {
                    	turnOnOff(true);
                    } else {
                    	turnOnOff(false);
                    }
                }
            }
        }, 0, 1000);

        return isTimerOn();
    }

    public boolean stopTimer() {
        if (_timer == null) {
            return false;
        }
        _startTime = _stopTime = _currentTime = _timeLeft = 0;
        _timer.cancel();

        return true;
    }

    public boolean isTimerOn() {
        return _timeLeft != 0;
    }

    private void updateTimeLeft() {
        _currentTime = System.currentTimeMillis();
        _timeLeft = _stopTime - _currentTime;
    }

    public long getTimeLeft() {
        return _timeLeft <= 0 ? 0 :
                _timeLeft % 1000 == 0 ? _timeLeft :
                        _timeLeft - (_timeLeft % 1000) + 1000;
    }
}
