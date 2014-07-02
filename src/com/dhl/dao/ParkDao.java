package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.Park;

@Repository
public class ParkDao extends BaseDao<Park> {
	
	public List<Park> getAllPark()
	{
		String hql = "from Park";
    	return find(hql);
	}
}
