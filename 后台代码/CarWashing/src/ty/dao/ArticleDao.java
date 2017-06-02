package ty.dao;

import java.util.List;

import ty.entity.Article;


public interface ArticleDao {

	boolean insertArticle(Article article);//添加一篇文章

	List<Article> findAllArticle();//在界面中显示所有的文章
	
	
	
}
