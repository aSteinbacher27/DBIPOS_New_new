package app;
import javafx.scene.control.Button;
/** POSButton class takes a height, width, and string parameter and generates a button using these parameters
 *
 * @author Alex Steinbacher
 * @version 1.0
 */
public class POSButton extends Button{
	double prefH;
	double prefW;
	String txt;
	/**
	 * @param prefH : The desired height of the button
	 * @param prefW : The desired width of the button
	 * @param txt : The desired text on the button
	 */
	public POSButton(double prefH, double prefW, String txt){
		this.prefH = prefH;
		this.prefW = prefW;
		this.txt = txt;
		this.setPrefSize(prefW, prefH);
		this.setText(txt);
	}	
}
