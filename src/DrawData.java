import java.awt.*;

public class DrawData
{
	private int 	nDrawMode;
	private Point	ptOne, ptTwo;
	private int		nOption;
	private Color	colorSelected;
	private boolean	bFill;
	
	public DrawData() {
		nDrawMode = DrawConstants.NONE;
		ptOne = new Point();
		ptTwo = new Point();
		nOption = 1;
		colorSelected = Color.black;
		bFill = false;
	} // DrawData()
	public DrawData(	int mode,
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
	
	public DrawData(DrawData data) {
		nDrawMode 		= data.getDrawMode();
		ptOne 			= data.getPointOne();
		ptTwo 			= data.getPointTwo();
		nOption 		= data.getOption();
		colorSelected 	= data.getColorSelected();
		bFill 			= data.getFill();
	} // DrawData()
	
	public int getDrawMode()		{ return nDrawMode; }
	public Point getPointOne()		{ return ptOne; }
	public Point getPointTwo()		{ return ptTwo; }
	public int getOption()			{ return nOption; }
	public Color getColorSelected()	{ return colorSelected; }
	public boolean getFill()		{ return bFill; }
	
	public void setDrawMode(int mode)	{ nDrawMode = mode; }
	public void setPointOne(Point pt)	{ ptOne = pt; }
	public void setPointTwo(Point pt)	{ ptTwo = pt; }
	public void setOption(int option)	{ nOption = option; }
	public void setColorSelected(Color c)	{ colorSelected = c; }
	public void setFill(boolean fill)	{ bFill = fill; }
	
} // DrawData class