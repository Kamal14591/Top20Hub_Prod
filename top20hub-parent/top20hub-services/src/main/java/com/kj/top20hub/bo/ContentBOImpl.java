package com.kj.top20hub.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kj.top20hub.dao.ContentDAO;
import com.kj.top20hub.dto.Content;


@Service
public class ContentBOImpl implements ContentBO {

	@Autowired
	private ContentDAO contentdao; 
	
	@Override
	@Transactional
	public List<Content> getAllContent() {
		return contentdao.getAllContent();
	}

	@Override
	@Transactional
	public Content getContent(int id) {
		return contentdao.getContent(id);
	}

	@Override
	@Transactional
	public int createContent(Content content) {
		return contentdao.createContent(content);
	}

	@Override
	public void updateContent(Content content) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Content deleteContent(int id) {
		return contentdao.deleteContent(id);
	}

}
