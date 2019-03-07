package com.nexters.project.dao;

import java.util.ArrayList;

public interface OptDao {
	
	public ArrayList<Integer> listDao(int cId);
	public void writeDao(int cId, int option);
}
