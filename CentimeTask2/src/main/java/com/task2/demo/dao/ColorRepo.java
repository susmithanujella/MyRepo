package com.task2.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task2.demo.model.Color;

import java.util.List;

public interface ColorRepo extends JpaRepository<Color,Integer>
{
	List<Color> getColorsByParentid(int pid);
}
