import javax.swing.JFrame;
/*
 * SimplePainter의 드라이버 클래스
 * 프레임 생성한다.
 */

public class SimplePainter
{
	public static void main(String[] args) {
		JFrame frame = new JFrame("SIMPLE PAINTER");			//	JFrame 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	종료 형식
		frame.setResizable(false);								//	크기변경 불가하게 함
		
		PrimaryPanel primary = new PrimaryPanel();				//	사용자 정의 패널을 생성
		
		frame.getContentPane().add(primary);					//	add함.
		frame.pack();											//	패널의 크기에 맞게 프레임 조절
		frame.setVisible(true);									//	보이게 함.
	} // main()
} // HighLowGame class