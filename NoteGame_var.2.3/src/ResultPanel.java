import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class ResultPanel implements WidthAndHeight, MouseListener {

	
	//결과 배경화면
	public static JLabel resultBG;
	
	//음악 이미지
	public static JLabel musicIconLabel;
	
	//이름
	public JTextField nameField;
	public static boolean scoreSave = false;
	
	//판정결과 수치
	public static JLabel combo;
	public static JLabel perpect;
	public static JLabel great;
	public static JLabel good;
	public static JLabel bad;
	public static JLabel miss;
	public static JLabel score;
	
	//버튼
	public JLabel reMenuBtn;
	public JLabel reGameBtn;
	public JLabel selectMusicBtn;
	public JLabel saveBtn;
	public JLabel exitBtn;
	
	//랭킹 1등 ~ 10등까지
	public JLabel[] rankChartHeader 		= new JLabel[4];
	public static JLabel[][] rankChart 		= new JLabel[10][4];
	
	//버튼 이미지
	public ImageIcon reMenuBtnIcon 			= new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png");
	public ImageIcon reGameBtnIcon 			= new ImageIcon("img/Btn/reGameBtnIcon.png");
	public ImageIcon selectMusicBtnIcon 	= new ImageIcon("img/Btn/selectMusicBtnIcon.png");
	public ImageIcon saveBtnIcon 			= new ImageIcon("img/Btn/saveBtnIcon.png");
	public ImageIcon exitBtnIcon 			= new ImageIcon("img/Btn/exitBtnIcon_ver2.png");
	public ImageIcon reMenuInBtnIcon 		= new ImageIcon("img/Btn/reMenuInBtnIcon_ver2.png");
	public ImageIcon reGameInBtnIcon 		= new ImageIcon("img/Btn/reGameInBtnIcon.png");
	public ImageIcon selectMusicInBtnIcon 	= new ImageIcon("img/Btn/selectMusicInBtnIcon.png");
	public ImageIcon saveInBtnIcon 			= new ImageIcon("img/Btn/saveInBtnIcon.png");
	public ImageIcon exitInBtnIcon 			= new ImageIcon("img/Btn/exitInBtnIcon_ver2.png");
	
	
	public ResultPanel() {
		
		//레이아웃 패널 생성
		JLayeredPane resultPanel = new JLayeredPane();
		resultPanel.setSize(WIDTH, HEIGHT);
		resultPanel.setLayout(null);
		NoteGame.mainPanel.add("result", resultPanel);
		
		//배경 생성
		resultBG = new JLabel();
		resultBG.setBounds(0, 0, WIDTH, HEIGHT);
		resultPanel.add(resultBG);
		resultPanel.setLayer(resultBG, 1);
		
		//곡 이미지 라벨
		musicIconLabel = new JLabel();
		musicIconLabel.setIcon(MusicSelectPanel.musicIcon);
		musicIconLabel.setBounds(50, 50, 200, 150);
		resultPanel.add(musicIconLabel);
		resultPanel.setLayer(musicIconLabel, 2);
		
		//콤보
		JLabel comboImg = new JLabel("combo", JLabel.CENTER);
		comboImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 0), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		comboImg.setForeground(Color.WHITE);
		resultPanel.add(comboImg);
		resultPanel.setLayer(comboImg, 2);
		combo = new JLabel("", JLabel.CENTER);
		combo.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 0), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		combo.setForeground(Color.WHITE);
		resultPanel.add(combo);
		resultPanel.setLayer(combo, 2);
		
		//퍼펙트
		JLabel perpectImg = new JLabel("perpect", JLabel.CENTER);
		perpectImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 1), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		perpectImg.setForeground(Color.WHITE);
		resultPanel.add(perpectImg);
		resultPanel.setLayer(perpectImg, 2);
		perpect = new JLabel("", JLabel.CENTER);
		perpect.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 1), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		perpect.setForeground(Color.WHITE);
		resultPanel.add(perpect);
		resultPanel.setLayer(perpect, 2);
		
		//그레이트
		JLabel greatImg = new JLabel("great", JLabel.CENTER);
		greatImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 2), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		greatImg.setForeground(Color.WHITE);
		resultPanel.add(greatImg);
		resultPanel.setLayer(greatImg, 2);
		great = new JLabel("", JLabel.CENTER);
		great.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 2), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		great.setForeground(Color.WHITE);
		resultPanel.add(great);
		resultPanel.setLayer(great, 2);
		
		//굿
		JLabel goodImg = new JLabel("good", JLabel.CENTER);
		goodImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 3), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		goodImg.setForeground(Color.WHITE);
		resultPanel.add(goodImg);
		resultPanel.setLayer(goodImg, 2);
		good = new JLabel("", JLabel.CENTER);
		good.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 3), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		good.setForeground(Color.WHITE);
		resultPanel.add(good);
		resultPanel.setLayer(good, 2);
		
		//베드
		JLabel badImg = new JLabel("bad", JLabel.CENTER);
		badImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 4), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		badImg.setForeground(Color.WHITE);
		resultPanel.add(badImg);
		resultPanel.setLayer(badImg, 2);
		bad = new JLabel("", JLabel.CENTER);
		bad.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 4), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		bad.setForeground(Color.WHITE);
		resultPanel.add(bad);
		resultPanel.setLayer(bad, 2);
		
		//미스
		JLabel missImg = new JLabel("miss", JLabel.CENTER);
		missImg.setBounds(250, 50 + (RESULTIMG_HEIGHT * 5), RESULTIMG_WIDTH, RESULTIMG_HEIGHT);
		missImg.setForeground(Color.WHITE);
		resultPanel.add(missImg);
		resultPanel.setLayer(missImg, 2);
		miss = new JLabel("", JLabel.CENTER);
		miss.setBounds(250 + RESULTIMG_WIDTH, 50 + (RESULTIMG_HEIGHT * 5), RESULTNUM_WIDTH, RESULTNUM_HEIGHT);
		miss.setForeground(Color.WHITE);
		resultPanel.add(miss);
		resultPanel.setLayer(miss, 2);
		
		//점수
		score = new JLabel("", JLabel.CENTER);
		score.setBounds(50, 50 + (RESULTIMG_HEIGHT * 6) + 10, 400, 50);
		score.setForeground(Color.WHITE);
		score.setFont(new Font(null, 0, 50));
		resultPanel.add(score);
		resultPanel.setLayer(score, 2);
		
		//이름 입력
		JLabel nameText = new JLabel("이름 : ", JLabel.LEFT);
		nameText.setBounds(160, 300, 50, RESULTBTN_HEIGHT);
		nameText.setForeground(Color.WHITE);
		resultPanel.add(nameText);
		resultPanel.setLayer(nameText, 2);
		nameField = new JTextField();
		nameField.setBounds(200, 300, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		nameField.setToolTipText("이름을 입력하세요");
		resultPanel.add(nameField);
		resultPanel.setLayer(nameField, 2);
		
		//저장하기 버튼
		saveBtn = new JLabel("", JLabel.CENTER);
		saveBtn.addMouseListener(this);
		saveBtn.setIcon(saveBtnIcon);
		saveBtn.setBounds(300, 300, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		resultPanel.add(saveBtn);
		resultPanel.setLayer(saveBtn, 2);
		
		//랭킹 머리 출력
		String[] header = new String[4];
		header[0] = "RANK";
		header[1] = "NAME";
		header[2] = "SOCRE";
		header[3] = "MODE";
		for (int i = 0; i < 4; i++) {
			
			rankChartHeader[i] = new JLabel(header[i], JLabel.CENTER);
			rankChartHeader[i].setForeground(Color.WHITE);
			rankChartHeader[i].setFont(new Font(null, 0, 16));
			resultPanel.add(rankChartHeader[i]);
			resultPanel.setLayer(rankChartHeader[i], 2);
		
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
				resultPanel.add(rankChart[i][j]);
				resultPanel.setLayer(rankChart[i][j], 2);
			
			}
			rankChart[i][0].setBounds(50, 360 + (22 * i), 50, 20);
			rankChart[i][1].setBounds(110, 360 + (22 * i), 100, 20);
			rankChart[i][2].setBounds(220, 360 + (22 * i), 100, 20);
			rankChart[i][3].setBounds(330, 360 + (22 * i), 150, 20);
			
		}
		
		//메인으로 버튼
		reMenuBtn = new JLabel();
		reMenuBtn.addMouseListener(this);
		reMenuBtn.setIcon(reMenuBtnIcon);
		reMenuBtn.setBounds(20, 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		resultPanel.add(reMenuBtn);
		resultPanel.setLayer(reMenuBtn, 2);
		
		//다시하기 버튼
		reGameBtn = new JLabel();
		reGameBtn.addMouseListener(this);
		reGameBtn.setIcon(reGameBtnIcon);
		reGameBtn.setBounds(20 + (RESULTBTN_WIDTH * 1), 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		resultPanel.add(reGameBtn);
		resultPanel.setLayer(reGameBtn, 2);
		
		//음악선택 버튼
		selectMusicBtn = new JLabel();
		selectMusicBtn.addMouseListener(this);
		selectMusicBtn.setIcon(selectMusicBtnIcon);
		selectMusicBtn.setBounds(20  + (RESULTBTN_WIDTH * 2), 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		resultPanel.add(selectMusicBtn);
		resultPanel.setLayer(selectMusicBtn, 2);
		
		//게임종료 버튼
		exitBtn = new JLabel();
		exitBtn.addMouseListener(this);
		exitBtn.setIcon(exitBtnIcon);
		exitBtn.setBounds(WIDTH - RESULTBTN_WIDTH - 20, 650, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		resultPanel.add(exitBtn);
		resultPanel.setLayer(exitBtn, 2);
		
	}

	//게임결과에 따라 배경 색상 다름
	public static void setBG(String decision) {
		
		if(decision == "perpect") {
			
			resultBG.setIcon(new ImageIcon("img/BG/PerpectResultPanelBG.png"));
			
		} else if (decision == "good") {
		
			resultBG.setIcon(new ImageIcon("img/BG/GoodResultPanelBG.png"));
			
		} else if (decision == "bad") {
			
			resultBG.setIcon(new ImageIcon("img/BG/BadResultPanelBG.png"));
			
		} else {
			
			resultBG.setIcon(new ImageIcon("img/BG/GoodResultPanelBG.png"));
		}
	}
	
	//결과 출력하기
	public static void setResult() {
		

		musicIconLabel.setIcon(MusicSelectPanel.musicIcon);
		
		combo.setText(GamePanel.maxCombo + "");
		perpect.setText(GamePanel.perpect + "");
		great.setText(GamePanel.great + "");
		good.setText(GamePanel.good + "");
		bad.setText(GamePanel.bad + "");
		miss.setText(GamePanel.miss + "");
		score.setText(GamePanel.score + "");
		
		if(NoteGameDB.DB) {
		
			setRankChart();
		}

		
		scoreSave = true;
		
		GamePanel.combo = 0;
		GamePanel.maxCombo = 0;
		GamePanel.perpect = 0;
		GamePanel.great = 0;
		GamePanel.good = 0;
		GamePanel.bad = 0;
		GamePanel.miss = 0;
		GamePanel.score = 0;
		GamePanel.comboLabel.setText(null);
		GamePanel.delHP.setBounds(GamePanel.delHP.getX(), GamePanel.delHP.getY(), 
				                  GamePanel.delHP.getWidth(), 0);
	}
	
	
	//랭킹 출력
	public static void setRankChart() {
		
		ResultSet rs;
		
		rs = NoteGameDB.getScore(MusicSelectPanel.selectMusic.name);
		
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//메인으로
		if(e.getComponent() == reMenuBtn) {
			
			NoteGame.card.show(NoteGame.mainPanel, "menu");
			
		//다시하기
		} else if (e.getComponent() == reGameBtn) {
			
			GamePanel.startGame = true;
			NoteGame.card.show(NoteGame.mainPanel, "startgame");
		
		//음악 선택
		} else if (e.getComponent() == selectMusicBtn) {
			
			NoteGame.card.show(NoteGame.mainPanel, "mselect");
			
		//저장하기
		} else if (e.getComponent() == saveBtn) {
			
			if(scoreSave) {
				
				if(NoteGameDB.DB) {
				NoteGameDB.setScore(MusicSelectPanel.selectMusic.name, 
							nameField.getText(), Integer.valueOf(score.getText())
							, MusicSelectPanel.selectDifficulty);
				}
				nameField.setText(null);
				scoreSave = false;
				//랭킹차트 초기화
				if(NoteGameDB.DB) {
				
					setRankChart();
				}
				
			}
			
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			System.exit(0);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//메인으로
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(reMenuInBtnIcon);
			
		//다시하기
		} else if (e.getComponent() == reGameBtn) {
			
			reGameBtn.setIcon(reGameInBtnIcon);
			
		//음악 선택
		} else if (e.getComponent() == selectMusicBtn) {
			
			selectMusicBtn.setIcon(selectMusicInBtnIcon);
			
		//저장하기
		} else if (e.getComponent() == saveBtn) {
			
			saveBtn.setIcon(saveInBtnIcon);
			
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(exitInBtnIcon);
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//메인으로
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(reMenuBtnIcon);
			
		//다시하기
		} else if (e.getComponent() == reGameBtn) {
			
			reGameBtn.setIcon(reGameBtnIcon);
			
		//음악 선택
		} else if (e.getComponent() == selectMusicBtn) {
			
			selectMusicBtn.setIcon(selectMusicBtnIcon);
			
		//저장하기
		} else if (e.getComponent() == saveBtn) {
			
			saveBtn.setIcon(saveBtnIcon);
			
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(exitBtnIcon);
			
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
}
