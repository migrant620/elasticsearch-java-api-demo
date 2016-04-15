package com.myelasticsearch.samples;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.metrics.MetricsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinBuilder;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksBuilder;
import org.elasticsearch.search.aggregations.metrics.percentiles.PercentilesBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStatsBuilder;

import com.myelasticsearch.utils.ESUtils;

public class Aggregations {
	private static String index_alias = "index_alias_company";
	private static String type_name = "type_company";
	
	public static void execute(){
		avgQuery();
		minQuery();
		maxQuery();
		extendedStatsQuery();
		valueCountQuery();
		percentileQuery();
		percentileRankQuery();
		rangeQuery();
		histogramQuery();
		dateHistogramQuery();
	}
	
	public static void avgQuery() {
		System.out.println("avgQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		AvgBuilder agg = AggregationBuilders.avg("avg_num").field("LastClose");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	public static void minQuery() {
		System.out.println("minQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		MinBuilder agg = AggregationBuilders.min("min_num").field("LastClose");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}

	public static void maxQuery() {
		System.out.println("maxQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		MaxBuilder agg = AggregationBuilders.max("max_num").field("LastClose");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	public static void extendedStatsQuery() {
		System.out.println("extendedStatsQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		ExtendedStatsBuilder agg = AggregationBuilders.extendedStats("extended_stats_num").field("LastClose");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}

	@SuppressWarnings("rawtypes")
	public static void valueCountQuery() {
		System.out.println("valueCountQuery......");
		TransportClient client = ESUtils.getTransportClient();
				
		MetricsAggregationBuilder agg = AggregationBuilders.count("value_count").field("LastClose");
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	public static void percentileQuery() {
		System.out.println("percentileQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		PercentilesBuilder agg = AggregationBuilders.percentiles("percentile_num").field("LastClose").percentiles(95,99,99.9);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}

	public static void percentileRankQuery() {
		System.out.println("percentileRankQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		PercentileRanksBuilder agg = AggregationBuilders.percentileRanks("percentile_rank_num").field("LastClose").percentiles(3,5);
		
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(10)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	@SuppressWarnings("rawtypes")
	public static void rangeQuery() {
		System.out.println("rangeQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		AggregationBuilder agg = AggregationBuilders.range("agg").field("LastClose").addUnboundedTo(3).addRange(3, 5).addUnboundedFrom(5);  
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(0)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	@SuppressWarnings("rawtypes")
	public static void histogramQuery() {
		System.out.println("histogramQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		AggregationBuilder agg = AggregationBuilders.histogram("agg").field("LastClose").interval(2);
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(0)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	@SuppressWarnings("rawtypes")
	public static void dateHistogramQuery() {
		System.out.println("dateHistogramQuery......");
		TransportClient client = ESUtils.getTransportClient();
		
		AggregationBuilder agg = AggregationBuilders.dateHistogram("agg").field("ListedDate").interval(DateHistogramInterval.YEAR).minDocCount(1);
		SearchResponse res = client.prepareSearch(index_alias)
				.setTypes(type_name)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.addAggregation(agg)
				.setFrom(0)
				.setSize(0)
				.execute().actionGet();
		System.out.println(res);
		
		client.close();
	}
	
	public static void main(String[] args){
		execute();
	}
}
