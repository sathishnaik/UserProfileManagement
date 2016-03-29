package com.cg.springmvc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	private SessionUtil() {
	}

	public static HttpSession createNewSession(HttpServletRequest request) {
		// create a new session, if one does not exists
		HttpSession session = request.getSession();
		return session;
	}

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(false);
	}

	public static void putSessionValue(HttpServletRequest request, String key,
			Object value) {
		HttpSession session = request.getSession(false);
		session.setAttribute(key, value);
	}

	public static Object getSessionValue(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		return session.getAttribute(key);
	}

	public static void cleanSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
	}

}
