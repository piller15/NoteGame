import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NoteGame implements WidthAndHeight {

	static CardLayout card = new CardLayout();
	static JPanel mainPanel;
	JFrame frame;
	
	//초기 프레임 생성
	public void createFrame() {
		
		//기본 프레임 설정
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(WIDTH + 6, HEIGHT + 29);
		
		//메인 페널 생성
		mainPanel = new JPanel();
		mainPanel.setLayout(card);
		mainPanel.setSize(WIDTH, HEIGHT);
		frame.add(mainPanel);
		
		//DB연결
		new NoteGameDB();
				
		//카드 페널 추가
		new MenuPanel();
		new MusicInfoPanel();
		new InfoPanel();
		new GamePanel();
		new MusicSelectPanel();
		new ResultPanel();
		new PausePanel();
		
		//최초 보여줄 화면
		card.show(mainPanel, "menu");
		
		
		
	}
	
	public static void main(String[] args) {

		NoteGame notegame = new NoteGame();
		
		notegame.createFrame();
		
	}

}
