package MusicUntility;

import processing.core.PApplet;

public class Guitar {
	
	public final static String[] NORMAL_TUNING = { "E","A","D","G","B","E"}; 
	public final static String[] UKULELE_TUNING = { "G", "C", "E", "A"}; 

	Site[] sites;
	
	public Guitar(String[] s){
		sites = new Site[s.length];
		for(int i = 0; i < s.length; i++){
			sites[i] = new Site(s[i]);
		}
	}
	
	public Guitar(){
		this(NORMAL_TUNING);
	}
	
	public int getLength(){
		return sites[0].getLenght();
	}
	
	public int getStringNum(){
		return sites.length;
	}
	
	public Site getString(int n){
		return sites[n];
	}
	
	public String printScale(Scale scale) {
		String s = "Guitar:\n";
		s += Site.printFret(12);
		for(Site si : sites){
			s += "\n" + si.printScale(scale);
		}
		return s;
	}

	private final int GUITAR_STRING_HEIGHT = 30;
	private final int GUITAR_BAR_WIDTH = 50;
	private final int[] GUITAR_BAR = {67,64,60,57,53,50,48,45,42,40,38,36};
	
	public void draw(PApplet applet, Scale scale){
		
		//Draw Guitar
		applet.pushStyle();
		applet.fill(0, 0, 100);
		applet.stroke(0,0,0);
		applet.strokeWeight(3);
		

		applet.rect(0,0, (getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, getStringNum() * GUITAR_STRING_HEIGHT);
		
		//Dots
		applet.pushMatrix();
		applet.pushStyle();
		applet.stroke(0,0,50);
		applet.noFill();
		applet.translate(GUITAR_BAR[0]+GUITAR_BAR[1], 0);
		applet.ellipse( GUITAR_BAR[2] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
		applet.translate(GUITAR_BAR[2]+GUITAR_BAR[3], 0);
		applet.ellipse( GUITAR_BAR[4] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f,7,7);
		applet.translate(GUITAR_BAR[4]+GUITAR_BAR[5], 0);
		applet.ellipse( GUITAR_BAR[6] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
		applet.translate(GUITAR_BAR[6]+GUITAR_BAR[7], 0);
		applet.ellipse( GUITAR_BAR[8] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f,3,3);
		applet.translate(GUITAR_BAR[8]+GUITAR_BAR[9]+GUITAR_BAR[10], 0);
		applet.ellipse( GUITAR_BAR[11] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f + 5,3,3);
		applet.ellipse( GUITAR_BAR[11] * 0.5f, -GUITAR_STRING_HEIGHT * 0.5f - 5,3,3);
		applet.popStyle();
		applet.popMatrix();
		
		//Strings
		applet.pushMatrix();
			applet.translate(0, GUITAR_STRING_HEIGHT * 0.5f);
			applet.fill(0);
			applet.textSize(20);
			for(int i = 0; i < getStringNum(); i++){
				Site s = getString(i);
				int tune = s.getTuneNummber();
				if(scale.inScale(tune)){
					applet.fill(Note.getHueOfNote(scale.NoteInScale(tune)), 80, 80);
					applet.ellipse(-20, 0, 25, 25);
				}
				applet.fill(0);
				applet.text(s.getTune(),-28,7);
//				line(0, 0, (guitare.getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, 0);
				applet.pushMatrix();
					applet.pushStyle();
					applet.noStroke();
					for(int j = 1; j < s.getLenght(); j++){
						if (scale.inScale(tune + j)) {
							applet.fill(Note.getHueOfNote(scale.NoteInScale(tune + j)), 80, 80);
//							ellipse(25, 0, 25, 25);
							applet.rect(0f, -15f, GUITAR_BAR[j-1], 30);
						}
						applet.translate(GUITAR_BAR[j-1],0);
					}
					applet.popStyle();
				applet.popMatrix();
				applet.line(0, 0, (getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, 0);
				applet.translate(0, GUITAR_STRING_HEIGHT);
			}
		applet.popMatrix();
		
		applet.noFill();
		applet.rect(0,0, (getString(0).getLenght() -1) * GUITAR_BAR_WIDTH, getStringNum() * GUITAR_STRING_HEIGHT);
		
		applet.stroke(0,0,0);

		//Bars
		applet.pushMatrix();
//			applet.translate(GUITAR_BAR_WIDTH, 0);
			for(int i = 1; i < getString(0).getLenght(); i++){
				applet.line(0, 0, 0, getStringNum() * GUITAR_STRING_HEIGHT);
				applet.translate(GUITAR_BAR[i-1], 0);
//				applet.translate(GUITAR_BAR_WIDTH, 0);
			}
		applet.popMatrix();
		
		applet.popStyle();
	}
	
	public int getWidth(){
		if(sites != null){
			if(sites.length > 0){
				return GUITAR_BAR_WIDTH * sites[0].getLenght();
			}
		}
		return 0;
	}
	
	public int getHeight(){
		return GUITAR_STRING_HEIGHT * sites.length;
	}
}
