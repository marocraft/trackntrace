package com.github.marocraft.trackntrace.domain;

/**
 * Data transfer object for variables
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 */
public class Variable {
	private String name;
	private int start;
	private int end;

	public Variable() {
		super();
	}

	public Variable(String name, int start, int end) {
		super();
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}