
public interface WidthAndHeight {

	//초기 창크기
	static final int WIDTH 	= 500;
	static final int HEIGHT = 700;
	
	//메뉴버튼 위치
	static final int MENUBTN_WIDTH 	= 180;
	static final int MENUBTN_HEIGHT = 30;
	static final int MENUBTN_X 		= (WIDTH - MENUBTN_WIDTH)/2;
	static final int MENUBTN_Y 		= 450;
	
	//정보 창 버튼 위치
	static final int INFOBTN_WIDTH 	= 120;
	static final int INFOBTN_HEIGHT = 20;
	static final int INFOBTN_X 		= WIDTH - INFOBTN_WIDTH - 30;
	static final int INFOBTN_Y 		= HEIGHT - INFOBTN_HEIGHT - 30;
	
	//음악선택 패널 위치 정보
	static final int MSELECTBTN_WIDTH 		= 100;
	static final int MSELECTBTN_HEIGHT 		= 30;
	static final int MSELECT_START_BTN_X 	= WIDTH - INFOBTN_WIDTH - 30;
	static final int MSELECT_START_BTN_Y 	= HEIGHT - INFOBTN_HEIGHT - 30;
	static final int MSELECT_RE_BTN_X 		= 10;
	static final int MSELECT_RE_BTN_Y 		= HEIGHT - INFOBTN_HEIGHT - 30;
	
	//노트 윈도우 위치정보
	static final int NOTE_WIDTH 		= 50;
	static final int NOTE_HEIGHT 		= 10;
	static final int NOTEWIN_WIDTH 		= 300;
	static final int NOTEWIN_HEIGHT 	= HEIGHT;
	static final int NOTEWIN_X 			= (WIDTH - NOTEWIN_WIDTH)/2;
	static final int NOTEWIN_Y 			= 0;
	
	//체력 위치 정보
	static final int HP_WIDTH 	= 17;
	static final int HP_HEIGHT 	= 521;
	static final int HP_X 		= NOTEWIN_X + NOTEWIN_WIDTH + 6;
	static final int HP_Y 		= HEIGHT - HP_HEIGHT;
	
	//판정바 위치정보
	static final int DECISION_BAR_X 		= 0;
	static final int DECISION_BAR_Y 		= HEIGHT - 130;
	static final int DECISION_BAR_WIDTH 	= NOTEWIN_WIDTH;
	static final int DECISION_BAR_HEIGHT 	= 20;
	
	//노트 라인 정보
	static final int LINE_X 		= 0;
	static final int LINE_Y 		= 0;
	static final int LINE_WIDTH 	= NOTEWIN_WIDTH/6;
	static final int LINE_HEIGHT 	= NOTEWIN_HEIGHT;
	
	//콤보 위치 정보
	static final int COMBO_WIDTH 	= 180;
	static final int COMBO_HEIGHT 	= 30;
	static final int COMBO_X 		= (NOTEWIN_WIDTH - COMBO_WIDTH)/2;
	static final int COMBO_Y 		= 100;
	
	//판정 결과 위치정보
	static final int DECISION_TEXT_WIDTH 	= 180;
	static final int DECISION_TEXT_HEIGHT 	= 40;
	static final int DECISION_TEXT_X 		= (NOTEWIN_WIDTH - DECISION_TEXT_WIDTH)/2;
	static final int DECISION_TEXT_Y 		= DECISION_BAR_Y - DECISION_TEXT_HEIGHT - 60;
	
	//이펙트 위치정보
	static final int DECISION_WIDTH 	= 50;
	static final int DECISION_HEIGHT 	= 50;
	static final int DECISION_X 		= 0;
	static final int DECISION_Y 		= DECISION_BAR_Y - DECISION_BAR_HEIGHT/2 - 10;
	
	//결과화면 버튼 정보
	static final int RESULTBTN_WIDTH 	= 120;
	static final int RESULTBTN_HEIGHT 	= 20;
	
	//결과 이미지 정보
	static final int RESULTIMG_WIDTH 	= 100;
	static final int RESULTIMG_HEIGHT 	= 30;
	
	//결과 숫자 크기
	static final int RESULTNUM_WIDTH 	= 100;
	static final int RESULTNUM_HEIGHT 	= RESULTIMG_HEIGHT;
	
	//랭킹 차트 좌표
	static final int RANKCHART_X = 50;
	static final int RANKCHART_Y = 350;
	
	//버튼 코드 정보
	static final int ESC 	= 27;
	static final int SPACE 	= 32;
	
}
