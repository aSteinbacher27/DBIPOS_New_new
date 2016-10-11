import javafx.scene.control.Button;

public class POSButton extends Button{
	double prefH;
	double prefW;
	String txt;
	
	public POSButton(double prefH, double prefW, String txt){
		this.prefH = prefH;
		this.prefW = prefW;
		this.txt = txt;
		this.setPrefSize(prefW, prefH);
		this.setText(txt);
	}	
}