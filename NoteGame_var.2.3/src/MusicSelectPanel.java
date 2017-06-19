import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MusicSelectPanel implements WidthAndHeight, MouseListener, ItemListener{

	//버튼
	public JLabel reMenuBtn;
	public JLabel startBtn;
	
	//버튼 이미지
	public ImageIcon reMenuBtnIcon;
	public ImageIcon startBtnIcon;
	
	//음악썸네일
	public JLabel musicImgLabel;
	
	//선택정보
	public static Music 	selectMusic;
	public static File 		selectFile;
	public static String 	selectSpeed;
	public static String 	selectDifficulty;
	public static ImageIcon musicIcon;
	
	//음악 선택영역
	public JComboBox<Music> 	music;
	public JComboBox<String> 	speed;
	public JComboBox<String> 	difficulty;
	
	public JLabel[] rankChartHeader 		= new JLabel[4];
	public static JLabel[][] rankChart 		= new JLabel[10][4];
	
	
	public MusicSelectPanel() {
		
		//음악 선택 배경설정 및 객체 생성
		JPanel mSelectPanel = new JPanel(){ImageIcon i = new ImageIcon("img/BG/MusicSelectPanelBG.png");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
			}
		};
		mSelectPanel.setLayout(null);
		mSelectPanel.setSize(WIDTH, HEIGHT);
		//음악선택 패널 카드리스트 추가
		NoteGame.mainPanel.add("mselect", mSelectPanel);
		
		//음악 썸네일 생성 기본값 바람에게 부탁해 easy
		musicImgLabel = new JLabel();
		musicIcon = new ImageIcon("img/Music/Ask_to_Wind_TrilogyESStyle.png");
		musicImgLabel.setIcon(musicIcon);
		musicImgLabel.setBounds(50, 50, 200, 150);
		mSelectPanel.add(musicImgLabel);
		
		
		//음악 선택 생성 기본값 바람에게 부탁해
		music = new JComboBox<Music>();
		music.addItem(new Music("바람에게 부탁해", 
				  new File("Music/Ask_to_Wind.wav"),
			      new File("MusicNoteFile/Ask_to_Wind_Easy.txt"),
			      new File("MusicNoteFile/Ask_to_Wind_Normal.txt"),
			      new File("MusicNoteFile/Ask_to_Wind_Hard.txt"),
			      new ImageIcon("img/Music/Ask_to_Wind_TrilogyESStyle.png"),
			      new ImageIcon("img/Music/Ask_to_Wind_TrilogyNMStyle.png"),
			      new ImageIcon("img/Music/Ask_to_Wind_TrilogyHDStyle.png")));
		music.addItem(new Music("바람의 기억", 
				  new File("Music/Memory_of_Wind.wav"),
			      new File("MusicNoteFile/Memory_of_Wind_Easy.txt"),
			      new File("MusicNoteFile/Memory_of_Wind_Normal.txt"),
			      new File("MusicNoteFile/Memory_of_Wind_Hard.txt"),
			      new ImageIcon("img/Music/Memory_of_Wind_TrilogyESStyle.png"),
			      new ImageIcon("img/Music/Memory_of_Wind_TrilogyNMStyle.png"),
			      new ImageIcon("img/Music/Memory_of_Wind_TrilogyHDStyle.png")));
		music.addItemListener(this);
		music.setBounds(250, 50, 200, 50);
		mSelectPanel.add(music);
	
		//난이도 선택
		difficulty = new JComboBox<String>();
		difficulty.addItem("Easy");
		difficulty.addItem("Normal");
		difficulty.addItem("Hard");
		difficulty.addItemListener(this);
		difficulty.setBounds(250, 100, 200, 50);
		mSelectPanel.add(difficulty);
		
		//속도 선택
		speed = new JComboBox<String>();
		speed.addItem("X1");
		speed.addItem("X2");
		speed.addItem("X3");
		speed.addItem("X4");
		speed.addItemListener(this);
		speed.setBounds(250, 150, 200, 50);
		mSelectPanel.add(speed);
		
		String[] header = new String[4];
		header[0] = "RANK";
		header[1] = "NAME";
		header[2] = "SOCRE";
		header[3] = "MODE";
		for (int i = 0; i < 4; i++) {
			
			rankChartHeader[i] = new JLabel(header[i], JLabel.CENTER);
			rankChartHeader[i].setForeground(Color.WHITE);
			rankChartHeader[i].setFont(new Font(null, 0, 16));
			mSelectPanel.add(rankChartHeader[i]);
			
		
		}
		rankChartHeader[0].setBounds(50, 340, 50, 20);
		rankChartHeader[1].setBounds(110, 340, 100, 20);
		rankChartHeader[2].setBounds(220, 340, 100, 20);
		rankChartHeader[3].setBounds(330, 340, 150, 20);
		
		//랭킹 출력
		for(int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 4; j++) {
			
				rankChart[i][j] = new JLabel("", JLabel.CENTER);
				rankChart[i][j].setForeground(Color.WHITE);
				rankChart[i][j].setFont(new Font(null, 0, 16));
				mSelectPanel.add(rankChart[i][j]);
			
			}
			rankChart[i][0].setBounds(50, 360 + (22 * i), 50, 20);
			rankChart[i][1].setBounds(110, 360 + (22 * i), 100, 20);
			rankChart[i][2].setBounds(220, 360 + (22 * i), 100, 20);
			rankChart[i][3].setBounds(330, 360 + (22 * i), 150, 20);
			
		}
		
		//메인으로 버튼 생성
		reMenuBtn = new JLabel();
		reMenuBtn.addMouseListener(this);
		reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
		reMenuBtn.setBounds(MSELECT_RE_BTN_X, MSELECT_RE_BTN_Y, MSELECTBTN_WIDTH, MSELECTBTN_HEIGHT);
		mSelectPanel.add(reMenuBtn);
		
		//게임시작 버튼 생성
		startBtn = new JLabel();
		startBtn.addMouseListener(this);
		startBtn.setBounds(MSELECT_START_BTN_X, MSELECT_START_BTN_Y, MSELECTBTN_WIDTH, MSELECTBTN_HEIGHT);
		startBtn.setIcon(new ImageIcon("img/Btn/gameStartBtn_ver2.png"));
		mSelectPanel.add(startBtn);
		
		//기본 선택 설정
		selectMusic 	 = (Music)music.getSelectedItem();
		selectFile 		 = selectMusic.getMusicText((String)difficulty.getSelectedItem());
		selectSpeed 	 = (String)speed.getSelectedItem();
		selectDifficulty = (String)difficulty.getSelectedItem();
		if(NoteGameDB.DB) {
			
			setRankChart();
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() == reMenuBtn) {
			
			NoteGame.card.show(NoteGame.mainPanel, "menu");
			
		} else if (e.getComponent() == startBtn) {
			
			GamePanel.startGame = true;
			NoteGame.card.show(NoteGame.mainPanel, "startgame");
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuInBtnIcon_ver2.png"));
			
			
		} else if (e.getComponent() == startBtn) {
			
			startBtn.setIcon(new ImageIcon("img/Btn/gameStartInBtn_ver2.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
			
		} else if (e.getComponent() == startBtn) {
			
			startBtn.setIcon(new ImageIcon("img/Btn/gameStartBtn_ver2.png"));
		}
	}

	//콤보박스 선택 이벤트
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		
		if(e.getItem().getClass() == Music.class) {
			
			selectMusic = (Music)music.getSelectedItem();
			selectFile 	= selectMusic.getMusicText((String)difficulty.getSelectedItem());
			musicIcon 	= selectMusic.getMusicIcon(selectDifficulty);
			musicImgLabel.setIcon(musicIcon);
			
			if(NoteGameDB.DB) {
				
				setRankChart();
			}
		}
		
		if(e.getItem() == "Easy") {
		
			musicIcon 	= selectMusic.getMusicIcon("Easy");
			selectFile 	= selectMusic.getMusicText("Easy");
			selectDifficulty = "Easy";
			musicImgLabel.setIcon(musicIcon);
		
		} else if (e.getItem() == "Normal") {
		
			musicIcon 	= selectMusic.getMusicIcon("Normal");
			selectFile 	= selectMusic.getMusicText("Normal");
			selectDifficulty = "Normal";
			musicImgLabel.setIcon(musicIcon);
			
		} else if (e.getItem() == "Hard") {
			
			musicIcon 	= selectMusic.getMusicIcon("Hard");
			selectFile 	= selectMusic.getMusicText("Hard");
			selectDifficulty = "Hard";
			musicImgLabel.setIcon(musicIcon);
			
		} else if (e.getItem() == "X1") {
			
			selectSpeed = "X1";
			
		} else if (e.getItem() == "X2") {
			
			selectSpeed = "X2";
			
		} else if (e.getItem() == "X3") {
			
			selectSpeed = "X3";
			
		} else if (e.getItem() == "X4") {
			
			selectSpeed = "X4";
		}
	}

	
	//랭킹 출력
	public void setRankChart() {
		
		ResultSet rs;
		
		rs = NoteGameDB.getScore(selectMusic.name);
		
		try {
			int count = 0;
			while(true) {
				
				if(rs.next()) {
					
					String mode = rs.getNString(4);
					String userName = rs.getNString(2);
					int score = rs.getInt(1);
					
					rankChart[count][0].setText("" + (count + 1));
					rankChart[count][1].setText(userName);
					rankChart[count][2].setText("" + score);
					rankChart[count][3].setText(mode);
					count++;
					if(count == 10) {
						break;
					}
				} else {
				
					rankChart[count][0].setText(null);
					rankChart[count][1].setText(null);
					rankChart[count][2].setText(null);
					rankChart[count][3].setText(null);
					count++;
					if(count == 10) {
						break;
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
