package com.cg.springmvc.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cg.springmvc.model.State;

public class UserProfileUtil {

	public static Map<Integer, String> getStates(List<State> list) {
		LinkedHashMap<Integer, String> states = new LinkedHashMap<Integer, String>();
		for (State state : list) {
			states.put(state.getStateId(), state.getStateName());
		}

		return states;
	}

}
