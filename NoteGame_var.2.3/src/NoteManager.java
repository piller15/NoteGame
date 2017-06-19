import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class NoteManager implements Runnable {

	//관리자 기본 정보
	public FileReader 		noteFile;	
	public static boolean 	gameStart = true;
	public static int 		speed;
	
	//노트 출력 판단
	boolean s = false;
	boolean d = false;
	boolean f = false;
	boolean j = false;
	boolean k = false;
	boolean l = false;
	static int time;
	static int tcount;
	
	//노트 저장 컬랙션
	public static Queue<Note> noteList;
	MoveNote moveNote;
	
	public NoteManager(FileReader noteFile, String speed) {
		
		if (speed == "X1") {
		
			this.speed = 4;
			
		} else if (speed == "X2") {
			
			this.speed = 3;
			
		} else if (speed == "X3") {
			
			this.speed = 2;
			
		} else if (speed == "X4") {
			
			this.speed = 1;
			
		}
		noteList = new LinkedList<Note>();
		this.noteFile = noteFile;
		Thread t = new Thread(this);
		t.start();
		moveNote = new MoveNote(this.speed);
	}

	
	//시간 추출하기
	public int getNoteTime() {
		
		String temp = null;
		int ch = 0;
		int time = 0;
		
		while(true) {
			
			try {
				ch = noteFile.read();
			
				if(ch == '\r') {
					ch = noteFile.read();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ch == '\n') {
				
				time = Integer.parseInt(temp);
				break;
				
			}
			if(temp == null) {
			
				temp = ""+(char)ch;
			} else {
			
				temp += ""+(char)ch;
			}
			
		}
		return time - (speed*580)/10;
	}
	
	//노트 라인 추출
	public void getNoteLine() {
		
		int ch = 0;
		
		//라인 추출
		while(true){
			
			try {
				ch = noteFile.read();
			
				if(ch == '\r') {
					ch = noteFile.read();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ch == '\n') {
				
				break;
				
			}
			if(ch == 's') {
				
				s = true;
				
			} else if(ch == 'd') {
				
				d = true;
				
			} else if(ch == 'f') {
				
				f = true;
				
			} else if(ch == 'j') {
				
				j = true;
				
			} else if(ch == 'k') {
				
				k = true;
				
			} else if(ch == 'l') {
				
				l = true;
				
			}
			
		}
	}
	
	//라인에 노트 추가하기
	public void inLineNote() {
		
		Note note;
		

		if(s) {

			note = new Note('s', time);
			noteList.add(note);
			note.addNote(0);
			s = false;
			
		}
		
		if(d) {

			note = new Note('d', time);
			noteList.add(note);
			note.addNote(1);
			d = false;
			
		}
		
		if(f) {

			note = new Note('f', time);
			noteList.add(note);
			note.addNote(2);
			f = false;
			
		}

		if(j) {
	
			note = new Note('j', time);
			noteList.add(note);
			note.addNote(3);
			j = false;
			
		}
		
		if(k) {

			note = new Note('k', time);
			noteList.add(note);
			note.addNote(4);
			k = false;
			
		}
		
		if(l) {

			note = new Note('l', time);
			noteList.add(note);
			note.addNote(5);
			l = false;
			
		}
	
	}
	
	//다음 노트 있는지 판단
	public boolean hasNextNote() {
		
		int ch = 0;
		
		try {
			ch = noteFile.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ch == '1') {
			
			try {
				//\r\n날리기
				ch = noteFile.read();
				ch = noteFile.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
			
		} else {
			try {
				//\r\n날리기
				ch = noteFile.read();
				ch = noteFile.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//초기 시간 받기
		time = getNoteTime();
		tcount = 0;
		
		//게임 시작시 실행
		while(gameStart) {
		
			if(tcount >= time) {
				
				//라인정보 받기
				getNoteLine();
				
				//라인에 출력
				inLineNote();
				
				//다음턴 있는지 판단
				if(hasNextNote()) {

					//다음턴 시간 받기
					time = getNoteTime();
					
				//노트 다 내려왔을 경우 결과 화면 으로 이동 (음악정지, 파일닫기, notemove삭제)
				} else {
					
					try {
						Thread.sleep(580 * speed + 5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						noteFile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MusicSelectPanel.selectMusic.stopMusic();
					NoteGame.card.show(NoteGame.mainPanel, "result");
					NoteManager.gameStart = false;
					moveNote = null;
					ResultPanel.setBG("perpect");
					ResultPanel.setResult();
					
					break;
				}
				
			}
			//화면 깨짐 방지
			GamePanel.noteWindow.repaint();
			
			//새로운 시간 받기
			tcount = (int)(System.currentTimeMillis() - GamePanel.startTime)/10;

		}
	}
}
