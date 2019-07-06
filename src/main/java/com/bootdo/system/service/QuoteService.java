package com.bootdo.system.service;

import com.bootdo.common.utils.R;
import com.bootdo.system.domain.QuoteDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-23 21:50:38
 */
public interface QuoteService {
	
	QuoteDO get(Long id);
	
	List<QuoteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QuoteDO quote);

	int save(MultipartFile file);
	
	int update(QuoteDO quote);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    R export(Map<String, Object> param, HttpServletResponse response);

    int quotation(Long id, MultipartFile file);
}
