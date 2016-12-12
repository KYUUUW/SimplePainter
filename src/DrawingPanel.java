import java.awt.*;
import java.math.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class DrawingPanel extends JPanel
{
	private PrimaryPanel 		primary;	//	���� ������ ���� ���� Ŭ���� ����
	private DrawData			nowData;	//	Draw�� �׸��� ���� �����͸� ������ �� �ִ� DrawData Ŭ���� ����
	private ArrayList<DrawData> dataList;	//	DrawData���� ������ ArrayList ������ : DataList Ŭ������ ArrayList�� �����Ѵٴ� ��
	
	private DrawListener drawL;				//	������ ����
	
	public DrawingPanel(PrimaryPanel p) {
		setBackground(Color.white);					//	���� ����
		setPreferredSize(new Dimension(600,400));	//	ũ�� ����
		
		primary = p;								//	���� ��ü ������
		
		drawL = new DrawListener();					//	������ ����
		addMouseListener(drawL);					//	�ڽ��� ��ü (DrawPanel)�� �����ʿ� ���ϰ� ��,	���콺 �����ʴ� 5����
		addMouseMotionListener(drawL);				//	���콺��� �����ʵ� ���ϰ� ��. �巡���ϰ�, ����� �ΰ���
		
		nowData = new DrawData();					//	nowData ����
		dataList = new ArrayList<DrawData>();		//	ArrayList�� ����
		
	} // DrawingPanel()
	
	//ArrayList
	public void deleteLastDrawData() {
		int n = dataList.size() - 1;
		dataList.remove(n);
	}
	
	public void setDrawMode(int mode) {				//	�׸��� ��� ����
		nowData.setDrawMode(mode);					//	drawMode ����
		System.out.println("MODE >> " + nowData.getDrawMode());	//	�ֿܼ� ���
	} // setDrawMode()
	
	public void setColorSelected(Color c) {			//	PrimaryPanel -> DrawingPanel -> DrawData�� ���ʷ� ������ ����
		nowData.setColorSelected(c);				//	nowData�� get, set��
	} // setColorSelected()
	public Color getColorSelected() {				//	PrimaryPanel <- DrawingPanel <- DrawData�� ���ʷ� ������ ����
		return nowData.getColorSelected();
	} // getColorSelected()
	
	//	Ư�������� get / set ���� ColorSelected, DrawMode�� �ִµ� option�� width�� ���ݷ� �޾Ƴ�.
	
	public void drawRectangle(	Graphics page,		//	page : Graphics ���� �׸��� ������ (ex: page.fillRect) ���� �������
								Point pt1,			//	�簢���� �׸��µ��� ù���� ������ �ʿ���.
								Point pt2,
								boolean fill) {		//	ä����� ��ä����� �����ִ� �Ķ����
		int x,y,width,height;						//	page.drawRect�� ��� ���̳� �����غ���. �� �޼ҵ�� �簢���� �׸��� �����̴�.
		x = y = width = height = 0;
		
		//	if �� ���� ������ �簢�� �׸��f���� �׻� ù �����Ϳ��� ������ ������ ������ �������� �׸��� �ʰ� ���� ���� ���µ� �پ��� �������� �׸��� ����.
		if (pt1.x < pt2.x && pt1.y < pt2.y) {	// ������ �Ʒ��� �׷�����
			x = pt1.x;
			y = pt1.y;
			width  = pt2.x - pt1.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {	//	������ ��
			x = pt1.x;
			y = pt2.y;
			width  = pt2.x - pt1.x;
			height = pt1.y - pt2.y;
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {	//	���� �Ʒ�
			x = pt2.x;
			y = pt1.y;
			width  = pt1.x - pt2.x;
			height = pt2.y - pt1.y;
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {	//	���� ��
			x = pt2.x;
			y = pt2.y;
			width  = pt1.x - pt2.x;
			height = pt1.y - pt2.y;
		} // if..else if
		
		//	��쿡 ���� x, y, width, height �������Ƿ� ���� �޼ҵ� �̿��ؼ� �׸��� ��.
		if (fill) 	page.fillRect(x, y, width, height);	//	fill �� true ��. fillRect  ���
		else		page.drawRect(x, y, width, height);	//	��ä��� drawRect ��.	
	
	} // drawRectangle()
	
	public void drawMyOval(Graphics page, 				//	Oval�� �׸��� �޼ҵ�μ� �׸��� �޼ҵ带 ���� �������� drawRect �� �� ����.	
							Point pt1, 
							Point pt2, 
							boolean fill) { 
		int x, y, width, height; 
									
		x = y = width = height = 0;

		
		if (pt1.x < pt2.x && pt1.y < pt2.y) { 			//	�� ������ �Լ��� ����� ������ ���Ұ� ������.
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
		super.paintComponent(page);				//	�������̵� �� ��ü�� �����ڸ� �ҷ��´�.
		
		Graphics2D page2 = (Graphics2D)page;	//	setStroke()�� Graphics2D���� ������.
												//	Graphics2D : Graphic�� ��ӹ��� ��ü
												//	�θ� ��ü�� �ڽİ�ü�� ĳ�����ؼ� �ڽİ�ü�� ���� �� �� ����.
												//	call by Referance �̹Ƿ� page = page2 ���� �ּ� ��� �� �� ����. (�̺κ��� �� ��Ƴ�)
		
		// boundary
		page.setColor(Color.gray);							//	�������� boundary �׸� (�׳� constructor���� setBound ���� �ȵų�?)
		page.drawRect(0,0,getWidth()-1,getHeight()-1);		//	ũ�� ���ȼ� �۰� �ؾ� ����. �ȱ׷� ���� ���.
		
		// nowData
		page.setColor(nowData.getColorSelected());			//	�������� ���������� �׸��°�. nowData�� ���� ���õ� �� ����.
		switch(nowData.getDrawMode()) {						//	DrawMode�� ���� �׷��� �ϴ� ���� �ٲ�Ƿ� switch ��.
			case DrawConstants.DOT:
				page.fillOval(	nowData.getPointOne().x		//	���콺 �����Ͱ� ����� �ǵ��� �ϱ� ����.
								-nowData.getOption()/2,		//	(���콺 ������) - (�� ũ�� ��)
								nowData.getPointOne().y		//	y �൵ ����.
								-nowData.getOption()/2,
								nowData.getOption(),
								nowData.getOption());
				break;
			case DrawConstants.LINE:
				page2.setStroke(new BasicStroke(			//	BasicStroke �β��� �����ϱ� ���� �޼ҵ�
								nowData.getOption()));		//	NowData���� ��.
				page.drawLine(	nowData.getPointOne().x,	//	DrawLine�� ù���� ���� ���� �ʿ�.
								nowData.getPointOne().y,
								nowData.getPointTwo().x,
								nowData.getPointTwo().y );
				break;
			case DrawConstants.RECT:						
				page2.setStroke(new BasicStroke(			//	�β� ����
								nowData.getOption()));
				drawRectangle(	page,						//	�ܼ�  drawRect�� ó�� �� �� �����Ƿ� ����� ���� �޼ҵ� �̿��Ѵ�.
								nowData.getPointOne(),		//	ù��, ����, ä��� ���� �Ķ���ͷ� �Ѱ���.
								nowData.getPointTwo(),
								nowData.getFill());
				page.drawString("Area = " + (nowData.getWidth() * nowData.getHeight()) , nowData.getPointOne().x + 5, nowData.getPointOne().y + 15);
				break;
			case DrawConstants.OVAL:
				page2.setStroke(new BasicStroke(			
						nowData.getOption()));
				drawMyOval(	page,						
						nowData.getPointOne(),		
						nowData.getPointTwo(),
						nowData.getFill());
				page.drawString("Area = " + String.format("%.2f",(nowData.getWidth()/2 * nowData.getHeight()/2 * Math.PI)),
								nowData.getPointOne().x + 5, nowData.getPointOne().y + 15);
				break;
		} // switch
		
		
		// savedData
		for (DrawData savedData : dataList) {				//	���� �׷ȴ� ���� ����Ʈ���� ������ �׷��� �Ѵ�.
															//	for( DrawData savedData : dataList)
															//	�̺κ� ���� �ʿ���
			
			page.setColor(savedData.getColorSelected());	//	�� �����ϰ�
			switch(savedData.getDrawMode()) {				//	DrawMode ���ؼ�
				case DrawConstants.DOT:
					page.fillOval(	savedData.getPointOne().x	//	���� ���� ������� �׸���. �Ʒ��� ��� ���� ����.
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
		public void mouseClicked(MouseEvent event) {			//	MouseClicked�� �������� �ʰ� ���콺 Ŭ���Ѱ�. �� ���������� �̿�Ǵ� ���콺 �̺�Ʈ
			if (nowData.getDrawMode() == DrawConstants.DOT) {	//	���� ���� ��ư�� DOT�̶��
				nowData.setPointOne(event.getPoint());			//	ù�������� ��ġ ��ǥ�� �����ϰ� (Point ��ü �̿�, event.getPoint��ü�� ���� ���콺 ��ǥ ������)
				nowData.setOption(primary.getOption());			//	������ü�� Primary���� �ɼ� ������. 
																//	(���� �ȵŴ°� �� ���� ���ݷ� ����? �׳� üũ�ڽ� üũ�ɶ� setOption���� DrawingPanel�� �� �Ѱ��ָ� �ȵų�?)
				
				dataList.add(new DrawData(nowData));			//	���⼭ ���� ���ο� ��ü�� ����� ������ �߿���.
																//	���⼭ �׳� nowData�� �Ѱ��ָ� nowData�� �ּҸ� �Ѱ� �ִ� ���̹Ƿ� data����Ʈ�� ���� ���� �ּҰ� �ְ� �ǰ�,
																//	���ϴ´�� ���� �׷ȴ� ��ϵ��� ������� ����.
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
				repaint();										//	�׻� add �Ŀ� �׸��� �ݿ��ϱ� ���ؼ��� repaint �ؾ��Ѵ�.
			} // if
		} // mouseClicked()
		
		public void mousePressed(MouseEvent event) {			//	MousePressed �� MouseReleased�� �̿��ϴ� ���� �������� ������ �ʿ��� �͵��̴�.
			if (nowData.getDrawMode() == DrawConstants.LINE ||	//	�� LINE, OVAL, RECT ������.	Pressed�� ù���� �����Ѵ�.
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointOne(event.getPoint());			//	MouthEvent �� ��ü�� ���� ���콺�� ��ǥ(Point)�� �����ϴ� getPoint �޼ҵ� ����. ù�� ������
				nowData.setOption(primary.getOption());			//	�ɼ��� ���� ���ݷ� �����.
				nowData.setFill(primary.getFill());				//	Fill�� ������ ����.
			} // if
		} // mousePressed()
		
		public void mouseReleased(MouseEvent event) {			//	���� ������.
			if (nowData.getDrawMode() == DrawConstants.LINE ||
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointTwo(event.getPoint());			//	���� nowData�� ����.
				
				dataList.add(new DrawData(nowData));			//	���콺�� ���̸� �׸��Ⱑ �Ϸ� �Ǿ����Ƿ� dataList�� ����.
				
				repaint();										//	�Ϸ�Ǿ������� �׻� repaint();
			} // if
		} // mouseReleased()
		
		public void mouseEntered(MouseEvent event) {}			//	���콺 ������
		public void mouseExited(MouseEvent event) {}			//	���콺 �������� ��������
		
		public void mouseDragged(MouseEvent event) {			//	���콺�� �巡�� ������ : ������ ����� ���̴� ���� ���콺 �巡�׵� �����.
			if (nowData.getDrawMode() == DrawConstants.LINE ||	
				nowData.getDrawMode() == DrawConstants.RECT ||
				nowData.getDrawMode() == DrawConstants.OVAL) {
				
				nowData.setPointTwo(event.getPoint());			//	�ϴ� nowData�� PointTwo �����ؼ� �ǽð����� �׸� �� �ְ� ��.
				repaint();										//	���� ���� �׷��� �� �� repaint(); -> paintComponent �ٽ� �����ϹǷ� �ű� Ȯ���غ���.
			} // if
		} // mouseDragged()		
		public void mouseMoved(MouseEvent event) {}
		
	} // DrawListener class
	
} // DrawingPanel class