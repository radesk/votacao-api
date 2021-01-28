package com.dbo.api.model.response;

import com.dbo.api.model.CpfStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpfResponse {
	
	private Enum<CpfStatus> status;

}
