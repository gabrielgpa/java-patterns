package com.ezoqc.defijava.helpers;

public interface Mapper<T, V> {
	T toEntity(V data);
	
	V toDTO(T data);
}
