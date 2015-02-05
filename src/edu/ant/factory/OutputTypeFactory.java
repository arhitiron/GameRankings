package edu.ant.factory;

import edu.ant.output.FTLOutput;
import edu.ant.output.Output;
import edu.ant.output.SimpleOutput;

public class OutputTypeFactory {

	public static Output getFTLOutput() {
		Output out = new FTLOutput();
		// some logic
		return out;
	}
	
	public static Output getSimpleOutput() {
		Output out = new SimpleOutput();
		// some logic
		return out;
	}
	
}
