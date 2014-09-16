package com.dhl.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dhl.domain.Led;

@Repository
public class LedDao extends BaseDao<Led> {
	
	
	public List<Led> getLedList(int pos)
	{
		String hql = "from Led where pos = "+pos;
    	return find(hql);
	}
}
