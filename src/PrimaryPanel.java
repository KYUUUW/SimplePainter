import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel
{
	private JPanel 			menuPanel,			//	윗쪽 상단 패널
							buttonMenuPanel, 	//	버튼 메뉴 패널
							optionMenuPanel;	//	옵션 메뉴 패널
	private JButton[]		btnMenuArray;		//	버튼들의 행렬
	private JPanel			subOptionMenuPanel;	//	그 오른쪽 fill 과 color 있는 메뉴
	private JLabel			lblSubOption;		//	LineWidth
	private JTextField		txtSubOption;		//	LineWidth input
	private JCheckBox		chkFill;			//	체크박스
	private JButton			btnColor;			//	색깔 선택 버튼
	
	private DrawingPanel	drawPanel;			//	중간에 그림 그리기 패널
	
	private JPanel			messagePanel;		//	아래 메세지 패널
	private JLabel 			lblMessage; 
	private JLabel			lblMessage2;
	
	private MenuMouseListener	menuMouseL;		//	메뉴에 마우스 올렸을때의 피드백
	private MenuActionListener	menuActionL;	//	버튼 메뉴 패널의 컴포넌트들 리스너
	private OptionActionListener optionActionL;	//	옵션 메뉴 패널의 컴포넌트들 리스너
	
	public PrimaryPanel() {
		setBackground(Color.white);					//	배경색
		setPreferredSize(new Dimension(620,590));	//	크기
		setLayout(null);							//	레이아웃 매니저 off
		
		menuMouseL = new MenuMouseListener();		//	리스너 생성
		menuActionL = new MenuActionListener();
		optionActionL = new OptionActionListener();
		
		menuPanel = new JPanel();	//	메뉴패널 생성
		menuPanel.setBackground(Color.white);
		menuPanel.setBounds(10,10,600,90);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MEUN"));
		menuPanel.setLayout(null);
		add(menuPanel);
		
		buttonMenuPanel = new JPanel();		//	버튼 메뉴 패널 생성
		buttonMenuPanel.setBackground(Color.white);
		buttonMenuPanel.setBounds(10,20,300,60);
		//buttonMenuPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonMenuPanel.setLayout(new GridLayout(2,3));		//	그리드 레이아웃 2행 3열 배치
		menuPanel.add(buttonMenuPanel);
		
		btnMenuArray = new JButton[6];	//	버튼의 행렬 생성 (생성하는법 주목)
		for (int i=0; i<6; i++) {
			btnMenuArray[i] = new JButton(DrawConstants.MENU[i]);
			btnMenuArray[i].setBackground(Color.white);
			btnMenuArray[i].setForeground(Color.darkGray);
			btnMenuArray[i].addMouseListener(menuMouseL);
			btnMenuArray[i].addActionListener(menuActionL);
			buttonMenuPanel.add(btnMenuArray[i]);
		} // for

		
		optionMenuPanel = new JPanel();	//	옵션 메뉴 패널 생성
		optionMenuPanel.setBackground(Color.white);
		optionMenuPanel.setBounds(320,20,270,60);
		optionMenuPanel.setBorder(BorderFactory.createEtchedBorder());
		optionMenuPanel.setLayout(null);
		menuPanel.add(optionMenuPanel);
		
		subOptionMenuPanel = new JPanel();	//	옵션 메뉴 패널 상단의 두께와 color
		subOptionMenuPanel.setBackground(Color.white);
		subOptionMenuPanel.setBounds(5,5,260,30);
		subOptionMenuPanel.setVisible(false);
		optionMenuPanel.add(subOptionMenuPanel);
		
		lblSubOption = new JLabel("Point Size : ");	//	Point Size 글씨
		subOptionMenuPanel.add(lblSubOption);
		
		txtSubOption = new JTextField(5);		//	Point의 크기 지정
		txtSubOption.setText("2");
		subOptionMenuPanel.add(txtSubOption);	//	리스너를 하지 않는 이유는 어차피 그려질때 txtSubOption의 값을 getText 할 것이기 때문
		
		btnColor = new JButton("COLOR");			//	Color 버튼
		btnColor.setFont(new Font("Arial",Font.BOLD,10));
		btnColor.addActionListener(optionActionL);	//	컬러는 옵션이므로 옵션 액션 리스너
		subOptionMenuPanel.add(btnColor);
		
		chkFill = new JCheckBox("FILL");	//	체크박스
		chkFill.setBounds(5,32,260,25);		//	이또한 굳이 리스너 add 할 필요 없음
		chkFill.setBackground(Color.white);
		chkFill.setHorizontalAlignment(SwingConstants.CENTER);
		chkFill.setVisible(false);
		optionMenuPanel.add(chkFill);
		
		drawPanel = new DrawingPanel(this);	//	파라미터로 자신을 주는건 업콜
		drawPanel.setBounds(10,110,600,400);
		add(drawPanel);
		
		messagePanel = new JPanel();		//	하단 메세지 패널
		messagePanel.setBackground(Color.white);
		messagePanel.setBounds(10,520,600,60);
		messagePanel.setLayout(null);
		messagePanel.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.red));
		add(messagePanel);
		
		lblMessage = new JLabel();
		lblMessage.setBounds(5,10,500,20);
		messagePanel.add(lblMessage);
		
		lblMessage2 = new JLabel();
		lblMessage2.setBounds(5, 25, 500, 20);
		messagePanel.add(lblMessage2);
		
	} // PrimaryPanel()
	
	public int getOption() {								//	포인트 크기 옵션 받는 메소드
		int option;		
		option = Integer.parseInt(txtSubOption.getText());	//	getText 메소드 이용해서 옵션 값 받음. 유효성 검사?
		return option;		
	} // getSubOption()
	
	public boolean getFill() {			//	Fill이 check 됐는지 받음
		return chkFill.isSelected();
	} // getFill()
	
	public void printMessage(Point pt1, Point pt2, int mode) {
		int length = 0, area = 0;
		int width = (pt1.x - pt2.x) > 0 ? (pt1.x - pt2.x) : -(pt1.x - pt2.x);
		int height = (pt1.y - pt2.y) > 0 ? (pt1.y - pt2.y) : -(pt1.y - pt2.y);

		if (mode == DrawConstants.LINE) {
			length = (int)Point.distance(pt1.x, pt1.y,pt2.x, pt2.y);
			//messagePanel.repaint();
		} else if (mode == DrawConstants.RECT) {
			length = width*2 + height*2;
			area = width*height;
		} else if (mode == DrawConstants.OVAL) {
			length = (int)((width + height)/2 * Math.PI) ;
			area = (int)(width*height/4*Math.PI);
		}
		lblMessage.setText("length = " + length +", area = " + area);
		messagePanel.repaint();
	}
	
	public void printMessage2 (Point pt) {
		lblMessage2.setText("Mouse Point = " + pt.x + " ," + pt.y);
		messagePanel.repaint();
	}
	
	private class MenuMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {	//	마우스가 컴포넌트에 올려져있을때
			JButton obj = (JButton)event.getSource();
			obj.setBackground(Color.cyan);
			obj.setForeground(Color.red);			
		} // mouseEntered()
		public void mouseExited(MouseEvent event) {		//	마우스가 컴포넌트 밖으로 나갔을때
			JButton obj = (JButton)event.getSource();
			obj.setBackground(Color.white);
			obj.setForeground(Color.darkGray);
		} // mouseExited()
	} // MenuMouseListener class
	
	private class MenuActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			int i;
			Object obj = event.getSource();			//	어떤 객체에서 이벤트가 일어났는지 알아내고, 그 객체를 obj에 대입
			
			for (i=0; i<6; i++) {					//	obj는 어떤 버튼인지 알아내고, i 에 대입
				if (obj == btnMenuArray[i]) break;
			} // for
			
			subOptionMenuPanel.setVisible(true);	//	어땟든 어떤 버튼이 눌리던간에 SubOptionMenuPanel이 활성화 된다.
			chkFill.setVisible(false);				//	Fill에 관한 부분은 기본적으로는 안보이게 함
			drawPanel.setDrawMode(i);				//	어떤 버튼이 눌렸는지 drawPanel에 보내줌. (참고로 drawPanel에서는 저 메소드로 nowData에 값 전달해줌)
			switch(i) {
				case DrawConstants.DOT:						//	case 가 배열된 순서에 주목할 것.
					//subOptionMenuPanel.setVisible(true);
					lblSubOption.setText("Point Size : ");	//	Dot 일때는 Point Size고
					//chkFill.setVisible(false);
					break;
				case DrawConstants.RECT:
				case DrawConstants.OVAL:
					//subOptionMenuPanel.setVisible(true);
					//lblSubOption.setText("Line Width : ");
					chkFill.setVisible(true);				//	Rect와 Oval일때만 Fill을 보이게 함.
					//break;
				case DrawConstants.LINE:
					//subOptionMenuPanel.setVisible(true);
					lblSubOption.setText("Line Width : ");	//	Line, Rect, Oval 은 Line Width가 나와야한다.
					//chkFill.setVisible(false);
					break;
				case DrawConstants.UNDO:
					drawPanel.deleteLastDrawData();
					drawPanel.repaint();
					break;
				default:
					subOptionMenuPanel.setVisible(false);	//	위에 해당되지 않으면, subOptionMenuPanel은 안보이게 함.
					//chkFill.setVisible(false);
					break;
			} // siwtch
			
		} // actionPerformed()
	} // MenuActionListener class
	
	private class OptionActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			Color c;										//	JcolorChooser.showDialog() 가 color 객체를 리턴하므로
			
			if (obj == btnColor) {	//	Color 버튼이 눌리면,
				c = JColorChooser.showDialog(	drawPanel,	//	JcolorChooser.showDialog() 로 색 선택하게 함. 첫번째 파라미터는 이 다이얼로그를 불러오는 컴포넌트인데, null도 상관 없다.
							"Pick a Color!!",				//	타이틀바의 String
							drawPanel.getColorSelected());	//	drawPanel에서 선택된 색을 알려주는 사용자 정의 메소드
				drawPanel.setColorSelected(c);				//	set 메소드
			} // if			
			
		} // actionPerformed()
	} // OptionActionListener class
	
} // PrimaryPanel class