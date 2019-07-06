package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.QuoteDO;
import com.bootdo.system.service.QuoteService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-23 21:50:38
 */
 
@Controller
@RequestMapping("/system/quote")
public class QuoteController {
	@Autowired
	private QuoteService quoteService;
	
	@GetMapping()
	@RequiresPermissions("system:quote:quote")
	String Quote(){
	    return "system/quote/quote";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:quote:quote")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QuoteDO> quoteList = quoteService.list(query);
		int total = quoteService.count(query);
		PageUtils pageUtils = new PageUtils(quoteList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:quote:add")
	String add(){
	    return "system/quote/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:quote:edit")
	String edit(@PathVariable("id") Long id,Model model){
		QuoteDO quote = quoteService.get(id);
		model.addAttribute("quote", quote);
	    return "system/quote/edit";
	}
	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("system:quote:add")
//	public R save( QuoteDO quote){
//		if(quoteService.save(quote)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:quote:edit")
	public R update( QuoteDO quote){
		quoteService.update(quote);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除报价")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:quote:remove")
	public R remove( Long id){
		if(quoteService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("批量删除报价")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:quote:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		quoteService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping( "/import")
	@RequiresPermissions("system:quote:import")
	public String importData(){
		return "system/quote/import";
	}

	/**
	 * 保存
	 */
	@Log("导入报价")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:quote:import")
	public R save(MultipartFile file){
		String fileName = file.getOriginalFilename();
		// 判断文件是.xlsx或者xls结尾。如果不是则抛出提示信息
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!"xlsx".equals(suffix) && !"xls".equals(suffix)) {
			return R.error("请上传Excel文件");
		}
		if(quoteService.save(file)>0){
			return R.ok();
		}
		return R.error();
	}

	//@ResponseBody
	@GetMapping("/export")
	@RequiresPermissions("system:quote:export")
	public void export(@RequestParam("itemCode") String itemCode, @RequestParam("manufacturerItemCode") String manufacturerItemCode, HttpServletResponse response){
		Map<String,Object> param = new HashMap<>();
		if (StringUtils.isNotBlank(itemCode)) {
			param.put("itemCode",itemCode);
		}
		if (StringUtils.isNotBlank(manufacturerItemCode)) {
			param.put("manufacturerItemCode", manufacturerItemCode);
		}
		quoteService.export(param, response);
	}

	@GetMapping( "/quotationPage/{id}")
	@RequiresPermissions("system:quote:quotation")
	public String quotationPage(@PathVariable("id") Long id,Model model){
		model.addAttribute("id", id);
		return "system/quote/quotation";
	}

	//@Log("上传原始报价单")
	@ResponseBody
	@PostMapping("/quotation")
	@RequiresPermissions("system:quote:quotation")
	public R quotation(MultipartFile file, Long id){
		String fileName = file.getOriginalFilename();
		// 判断文件是.xlsx或者xls结尾。如果不是则抛出提示信息
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!"jpg".equals(suffix.toLowerCase()) && !"jpeg".equals(suffix.toLowerCase()) && !"png".equals(suffix.toLowerCase())) {
			return R.error("请上传jpg或jpeg或png图片文件");
		}
		if(quoteService.quotation(id, file)>0){
			return R.ok();
		}
		return R.error();
	}
}
