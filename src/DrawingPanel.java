import java.awt.*;
import java.math.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class DrawingPanel extends JPanel
{
	private PrimaryPanel 		primary;	//	업콜 구조를 위해 상위 클래스 선언
	private DrawData			nowData;	//	Draw된 그림에 대한 데이터를 저장할 수 있는 DrawData 클래스 선언
	private ArrayList<DrawData> dataList;	//	DrawData들을 저장할 ArrayList 선언함 : DataList 클래스를 ArrayList로 선언한다는 뜻
	
	private DrawListener drawL;				//	리스너 선언
	
	public DrawingPanel(PrimaryPanel p) {
		setBackground(Color.white);					//	배경색 설정
		setPreferredSize(new Dimension(600,400));	//	크기 설정
		
		primary = p;								//	상위 객체 가져옴
		
		drawL = new DrawListener();					//	리스너 생성
		addMouseListener(drawL);					//	자신의 객체 (DrawPanel)을 리스너에 속하게 함,	마우스 리스너는 5가지
		addMouseMotionListener(drawL);				//	마우스모션 리스너도 속하게 함. 드래그하고, 무브드 두가지
		
		nowData = new DrawData();					//	nowData 생성
		dataList = new ArrayList<DrawData>();		//	ArrayList도 생성
		
	} // DrawingPanel()
	
	//ArrayList
	public void deleteLastDrawData() {
		int n = dataList.size() - 1;
		dataList.remove(n);
	}
	
	public void setDrawMode(int mode) {				//	그리기 모드 설정
		nowData.setDrawMode(mode);					//	drawMode 지정
		System.out.println("MODE >> " + nowData.getDrawMode());	//	콘솔에 출력
	} // setDrawMode()
	
	public void setColorSelected(Color c) {			//	PrimaryPanel -> DrawingPanel -> DrawData로 차례로 데이터 전달
		nowData.setColorSelected(c);				//	nowData의 get, set임
	} // setColorSelected()
	public Color getColorSelected() {				//	PrimaryPanel <- DrawingPanel <- DrawData로 차례로 데이터 전달
		return nowData.getColorSelected();
	} // getColorSelected()
	
	//	특이한점은 get / set 에서 ColorSelected, DrawMode는 있는데 option과 width는 업콜로 받아냄.
	
	public void drawRectangle(	Graphics page,		//	page : Graphics 에는 그리기 도구들 (ex: page.fillRect) 등이 담겨있음
								Point pt1,			//	사각형을 그리는데는 첫점과 끝점이 필요함.
								Point pt2,
								boolean fill) {		//	채우는지 안채우는지 정해주는 파라미터
		int x,y,width,height;						//	page.drawRect에 어떤게 쓰이나 생각해보자. 이 메소드는 사각형을 그리기 위함이다.
		x = y = width = height = 0;
		
		//	if 문 쓰는 이유는 사각형 그리늗것이 항상 첫 포인터에서 오를쪽 밑으로 내리는 방향으로 그리지 않고 왼쪽 위로 가는등 다양한 방향으로 그리기 때문.
		if (pt1.x < pt2.x && pt1.y < pt2.y) {	// 오른쪽 아래로 그렸을때
			x = pt1.x;
			y = pt1.y;
			width  = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {	//	오른쪽 위
			x = pt1.x;
			y = pt2.y;
			width  = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {	//	왼쪽 아래
			x = pt2.x;
			y = pt1.y;
			width  = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {	//	왼쪽 위
			x = pt2.x;
			y = pt2.y;
			width  = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if..else if
		
		//	경우에 따라서 x, y, width, height 정했으므로 이제 메소드 이용해서 그리면 됨.
		if (fill) 	page.fillRect(x, y, width, height);	//	fill 이 true 라서. fillRect  사용
		else		page.drawRect(x, y, width, height);	//	안채우면 drawRect 씀.	
	
	} // drawRectangle()
	
	public void drawMyOval(Graphics page, 				//	Oval을 그리는 메소드로서 그리는 메소드를 빼고 나머지는 drawRect 와 다 같음.	
							Point pt1, 
							Point pt2, 
							boolean fill) { 
		int x, y, width, height; 
									
		x = y = width = height = 0;

		
		if (pt1.x < pt2.x && pt1.y < pt2.y) { 			//	이 과정을 함수로 만들어 버려도 편할거 같은데.
			x = pt1.x;
			y = pt1.y;
			width = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) { 
			x = pt1.x;
			y = pt2.y;
			width = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) { 
			x = pt2.x;
			y = pt1.y;
			width = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) { 
			x = pt2.x;
			y = pt2.y;
			width = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if..else if

		
		if (fill)
			page.fillOval(x, y, width, height); 
		else
			page.drawOval(x, y, width, height); 

	} // drawMyOval()
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);				//	오버라이딩 된 객체의 생성자를 불러온다.
		
		Graphics2D page2 = (Graphics2D)page;	//	setStroke()는 Graphics2D에서 제공함.
												//	Graphics2D : Graphic를 상속받은 객체
												//	부모 객체는 자식객체로 캐스팅해서 자식객체에 대입 할 수 있음.
												//	call by Referance 이므로 page = page2 같은 주소 라고 볼 수 있음. (이부분이 좀 어렵네)
		
		// boundary
		page.setColor(Color.gray);							//	수동으로 boundary 그림 (그냥 constructor에서 setBound 쓰면 안돼나?)
		page.drawRect(0,0,getWidth()-1,getHeight()-1);		//	크기 한픽셀 작게 해야 보임. 안그럼 범위 벗어남.
		
		// nowData
		page.setColor(nowData.getColorSelected());			//	이제부터 본격적으로 그리는것. nowData로 부터 선택된 색 받음.
		switch(nowData.getDrawMode()) {						//	DrawMode에 따라 그려야 하는 것이 바뀌므로 switch 문.
			case DrawConstants.DOT:
				page.fillOval(	nowData.getPointOne().x		//	마우스 포인터가 가운데가 되도록 하기 위함.
								-nowData.getOption()/2,		//	(마우스 포인터) - (점 크기 반)
								nowData.getPointOne().y		//	y 축도 같음.
								-nowData.getOption()/2,
								nowData.getOption(),
								nowData.getOption());
				break;
			case DrawConstants.LINE:
				page2.setStroke(new BasicStroke(			//	BasicStroke 두께를 설정하기 위한 메소드
								nowData.getOption()));		//	NowData에서 얻어감.
				page.drawLine(	nowData.getPointOne().x,	//	DrawLine은 첫점과 끝점 정보 필요.
								nowData.getPointOne().y,
								nowData.getPointTwo().x,
								nowData.getPointTwo().y );
				break;
			case DrawConstants.RECT:						
				page2.setStroke(new BasicStroke(			//	두께 조절
								nowData.getOption()));
				drawRectangle(	page,						//	단순  drawRect로 처리 할 수 없으므로 사용자 정의 메소드 이용한다.
								nowData.getPointOne(),		//	첫점, 끝점, 채우기 유무 파라미터로 넘겨줌.
								nowData.getPointTwo(),
								nowData.getFill());
				break;
			case DrawConstants.OVAL:
				page2.setStroke(new BasicStroke(			
						nowData.getOption()));
				drawMyOval(	page,						
						nowData.getPointOne(),		
						nowData.getPointTwo(),
						nowData.getFill());
				break;
		} // switch
		
		
		// savedData
		for (DrawData savedData : dataList) {				//	전에 그렸던 것을 리스트에서 꺼내서 그려야 한다.
															//	for( DrawData savedData : dataList)
															//	이부분 공부 필요함
			
			page.setColor(savedData.getColorSelected());	//	색 세팅하고
			switch(savedData.getDrawMode()) {				//	DrawMode 비교해서
				case DrawConstants.DOT:
					page.fillOval(	savedData.getPointOne().x	//	위와 같은 방법으로 그린다. 아래도 모두 위와 같음.
								-savedData.getOption()/2,
								savedData.getPointOne().y
								-savedData.getOption()/2,
								savedData.getOption(),
								savedData.getOption());
					break;
				case DrawConstants.LINE:
					page2.setStroke(new BasicStroke(
								savedData.getOption()));
					page.drawLine(savedData.getPointOne().x,
								savedData.getPointOne().y,
								savedData.getPointTwo().x,
								savedData.getPointTwo().y );
					break;
				case DrawConstants.RECT:
					page2.setStroke(new BasicStroke(
								savedData.getOption()));
					drawRectangle(	page,
								savedData.getPointOne(),
								savedData.getPointTwo(),
								savedData.getFill());
					break;
				case DrawConstants.OVAL:
					page2.setStroke(new BasicStroke(			
							savedData.getOption()));
					drawMyOval(	page,						
							savedData.getPointOne(),		
							savedData.getPointTwo(),
							savedData.getFill());
					break;
			} // switch
		} // for		
		
	} // paintComponent()
	
	private class DrawListener implements 	MouseListener,
											MouseMotionListener
	{
		public void mouseClicked(MouseEvent event) {			//	MouseClicked는 움직이지 않고 마우스 클릭한것. 즉 점찍을때만 이용되는 마우스 이벤트
			if (nowData.getDrawMode() == DrawConstants.DOT) {	//	지금 눌린 버튼이 DOT이라면
				nowData.setPointOne(event.getPoint());			//	첫번재점의 위치 좌표를 설정하고 (Point 객체 이용, event.getPoint객체가 현재 마우스 좌표 리턴함)
				nowData.setOption(primary.getOption());			//	상위객체인 Primary에서 옵션 가져옴. 
																//	(이해 안돼는건 왜 굳이 업콜로 하지? 그냥 체크박스 체크될때 setOption만들어서 DrawingPanel에 값 넘겨주면 안돼나?)
				
				dataList.add(new DrawData(nowData));			//	여기서 굳이 새로운 객체를 만드는 이유가 중요함.
																//	여기서 그냥 nowData를 넘겨주면 nowData의 주소를 넘겨 주는 것이므로 data리스트에 전부 같은 주소가 있게 되고,
																//	원하는대로 전에 그렸던 기록들이 저장되지 않음.
				/*
				dataList.add(new DrawData(
					nowData.getDrawMode(),
					nowData.getPointOne(),
					nowData.getPointTwo(),
					nowData.getOption(),
					nowData.getColorSelected(),
					nowData.getFill()
					));
				*/
				repaint();										//	항상 add 후에 그림을 반영하기 위해서는 repaint 해야한다.
			} // if
		} // mouseClicked()
		
		public void mousePressed(MouseEvent event) {			//	MousePressed 와 MouseReleased를 이용하는 경우는 시작점과 끝점이 필요한 것들이다.
			if (nowData.getDrawMode() == DrawConstants.LINE ||	//	즉 LINE, OVAL, RECT 세가지.	Pressed는 첫점을 세팅한다.
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointOne(event.getPoint());			//	MouthEvent 의 객체는 현재 마우스의 좌표(Point)를 리턴하는 getPoint 메소드 있음. 첫점 세팅함
				nowData.setOption(primary.getOption());			//	옵션은 굳이 업콜로 사용함.
				nowData.setFill(primary.getFill());				//	Fill도 마찬로 업콜.
			} // if
		} // mousePressed()
		
		public void mouseReleased(MouseEvent event) {			//	끝점 세팅함.
			if (nowData.getDrawMode() == DrawConstants.LINE ||
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointTwo(event.getPoint());			//	끝점 nowData로 보냄.
				
				dataList.add(new DrawData(nowData));			//	마우스가 놓이면 그리기가 완료 되었으므로 dataList로 보냄.
				
				repaint();										//	완료되었을때는 항상 repaint();
			} // if
		} // mouseReleased()
		
		public void mouseEntered(MouseEvent event) {}			//	마우스 들어갔을때
		public void mouseExited(MouseEvent event) {}			//	마우스 구역에서 나갔을때
		
		public void mouseDragged(MouseEvent event) {			//	마우스가 드래그 됐을때 : 프레스 릴리즈가 쓰이는 것은 마우스 드래그도 써야함.
			if (nowData.getDrawMode() == DrawConstants.LINE ||	
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointTwo(event.getPoint());			//	일단 nowData에 PointTwo 저장해서 실시간으로 그릴 수 있게 함.
				repaint();										//	역시 뭔가 그려야 할 땐 repaint(); -> paintComponent 다시 실행하므로 거기 확인해보자.
				
				primary.printMessage(nowData.getPointOne(), nowData.getPointTwo(), nowData.getDrawMode());
			} // if
		} // mouseDragged()		
		public void mouseMoved(MouseEvent event) {}
		
	} // DrawListener class
	
} // DrawingPanel class