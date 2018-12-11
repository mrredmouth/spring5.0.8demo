package com.ccg.spring.webservice.czservice.provider;

import javax.jws.WebParam;
import javax.jws.WebService;
import com.ccg.spring.bean.Boy;

@WebService
public interface SendService {
	public boolean sendOA(@WebParam(name="param")String param);
	public boolean sendOrg(Boy boy);
}
