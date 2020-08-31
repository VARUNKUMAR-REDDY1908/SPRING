package com.nt.dao;

import java.util.List;

import com.nt.bo.PoliticianBO;

public interface PoliticianDAO {
	public  List<PoliticianBO> getAllPoliticiansByDesgs(String desg1,String desg2,String desg3)throws Exception;
}
