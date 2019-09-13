package added;

import tst.Displayer;

public class DisplayerImpl implements Displayer {

	@Override
	public String getStringToDisplay() {
		return "Greetings from the additional JAR :)";
	}

}