package fr.fms.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface EShopBusiness {

	public List <Article> getListArticles();

	public Article addArticles(Article article);
	
	public List<Article> getArticlesByCategory (Long categoryId);

	public Category findCategories(String name);

	public void removeById(Long id);

	public Optional<Article> findArticle(Long id);

	public Category addCategories(Category category);

	public void removeCategoryById(Long id);

	public Optional<Category> findCategory(Long id);

	public List<Category> getListCategories();
	
	public Page<Article> findAllByPage(Pageable pageable);
}
