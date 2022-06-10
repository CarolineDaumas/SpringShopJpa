package fr.fms;

import java.awt.Menu;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.EShopBusinessImpl;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {
	
	@Autowired
	EShopBusinessImpl eshopBusiness;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		int nbMenu;
		System.out.println("Bonjour et bienvenue dans la console d'administration de l'eShop");
		System.out.println();
		
		Scanner scan= new Scanner(System.in);
		
		do {
			System.out.println();
			System.out.println("Choisisssez une option, saisissez un chiffre");
			menu();
			
			while(scan.hasNextInt()==false)
				scan.next();
			
			nbMenu=scan.nextInt();
			
			switch (nbMenu) {
			
			case 1: displayArticles();
					break;
					
			case 2: addArticle();
					break;

			case 3: removeArticle();
					break;
			
			case 4: updateArticle();
					break;
			
			case 5: displaySingleArticle();
						
				break;
				
			case 6: System.out.println("Gestion des catégories, choisissez une option");
					System.out.println("1 - Ajouter une catégorie");
					System.out.println("2 - Supprimer une catégorie");
					System.out.println("3-  Mettre à jour les catégories");
					System.out.println("4 - Afficher tous les articles d'une catégorie");
					System.out.println("5- Afficher toutes les catégories");
					
					int nbMenu2=scan.nextInt();
					
					switch (nbMenu2) {
					
					case 1: addCategory();
							break;
					case 2: removeCategory();
							break;
					case 3: updateCategory();
					
					case 4: displayArticlesByCategories();
							break;
					
					case 5: displayCategories();

					default:
						break;
						
					} break;
			
			case 7: 
				System.out.println("Au revoir");
				break;
			
			default:
				System.out.println("Saisie erronée");
				break;
			}	
			} while (nbMenu!=7);
			scan.close();
		}
	
		
		
		

	
	public static void menu() {
		System.out.println();
		System.out.println();

		String menu[] = { "1- Afficher tous les articles en base", "2- Ajouter un article", "3- Supprimer un article", "4- Mettre à jour un article", "5- Afficher un article",
				 "6- Gestion des catégories", "7- Quitter"};

		for (int i = 0; i < menu.length; i++) {
			System.out.println(menu[i]);
			System.out.println();
		}
	}
	
	public void displayArticles() {
		
	List<Article>articles=eshopBusiness.getListArticles();
	
	System.out.println();
	System.out.println("------------------------------------------------------------------------------------------------");
	System.out.printf("%5s | %-20s | %-20s | %-20s |%-20s |", "REF.", "MARQUE", "DESCRIPTION","PRIX","CATEGORIE");
	System.out.println();
	System.out.println("-------------------------------------------------------------------------------------------------");

		for (Article art: articles) {
			
			System.out.format("%5s | %-20s | %-20s | %-20s| %-20s|",art.getId(), art.getBrand(),art.getDescription(),art.getPrice(),art.getCategory());
				System.out.println();
				System.out.println(
						"------------------------------------------------------------------------------------------------");
		
	}
	}
	
	public void addArticle() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez la marque du téléphone");
		String name = scan.next();
		System.out.println("Indiquez la description de l'article");
		String description = scan.next();
		System.out.println("Indiquez le prix de l'article");
		double price = scan.nextDouble();
		System.out.println("Indiquez la catégorie de l'article");
		String nameCategory = scan.next();
		Category category = eshopBusiness.findCategories(nameCategory);
		Article article = new Article(name, description, price, category);
		eshopBusiness.addArticles(article, nameCategory);
		System.out.println("Article ajouté en base");
	}
	
	public void removeArticle() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez l'identifiant de l'article que vous voulez supprimer");
		Long id=scan.nextLong();
		eshopBusiness.removeById(id);
		System.out.println("Article supprimé de la base");
	}
	
	public void updateArticle() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez l'identifiant de l'article que vous voulez mettre à jour");
		Long id3=scan.nextLong();
		Optional<Article> article3=eshopBusiness.findArticle(id3);
		System.out.println("Indiquez la marque de l'article");
		String name1 = scan.next();
		System.out.println("Indiquez la description de l'article");
		String description1 = scan.next();
		System.out.println("Indiquez le prix de l'article");
		double price1 = scan.nextDouble();
		System.out.println("Indiquez la catégorie de l'article");
		String nameCategory1 = scan.next();
		Category category1 = eshopBusiness.findCategories(nameCategory1);
		article3.get().setBrand(name1);
		article3.get().setDescription(description1);
		article3.get().setPrice(price1);
	
		article3.get().setCategory(category1);
		eshopBusiness.addArticles(article3.get(), nameCategory1);
		System.out.println("Article modifié en base");
	}
	
	public void addCategory() {
		
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez le nom de la catégorie");
		String nameCategory = scan.next();
		Category category2=new Category(nameCategory);
		eshopBusiness.addCategories(category2);
		System.out.println("Catégorie ajoutée en base");
		
	}
	
	public void removeCategory() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez l'identifiant de la catégorie que vous voulez supprimer");
		Long id=scan.nextLong();
		eshopBusiness.removeCategoryById(id);
		System.out.println("Catégorie supprimée de la base");
	}
	
	public void updateCategory() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez l'identifiant de la catégorie que vous voulez mettre à jour");
		Long id=scan.nextLong();
		Optional<Category> category=eshopBusiness.findCategory(id);
		System.out.println("Indiquez le nom de la catégorie");
		String name = scan.next();
		category.get().setName(name);
		eshopBusiness.addCategories(category.get());
		System.out.println("Catégorie modifiée en base");
		
	}
	
	public void displayCategories() {
		
			
			List<Category> categories=eshopBusiness.getListCategories();
			
			System.out.println();
			System.out.println("---------------------------");
			System.out.printf("%5s | %-20s |", "REf", "NOM");
			System.out.println();
			System.out.println("---------------------------");

				for (Category cat: categories) {
					
					System.out.format("%5s | %-20s | ",cat.getId(), cat.getName());
						System.out.println();
						System.out.println("---------------------------");
				
			}
			}
	
	public void displayArticlesByCategories() {
		Scanner scan= new Scanner(System.in);
		System.out.println("Indiquez l'identifiant de la catégorie que vous voulez afficher");
		Long id=scan.nextLong();
		List<Article> articles= eshopBusiness.getArticlesByCategory(id);
		
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("%5s | %-20s | %-20s | %-20s |", "REF.", "MARQUE", "DESCRIPTION",
				"PRIX");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");

			for (Article art: articles) {
				
				System.out.format("%5s | %-20s | %-20s | %-20s|",art.getId(), art.getBrand(),art.getDescription(),art.getPrice());
					System.out.println();
					System.out.println(
							"----------------------------------------------------------------------------");
			
		}
	}
		
		public void displaySingleArticle() {
			Scanner scan= new Scanner(System.in);
			System.out.println("Quel article voulez vous afficher? Saisissez un identifiant");
			Long id2=scan.nextLong();
			Optional<Article> article2=eshopBusiness.findArticle(id2);
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------");
			System.out.printf("%5s | %-20s | %-20s | %-20s |%-20s |", "REF.", "MARQUE", "DESCRIPTION","PRIX","CATEGORIE");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------");

			System.out.format("%5s | %-20s | %-20s | %-20s| %-20s|",article2.get().getId(), article2.get().getBrand(),
					article2.get().getDescription(),article2.get().getPrice(),article2.get().getCategory());
						System.out.println();
						System.out.println(
								"----------------------------------------------------------------------------------------------------");
			
}
}
	

