package studentManage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class SQLHelp {

	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost:3306/niit?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	public static final String username = "root";
	public static final String password = "123456";
	
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	private ResultSet rSet = null;
	
	// 加载驱动
	// 静态初始化块（只执行一次）
	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 链接数据库
	public void  connectDB() {
		try {
			connection = DriverManager.getConnection(DB_URL,username,password);
			System.out.println("数据库链接成功");
		} catch (SQLException e) {
			System.out.println("数据库链接失败");
			e.printStackTrace();
		}
	}
	
	// 关闭资源
	public void close() {
		if(rSet != null) {
			try {
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  void addStudent(int id, String nameString, String genderString, String dobString, int batch) throws SQLException {
		
		// try finally 无论是否抛出异常都将执行 finally 中的语句
		try {
			// 先链接到数据库
			connectDB();
			
			// sql 语句
			// 静态 sql 语句需要进行字符串拼接
			// 动态 sql 语句
			String addsql = "insert into student values(?, ?, ?, ?, ?)";
			
			pStatement = connection.prepareStatement(addsql);
			
			pStatement.setInt(1, id);
			pStatement.setString(2, nameString);
			pStatement.setString(3, genderString);
			pStatement.setString(4, dobString);
			pStatement.setInt(5, batch);
						
			pStatement.executeUpdate();
			
		} finally {
			
			close();
			
		}
		
	}
	
	public void changeStudent(int id, String nameString, String genderString, String dobString, int batch) throws SQLException {
					
		try {
			connectDB();
			
			String changesql = "update student set name = ?, gender = ?, dob = ?, batch = ? where id = ?";
					
			pStatement = connection.prepareStatement(changesql);

			pStatement.setString(1, nameString);
			pStatement.setString(2, genderString);
			pStatement.setString(3, dobString);
			pStatement.setInt(4, batch);
			pStatement.setInt(5, id);
					
			pStatement.executeUpdate();
			
		} finally {
			close();
		}	

	}

	public void deletestudent(int id) throws SQLException {
				
		try {
			
			connectDB();
			
			String deleteString = "select * from student where id = ?";

			pStatement = connection.prepareStatement(deleteString);
			
			pStatement.setInt(1, id);
			
			pStatement.execute();

		} finally {
			close();
		}
		
	}
	
	public Student queryStudent(int id) throws SQLException {
		try {
			connectDB();
			
			String querysql = "select * from student where id = ?";
			pStatement = connection.prepareStatement(querysql);
			pStatement.setInt(1, id);
			// 三种方法执行
			// execute()	都可以使用，返回true或false
			// executeQuery() 专门用于查询，返回结果集
			// executeUpdate() 专门用于删除、更新
			rSet = pStatement.executeQuery();
			
			if(rSet.next()) {
				
				String nameString = rSet.getString(2);
				String genderString = rSet.getString(3);
				String dobString = rSet.getString(4);
				int batchString = rSet.getInt(5);
				
				// 查询到学生信息返回结果集
				return new Student(nameString, genderString, dobString, batchString);
			} else {
				// 没有查询到学生信息，返回null
				return null;
			}

		} finally {
			close();
		}

	}

}

