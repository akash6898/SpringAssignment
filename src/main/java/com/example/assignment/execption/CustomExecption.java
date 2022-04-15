package com.example.assignment.execption;

public class CustomExecption extends Exception {
	public CustomExecption() {
		super();
	}
	public CustomExecption(String errors) {
		super(errors);
	}
}
