package com.first.project.Messanger.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.first.project.Messanger.dataBase.DataBaseClass;
import com.first.project.Messanger.model.Message;

public class MessageService {
	//private static Map<Long, Message> messages = DataBaseClass.getAllMessages();
	//private static Map<Long, Message> message = DataBaseClass.getMessage();

	/*public static ArrayList<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}*/
	
	public static List<Message> getAllMessages() {
		return DataBaseClass.getAllMessages();
	}
	
	public static List<Message> getMessage(long id){
		return DataBaseClass.getMessage(id);
	}
	public static List<Message> getMessagesForYear(int year){
		return DataBaseClass.getMessagesForYear(year);
	}
	public static List<Message> getAllMessagesPaginated(int start,  int size){
		return DataBaseClass.getAllMessagesPaginated(start,size);
	}
	
	public static List<Message> postMessage(Message msg) throws SQLException {
		return DataBaseClass.postMessage(msg);
	}
	
	public static List<Message> updateMessage(Message msg, long id) {
		return DataBaseClass.updateMessage(msg, id);
		
	}
	
	public static String deleteMessage(long id) {
		return DataBaseClass.deleteMessage(id);
	}
}
