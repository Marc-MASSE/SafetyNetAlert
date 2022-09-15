package fr.marc.safetynetalert.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jsoniter.any.Any;

/*
* Class used to convert a list of Any in a list of String 
*/

public class ToStringListConverter {
	
	public static List<String> convert (List<Any> list){
		
		List<String> listOfString = list
				.stream()
				.map(object -> Objects.toString(object, null))
				.collect(Collectors.toList());
		
		return listOfString;
	}

}
