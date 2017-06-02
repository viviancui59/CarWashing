package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ty.dao.ArticleDao;
import ty.dbutil.DBManager;
import ty.entity.Article;

public class ArticleDaoImpl implements ArticleDao {

	private DBManager dbManager = new DBManager();
	@Override
	public boolean insertArticle(Article article) {

		String sql="insert into article values(null,?,?,?,now())";
		
		return dbManager.execUpdate(sql,article.getTitle(),article.getContent(),article.getAuthor())>0;
	
	}

	@Override
	public List<Article> findAllArticle() {
		String sql ="select * from article";

		  ResultSet rs= dbManager.execQuery(sql);
		   List<Article> list=new ArrayList<Article>();
		   try {
			while(rs.next()){
				Article article=new Article();
				article.setArticleid(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setContent(rs.getString(3));
				article.setAuthor(rs.getString(4));
				article.setTime(rs.getString(5));

				   list.add(article);
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		     dbManager.closeConnection();
			}
		   
			return list;
	}
}
