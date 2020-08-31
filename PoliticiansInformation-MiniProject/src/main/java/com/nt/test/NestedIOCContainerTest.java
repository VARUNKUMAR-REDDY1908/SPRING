package com.nt.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nt.controller.MainController;
import com.nt.vo.PoliticianVO;

public class NestedIOCContainerTest {

	public static void main(String[] args) {
		ApplicationContext parentCtx=null,childCtx=null;
		MainController controller=null;
		List<PoliticianVO> listVO=null;
		//create parent IOC container
		parentCtx=new ClassPathXmlApplicationContext("com/nt/cfgs/business-beans.xml");
		//create child IOC container
		childCtx=new ClassPathXmlApplicationContext(new String[] {"com/nt/cfgs/presentation-beans.xml"},parentCtx);
		//get Controller class object
		controller=childCtx.getBean("controller",MainController.class);
		//invoke method
		System.out.println("...................................................");
		try {
			listVO=controller.gatherPoliticianByDesgs("PM","CM", "HM");
			//display results
			for(PoliticianVO vo: listVO) {
				System.out.println(vo);
			}
		}//try
		catch(Exception e){
			e.printStackTrace();
		}
		
		//close containers
		((AbstractApplicationContext) parentCtx).close();
		((AbstractApplicationContext) childCtx).close();

	}//main
}//class