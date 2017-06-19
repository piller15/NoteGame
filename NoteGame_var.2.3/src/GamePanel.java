import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GamePanel implements WidthAndHeight, KeyListener, Runnable{

	//게임 시작판단
	public static boolean startGame = false;
	
	//게임화면 구성
	public static JLayeredPane noteWindow;
	public static JLabel decisionLabel;
	public static JLabel hp;
	public static JLabel delHP;
	public static JLabel comboLabel;
	public static JLabel[] decision = new JLabel[6];
	public JLabel[] line = new JLabel[6];
	public JLabel decisionBar;
	public JLabel scoreLabel;
	
	//게임 시작한 시간
	public static long startTime;
	static int decisionTime;
	boolean musicStart = true;
	
	//버튼 이미지
	ImageIcon lineBG 		= new ImageIcon("img/Effect/lineBG.png");
	ImageIcon decisionBarBG = new ImageIcon("img/Game/decisionBar.png");
	ImageIcon HPIcon 		= new ImageIcon("img/Game/HPIcon.png");
	ImageIcon delHPIcon 	= new ImageIcon("img/Game/DelHPIcon.png");
	
	//판정 출력
	static boolean decisionSet = false;
	
	//결과
	public static int combo;
	public static int perpect;
	public static int great;
	public static int good;
	public static int bad;
	public static int miss;
	public static int score;
	public static int maxCombo;
	
	public GamePanel() {
		
		JLayeredPane gamePanel = new JLayeredPane(){ImageIcon i = new ImageIcon("img/BG/GamePanelBG.png");
						public void paintComponent(Graphics g) {
							g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
						}
					};
		
		//노트 윈도우 설정
		noteWindow = new JLayeredPane(){ImageIcon i = new ImageIcon("img/BG/noteWindowBG.jpg");
						public void paintComponent(Graphics g) {
							g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
						}
					};
		noteWindow.setLayout(null);
		noteWindow.setBounds(100, 0, NOTEWIN_WIDTH, NOTEWIN_HEIGHT);
		noteWindow.addKeyListener(this);
		gamePanel.add(noteWindow);
		
		
		//라인 생성	
		for(int i = 0; i < line.length; i++) {
			
			line[i] = new JLabel();
			decision[i] = new JLabel();
			
			//라인 이팩트 생성
			line[i].setBounds(LINE_X + (i * LINE_WIDTH), LINE_Y, LINE_WIDTH, LINE_HEIGHT);
			noteWindow.add(line[i]);
			noteWindow.setLayer(line[i], 4);
			
			//판정이펙트 생성
			decision[i].setBounds(DECISION_X + (i * DECISION_WIDTH), DECISION_Y, DECISION_WIDTH, DECISION_HEIGHT);
			noteWindow.add(decision[i]);
			noteWindow.setLayer(decision[i], 4);
		}
		
		//콤보 위치 생성
		comboLabel = new JLabel("", JLabel.CENTER);
		comboLabel.setBounds(COMBO_X, COMBO_Y, COMBO_WIDTH, COMBO_HEIGHT);
		comboLabel.setFont(new Font(null, 0, 40));
		comboLabel.setForeground(Color.WHITE);
		noteWindow.add(comboLabel);
		noteWindow.setLayer(comboLabel, 3);
		
		//판정 위치 생성
		decisionLabel = new JLabel("", JLabel.CENTER);
		decisionLabel.setBounds(DECISION_TEXT_X, DECISION_TEXT_Y, DECISION_TEXT_WIDTH, DECISION_TEXT_HEIGHT);
		decisionLabel.setFont(new Font(null, 0, 30));
		decisionLabel.setForeground(Color.WHITE);
		noteWindow.add(decisionLabel);
		noteWindow.setLayer(decisionLabel, 3);
		
		//판정바 생성
		decisionBar = new JLabel();
		decisionBar.setBounds(DECISION_BAR_X, DECISION_BAR_Y, DECISION_BAR_WIDTH, DECISION_BAR_HEIGHT);
		decisionBar.setIcon(decisionBarBG);
		noteWindow.add(decisionBar);
		noteWindow.setLayer(decisionBar, 1);
		
		//HP생성
		hp = new JLabel();
		hp.setBounds(HP_X, HP_Y, HP_WIDTH, HP_HEIGHT);
		hp.setIcon(HPIcon);
		gamePanel.add(hp);
		gamePanel.setLayer(hp, 1);
		
		//데미지 바
		delHP = new JLabel();
		delHP.setBounds(HP_X, HP_Y, HP_WIDTH, 0);
		delHP.setIcon(delHPIcon);
		gamePanel.add(delHP);
		gamePanel.setLayer(delHP, 2);
		
		NoteGame.mainPanel.add("startgame", gamePanel);
		Thread t = new Thread(this);
		t.start();
	}

	//키 입력시 동작들
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyChar() == 's' ||
		   e.getKeyChar() == 'S') {
			
			catchNote('s', 0);
			
		} else if(e.getKeyChar() == 'd' ||
				  e.getKeyChar() == 'D') {
			
			catchNote('d', 1);
			
		} else if(e.getKeyChar() == 'f' ||
				  e.getKeyChar() == 'F') {
			
			catchNote('f', 2);
			
		} else if(e.getKeyChar() == 'j' ||
				  e.getKeyChar() == 'J') {
			
			catchNote('j', 3);
			
		} else if(e.getKeyChar() == 'k' ||
				  e.getKeyChar() == 'K') {
			
			catchNote('k', 4);
			
		} else if(e.getKeyChar() == 'l' ||
				  e.getKeyChar() == 'L') {
			
			catchNote('l', 5);
			
		}
		
		//일시정지 화면을 돌아감
		if((int)e.getKeyChar() == ESC) {
			
			musicStart = false;
			MusicSelectPanel.selectMusic.stopMusic();
			NoteGame.card.show(NoteGame.mainPanel, "pause");
			NoteManager.gameStart = false;
			GamePanel.comboLabel.setText(null);
			//지금까지 게임내요 초기화
			ResultPanel.setResult();
			
			//노트 컬렉션 초기화
			while(!NoteManager.noteList.isEmpty()) {
				Note noteObj = NoteManager.noteList.remove();
				noteObj.note.setIcon(null);
				noteWindow.remove(noteObj.note);
			}
		}
	}

	//이펙트 출력
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyChar() == 's' ||
		   e.getKeyChar() == 'S') {
			
			line[0].setIcon(lineBG);
			
		} else if(e.getKeyChar() == 'd' ||
				  e.getKeyChar() == 'D') {
			
			line[1].setIcon(lineBG);
			
		} else if(e.getKeyChar() == 'f' ||
				  e.getKeyChar() == 'F') {
			
			line[2].setIcon(lineBG);
			
		} else if(e.getKeyChar() == 'j' ||
				  e.getKeyChar() == 'J') {
			
			line[3].setIcon(lineBG);
			
		} else if(e.getKeyChar() == 'k' ||
				  e.getKeyChar() == 'K') {
			
			line[4].setIcon(lineBG);
			
		} else if(e.getKeyChar() == 'l' ||
				  e.getKeyChar() == 'L') {
			
			line[5].setIcon(lineBG);
			
		}
	}

	//이펙트 출력
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyChar() == 's' ||
		   e.getKeyChar() == 'S') {
			
			line[0].setIcon(null);
			
		} else if(e.getKeyChar() == 'd' ||
				  e.getKeyChar() == 'D') {
			
			line[1].setIcon(null);
			
		} else if(e.getKeyChar() == 'f' ||
				  e.getKeyChar() == 'F') {
			
			line[2].setIcon(null);
			
		} else if(e.getKeyChar() == 'j' ||
				  e.getKeyChar() == 'J') {
			
			line[3].setIcon(null);
			
		} else if(e.getKeyChar() == 'k' ||
				  e.getKeyChar() == 'K') {
			
			line[4].setIcon(null);
			
		} else if(e.getKeyChar() == 'l' ||
				  e.getKeyChar() == 'L') {
			
			line[5].setIcon(null);
			
		}
	}

	//게임 동작 대기
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {

			//게임 시작시 동작(초기화)
			if(startGame) {
				
				//초기시간 저장
				startTime = System.currentTimeMillis();
				
				try {
					
					//노트 관리자 생성
					NoteManager.gameStart = true;
					new NoteManager(new FileReader(MusicSelectPanel.selectFile), 
							        MusicSelectPanel.selectSpeed);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				noteWindow.requestFocus();
				musicStart = true;
				startGame = false;
				
				
				//5초 기다리기
				while(musicStart) {
					
					if(startTime/10 <= (System.currentTimeMillis())/10 - 500) {
					
						musicStart = true;
						break;
					}
				}
				//음악 시작
				if(musicStart) {
				
					MusicSelectPanel.selectMusic.playMusic();
				}
				
			}
			
			//판정바 나타내는 시간
			if(decisionSet) {
				
				if(decisionTime > 500) {
					
					decisionLabel.setIcon(null);
					decisionLabel.setText(null);
					decisionSet = false;
					decisionTime = 0;
				}
				decisionTime++;
			}
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//입력시 시간과 비교하여 판정결과
	public void catchNote(char inputLine, int lineNum) {
		
		int inputTime = (int)(System.currentTimeMillis() - startTime)/10 - (NoteManager.speed*580)/10;
		Note noteOBj;
		Iterator<Note> it;
		it = NoteManager.noteList.iterator();
		Effect eft;
		
		
		while(it.hasNext()) {
			
			noteOBj = it.next();
			
			if(noteOBj.note.getName() != "catched" && inputLine == noteOBj.ch) {
				
				//perpect
				if(noteOBj.time - 7 <= inputTime && inputTime <= noteOBj.time + 7) {
					
					noteOBj.note.setName("catched");
					combo++;
					setdecision("perpect");
					score += 50 + (combo * 15);
					perpect++;
					setHP(5);
					
					if(maxCombo <= combo) {
						
						maxCombo = combo;
					}
					noteOBj.note.setIcon(null);
					eft = new Effect(lineNum);
					eft = null;
					break;
					
				//great
				} else if(noteOBj.time - 10 <= inputTime && inputTime <= noteOBj.time + 10){
					
					noteOBj.note.setName("catched");
					combo++;
					setdecision("great");
					score += 30 + (combo * 10);
					great++;
					setHP(3);
					
					if(maxCombo <= combo) {
						
						maxCombo = combo;
					}
					noteOBj.note.setIcon(null);
					eft = new Effect(lineNum);
					eft = null;
					break;
					
				//good
				} else if(noteOBj.time - 13 <= inputTime && inputTime <= noteOBj.time + 13) {
					
					noteOBj.note.setName("catched");
					score += 10 + (combo * 5);
					combo++;
					setdecision("good");
					good++;
					setHP(2);
					
					if(maxCombo <= combo) {
						
						maxCombo = combo;
					}
					noteOBj.note.setIcon(null);
					eft = new Effect(lineNum);
					eft = null;
					break;
					
				//bad	
				} else if(noteOBj.time - 16 <= inputTime && inputTime <= noteOBj.time + 16) {
					
					noteOBj.note.setName("catched");
					combo++;
					score += 5;
					setdecision("bad");
					bad++;
					setHP(1);
					
					if(maxCombo <= combo) {
						
						maxCombo = combo;
					}
					noteOBj.note.setIcon(null);
					eft = new Effect(lineNum);
					eft = null;
					break;
				}
			}
			
		}
	
	}
	
	//체력 증가 감소
	public static void setHP(int hp) {
		
		//최대 체력일때 증가하지 않음
		if(0 <= delHP.getHeight()) {
		
			delHP.setBounds(HP_X, HP_Y, HP_WIDTH, delHP.getHeight() - hp);
			
			//최대체력 이상으로 증가 방지
			if(delHP.getHeight() <= 0) {
				
				delHP.setBounds(HP_X, HP_Y, HP_WIDTH, 0);
			}
			
			//체력이 다떨어졌을때 죽음
			if(delHP.getHeight() >= HP_HEIGHT) {
				
				MusicSelectPanel.selectMusic.stopMusic();
				NoteManager.gameStart = false;
				ResultPanel.setBG("bad");
				ResultPanel.setResult();
				while(!NoteManager.noteList.isEmpty()) {
					Note noteObj = NoteManager.noteList.remove();
					noteObj.note.setIcon(null);
					noteWindow.remove(noteObj.note);
				}
				NoteGame.card.show(NoteGame.mainPanel, "result");
			}
		}
		
	}

	//판정 나타내기
	public static void setdecision(String decision) {
		
		if(decision == "perpect") {
			
			decisionLabel.setText("perpect");
			comboLabel.setText(combo + "");
			decisionSet = true;
			decisionTime = 0;
			
		} else if (decision == "great") {
			
			decisionLabel.setText("great");
			comboLabel.setText(combo + "");
			decisionSet = true;
			decisionTime = 0;
			
		} else if (decision == "good") {
			
			decisionLabel.setText("good");
			comboLabel.setText(combo + "");
			decisionSet = true;
			decisionTime = 0;
			
		} else if (decision == "bad") {
			
			decisionLabel.setText("bad");
			comboLabel.setText(combo + "");
			decisionSet = true;
			decisionTime = 0;
			
		} else if (decision == "miss") {
			
			decisionLabel.setText("miss");
			comboLabel.setText("");
			decisionSet = true;
			decisionTime = 0;
			
		}
	}
}