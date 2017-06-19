import javax.swing.ImageIcon;

public class Effect implements Runnable {

	int line;
	ImageIcon[] effectIcon = new ImageIcon[5];
	
	public Effect(int line) {
	
		effectIcon[0] = new ImageIcon("img/Effect/effect_1.png");
		effectIcon[1] = new ImageIcon("img/Effect/effect_2.png");
		effectIcon[2] = new ImageIcon("img/Effect/effect_3.png");
		effectIcon[3] = new ImageIcon("img/Effect/effect_4.png");
		effectIcon[4] = new ImageIcon("img/Effect/effect_5.png");
		this.line = line;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 5; i++) {
			
			GamePanel.decision[line].setIcon(effectIcon[i]);
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GamePanel.decision[line].setIcon(null);
		}
		
	}
	
}
