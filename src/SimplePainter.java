import javax.swing.JFrame;
/*
 * SimplePainter�� ����̹� Ŭ����
 * ������ �����Ѵ�.
 */

public class SimplePainter
{
	public static void main(String[] args) {
		JFrame frame = new JFrame("SIMPLE PAINTER");			//	JFrame ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	���� ����
		frame.setResizable(false);								//	ũ�⺯�� �Ұ��ϰ� ��
		
		PrimaryPanel primary = new PrimaryPanel();				//	����� ���� �г��� ����
		
		frame.getContentPane().add(primary);					//	add��.
		frame.pack();											//	�г��� ũ�⿡ �°� ������ ����
		frame.setVisible(true);									//	���̰� ��.
	} // main()
} // HighLowGame class