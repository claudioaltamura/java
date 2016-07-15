package de.claudioaltamura.json.example;

import java.util.ArrayList;
import java.util.List;

public class User {
	 
	private int age = 111;
	private String name = "me";
	private List<String> messages = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("msg 1");
			add("msg 2");
			add("msg 3");
		}
	};
 
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", " +
				"messages=" + messages + "]";
	}
}