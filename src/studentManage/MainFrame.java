package studentManage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// 有了main函数才可以单独运行
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		// 设置大小不可改变
		setResizable(false);
		// 设置标题
		setTitle("学生管理系统");
		// 由于这是主页面，所有当主页面关闭的时候，程序就直接退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(int x,int y,int width,int height);
		// x 和 y 是窗口打开时的位置
		// width 和 height 是窗口打宽度和高度
		setBounds(100, 100, 450, 350);
		// setLocationRelativeTo(c)
		// 设置窗口相对于 c 的位置，当 c 为空或者 null 时，默认为是相对于屏幕中央
		setLocationRelativeTo(null);
		// 实例化一个 pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生管理系统");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel.setBounds(140, 10, 163, 44);
		contentPane.add(lblNewLabel);
		
		JButton addButton = new JButton("添加学生");
		// addActionListener 注册监听器
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 设置新窗口可见
				new AddFrame().setVisible(true);
			}
		});
	
		addButton.setFont(new Font("宋体", Font.PLAIN, 18));
		addButton.setBounds(167, 64, 114, 37);
		contentPane.add(addButton);
		
		JButton changeButton = new JButton("修改信息");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 同上
				new ChangeFrame().setVisible(true);
			}
		});
		changeButton.setFont(new Font("宋体", Font.PLAIN, 18));
		changeButton.setBounds(167, 121, 114, 37);
		contentPane.add(changeButton);
		
		JButton deleteButton = new JButton("删除学生");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 同上
				new DeleteFrame().setVisible(true);
			}
		});
		deleteButton.setFont(new Font("宋体", Font.PLAIN, 18));
		deleteButton.setBounds(167, 180, 114, 37);
		contentPane.add(deleteButton);
		
		JButton queryButton = new JButton("查询信息");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 同上
				new QueryFrame().setVisible(true);
			}
		});
		queryButton.setFont(new Font("宋体", Font.PLAIN, 18));
		queryButton.setBounds(167, 240, 114, 37);
		contentPane.add(queryButton);
	}
}

