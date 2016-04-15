package com.myelasticsearch.samples;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.myelasticsearch.utils.ESUtils;

public class Query {
//	private static String index_name = "index_company";
	private static String index_alias = "index_alias_company";
	private static String type_name = "type_company";
	
	public static void execute(){
		matchAllQuery();
		matchQuery();
		multiMatchQuery();
		commonTermQuery();
		termQuery();
		rangeQuery();
		existsQuery();
		prefixQuery();
		wildcardQuery();
		regexpQuery();
		fuzzyQuery();
		typeQuery();
		idsQuery();
		constantScoreQuery();
		boolQuery();
		disMaxQuery();
	}
	
	public static void matchAllQuery() {
		System.out.println("matchAllQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()){
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void matchQuery() {
		System.out.println("matchQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.matchQuery("LastClose", 4.98);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()){
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void multiMatchQuery() {
		System.out.println("multiMatchQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		String[] queryFields = {"ChiName", "SecuAbbr"};
		QueryBuilder qb = QueryBuilders.multiMatchQuery("北京首钢", queryFields);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) { 
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void commonTermQuery() {
		System.out.println("commonTermQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.commonTermsQuery("EngName", "Beijing");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void termQuery() {
		System.out.println("termQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.termQuery("SecuCode", "000002");
				
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void rangeQuery() {
		System.out.println("rangeQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders
				.rangeQuery("LastClose")
				.gte(1)
				.lt(10);		
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void existsQuery() {
		System.out.println("existsQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.existsQuery("CompanyCode");
		//QueryBuilder qb = QueryBuilders.prefixQuery("name", "prefix");
		//QueryBuilder qb = QueryBuilders.wildcardQuery("user", "k?mc*");
		//QueryBuilder qb = QueryBuilders.regexpQuery("user", "k.*y");
		//QueryBuilder qb = QueryBuilders.fuzzyQuery("name", "kimzhy");
		//QueryBuilder qb = QueryBuilders.typeQuery("my_type");
		//QueryBuilder qb = QueryBuilders.idsQuery("my_type","type2").addIds("1","2","5");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void prefixQuery() {
		System.out.println("prefixQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.prefixQuery("SecuCode", "000");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void wildcardQuery() {
		System.out.println("wildcardQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.wildcardQuery("ChiSpelling", "p?y*");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void regexpQuery() {
		System.out.println("regexpQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.regexpQuery("ChiSpelling", "p.*h");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void fuzzyQuery() {
		System.out.println("fuzzyQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.fuzzyQuery("ChiSpelling", "payy");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void typeQuery() {
		System.out.println("typeQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.typeQuery(type_name);

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void idsQuery() {
		System.out.println("idsQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		QueryBuilder qb = QueryBuilders.idsQuery(index_alias,type_name).addIds("AVQUGGZFLd19m5R4UVpv","AVQUGGbRLd19m5R4UVp0");

		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void constantScoreQuery() {
		System.out.println("constantScoreQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("EngName", "china")).boost(2.0f);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void boolQuery() {
		System.out.println("boolQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders.boolQuery()
				.must(QueryBuilders.termQuery("EngName","china"))
				.mustNot(QueryBuilders.termQuery("EngName","company"))
				.should(QueryBuilders.termQuery("EngName", "railway"))
				.filter(QueryBuilders.termQuery("EngName","technology"));
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()){
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void disMaxQuery() {
		System.out.println("disMaxQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		QueryBuilder qb = QueryBuilders
				.disMaxQuery()
				.add(QueryBuilders.termQuery("EngName", "china"))
				.add(QueryBuilders.termQuery("EngName", "zhejiang")).boost(1.2f).tieBreaker(0.7f);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(qb)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		for (SearchHit hit: res.getHits().getHits()) { 
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
	}
	
	public static void main(String[] args){
		execute();
	}
}
