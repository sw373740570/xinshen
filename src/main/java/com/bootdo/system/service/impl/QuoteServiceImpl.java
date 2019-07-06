package com.bootdo.system.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.QuoteDao;
import com.bootdo.system.domain.QuoteDO;
import com.bootdo.system.service.QuoteService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class QuoteServiceImpl implements QuoteService {
	@Autowired
	private QuoteDao quoteDao;

	@Autowired
	private BootdoConfig bootdoConfig;
	
	@Override
	public QuoteDO get(Long id){
		return quoteDao.get(id);
	}
	
	@Override
	public List<QuoteDO> list(Map<String, Object> map){
		return quoteDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return quoteDao.count(map);
	}
	
	@Override
	public int save(QuoteDO quote){
		return quoteDao.save(quote);
	}

	@Override
	public int save(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		try {
			List<QuoteDO> list = getData(suffix, file.getInputStream());
			return quoteDao.batchSave(list);
		} catch (IOException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	private List<QuoteDO> getData(String suffix, InputStream inputStream) throws IOException {
		ArrayList<QuoteDO> arrayList = new ArrayList<QuoteDO>();
		// 具体执行导入，可以引入策略模式
		// 解决excel2003和excel2007版本的问题
		if ("xlsx".equals(suffix)) {
			xlsxImp(inputStream, arrayList);
		}
		if ("xls".equals(suffix)) {
			xlsImp(inputStream, arrayList);
		}
		// 万一新增一种新格式，对修改打开了，不符合oo编程规范
		return arrayList;
	}

	private void xlsImp(InputStream inputStream, ArrayList<QuoteDO> arrayList) throws IOException {
		// 初始整个Excel
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		// 获取第一个sheet表
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);
			//整行都为空去掉
			if(row==null) {
				continue;
			}
			QuoteDO quoteDO = new QuoteDO();
			quoteDO.setCreateBy(ShiroUtils.getUserId());
			quoteDO.setCreateTime(new Date());
			HSSFCell cell1 = row.getCell(0);
			if (cell1 != null) {
				quoteDO.setItemCode(cell1.getStringCellValue());
			}
			HSSFCell cell2 = row.getCell(1);
			if (cell2 != null) {
				quoteDO.setName(cell2.getStringCellValue());
			}
			HSSFCell cell3 = row.getCell(2);
			if (cell3 != null) {
				quoteDO.setSupplier(cell3.getStringCellValue());
			}
			HSSFCell cell4 = row.getCell(3);
			if (cell4 != null) {
				quoteDO.setRmbPrice(BigDecimal.valueOf(cell4.getNumericCellValue()));
			}
			HSSFCell cell5 = row.getCell(4);
			if (cell5 != null) {
				quoteDO.setUsdPrice(BigDecimal.valueOf(cell5.getNumericCellValue()));
			}
			HSSFCell cell6 = row.getCell(5);
			if (cell6 != null) {
				quoteDO.setManufacturerItemCode(cell6.getStringCellValue());
			}
			HSSFCell cell7 = row.getCell(6);
			if (cell7 != null) {
				quoteDO.setQuoteDate(HSSFDateUtil.getJavaDate(cell7.getNumericCellValue()));
			}
			arrayList.add(quoteDO);
		}
	}

	private void xlsxImp(InputStream inputStream, ArrayList<QuoteDO> arrayList) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			//整行都为空去掉
			if(row==null) {
				continue;
			}
			QuoteDO quoteDO = new QuoteDO();
			quoteDO.setCreateBy(ShiroUtils.getUserId());
			quoteDO.setCreateTime(new Date());
			XSSFCell cell1 = row.getCell(0);
			if (cell1 != null) {
				quoteDO.setItemCode(cell1.getStringCellValue());
			}
			XSSFCell cell2 = row.getCell(1);
			if (cell2 != null) {
				quoteDO.setName(cell2.getStringCellValue());
			}
			XSSFCell cell3 = row.getCell(2);
			if (cell3 != null) {
				quoteDO.setSupplier(cell3.getStringCellValue());
			}
			XSSFCell cell4 = row.getCell(3);
			if (cell4 != null) {
				quoteDO.setRmbPrice(BigDecimal.valueOf(cell4.getNumericCellValue()));
			}
			XSSFCell cell5 = row.getCell(4);
			if (cell5 != null) {
				quoteDO.setUsdPrice(BigDecimal.valueOf(cell5.getNumericCellValue()));
			}
			XSSFCell cell6 = row.getCell(5);
			if (cell6 != null) {
				quoteDO.setManufacturerItemCode(cell6.getStringCellValue());
			}
			XSSFCell cell7 = row.getCell(6);
			if (cell7 != null) {
				quoteDO.setQuoteDate(HSSFDateUtil.getJavaDate(cell7.getNumericCellValue()));
			}
			arrayList.add(quoteDO);
		}
	}

	@Override
	public int update(QuoteDO quote){
		return quoteDao.update(quote);
	}
	
	@Override
	public int remove(Long id){
		return quoteDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return quoteDao.batchRemove(ids);
	}

	@Override
	public R export(Map<String, Object> param, HttpServletResponse response) {
		//创建工作簿
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet();
		XSSFRow row = sheet.createRow(0);
		//设置样式
		XSSFCellStyle style = xssfWorkbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		Font font = xssfWorkbook.createFont();
		font.setBold(true);
		style.setFont(font);
		//设置标题
		String[] title = {"品目编码","品目名","供应商","RMB单价","美金单价","制造商料号","报价日期"};
		XSSFCell cell;
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		//设置列宽
		sheet.setColumnWidth(0,16*256);
		sheet.setColumnWidth(1,45*256);
		sheet.setColumnWidth(2,28*256);
		sheet.setColumnWidth(3,12*256);
		sheet.setColumnWidth(4,12*256);
		sheet.setColumnWidth(5,24*256);
		sheet.setColumnWidth(6,12*256);
		//设置数据
		List<QuoteDO> list = quoteDao.list(param);
		for (int i = 0; i< list.size(); i++){
			XSSFRow row1 = sheet.createRow(i + 1);
			XSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue(list.get(i).getItemCode());
			cell1 = row1.createCell(1);
			cell1.setCellValue(list.get(i).getName());
			cell1 = row1.createCell(2);
			cell1.setCellValue(list.get(i).getSupplier());
			cell1 = row1.createCell(3);
			cell1.setCellValue(list.get(i).getRmbPrice()==null?0:list.get(i).getRmbPrice().doubleValue());
			cell1 = row1.createCell(4);
			cell1.setCellValue(list.get(i).getUsdPrice()==null?0:list.get(i).getUsdPrice().doubleValue());
			cell1 = row1.createCell(5);
			cell1.setCellValue(list.get(i).getManufacturerItemCode());
			cell1 = row1.createCell(6);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			cell1.setCellValue(list.get(i).getQuoteDate() == null?"":format.format(list.get(i).getQuoteDate()));
		}
		//相应到客户端
		try {
			String fileName = "供应商报价_" + System.currentTimeMillis() + ".xlsx";
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			OutputStream os = response.getOutputStream();
			xssfWorkbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.ok();
	}

	@Override
	public int quotation(Long id, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		try {
			String subPath = DateUtils.format(new Date(),"yyyyMM") + "/";
			FileUtil.uploadFile(file.getBytes(),bootdoConfig.getUploadPath() + subPath, fileName);
			QuoteDO quoteDO = new QuoteDO();
			quoteDO.setId(id);
			quoteDO.setUpdateBy(ShiroUtils.getUserId());
			quoteDO.setUpdateTime(new Date());
			quoteDO.setSourceQuotation("/files/" + subPath + fileName);
			return quoteDao.update(quoteDO);
		} catch (Exception e) {
			return 0;
		}
	}

}
