package com.github.marocraft.trackntrace.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for variables
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Variable {
	private String name;
	private int start;
	private int end;
}