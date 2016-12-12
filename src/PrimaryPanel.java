import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel
{
	private JPanel 			menuPanel,			//	���� ��� �г�
							buttonMenuPanel, 	//	��ư �޴� �г�
							optionMenuPanel;	//	�ɼ� �޴� �г�
	private JButton[]		btnMenuArray;		//	��ư���� ���
	private JPanel			subOptionMenuPanel;	//	�� ������ fill �� color �ִ� �޴�
	private JLabel			lblSubOption;		//	LineWidth
	private JTextField		txtSubOption;		//	LineWidth input
	private JCheckBox		chkFill;			//	üũ�ڽ�
	private JButton			btnColor;			//	���� ���� ��ư
	
	private DrawingPanel	drawPanel;			//	�߰��� �׸� �׸��� �г�
	private JPanel			messagePanel;		//	�Ʒ� �޼��� �г�
	
	private MenuMouseListener	menuMouseL;		//	�޴��� ���콺 �÷������� �ǵ��
	private MenuActionListener	menuActionL;	//	��ư �޴� �г��� ������Ʈ�� ������
	private OptionActionListener optionActionL;	//	�ɼ� �޴� �г��� ������Ʈ�� ������
	
	public PrimaryPanel() {
		setBackground(Color.white);					//	����
		setPreferredSize(new Dimension(620,590));	//	ũ��
		setLayout(null);							//	���̾ƿ� �Ŵ��� off
		
		menuMouseL = new MenuMouseListener();		//	������ ����
		menuActionL = new MenuActionListener();
		optionActionL = new OptionActionListener();
		
		menuPanel = new JPanel();	//	�޴��г� ����
		menuPanel.setBackground(Color.white);
		menuPanel.setBounds(10,10,600,90);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MEUN"));
		menuPanel.setLayout(null);
		add(menuPanel);
		
		buttonMenuPanel = new JPanel();		//	��ư �޴� �г� ����
		buttonMenuPanel.setBackground(Color.white);
		buttonMenuPanel.setBounds(10,20,300,60);
		//buttonMenuPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonMenuPanel.setLayout(new GridLayout(2,3));		//	�׸��� ���̾ƿ� 2�� 3�� ��ġ
		menuPanel.add(buttonMenuPanel);
		
		btnMenuArray = new JButton[6];	//	��ư�� ��� ���� (�����ϴ¹� �ָ�)
		for (int i=0; i<6; i++) {
			btnMenuArray[i] = new JButton(DrawConstants.MENU[i]);
			btnMenuArray[i].setBackground(Color.white);
			btnMenuArray[i].setForeground(Color.darkGray);
			btnMenuArray[i].addMouseListener(menuMouseL);
			btnMenuArray[i].addActionListener(menuActionL);
			buttonMenuPanel.add(btnMenuArray[i]);
		} // for

		
		optionMenuPanel = new JPanel();	//	�ɼ� �޴� �г� ����
		optionMenuPanel.setBackground(Color.white);
		optionMenuPanel.setBounds(320,20,270,60);
		optionMenuPanel.setBorder(BorderFactory.createEtchedBorder());
		optionMenuPanel.setLayout(null);
		menuPanel.add(optionMenuPanel);
		
		subOptionMenuPanel = new JPanel();	//	�ɼ� �޴� �г� ����� �β��� color
		subOptionMenuPanel.setBackground(Color.white);
		subOptionMenuPanel.setBounds(5,5,260,30);
		subOptionMenuPanel.setVisible(false);
		optionMenuPanel.add(subOptionMenuPanel);
		
		lblSubOption = new JLabel("Point Size : ");	//	Point Size �۾�
		subOptionMenuPanel.add(lblSubOption);
		
		txtSubOption = new JTextField(5);		//	Point�� ũ�� ����
		txtSubOption.setText("2");
		subOptionMenuPanel.add(txtSubOption);	//	�����ʸ� ���� �ʴ� ������ ������ �׷����� txtSubOption�� ���� getText �� ���̱� ����
		
		btnColor = new JButton("COLOR");			//	Color ��ư
		btnColor.setFont(new Font("Arial",Font.BOLD,10));
		btnColor.addActionListener(optionActionL);	//	�÷��� �ɼ��̹Ƿ� �ɼ� �׼� ������
		subOptionMenuPanel.add(btnColor);
		
		chkFill = new JCheckBox("FILL");	//	üũ�ڽ�
		chkFill.setBounds(5,32,260,25);		//	�̶��� ���� ������ add �� �ʿ� ����
		chkFill.setBackground(Color.white);
		chkFill.setHorizontalAlignment(SwingConstants.CENTER);
		chkFill.setVisible(false);
		optionMenuPanel.add(chkFill);
		
		drawPanel = new DrawingPanel(this);	//	�Ķ���ͷ� �ڽ��� �ִ°� ����
		drawPanel.setBounds(10,110,600,400);
		add(drawPanel);
		
		messagePanel = new JPanel();		//	�ϴ� �޼��� �г�
		messagePanel.setBackground(Color.white);
		messagePanel.setBounds(10,520,600,60);
		messagePanel.setLayout(null);
		messagePanel.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.red));
		add(messagePanel);
		
	} // PrimaryPanel()
	
	public int getOption() {								//	����Ʈ ũ�� �ɼ� �޴� �޼ҵ�
		int option;		
		option = Integer.parseInt(txtSubOption.getText());	//	getText �޼ҵ� �̿��ؼ� �ɼ� �� ����. ��ȿ�� �˻�?
		return option;		
	} // getSubOption()
	
	public boolean getFill() {			//	Fill�� check �ƴ��� ����
		return chkFill.isSelected();
	} // getFill()
	
	private class MenuMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {	//	���콺�� ������Ʈ�� �÷���������
			JButton obj = (JButton)event.getSource();
			obj.setBackground(Color.cyan);
			obj.setForeground(Color.red);			
		} // mouseEntered()
		public void mouseExited(MouseEvent event) {		//	���콺�� ������Ʈ ������ ��������
			JButton obj = (JButton)event.getSource();
			obj.setBackground(Color.white);
			obj.setForeground(Color.darkGray);
		} // mouseExited()
	} // MenuMouseListener class
	
	private class MenuActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			int i;
			Object obj = event.getSource();			//	� ��ü���� �̺�Ʈ�� �Ͼ���� �˾Ƴ���, �� ��ü�� obj�� ����
			
			for (i=0; i<6; i++) {					//	obj�� � ��ư���� �˾Ƴ���, i �� ����
				if (obj == btnMenuArray[i]) break;
			} // for
			
			subOptionMenuPanel.setVisible(true);	//	��� � ��ư�� ���������� SubOptionMenuPanel�� Ȱ��ȭ �ȴ�.
			chkFill.setVisible(false);				//	Fill�� ���� �κ��� �⺻�����δ� �Ⱥ��̰� ��
			drawPanel.setDrawMode(i);				//	� ��ư�� ���ȴ��� drawPanel�� ������. (����� drawPanel������ �� �޼ҵ�� nowData�� �� ��������)
			switch(i) {
				case DrawConstants.DOT:						//	case �� �迭�� ������ �ָ��� ��.
					//subOptionMenuPanel.setVisible(true);
					lblSubOption.setText("Point Size : ");	//	Dot �϶��� Point Size��
					//chkFill.setVisible(false);
					break;
				case DrawConstants.RECT:
				case DrawConstants.OVAL:
					//subOptionMenuPanel.setVisible(true);
					//lblSubOption.setText("Line Width : ");
					chkFill.setVisible(true);				//	Rect�� Oval�϶��� Fill�� ���̰� ��.
					//break;
				case DrawConstants.LINE:
					//subOptionMenuPanel.setVisible(true);
					lblSubOption.setText("Line Width : ");	//	Line, Rect, Oval �� Line Width�� ���;��Ѵ�.
					//chkFill.setVisible(false);
					break;
				case DrawConstants.UNDO:
					drawPanel.deleteLastDrawData();
					drawPanel.repaint();
					break;
				default:
					subOptionMenuPanel.setVisible(false);	//	���� �ش���� ������, subOptionMenuPanel�� �Ⱥ��̰� ��.
					//chkFill.setVisible(false);
					break;
			} // siwtch
			
		} // actionPerformed()
	} // MenuActionListener class
	
	private class OptionActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			Color c;										//	JcolorChooser.showDialog() �� color ��ü�� �����ϹǷ�
			
			if (obj == btnColor) {	//	Color ��ư�� ������,
				c = JColorChooser.showDialog(	drawPanel,	//	JcolorChooser.showDialog() �� �� �����ϰ� ��. ù��° �Ķ���ʹ� �� ���̾�α׸� �ҷ����� ������Ʈ�ε�, null�� ��� ����.
							"Pick a Color!!",				//	Ÿ��Ʋ���� String
							drawPanel.getColorSelected());	//	drawPanel���� ���õ� ���� �˷��ִ� ����� ���� �޼ҵ�
				drawPanel.setColorSelected(c);				//	set �޼ҵ�
			} // if			
			
		} // actionPerformed()
	} // OptionActionListener class
	
} // PrimaryPanel class