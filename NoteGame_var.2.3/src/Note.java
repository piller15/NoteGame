import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Note implements WidthAndHeight{

	JLabel note;
	int line;
	int time;
	char ch;
	boolean getNote = true;
	boolean setNote = false;
	
	//note생성
	public Note(char ch, int time) {
	
		this.time = time;
		this.ch = ch;
		note = new JLabel();
		note.setName("note");
		if(ch == 's' || ch == 'f' || ch == 'j' || ch == 'l') {
		
			note.setIcon(new ImageIcon("img/Game/note_w.png"));
			
		} else if(ch == 'd' || ch == 'k') {
			
			note.setIcon(new ImageIcon("img/Game/note_b.png"));
		}
			
	}

	public void addNote(int line) {
		
		
		note.setBounds(line * 50, 0, NOTE_WIDTH, NOTE_HEIGHT);
		GamePanel.noteWindow.add(note);
		GamePanel.noteWindow.setLayer(note, 2);
		this.line = line;
		setNote = true;
		
	}
	
}
