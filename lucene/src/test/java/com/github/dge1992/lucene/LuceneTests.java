package com.github.dge1992.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

/**
 * 
 * @author 董感恩
 * @date 2020-05-13 11:12:12
 * @desc Lucene测试
 **/
@SpringBootTest
class LuceneTests {

    private Directory directory;

    private IndexReader indexReader;

    private IndexSearcher indexSearcher;

    @BeforeEach
    public void setUp() throws IOException {
        //索引存放的位置，设置在当前目录中
        directory = FSDirectory.open(Paths.get("./index"));

        //创建索引的读取器
        indexReader = DirectoryReader.open(directory);

        //创建一个索引的查找器，来检索索引库
        indexSearcher = new IndexSearcher(indexReader);
    }

    @AfterEach
    public void tearDown() throws Exception {
        indexReader.close();
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:26:14
     * @desc 执行查询
     **/
    public void executeQuery(Query query) throws IOException {

        TopDocs topDocs = indexSearcher.search(query, 100);

        //打印查询到的记录数
        System.out.println("总共查询到" + topDocs.totalHits + "个文档");
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

            //取得对应的文档对象
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println("id：" + document.get("id"));
            System.out.println("title：" + document.get("title"));
            System.out.println("content：" + document.get("content"));
        }
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:26:37
     * @desc 查询分词
     **/
    public void printAnalyzerDoc(Analyzer analyzer, String text) throws IOException {

        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
        } finally {
            tokenStream.close();
            analyzer.close();
        }
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:13:25
     * @desc 添加文档
     **/
    @Test
    public void indexWriterTest() throws IOException {
        long start = System.currentTimeMillis();

        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        //创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

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

    /**
     * @author 董感恩
     * @date 2020-05-13 13:28:16
     * @desc 删除文档
     **/
    @Test
    public void deleteDocumentsTest() throws IOException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        //创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        // 删除title中含有关键词“Spark”的文档
        long count = indexWriter.deleteDocuments(new Term("title", "Spark"));

        //  除此之外IndexWriter还提供了以下方法：
        // DeleteDocuments(Query query):根据Query条件来删除单个或多个Document
        // DeleteDocuments(Query[] queries):根据Query条件来删除单个或多个Document
        // DeleteDocuments(Term term):根据Term来删除单个或多个Document
        // DeleteDocuments(Term[] terms):根据Term来删除单个或多个Document
        // DeleteAll():删除所有的Document

        //使用IndexWriter进行Document删除操作时，文档并不会立即被删除，而是把这个删除动作缓存起来，当IndexWriter.Commit()或IndexWriter.Close()时，删除操作才会被真正执行。

        indexWriter.commit();
        indexWriter.close();

        System.out.println("删除数量:" + count);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:31:27
     * @desc 更新文档
     **/
    @Test
    public void updateDocumentTest() throws IOException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        //创建索引写入配置
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //创建索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        Document doc = new Document();

        int id = 1;

        doc.add(new IntPoint("id", id));
        doc.add(new StringField("title", "Mysql", Field.Store.YES));
        doc.add(new TextField("content", "Mysql是目前最流行的数据库", Field.Store.YES));
        doc.add(new StoredField("id", id));

        long count = indexWriter.updateDocument(new Term("id", "1"), doc);
        System.out.println("更新文档数量:" + count);
        indexWriter.close();
    }

    @Test
    public void termQueryTest() throws IOException {

        String searchField = "title";
        //这是一个条件查询的api，用于添加条件
        TermQuery query = new TermQuery(new Term(searchField, "Mysql"));//Spark

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    @Test
    public void BooleanQueryTest() throws IOException {

        String searchField1 = "title";
        String searchField2 = "content";
        Query query1 = new TermQuery(new Term(searchField1, "Spark"));
        Query query2 = new TermQuery(new Term(searchField2, "Apache"));
        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        // BooleanClause用于表示布尔查询子句关系的类，
        // 包 括：
        // BooleanClause.Occur.MUST，
        // BooleanClause.Occur.MUST_NOT，
        // BooleanClause.Occur.SHOULD。
        // 必须包含,不能包含,可以包含三种.有以下6种组合：
        //
        // 1．MUST和MUST：取得连个查询子句的交集。
        // 2．MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果。
        // 3．SHOULD与MUST_NOT：连用时，功能同MUST和MUST_NOT。
        // 4．SHOULD与MUST连用时，结果为MUST子句的检索结果,但是SHOULD可影响排序。
        // 5．SHOULD与SHOULD：表示“或”关系，最终检索结果为所有检索子句的并集。
        // 6．MUST_NOT和MUST_NOT：无意义，检索无结果。

        builder.add(query1, BooleanClause.Occur.SHOULD);
        builder.add(query2, BooleanClause.Occur.SHOULD);

        BooleanQuery query = builder.build();

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:46:05
     * @desc 匹配前缀
     **/
    @Test
    public void prefixQueryTest() throws IOException {
        String searchField = "title";
        Term term = new Term(searchField, "Spar");
        Query query = new PrefixQuery(term);

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:47:31
     * @desc 短语搜索
     **/
    @Test
    public void phraseQueryTest() throws IOException {

        String searchField = "content";
        String query1 = "apache";
        String query2 = "spark";

        PhraseQuery.Builder builder = new PhraseQuery.Builder();
        builder.add(new Term(searchField, query1));
        builder.add(new Term(searchField, query2));
        builder.setSlop(0);
        PhraseQuery phraseQuery = builder.build();

        //执行查询，并打印查询到的记录数
        executeQuery(phraseQuery);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:48:50
     * @desc 相近词搜索
     **/
    @Test
    public void fuzzyQueryTest() throws IOException {

        String searchField = "content";
        Term t = new Term(searchField, "大规模");
        Query query = new FuzzyQuery(t);

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:50:12
     * @desc 通配符搜索
     **/
    @Test
    public void wildcardQueryTest() throws IOException {
        String searchField = "content";
        Term term = new Term(searchField, "大*规模");
        Query query = new WildcardQuery(term);

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:51:35
     * @desc 分词查询
     **/
    @Test
    public void queryParserTest() throws IOException, ParseException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        String searchField = "content";

        //指定搜索字段和分析器
        QueryParser parser = new QueryParser(searchField, analyzer);

        //用户输入内容
        Query query = parser.parse("计算的引擎");

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:52:53
     * @desc 多个 Field 分词查询
     **/
    @Test
    public void multiFieldQueryParserTest() throws IOException, ParseException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        String[] filedStr = new String[]{"title", "content"};

        //指定搜索字段和分析器
        QueryParser queryParser = new MultiFieldQueryParser(filedStr, analyzer);

        //用户输入内容
        Query query = queryParser.parse("Spark");

        //执行查询，并打印查询到的记录数
        executeQuery(query);
    }

    /**
     * @author 董感恩
     * @date 2020-05-13 13:55:31
     * @desc 中文分词器
     **/
    @Test
    public void AnalyzerTest() throws IOException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = null;
        String text = "Apache Spark 是专为大规模数据处理而设计的快速通用的计算引擎";

        analyzer = new IKAnalyzer();//IKAnalyzer 中文分词
        printAnalyzerDoc(analyzer, text);
        System.out.println();

        /*analyzer = new ComplexAnalyzer();//MMSeg4j 中文分词
        printAnalyzerDoc(analyzer, text);
        System.out.println();*/

        analyzer = new SmartChineseAnalyzer();//Lucene 中文分词器
        printAnalyzerDoc(analyzer, text);
    }

    @Test
    public void HighlighterTest() throws IOException, ParseException, InvalidTokenOffsetsException {
        //Analyzer analyzer = new StandardAnalyzer(); // 标准分词器，适用于英文
        //Analyzer analyzer = new SmartChineseAnalyzer();//中文分词
        //Analyzer analyzer = new ComplexAnalyzer();//中文分词
        //Analyzer analyzer = new IKAnalyzer();//中文分词

        Analyzer analyzer = new IKAnalyzer();//中文分词

        String searchField = "content";
        String text = "Apache Spark 大规模数据处理";

        //指定搜索字段和分析器
        QueryParser parser = new QueryParser(searchField, analyzer);

        //用户输入内容
        Query query = parser.parse(text);

        TopDocs topDocs = indexSearcher.search(query, 100);

        // 关键字高亮显示的html标签，需要导入lucene-highlighter-xxx.jar
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

            //取得对应的文档对象
            Document document = indexSearcher.doc(scoreDoc.doc);

            // 内容增加高亮显示
            TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(document.get("content")));
            String content = highlighter.getBestFragment(tokenStream, document.get("content"));

            System.out.println(content);
        }

    }
}
