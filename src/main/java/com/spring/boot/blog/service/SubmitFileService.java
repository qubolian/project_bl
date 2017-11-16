package com.spring.boot.blog.service;

import java.util.List;

import com.spring.boot.blog.domain.SubmitFile;

public interface SubmitFileService {
	/**
	 * 保存大纲和教学进度表
	 * @param SubmitFile
	 * @return
	 */
	SubmitFile saveSubmitFile(SubmitFile SubmitFile);
	
	/**
	 * 删除大纲和教学进度表
	 * @param SubmitFile
	 * @return
	 */
	void removeSubmitFile(Long id);
	
	/**
	 * 删除列表里面的大纲和教学进度表
	 * @param SubmitFile
	 * @return
	 */
	void removeSubmitFilesInBatch(List<SubmitFile> SubmitFiles);
	
	/**
	 * 更新大纲和教学进度表
	 * @param SubmitFile
	 * @return
	 */
	SubmitFile updateSubmitFile(SubmitFile SubmitFile);
	
	/**
	 * 根据id获取大纲和教学进度表
	 * @param SubmitFile
	 * @return
	 */
	SubmitFile getSubmitFileById(Long id);
	
	/**
	 * 获取大纲和教学进度表列表
	 * @param SubmitFile
	 * @return
	 */
	List<SubmitFile> listSubmitFiles();
	
}
