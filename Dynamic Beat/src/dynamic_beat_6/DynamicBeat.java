package dynamic_beat_6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEntered = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasic = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEntered = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasic = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEntered = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasic = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEntered = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasic = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/On my way Title Image.png")).getImage();
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/On my way Start Image.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).png")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasic);
	private JButton quitButton = new JButton(quitButtonBasic);
	private JButton leftButton = new JButton(leftButtonBasic);
	private JButton rightButton = new JButton(rightButtonBasic);
	
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false; // 처음에는 메인 화면이 아닌 시작 화면이기 때문에 false로 설정한다
	
	public DynamicBeat() {
		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 사라진다
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 게임창의 크기를 정한다
		setResizable(false); // 게임창의 크기를 고정시킨다
		setLocationRelativeTo(null); // 게임 실행 시 게임창을 화면의 정중앙에 위치시킨다
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료했을 때 프로그램이 종료된다
		setVisible(true); // 게임창이 정상적으로 화면에 출력되게 한다 기본값은 false
		setBackground(new Color(0, 0, 0, 0)); // paintComponent의 색이 회색이 아닌 흰색으로 변경된다
		setLayout(null); // 버튼이나 JLabel을 넣었을 때 그 자리에 위치하게 된다

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false); // JButton이 기본적인 템플릿을 가지고 있기 때문에 그것을 없애주는 작업
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				ButtonEnteredMusic.start();
			} // 마우스가 이미지 위로 올라갔을 때 이미지를 바꿔주는 작업
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} // 마우스가 이미지에서 벗어났을 때 이미지를 바꿔주는 작업
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				ButtonEnteredMusic.start();
				try { // 버튼 클릭 소리재생 약 1초후 프로그램이 종료되게 하는 작업
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			} // 해당 이미지를 눌렀을 때 프로그램이 종료되게 한다
		});
		add(exitButton);

		startButton.setBounds(40, 100, 400, 100);
		startButton.setBorderPainted(false); 
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEntered);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				ButtonEnteredMusic.start();
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasic);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				ButtonEnteredMusic.start();
				startButton.setVisible(false); // 스타트 버튼이 작동 된 후에 해당 이미지를 없앰
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				isMainScreen = true;
			} // 스타트 버튼이 눌리기 전에 이미지를 버튼이 눌린 후 새로운 이미지로 바꿔주기 위한 작업
		});
		add(startButton);

		quitButton.setBounds(40, 230, 400, 100);
		quitButton.setBorderPainted(false); // JButton이 기본적인 템플릿을 가지고 있기 때문에 그것을 없애주는 작업
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEntered);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				ButtonEnteredMusic.start();
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasic);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				ButtonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			} 
		});
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false); // JButton이 기본적인 템플릿을 가지고 있기 때문에 그것을 없애주는 작업
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEntered);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				ButtonEnteredMusic.start();
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasic);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				ButtonEnteredMusic.start();
//			왼쪽 버튼 이벤트
			} 
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false); // JButton이 기본적인 템플릿을 가지고 있기 때문에 그것을 없애주는 작업
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEntered);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				ButtonEnteredMusic.start();
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasic);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				ButtonEnteredMusic.start();
//			오른쪽 버튼 이벤트
			} 
		});
		add(rightButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
//		메뉴창을 마우스로 클릭한 뒤 움직이는 작업
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); // JFrame에 menuBar를 추가한다
		
//		메인 클래스 위치를 기반으로 이미지 파일을 얻어온 뒤에 이미지 인스턴스를 변수에 초기화시킨다
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g) { // GUI화면에서 가장 첫 번째로 이미지를 그려주는 메소드
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 프로그램 크기만큼 이미지를 생성한다 스크린 이미지의 크기
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic); // 스크린 이미지에 우리가 원하는 이미지를 그려준다
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 0, 0 에 그려준다
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null); // 인트로 이미지를 스크린 이미지에 그려준다
		if(isMainScreen) 
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		} // 이미지를 단순히 화면에 출력해줄 때 사용한다
		paintComponents(g); // 고정된 이미지를 그려주는 메소드 , 추가가 된 부분도 보여준다 add(~~Button)
		this.repaint(); // 다시 paint 메소드를 부른다 
	} // 전체 화면 이미지를 매 순간마다 프로그램이 종료되는 순간까지 계속 반복해서 그려준다
}
