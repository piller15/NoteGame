import javax.swing.JLabel;

public class MoveNote implements Runnable, WidthAndHeight {

	//노트 움직이는 속도
	int speed;
	
	//노트 움직이는 속도를 받아서 그 속도로 움직이게 함
	public MoveNote(int speed) {
	
		this.speed = speed;
		Thread t = new Thread(this);
		t.start();
	}

	//노트 리스트를 받아서 노트를 움직임
	public void moveLine() {
		
		JLabel note;
		
		for(int i = 0; i < GamePanel.noteWindow.getComponentCount(); i++) {
		
			if (GamePanel.noteWindow.getComponent(i).getName() == "note") {
				
				note = (JLabel)GamePanel.noteWindow.getComponent(i);
				note.setBounds(note.getX(), note.getY() + 1, note.getWidth(), note.getHeight());
				
				//노트 놓쳤을때 (콤보 초기화, HP감소, 미스판정 출력)
				if(note.getY() >= NOTEWIN_HEIGHT) {
					
					GamePanel.noteWindow.remove(note);
					GamePanel.setHP(-50);
					GamePanel.setdecision("miss");
					GamePanel.miss++;
					GamePanel.combo = 0;
					if(!NoteManager.noteList.isEmpty()) {
					
						NoteManager.noteList.remove();
					}
					
				}
				
			//이미 잡은 노트 처리
			} else if(GamePanel.noteWindow.getComponent(i).getName() == "catched") {
				
				note = (JLabel)GamePanel.noteWindow.getComponent(i);
				note.setBounds(note.getX(), note.getY() + 1, note.getWidth(), note.getHeight());
				
				if(note.getY() >= NOTEWIN_HEIGHT) {
					
					GamePanel.noteWindow.remove(note);
					
					if(!NoteManager.noteList.isEmpty()) {
						
						NoteManager.noteList.remove();
					}
				}
			}
		}
		
	}
	
	//속도 간격으로 노트 움직이는  메소드를 실행
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(NoteManager.gameStart) {
		
			//노트 움직이기
			moveLine();
	
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
