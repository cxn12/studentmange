package studentManage;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField dobField;
	private JTextField batchField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFrame frame = new AddFrame();
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
	public AddFrame() {
		// 和前面类似
		setResizable(false);
		setTitle("添加学生");
		// 这个地方，添加学生只是这个程序的一部分，所以当关闭这部分的时候，程序不直接退出，只是关闭该部分程序
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel TitleLabel = new JLabel("请输入新学生的信息：");
		TitleLabel.setFont(new Font("宋体", Font.BOLD, 20));
		TitleLabel.setBounds(71, 34, 220, 45);
		panel.add(TitleLabel);
		
		JLabel idLabel = new JLabel("学号：");
		idLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		idLabel.setBounds(71, 105, 50, 30);
		panel.add(idLabel);
		
		idField = new JTextField();
		idField.setBounds(143, 99, 240, 45);
		panel.add(idField);
		idField.setColumns(10);
		
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		nameLabel.setBounds(71, 160, 50, 30);
		panel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(143, 154, 240, 45);
		panel.add(nameField);
		
		JLabel genderLabel = new JLabel("性别：");
		genderLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		genderLabel.setBounds(71, 215, 50, 30);
		panel.add(genderLabel);
		
		genderField = new JTextField();
		genderField.setColumns(10);
		genderField.setBounds(143, 209, 240, 45);
		panel.add(genderField);
		
		JLabel dobLabel = new JLabel("出生日期：");
		dobLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		dobLabel.setBounds(41, 270, 80, 30);
		panel.add(dobLabel);
		
		dobField = new JTextField();
		dobField.setColumns(10);
		dobField.setBounds(143, 264, 240, 45);
		panel.add(dobField);
		
		JLabel batchLabel = new JLabel("班级：");
		batchLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		batchLabel.setBounds(71, 325, 50, 30);
		panel.add(batchLabel);
		
		batchField = new JTextField();
		batchField.setColumns(10);
		batchField.setBounds(143, 319, 240, 45);
		panel.add(batchField);
		
		JButton addButton = new JButton("添加");
		// 添加鼠标监听事件
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 先取出数据
				// getText() 返回字符串类型的
				// Integer.parseInt 数据类型转换
				int id = Integer.parseInt(idField.getText());

				String nameString = nameField.getText();

				String genderString = genderField.getText();

				String dobfieldString = dobField.getText();

				int batch = Integer.parseInt(batchField.getText());
				// 输出一下学生的信息，方便修改
				System.out.println(id + "\t" + nameString + "\t" + genderString + "\t" + dobfieldString + "\t" + batch);
				// SQLHelp 是自己写的一个工具类
				SQLHelp sqlHelp = new SQLHelp();
				try {
					// 调用添加学生信息的方法
					sqlHelp.addStudent(id, nameString, genderString, dobfieldString, batch);
					// 弹出对话框，提示用户添加成功
					JOptionPane.showMessageDialog(AddFrame.this, "添加成功！");
				} catch (SQLException e1) {
					// 这里捕获一下异常，因为学生的学号是唯一的，所以当学号已经存在的时候，提示用户，该学生信息已存在
					// 异常代码为 23000
					if(e1.getSQLState().equals("23000")) {
						JOptionPane.showMessageDialog(AddFrame.this, "添加失败!该学生已存在");
					}
					e1.printStackTrace();
				}

			}
		});
		addButton.setFont(new Font("宋体", Font.PLAIN, 18));
		addButton.setBounds(182, 389, 97, 33);
		panel.add(addButton);
	}
}

