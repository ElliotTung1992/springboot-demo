package com.github.dge1992.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author 董感恩
 * @date 2020-05-25 15:25
 * @desc 写
 */
public class LuceneWrite {

    /**
     * @author 董感恩
     * @date 2020-05-13 13:13:25
     * @desc 添加文档
     **/
    @Test
    public void indexWriterTest() throws IOException {
        long start = System.currentTimeMillis();

        Directory directory = FSDirectory.open(Paths.get("./index"));

        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        //创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //文件不合并
        indexWriterConfig.setUseCompoundFile(false);

        //创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //创建Document对象，存储索引
        Document doc = new Document();

        int id = 1;

        //将字段加入到doc中
        doc.add(new IntPoint("id", id));
        doc.add(new StringField("title", "Spark", Field.Store.YES));
        doc.add(new TextField("content", "Apache Spark 是专为大规模数据处理而设计的快速通用的计算引擎", Field.Store.YES));
        doc.add(new StoredField("id", id));

        //将doc对象保存到索引库中
        indexWriter.addDocument(doc);

        indexWriter.commit();

        long end = System.currentTimeMillis();
        System.out.println("添加文档花费了" + (end - start) + " 毫秒");
    }
}
