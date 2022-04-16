package com.ShopTechnological.Application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	public static void main(String[] args) {
		String text="haha/hds/";
		Pattern pattern=Pattern.compile("haha/[a-z]{1,5}");
		Matcher matcher=pattern.matcher(text);
		System.out.print(matcher.matches());
		
	}
}
