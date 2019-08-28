package com.github.marocraft.trackntrace.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Mapping;
import com.github.marocraft.trackntrace.domain.LogTrace;
import com.github.marocraft.trackntrace.domain.Variable;

/**
 * Utilities commonly used by the whole application
 * 
 * @author Khalid ELABBADI
 *
 */
@Component
public class CommonUtils {

	public static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * Replace each field in the template by its value
	 * 
	 * @param format
	 * @param field
	 * @param logTrace
	 * @return
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public String replace(String format, String field, LogTrace logTrace) throws IllegalAccessException {
		Object valueOfField = valueOf(field, logTrace);
		if (format != null)
			return format.replaceAll("\"\\{\\{" + field + "\\}\\}\"", "\"" + valueOfField + "\"") + "";
		return null;
	}

	public List<Variable> extractVariables(String expression) {

		final String regex = "\\{\\{([0-9a-zA-Z_]+)\\}\\}";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(expression);
		final List<Variable> variables = new ArrayList<>(matcher.groupCount());

		while (matcher.find()) {
			Variable variable = new Variable();
			for (int i = 1; i <= matcher.groupCount(); i++) {
				variable.setName(matcher.group(i));
				variable.setStart(matcher.start());
				variable.setEnd(matcher.end());
				variables.add(variable);
			}
		}

		return variables;
	}

	private Object valueOf(String fieldName, LogTrace trace) throws IllegalAccessException {
		if (trace != null) {
			Field[] clazzFields = trace.getClass().getDeclaredFields();
			for (Field clazzField : clazzFields) {
				Mapping annotation = clazzField.getAnnotation(Mapping.class);
				if (annotation != null && annotation.field().equals(fieldName)) {
					clazzField.setAccessible(true);
					return clazzField.get(trace);
				}
			}
		}
		return null;

	}

	public static String toLowerHex(long v) {
		char[] data = new char[16];
		writeHexLong(data, 0, v);
		return new String(data);
	}

	private static void writeHexByte(char[] data, int pos, byte b) {
		data[pos + 0] = CommonUtils.HEX_DIGITS[(b >> 4) & 0xf];
		data[pos + 1] = CommonUtils.HEX_DIGITS[b & 0xf];
	}

	private static void writeHexLong(char[] data, int pos, long v) {
		writeHexByte(data, pos + 0, (byte) ((v >>> 56L) & 0xff));
		writeHexByte(data, pos + 2, (byte) ((v >>> 48L) & 0xff));
		writeHexByte(data, pos + 4, (byte) ((v >>> 40L) & 0xff));
		writeHexByte(data, pos + 6, (byte) ((v >>> 32L) & 0xff));
		writeHexByte(data, pos + 8, (byte) ((v >>> 24L) & 0xff));
		writeHexByte(data, pos + 10, (byte) ((v >>> 16L) & 0xff));
		writeHexByte(data, pos + 12, (byte) ((v >>> 8L) & 0xff));
		writeHexByte(data, pos + 14, (byte) (v & 0xff));
	}

	private static long randomLong() {
		return java.util.concurrent.ThreadLocalRandom.current().nextLong();
	}

	public static long nextId() {
		long nextId = randomLong();
		while (nextId == 0L) {
			nextId = randomLong();
		}
		return nextId;
	}
}