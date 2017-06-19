import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel implements WidthAndHeight, MouseListener{

	JLabel reMenuBtn;
	
	public InfoPanel() {
		
		JPanel infoPanel = new JPanel(){ImageIcon i = new ImageIcon("img/BG/GameInfoPanelBG.png");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
			}
		};
		infoPanel.setLayout(null);
		infoPanel.setSize(WIDTH, HEIGHT);
		
		JLabel infoImg = new JLabel();
		infoImg.setIcon(new ImageIcon("img/BG/infoimg.png"));
		infoImg.setBounds(50, 100, 400, 500);
		infoPanel.add(infoImg);
		
		//메뉴로 돌아가기 버튼 생성
		reMenuBtn = new JLabel();
		reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
		reMenuBtn.addMouseListener(this);
		reMenuBtn.setBounds(INFOBTN_X, INFOBTN_Y, INFOBTN_WIDTH, INFOBTN_HEIGHT);
		infoPanel.add(reMenuBtn);
		

		NoteGame.mainPanel.add("info", infoPanel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() == reMenuBtn) {
			
			NoteGame.card.show(NoteGame.mainPanel, "menu");
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
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getComponent() == reMenuBtn) {
			
			reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
		}
	}
}
