package com.github.dge.es.service.impl;

import com.github.dge.es.dao.GoodsRepository;
import com.github.dge.es.model.Goods;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-08 21:29
 */
@SpringBootTest
class GoodsServiceImplTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void test(){
        System.out.println("hello world");
    }

    @Test
    void delIndex(){
        boolean del = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("mall")).delete();
        System.out.println(del);
    }

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    void save(){
        Goods goods = new Goods();
        //goods.setId("SvbQ_3wBGFKsW0BlawUe");
        goods.setName("aog");
        goods.setCategory("电子产品");
        goods.setPrice(new BigDecimal("55.66"));
        goods.setPicture("www.alibaba.com");
        goodsRepository.save(goods);
    }

    @Test
    void queryById(){
        Optional<Goods> optionalGoods = goodsRepository.findById("SvbQ_3wBGFKsW0BlawUe");
        optionalGoods.ifPresent(System.out::println);
    }

    @Test
    void findAll(){
        Iterable<Goods> goodsIterable = goodsRepository.findAll();
        for (Goods goods : goodsIterable) {
            System.out.println(goods);
        }
    }

    @Test
    void delete(){
        Goods goods = new Goods();
        goods.setId("S_bX_3wBGFKsW0BlUQUO");
        goodsRepository.delete(goods);
    }

    @Test
    void findPage(){
        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        int page = 0;
        int size = 2;

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Goods> goodsPage = goodsRepository.findAll(pageRequest);
        for (Goods goods : goodsPage) {
            System.out.println(goods);
        }
    }

    @Test
    void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "索");
        NativeSearchQuery query=new NativeSearchQueryBuilder()
                .withQuery(termQueryBuilder)
                .build();
        SearchHits<Goods> searchHits = elasticsearchRestTemplate.search(query, Goods.class);
        for (SearchHit<Goods> searchHit : searchHits) {
            Goods goods = searchHit.getContent();
            System.out.println(goods);
        }
    }
}