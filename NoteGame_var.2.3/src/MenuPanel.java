import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel implements WidthAndHeight, MouseListener {

	//패널 버튼
	public JLabel startBtn;
	public JLabel infoBtn;
	public JLabel mInfoBtn;
	public JLabel exitBtn;
	
	//기본이미지
	public ImageIcon startBtnIcon 	= new ImageIcon("img/Btn/StartGameBtnIcon.png");
	public ImageIcon infoBtnIcon 	= new ImageIcon("img/Btn/InfoBtnIcon.png");
	public ImageIcon mInfoBtnIcon 	= new ImageIcon("img/Btn/MusicInfoBtnIcon.png");
	public ImageIcon exitBtnIcon 	= new ImageIcon("img/Btn/ExitBtnIcon.png");
	public ImageIcon startInBtnIcon = new ImageIcon("img/Btn/StartGameInBtnIcon.png");
	public ImageIcon infoInBtnIcon 	= new ImageIcon("img/Btn/InfoInBtnIcon.png");
	public ImageIcon mInfoInBtnIcon = new ImageIcon("img/Btn/MusicInfoInBtnIcon.png");
	public ImageIcon exitInBtnIcon 	= new ImageIcon("img/Btn/ExitInBtnIcon.png");
	
	//메뉴 구성
	public MenuPanel() {
		
		//메뉴 패널 설정
		JPanel menuPanel = new JPanel(){ImageIcon i = new ImageIcon("img/BG/MenuPanelBG.png");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
			}
		};
		menuPanel.setLayout(null);
		menuPanel.setSize(WIDTH, HEIGHT);
		NoteGame.mainPanel.add("menu", menuPanel);
		
		//게임시작 버튼 설정
		startBtn = new JLabel();
		startBtn.setBounds(MENUBTN_X, MENUBTN_Y, MENUBTN_WIDTH, MENUBTN_HEIGHT);
		startBtn.addMouseListener(this);
		startBtn.setIcon(startBtnIcon);
		menuPanel.add(startBtn);
		
		//게임정보 버튼 설정
		infoBtn = new JLabel();
		infoBtn.setBounds(MENUBTN_X, MENUBTN_Y + ((MENUBTN_HEIGHT+10)*1) , MENUBTN_WIDTH, MENUBTN_HEIGHT);
		infoBtn.addMouseListener(this);
		infoBtn.setIcon(infoBtnIcon);
		menuPanel.add(infoBtn);
		
		//음악정보 버튼 설졍
		mInfoBtn = new JLabel();
		mInfoBtn.setBounds(MENUBTN_X, MENUBTN_Y+ ((MENUBTN_HEIGHT+10)*2), MENUBTN_WIDTH, MENUBTN_HEIGHT);
		mInfoBtn.addMouseListener(this);
		mInfoBtn.setIcon(mInfoBtnIcon);
		menuPanel.add(mInfoBtn);
		
		//게임종료 버튼 설정
		exitBtn = new JLabel();
		exitBtn.setBounds(MENUBTN_X, MENUBTN_Y+ ((MENUBTN_HEIGHT+10)*3), MENUBTN_WIDTH, MENUBTN_HEIGHT);
		exitBtn.addMouseListener(this);
		exitBtn.setIcon(exitBtnIcon);
		menuPanel.add(exitBtn);
		
		
		
	}

	//버튼 클릭시 동작들
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == startBtn) {
		
			NoteGame.card.show(NoteGame.mainPanel, "mselect");
			
		} else if(e.getComponent() == infoBtn) {
			
			NoteGame.card.show(NoteGame.mainPanel, "info");
			
		} else if(e.getComponent() == mInfoBtn) {
		
			NoteGame.card.show(NoteGame.mainPanel, "minfo");
			
		} else if(e.getComponent() == exitBtn) {
			
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
		
		if(e.getComponent() == startBtn) {
			
			startBtn.setIcon(startInBtnIcon);
			
		} else if(e.getComponent() == infoBtn) {
			
			infoBtn.setIcon(infoInBtnIcon);
			
		} else if(e.getComponent() == mInfoBtn) {
		
			mInfoBtn.setIcon(mInfoInBtnIcon);
			
		} else if(e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(exitInBtnIcon);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == startBtn) {
			
			startBtn.setIcon(startBtnIcon);
			
		} else if(e.getComponent() == infoBtn) {
			
			infoBtn.setIcon(infoBtnIcon);
			
		} else if(e.getComponent() == mInfoBtn) {
		
			mInfoBtn.setIcon(mInfoBtnIcon);
			
		} else if(e.getComponent() == exitBtn) {
			
			exitBtn.setIcon(exitBtnIcon);
		}
	}
}
