package org.jsp.event.responsestructure;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseStructure<T> {
	//T means type class
	private int httpStatus;
	private String message;
	private T body;
	

}
