package com.github.dge.es.dao;

import com.github.dge.es.model.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-08 21:09
 */
@Repository
public interface GoodsRepository extends ElasticsearchRepository<Goods, String> {

}
