package ty.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	private Connection conn;
	private PreparedStatement pstmt;
	
	//��������
	public DBManager(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//����ݿ�����
	private void openConnection(){  
		
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carwashing", "root", "1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//��ݸ���  
	public int execUpdate(String sql, Object... params){
		
		this.openConnection();
		
		try {
			//����Ԥ����������
			this.pstmt = this.conn.prepareStatement(sql);
			
			//����ֵ
			for(int i=0; i< params.length; i++){
				this.pstmt.setObject(i+1, params[i]);
			}
			
			//�������
			return this.pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return -1;
		} finally{
			this.closeConnection();
		}
		
	}
	
	//��ݲ�ѯ
	public ResultSet execQuery(String sql, Object... params){
		
		this.openConnection();
		
		try {
			//����Ԥ����������
			this.pstmt = this.conn.prepareStatement(sql);
			
			//����ֵ
			for(int i=0; i< params.length; i++){
				this.pstmt.setObject(i+1, params[i]);
			}
			
			//��ѯ���
			return this.pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	//�ر���ݿ�����
	public void closeConnection(){
		
		try {
			if(this.pstmt!=null){
				this.pstmt.close(); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(this.conn!=null){
				this.conn.close(); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}}
