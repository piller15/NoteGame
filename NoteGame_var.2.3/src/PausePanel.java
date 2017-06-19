import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PausePanel implements WidthAndHeight, MouseListener{

	JLabel reMenuBtn;
	JLabel reGameBtn;
	JLabel selectMusicBtn;
	JLabel exitBtn;
	
	public PausePanel() {
		
		JPanel pausePanel = new JPanel(){ImageIcon i = new ImageIcon("img/BG/GameInfoPanelBG.png");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
			}
		};
		pausePanel.setLayout(null);
		pausePanel.setSize(WIDTH, HEIGHT);
		NoteGame.mainPanel.add("pause", pausePanel);
		
		//안내 이미지 출력
		JLabel infoImg = new JLabel();
		infoImg.setIcon(new ImageIcon("img/BG/pauseimg.png"));
		infoImg.setBounds(20, 100, 466, 428);
		pausePanel.add(infoImg);
		
		//메인으로 버튼 생성
		reMenuBtn = new JLabel();
		reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
		reMenuBtn.addMouseListener(this);
		reMenuBtn.setBounds(20, 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		pausePanel.add(reMenuBtn);
		
		//다시하기 버튼
		reGameBtn = new JLabel();
		reGameBtn.addMouseListener(this);
		reGameBtn.setIcon(new ImageIcon("img/Btn/reGameBtnIcon.png"));
		reGameBtn.setBounds(20 + (RESULTBTN_WIDTH * 1), 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		pausePanel.add(reGameBtn);
		
		//음악선택 버튼
		selectMusicBtn = new JLabel();
		selectMusicBtn.addMouseListener(this);
		selectMusicBtn.setIcon(new ImageIcon("img/Btn/selectMusicBtnIcon.png"));
		selectMusicBtn.setBounds(20  + (RESULTBTN_WIDTH * 2), 600, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		pausePanel.add(selectMusicBtn);
		
		//게임종료 버튼
		exitBtn = new JLabel();
		exitBtn.addMouseListener(this);
		exitBtn.setIcon(new ImageIcon("img/Btn/exitBtnIcon_ver2.png"));
		exitBtn.setBounds(WIDTH - RESULTBTN_WIDTH - 20, 650, RESULTBTN_WIDTH, RESULTBTN_HEIGHT);
		pausePanel.add(exitBtn);
		
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
			
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			System.exit(0);
			
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
		
		//메인으로
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuInBtnIcon_ver2.png"));
			
		//다시하기
		} else if (e.getComponent() == reGameBtn) {
			
			reGameBtn.setIcon(new ImageIcon("img/Btn/reGameInBtnIcon.png"));
			
		//음악 선택
		} else if (e.getComponent() == selectMusicBtn) {
			
			selectMusicBtn.setIcon(new ImageIcon("img/Btn/selectMusicInBtnIcon.png"));
				
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(new ImageIcon("img/Btn/exitInBtnIcon_ver2.png"));
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//메인으로
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
			
		//다시하기
		} else if (e.getComponent() == reGameBtn) {
			
			reGameBtn.setIcon(new ImageIcon("img/Btn/reGameBtnIcon.png"));
			
		//음악 선택
		} else if (e.getComponent() == selectMusicBtn) {
			
			selectMusicBtn.setIcon(new ImageIcon("img/Btn/selectMusicBtnIcon.png"));
					
		//게임종료
		} else if (e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(new ImageIcon("img/Btn/exitBtnIcon_ver2.png"));
			
		}
	}
}
