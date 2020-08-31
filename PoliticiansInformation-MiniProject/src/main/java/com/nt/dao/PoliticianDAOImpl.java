package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.nt.bo.PoliticianBO;

public class PoliticianDAOImpl implements PoliticianDAO {
	private static final String GET_POLITICIANS_BY_DESGS="SELECT SNO,NAME,AGE,POSITION,STATE,QUALIFICATION,SAL FROM POLITICIANS_INFO WHERE POSITION IN(?,?,?) ORDER BY SNO";
	private  DataSource ds;
	

	public PoliticianDAOImpl(DataSource ds) {
		this.ds = ds;
	}


	@Override
	public List<PoliticianBO> getAllPoliticiansByDesgs(String desg1, String desg2, String desg3) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PoliticianBO> listBO=null;
		PoliticianBO bo=null;
		try {
			//get Pooled jdbc con object
			con=ds.getConnection();
			//create PreparedStatement object
			ps=con.prepareStatement(GET_POLITICIANS_BY_DESGS);
			//set values to query params
			ps.setString(1,desg1); ps.setString(2,desg2); ps.setString(3, desg3);
			//execute the query
			rs=ps.executeQuery();
			//convert ResultSet object records to ListBO
			listBO=new ArrayList();
			while(rs.next()) {
				bo=new PoliticianBO();
				//copy each record of ResultSet obj to BO class object
				bo.setSno(rs.getInt(1));  // here simple int value given by rs.getInt(1) is automatically converted into Integer wrapper obj by using autoboxing feature (java 5)
			   bo.setName(rs.getString(2));
			   bo.setAge(rs.getInt(3));
			   bo.setPosition(rs.getNString(4));
			   bo.setState(rs.getString(5));
			   bo.setQualification(rs.getString(6));
			   bo.setSal(rs.getFloat(7));  //auto boxing
			   //add each obj of BO class to List Collection
			   listBO.add(bo);
			}//while
		}//try
		catch(SQLException se) { //known exception
			se.printStackTrace();
			throw se;  //exception rethrowing..
		}
		catch(Exception e) {  //unknow exception
			e.printStackTrace();
			throw e;  //exeception rethrowing
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
				throw se;  //exeception rethrowing
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
				throw se;  //exeception rethrowing
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
				throw se;  //exeception rethrowing
			}
		}//finally
		
		return listBO;
	}//getEmpsByDesgs(-,-,-)

}//class

