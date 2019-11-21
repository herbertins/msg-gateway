package br.com.condoux.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class DataInformation {
	
	@Getter @Setter
	private Long code;

}
