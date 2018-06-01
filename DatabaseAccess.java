import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseAccess
 */
@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://120.79.89.4:3306/music";
    
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "guest";
    static final String PASS = "Rao614614614@"; 
    public DatabaseAccess() {
        super();   
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "��ϲ��ע��ɹ�";
         String name =new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        String password =new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8");
        String password1 =new String(request.getParameter("password1").getBytes("ISO8859-1"),"UTF-8");
        String email =new String(request.getParameter("email").getBytes("ISO8859-1"),"UTF-8");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(conn);
            stmt = conn.createStatement();
            System.out.println(stmt);
            String sql;
            sql = "INSERT INTO user_info VALUES ('9','"+name+"','"+password+"','"+email+"')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // ���� JDBC ����
            se.printStackTrace();
        } catch(Exception e) {
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // ��������ڹر���Դ�Ŀ�
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
       String docType = "<!DOCTYPE html> \n";
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor=\"#f0f0f0\">\n" +
            "<h1 align=\"center\">" + name+ "</h1>\n" +
            "<ul>\n" +
            "  <li><b>����</b>��"
            + password + "\n" +
            "  <li><b>����</b>��"
            + email + "\n" +
            "</ul>\n" +
            "</body></html>");
       
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}