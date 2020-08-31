package com.nt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nt.dto.PoliticianDTO;
import com.nt.service.PoliticianMgmtService;
import com.nt.vo.PoliticianVO;

public class MainController {
	private  PoliticianMgmtService service;

	public MainController(PoliticianMgmtService service) {
			this.service = service;
	}
	
	public  List<PoliticianVO> gatherPoliticianByDesgs(String desg1,String desg2,String desg3)throws Exception{
		List<PoliticianDTO> listDTO=null;
		List<PoliticianVO> listVO=null;
		PoliticianVO vo=null;
		//use service
		  listDTO=service.fetchPoliticianByDesgs(desg1, desg2, desg3);
		  //convert listDTO to listVO
		  listVO=new ArrayList();
		  for(PoliticianDTO dto:listDTO) {
			  //convert each dto to each vo
                  vo=new PoliticianVO();
                  BeanUtils.copyProperties(dto, vo);
                  vo.setSno(String.valueOf(dto.getSno()));
                  vo.setSal(String.valueOf(dto.getSal()));
                  vo.setAge(String.valueOf(dto.getAge()));
                  vo.setName(dto.getName());
                  vo.setState(dto.getState());
                  vo.setQualification(dto.getQualification());
                  //add each vo to listVO
                  listVO.add(vo);
		  }//for
		  return listVO;
	} //method
	

}//class