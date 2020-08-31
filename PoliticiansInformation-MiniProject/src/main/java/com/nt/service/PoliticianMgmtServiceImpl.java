package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nt.bo.PoliticianBO;
import com.nt.dao.PoliticianDAO;
import com.nt.dto.PoliticianDTO;

public class PoliticianMgmtServiceImpl implements PoliticianMgmtService{
	  private  PoliticianDAO  dao;
	     
		public PoliticianMgmtServiceImpl(PoliticianDAO dao) {
			this.dao = dao;
		}
@Override
		public List<PoliticianDTO> fetchPoliticianByDesgs(String desg1, String desg2, String desg3)throws Exception {
			List<PoliticianBO> listBO=null;
			List<PoliticianDTO>  listDTO=null;
			PoliticianDTO dto=null;
			//convert inputs(desgs) to uppercase letter  (b.logic)
			desg1=desg1.toUpperCase();
			desg2=desg2.toUpperCase();
			desg3=desg3.toUpperCase();
			//use DAO
			listBO=dao.getAllPoliticiansByDesgs(desg1, desg2, desg3);
			//convert listBO to listDTO
			  listDTO=new ArrayList();
			  for(PoliticianBO bo: listBO) {
				  //copy each bo to new DTO object
				 dto=new PoliticianDTO();
				 BeanUtils.copyProperties(bo, dto); // copies data from one javabean object to anthoer bean obj when they have matching properties names,types
				 dto.setSal((float) Math.round(bo.getSal())); //converts 7000.56 to 7001.0
				// dto.setSno(listDTO.size()+1);  //serinal number generation..
				 //add each DTO 
				 listDTO.add(dto);
			  }//for
			  
			return listDTO;
		}//method
	}//class

