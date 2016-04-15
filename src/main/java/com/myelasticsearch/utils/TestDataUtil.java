package com.myelasticsearch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class TestDataUtil {

	public static List<Map<String, Object>> getIndexBulkPostData() throws ParseException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String,Object> map = Maps.newHashMap();
//		map.put("_id", 6);
		map.put("CompanyCode", 6);
		map.put("SecuCode", "000002");
		map.put("ChiName", "万科企业股份有限公司");
		map.put("SecuAbbr", "万  科Ａ");
		map.put("EngName", "China Vanke Co.,Ltd.");
		map.put("ChiSpelling", "WKA");
		map.put("SecuMarket", "90");
		map.put("ListedDate", dateformat.parse("1991-01-29"));
		map.put("LastClose", 24.43);
		list.add(map);
		
		map = Maps.newHashMap();
//		map.put("_id", 23);
		map.put("CompanyCode", 23);
		map.put("SecuCode", "000008");
		map.put("ChiName", "神州高铁技术股份有限公司");
		map.put("SecuAbbr", "神州高铁");
		map.put("EngName", "China High-speed Railway Technology Co.,Ltd.");
		map.put("ChiSpelling", "SZGT");
		map.put("SecuMarket", "90");
		map.put("ListedDate", dateformat.parse("1992-05-07"));
		map.put("LastClose", 10.68);
		list.add(map);
		
		map = Maps.newHashMap();
//		map.put("_id", 516);
		map.put("CompanyCode", 516);
		map.put("SecuCode", "000959");
		map.put("ChiName", "北京首钢股份有限公司");
		map.put("SecuAbbr", "首钢股份");
		map.put("EngName", "Beijing Shougang Co.,Ltd.");
		map.put("ChiSpelling", "SGGF");
		map.put("SecuMarket", "90");
		map.put("ListedDate", dateformat.parse("1999-12-16"));
		map.put("LastClose", 4.98);
		list.add(map);
		
		map = Maps.newHashMap();
//		map.put("_id", 1062);
		map.put("CompanyCode", 1062);
		map.put("SecuCode", "600052");
		map.put("ChiName", "浙江广厦股份有限公司");
		map.put("SecuAbbr", "浙江广厦");
		map.put("EngName", "Zhejiang Guangsha Co.,Ltd.");
		map.put("ChiSpelling", "ZJGS");
		map.put("SecuMarket", "83");
		map.put("ListedDate", dateformat.parse("1997-04-15"));
		map.put("LastClose", 6.97);
		list.add(map);
		
		map = Maps.newHashMap();
//		map.put("_id", 42396);
		map.put("CompanyCode", 42396);
		map.put("SecuCode", "601818");
		map.put("ChiName", "中国光大银行股份有限公司");
		map.put("SecuAbbr", "光大银行");
		map.put("EngName", "China Everbright Bank Company Limited");
		map.put("ChiSpelling", "GDYH");
		map.put("SecuMarket", "83");
		map.put("ListedDate", dateformat.parse("2010-08-18"));
		map.put("LastClose", 3.75);
		list.add(map);
		
		return list;
	}
}
