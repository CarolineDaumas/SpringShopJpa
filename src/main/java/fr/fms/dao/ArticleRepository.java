package fr.fms.dao;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {
	public Optional<Article> findById(Long id);
	public List<Article> findByBrand (String brand);
	public List<Article> findByBrandContains (String brand);
	public List<Article> findByBrandAndPrice (String brand, double price);
	public List<Article> findByBrandAndPriceGreaterThan(String brand, double price);
	
	@Query ("select A from Article A where A.brand like %:x% and A.price> :y")
	public List<Article> searchArticles(@Param("x") String kw, @Param("y")double price);
	
	public List<Article> findByCategoryId (Long categoryId);
	public List<Article> findByBrandAndDescription(String brand, String description);
	public void deleteById (Long id);
	
	public List<Article> findAll();
	
	
}
