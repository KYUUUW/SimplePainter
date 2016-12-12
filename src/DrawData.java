import java.awt.*;

public class DrawData
{
	private int 	nDrawMode;		//	drawMode : 어떤 버튼이 눌렸고 어떤걸 눌러야 하는지
	private Point	ptOne, ptTwo;	//	첫번째점과 나중 점
	private int		nOption;		//	점의 크기 or 두께
	private Color	colorSelected;	//	색
	private boolean	bFill;			//	채우냐 안채우냐
	
	public DrawData() {					//	디폴트로 아무것도 파라미터로 주지 않았을때
		nDrawMode = DrawConstants.NONE;
		ptOne = new Point();
		ptTwo = new Point();
		nOption = 1;
		colorSelected = Color.black;
		bFill = false;
	} // DrawData()
	public DrawData(	int mode,		//	일일이 다 지정해 주었을때
						Point p1,
						Point p2,
						int option,
						Color c,
						boolean fill) {
		nDrawMode = mode;
		ptOne = p1;
		ptTwo = p2;
		nOption = option;
		colorSelected = c;
		bFill = fill;
	} // DrawData()
	
	public DrawData(DrawData data) {	//	데이터째로 전달해줬을때 (ArrayList 할때 필요하다.)
		nDrawMode 		= data.getDrawMode();
		ptOne 			= data.getPointOne();
		ptTwo 			= data.getPointTwo();
		nOption 		= data.getOption();
		colorSelected 	= data.getColorSelected();
		bFill 			= data.getFill();
	} // DrawData()
	
	//	데이터 클래스이므로 get/set이 중요하다. 기본적인 get/set만 있으므로 따로 설명 안하겠음.
	public int getDrawMode()		{ return nDrawMode; }
	public Point getPointOne()		{ return ptOne; }
	public Point getPointTwo()		{ return ptTwo; }
	public int getOption()			{ return nOption; }
	public Color getColorSelected()	{ return colorSelected; }
	public boolean getFill()		{ return bFill; }
	public int getWidth()			{ return (ptOne.x - ptTwo.x) > 0 ? (ptOne.x - ptTwo.x) : - (ptOne.x - ptTwo.x); }
	public int getHeight()			{ return (ptOne.y - ptTwo.y) > 0 ? (ptOne.y - ptTwo.y) : - (ptOne.y - ptTwo.y); }
	public Point getMiddle()			{ 
		Point pt = new Point();
		pt.setLocation ((ptOne.x + ptTwo.x)/2, (ptOne.y + ptTwo.y)/2);
		return pt;
	}
	
	public void setDrawMode(int mode)	{ nDrawMode = mode; }
	public void setPointOne(Point pt)	{ ptOne = pt; }
	public void setPointTwo(Point pt)	{ ptTwo = pt; }
	public void setOption(int option)	{ nOption = option; }
	public void setColorSelected(Color c)	{ colorSelected = c; }
	public void setFill(boolean fill)	{ bFill = fill; }
	
} // DrawData class