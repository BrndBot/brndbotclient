package com.brndbot.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientInterfaceFactory {

	public static ClientInterface getInterfaceForClass(String name)
			throws ClassNotFoundException {
		Class<?> clas = Class.forName(name + "Factory");
		try {
			Method mth = clas.getMethod("getClientInterface");
			ClientInterface ci = (ClientInterface) mth.invoke(null);
			return ci;
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			return null;		// should never happen!
		}
	}
}
