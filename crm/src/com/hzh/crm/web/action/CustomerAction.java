package com.hzh.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;

import com.hzh.crm.domain.Customer;
import com.hzh.crm.domain.PageBean;
import com.hzh.crm.service.CustomerService;
import com.hzh.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户Action类
 * 
 * @author ken
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	// 注入Service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 使用set方法的方式接收数据
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 文件上传提供的三个属性 此处的upload名字与input的name属性一致
	 */
	private String uploadFileName; // 文件名称
	private File upload; // 上传文件
	private String uploadContentType; // 文件类型

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 跳转到新增客户页面的方法 saveUI
	 */
	public String saveUI() {
		// 查询字典数据
		return "saveUI";
	}

	/**
	 * 添加客户到数据库的方法 saveCustomer
	 * @throws IOException 
	 */
	public String saveCustomer() throws IOException {
		// 上传图片
		if (upload != null) {
			// 文件上传
			// 设置文件上传路径
			String path = "/Users/ken/Desktop/Main/Program/java/FileUpload";
			// 一个目录下存放的相同文件名: 随机文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// 一个目录下存放的文件过多，目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			// 创建目录:
			String url = path+realPath;
			File file = new File(url);
			if(!file.exists()) {
				file.mkdirs();
			}
			// 文件上传
			File destFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, destFile);
			
			// 设置image的属性的值:
			customer.setCust_image(url + "/" + uuidFileName);
			
		}
		// 保存客户
		customerService.save(customer);
		return SUCCESS;
	}

	/**
	 * 分页查询客户的方法 findAll
	 */
	public String findAll() {
		// 接收参数: 分页参数

		// 最好使用DetachedCriteria对象(条件查询-带分页)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 调用业务层查询
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

}
