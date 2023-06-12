package studentManage;


import java.sql.*;

public class JDBC{

    public static void main(String[] args) throws Exception {
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开链接
            Connection conn = DriverManager.getConnection("jdbc:conn-local://localhost:3306/text?useSSL=false&serverTimezone=UTC", "SQ", "109036");

            // 执行查询
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name, url FROM text";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print("  NAME " + name);
                System.out.print("  URL: " + url);
                System.out.print("\n");
            }
            // 释放资源
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


