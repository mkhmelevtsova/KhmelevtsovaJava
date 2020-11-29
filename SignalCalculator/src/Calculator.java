import java.util.Arrays;
import java.util.Collections;
import java.util.function.*;

public class Calculator {

	private Byte[] _signals;
	
	public Calculator() {}
	
	public Calculator(Byte[] signals) {
		_signals = signals;
	}
	
	public int DynamicSignalDiapason() {
		int minValue = Collections.min(Arrays.asList(this._signals));
		int maxValue = Collections.max(Arrays.asList(this._signals));
		return maxValue - minValue;
	}
	
	public double Energy() {
		double energy = 0;
		for (Byte signal : this._signals) {
			energy += Math.pow(signal, 2);
		}
		return energy;
	}
	
	public Function<Double, Double> AverageSignalPower = (energy) -> energy / this._signals.length;
	
	public double AverageSignalResponses() {
		double sum = 0;
		for (Byte signal: this._signals) {
			sum += signal;
		}
		return sum / this._signals.length;
	}
	
	public double ResponsesValueDispersion(double averageSignalResponses) {
        int sum = 0;
        for (Byte signal: this._signals) {
            sum += Math.pow((signal - averageSignalResponses), 2);
        }
        return sum / this._signals.length;
	}
	
	public void AutoCorrelationFunction(int number) {
		int number1 = Math.abs(number);
		int number2 = -number1;
		var Ms = this.AverageSignalResponses();
		for (int i = number2; i <= number1; i++) {
			System.out.print(i + " - ");
			var t = i;
			if (t < 0) t = -t;
			double sum = 0;
			for (int j = 0; j < (this._signals.length - t - 1); j++) {
				sum += (this._signals[j + t] - Ms) * (this._signals[j] - Ms);
			}
			double result = (1.00 / (this._signals.length - t)) * sum;
			System.out.print(result + "\n");
		}
	}
}
