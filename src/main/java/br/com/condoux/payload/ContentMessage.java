package br.com.condoux.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class ContentMessage {

	@Getter @Setter
	private String en;

	@Getter @Setter
	private String pt;
	
}
