package fr.fms.business;

import java.util.List;
import java.util.Optional;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface EShopBusiness {

	public List <Article> getListArticles();

	Article addArticles(Article article);
	
	public List<Article> getArticlesByCategory (Long categoryId);

	Category findCategories(String name);

	void removeById(Long id);

	Optional<Article> findArticle(Long id);

	Category addCategories(Category category);

	void removeCategoryById(Long id);

	Optional<Category> findCategory(Long id);

	List<Category> getListCategories();
}
