package com.nt.service;

import java.util.List;

import com.nt.dto.PoliticianDTO;



public interface PoliticianMgmtService {
	public   List<PoliticianDTO>  fetchPoliticianByDesgs(String desg1,String desg2,String desg3)throws Exception;
	}

