import java.io.FileInputStream;
import java.io.IOException;

public class CalculationProgram {

	public static void main(String[] args) throws IOException {
		String filename = "";
		if (args.length == 0)
			filename =  "E:\\XAI_notes\\4\\Crossplatform\\Labs\\Iv_3\\15.bin";
		else filename = args[0];
        FileInputStream fileStream = new FileInputStream(filename);
        byte[] signalsInputed = new byte[fileStream.available()];
        fileStream.read(signalsInputed, 0, fileStream.available());
        fileStream.close();
        Byte[] signals = new Byte[signalsInputed.length];
        for (int i = 0; i < signalsInputed.length; i++) {
        	signals[i] = Byte.valueOf(signalsInputed[i]);
        }

        Calculator calc = new Calculator(signals);

        //Calculations
        int dynamicSignalRange = calc.DynamicSignalDiapason();
        double energy = calc.Energy();
        double averagePower = (calc.AverageSignalPower.apply(Double.valueOf(energy))).doubleValue();
        double averageSignalResponses = calc.AverageSignalResponses();
        double dispersion = calc.ResponsesValueDispersion(averageSignalResponses);

        System.out.println("Amount of signal counts = " + signals.length);
        System.out.println("Dynamic signal range = " + dynamicSignalRange);
        System.out.println("Energy of the signal = " + energy);
        System.out.println("Average signal power = " + averagePower);
        System.out.println("Average signal count value = " + averageSignalResponses);
        System.out.println("Variance of signal counts = " + dispersion);
        
        calc.AutoCorrelationFunction(4);
    }
}
