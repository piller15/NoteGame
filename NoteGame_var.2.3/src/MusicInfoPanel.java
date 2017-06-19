import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MusicInfoPanel implements WidthAndHeight, MouseListener{

	JLabel reMenuBtn;
	
	public MusicInfoPanel() {
		
		JPanel mInfoPanel = new JPanel(){ImageIcon i = new ImageIcon("img/BG/MusicInfoPanelBG.png");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(), 0, 0, i.getIconWidth(), i.getIconHeight(), null);
			}
		};
		mInfoPanel.setLayout(null);
		mInfoPanel.setSize(WIDTH, HEIGHT);
		NoteGame.mainPanel.add("minfo", mInfoPanel);
		
		//바람에게 부탁해 이미지 출력
		JLabel Ask_to_Wind_infoImg = new JLabel();
		Ask_to_Wind_infoImg.setIcon(new ImageIcon("img/Music/Ask_to_Wind_TrilogyESStyle.png"));
		Ask_to_Wind_infoImg.setBounds(25, 15, 200, 150);
		mInfoPanel.add(Ask_to_Wind_infoImg);
		
		//바람에게 부탁해 정보 출력
		JLabel Ask_to_Wind_info = new JLabel();
		Ask_to_Wind_info.setBounds(25, 170, 450, 160);
		Ask_to_Wind_info.setForeground(Color.WHITE);
		Ask_to_Wind_info.setFont(new Font(null, 0, 15));
		Ask_to_Wind_info.setText(
				"<html>바람에게 부탁해<br>"
				+ "DJMAX에서 Forte Escape하면 가장 먼저 떠오르는 곡으로,"
				+ " 기존 스타일과는 판이하게 다른 서정성을 중시한 분위기의 곡이다."
				+ " 멜로디, 가사, 보컬, BGA, 난이도 모두 적절하게 조화를 이룬"
				+ " 초심자용 곡으로서 많은 사랑을 받아왔다."
				+ " 인기곡 차트 1위에서 내려온 적이 거의 없을 정도."
				+ " DJMAX 온라인의 대표곡이었다고 해도 과언은 아니다."
				+ " 우려먹을 만도 하다. Forte Escape 본인도 이렇게까지 인기가 좋을 줄 몰랐다고."
				+ " 또한 Miya의 유명세의 도화선이 되기도 했다."
				+ "</html>");
		mInfoPanel.add(Ask_to_Wind_info);
		
		//바람의 기억 이미지 출력
		JLabel Memory_of_Windimg = new JLabel();
		Memory_of_Windimg.setIcon(new ImageIcon("img/Music/Memory_of_Wind_TrilogyESStyle.png"));
		Memory_of_Windimg.setBounds(25, 340, 200, 150);
		mInfoPanel.add(Memory_of_Windimg);
		
		//바람의 기억 정보 출력
		JLabel Memory_of_Wind = new JLabel();
		Memory_of_Wind.setBounds(25, 490, 450, 170);
		Memory_of_Wind.setForeground(Color.WHITE);
		Memory_of_Wind.setFont(new Font(null, 0, 15));
		Memory_of_Wind.setText(
				"<html>바람에게 부탁해<br>"
				+ "바람 시리즈의 두번째 곡으로,"
				+ " 바람에게 부탁해에서 바람에게 부탁해 Interude를 통해 연결되는 식으로 이어져 있다."
				+ " 애초에는 시리즈로 만들 생각은 아니었는데 곡을 먼저 제작해 놓고 제목과 가사를 입혀보니 딱 맞아 떨어졌다고 한다."
				+ " Forte Escape로서는 DJMAX 시리즈의 부활이라는 느낌으로 대표곡으로 밀려고 했었지만"
				+ " Someday의 인기에 눌려 작곡자로서는 아쉽지만 프로듀서로서 버려야 했다는 후문이 있다."
				+ "</html>");
		mInfoPanel.add(Memory_of_Wind);
		
		//메인으로 버튼 생성
		reMenuBtn = new JLabel();
		reMenuBtn.setIcon(new ImageIcon("img/Btn/reMenuBtnIcon_ver2.png"));
		reMenuBtn.addMouseListener(this);
		reMenuBtn.setBounds(INFOBTN_X, INFOBTN_Y, INFOBTN_WIDTH, INFOBTN_HEIGHT);
		mInfoPanel.add(reMenuBtn);
		
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
