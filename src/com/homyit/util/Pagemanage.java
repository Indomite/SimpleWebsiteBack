package com.homyit.util;

import java.util.List;

public class Pagemanage<T> {
	  	private List<T> list;  //��ǰҳ�б����ݣ����ݿ��ѯ�õ�
	    private int pageNumber;  //��ǰҳ�룬ǰ��ҳ�洫��
	    private int totalRecord;  //�ܼ�¼�������ݿ��ѯ�õ�
	    private int pageSize;    //ÿҳ��ʾ������ǰ��ҳ�洫��
	    
	    public Pagemanage(int pageNumber, int totalRecord, int pageSize) {
	        super();
	        this.pageNumber = pageNumber;
	        this.totalRecord = totalRecord;
	        this.pageSize = pageSize;
	    }

	    public List<T> getList() {
	        return list;
	    }
	    
	    public void setList(List<T> list) {
	        this.list = list;
	    }
	    
	    public int getPageNumber() {     //����ҳ�벻��<1��Ҳ����>totalPage
	        if(pageNumber<1){
	            pageNumber=1;
	        }else if (pageNumber>getTotalPage()) {
	            pageNumber=getTotalPage();
	        }
	        return pageNumber;
	    }
	    public void setPageNumber(int pageNumber) {
	        this.pageNumber = pageNumber;
	    }
	    public int getTotalRecord() {
	        return totalRecord;
	    }
	    public void setTotalRecord(int totalRecord) {
	        this.totalRecord = totalRecord;
	    }
	    public int getPageSize() {
	        return pageSize;
	    }
	    public void setPageSize(int pageSize) {
	        this.pageSize = pageSize;
	    }
	    public int getTotalPage() {
	        return (int) Math.ceil((double)getTotalRecord()/getPageSize());
	    }
	    public int getIndex() {
	        return (getPageNumber()-1)*getPageSize();     //��ҳ��ѯ�������ݷ��ʲ�һ�������getIndex�����������ֵ
	    }                                                 //����getIndex�����е�����getPageNumber��������֤��ҳ����������Χ��
		public void setTotalPage(int totalPage) {
		}
		
}