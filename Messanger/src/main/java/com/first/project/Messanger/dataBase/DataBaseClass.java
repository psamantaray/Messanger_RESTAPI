package com.first.project.Messanger.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.first.project.Messanger.model.Message;

public class DataBaseClass {

	//private static Map<Long, Message> messages = new HashMap<Long, Message>();
	static Statement stmt=null;
	static ResultSet rs=null;
	static Connection con = null;
	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
			stmt = con.createStatement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<Message> getAllMessages() {
		connection();
		List<Message> lst = new ArrayList<Message>();
		try {
			rs = stmt.executeQuery("select * from messanger");
			while (rs.next()) {
				lst.add(new Message(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5)));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return lst;
	}
	
	public static List<Message> getMessage(long id) {
		connection();
		List<Message> lst = new ArrayList<Message>();
		try {
			rs = stmt.executeQuery("select * from messanger where id="+id);
			while (rs.next()) {
				lst.add(new Message(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4),rs.getTimestamp(5)));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return lst;
	}
	public static List<Message> postMessage(Message msg) throws SQLException {
		connection();
		String message = msg.getMessage();
		String author = msg.getAuthor();
		long id =0;
		try {
			String queryNosOfrcd = "Select max(id) from messanger";
			rs = stmt.executeQuery(queryNosOfrcd);
			rs.next();
			id =rs.getLong(1)+1; 
			Thread.sleep(3000);
			String query = "insert into messanger(id,message,author,created,lastmodified) values (" + id + ",'" + message + "',"
					+ "'" + author + "',"+"now(),now())";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return getMessage(id);
	}

	public static List<Message> updateMessage(Message msg, long id) {
		try {
			connection();
			String message = msg.getMessage();
			String query = "update messanger set message='"+message+"',"+"author='"+msg.getAuthor()+"',lastmodified=now()"
					+ "where id="+id;
			stmt.executeUpdate(query);

		} catch (Exception e) {
			System.out.println(e);
		}
		return getMessage(id);
	}
	
	public static String deleteMessage(long id) {
		try {
			connection();
			String query = "Delete from messanger where id="+id;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Message with ID "+id+" deleted";
	}
	
	public static List<Message> getMessagesForYear(int year){
		List<Message> allMessage = getAllMessages();
		List<Message> filteredMessages = new ArrayList<Message>();
		Calendar cld = Calendar.getInstance();
		Iterator<Message> itr = allMessage.iterator();
		while(itr.hasNext()) {
			Message m= itr.next();
			cld.setTime(m.getCreated());
			int messageYear =cld.get(Calendar.YEAR);
			if (messageYear==year) {
				filteredMessages.add(m);
			}
		}
		return filteredMessages;
	}
	
	public static List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> allMessage = getAllMessages();
		return allMessage.subList(start, size);
	}
}
