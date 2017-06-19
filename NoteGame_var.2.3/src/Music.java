import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;

public class Music {

	//음악 정보
	public String 		name;
	public AudioClip 	clip;
	public File 		musicFile;
	
	//노트정보 파일
	public File easyFile;
	public File normalFile;
	public File hardFile;
	
	//음악 이미지
	public ImageIcon easyIcon;
	public ImageIcon normalIcon;
	public ImageIcon hardIcon;
	
	public Music(String name, File musicFile, File easyFile, File normalFile, File hardFile, 
			     ImageIcon easyIcon, ImageIcon normalIcon, ImageIcon hardIcon) {
		
		this.name 		= name;
		this.musicFile 	= musicFile;
		this.easyFile 	= easyFile;
		this.normalFile = normalFile;
		this.hardFile 	= hardFile;
		this.easyIcon 	= easyIcon;
		this.normalIcon = normalIcon;
		this.hardIcon 	= hardIcon;
		
		try {
			clip = Applet.newAudioClip(musicFile.toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//음악 시작
	public void playMusic() {
	
		try {
		    clip.play();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//음악 정지
	public void stopMusic() {
		
		try {
		    clip.stop();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		
		return name;
	}
	
	//난이도에 따라 이미지 반환
	public ImageIcon getMusicIcon(String difficulty) {
		
		if(difficulty == "Easy") {
			
			return easyIcon;
			
		} else if (difficulty == "Normal") {
			
			return normalIcon;
			
		} else if (difficulty == "Hard") {
			
			return hardIcon;
			
		} else {
			
			return null;
		}
	}
	
	//난이도에 따라 노트 파일 반환
	public File getMusicText(String difficulty) {
		
		if(difficulty == "Easy") {
			
			return easyFile;
			
		} else if (difficulty == "Normal") {
			
			return normalFile;
			
		} else if (difficulty == "Hard") {
			
			return hardFile;
			
		} else {
			
			return null;
		}
	}
}
