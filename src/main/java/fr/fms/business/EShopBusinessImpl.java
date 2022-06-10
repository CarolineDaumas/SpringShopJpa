package fr.fms.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@Service
public class EShopBusinessImpl implements EShopBusiness {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Article> getListArticles() {
		return articleRepository.findAll() ;
	}

	@Override
	public Article addArticles(Article article) {
		return articleRepository.save(article);
		
	}

	@Override
	public Category findCategories (String name) {
		Category category= categoryRepository.findByName(name);
		return category;
}
	
	@Override
	public void removeById (Long id) {
		articleRepository.deleteById(id);
	}
	
	@Override
	public Optional<Article> findArticle(Long id) {
		return articleRepository.findById(id);
	}
	
	@Override
	public Category addCategories (Category category) {
		categoryRepository.save(category);
		return category;
	}
	
	@Override
	public void removeCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}
	
	@Override
	public Optional<Category> findCategory(Long id) {
		return categoryRepository.findById(id);
	}
	
	@Override
	public List<Category>getListCategories(){
		return categoryRepository.findAll();
		
	
}

	@Override
	public List<Article> getArticlesByCategory(Long categoryId) {
		return articleRepository.findByCategoryId(categoryId);
	}

	@Override
	public Page<Article> findAllByPage(Pageable pageable) {
		
		return articleRepository.findAll(pageable);
	}
	
	
}
