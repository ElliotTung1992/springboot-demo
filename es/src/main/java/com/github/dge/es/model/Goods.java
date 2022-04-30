package com.github.dge.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-08 21:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "mall")
public class Goods {

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Keyword)
    private String category;
    private BigDecimal price;
    @Field(type = FieldType.Text, index = false)
    private String picture;
}
