package com.bootdo.system.dao;

import com.bootdo.system.domain.QuoteDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-23 21:50:38
 */
@Mapper
public interface QuoteDao {

	QuoteDO get(Long id);
	
	List<QuoteDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QuoteDO quote);
	
	int update(QuoteDO quote);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchSave(List<QuoteDO> list);
}
