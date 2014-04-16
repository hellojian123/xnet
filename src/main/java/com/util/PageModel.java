package com.util;

import java.util.List;
/**
 * 分页模型
 * @author Administrator
 *
 * @param <T>
 */
public class PageModel<T> {

	private List<T> result;
	
	private int maxPage;
	
	private int curPage;
	
	private int count;
	
	private T obj;
	
	public PageModel() {
		super();
	}
	
	public PageModel(List<T> result, int maxPage) {
		super();
		this.result = result;
		this.maxPage = maxPage;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
