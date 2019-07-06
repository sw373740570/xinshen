package com.bootdo.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-23 21:50:38
 */
public class QuoteDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//品目编码
	private String itemCode;
	//品目名
	private String name;
	//供应商
	private String supplier;
	//RMB单价
	private BigDecimal rmbPrice;
	//美金单价
	private BigDecimal usdPrice;
	//制造商料号
	private String manufacturerItemCode;
	//报价日期
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date quoteDate;
	//是否删除
	private Integer delFlag;
	//创建日期
	private Date createTime;
	//创建者
	private Long createBy;
	//修改日期
	private Date updateTime;
	//修改者
	private Long updateBy;

	private String remark;

	private String sourceQuotation;

	private String type;
	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：品目编码
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * 获取：品目编码
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * 设置：品目名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：品目名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：供应商
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	/**
	 * 获取：供应商
	 */
	public String getSupplier() {
		return supplier;
	}
	/**
	 * 设置：RMB单价
	 */
	public void setRmbPrice(BigDecimal rmbPrice) {
		this.rmbPrice = rmbPrice;
	}
	/**
	 * 获取：RMB单价
	 */
	public BigDecimal getRmbPrice() {
		return rmbPrice;
	}
	/**
	 * 设置：美金单价
	 */
	public void setUsdPrice(BigDecimal usdPrice) {
		this.usdPrice = usdPrice;
	}
	/**
	 * 获取：美金单价
	 */
	public BigDecimal getUsdPrice() {
		return usdPrice;
	}
	/**
	 * 设置：制造商料号
	 */
	public void setManufacturerItemCode(String manufacturerItemCode) {
		this.manufacturerItemCode = manufacturerItemCode;
	}
	/**
	 * 获取：制造商料号
	 */
	public String getManufacturerItemCode() {
		return manufacturerItemCode;
	}
	/**
	 * 设置：报价日期
	 */
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	/**
	 * 获取：报价日期
	 */
	public Date getQuoteDate() {
		return quoteDate;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：修改日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：修改者
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：修改者
	 */
	public Long getUpdateBy() {
		return updateBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSourceQuotation() {
		return sourceQuotation;
	}

	public void setSourceQuotation(String sourceQuotation) {
		this.sourceQuotation = sourceQuotation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
