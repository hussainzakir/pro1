package com.repsrv.csweb.core.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.repsrv.csweb.core.model.account.imports.Row;

public class MapUtils {

	private MapUtils() {}
	
	public static<T> List<T> flatten(Stream<List<T>> s){
		return s.flatMap(l -> l.stream()).collect(Collectors.toList());
	}
}
