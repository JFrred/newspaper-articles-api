package com.example;

import com.example.model.Article;
import com.example.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class NewspaperArticleApiApplication {
	private final ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(NewspaperArticleApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	void initDb() {
		Article article1 = new Article(1, "article 1; A newspaper is a periodical publication containing written information about current events and is often typed in black ink with a white or gray background.", LocalDate.now().minusYears(100), "Wall Street Journal", "Author 0", Timestamp.from(Instant.now()));
		Article article2 = new Article(2,"article 2; Newspapers can cover a wide variety of fields such as politics, business, sports and art, and often include materials such as opinion columns, weather forecasts, reviews of local services,",   LocalDate.of(2020, 5, 10),"New York Times", "Author 0", Timestamp.from(Instant.now()));
		Article article3 = new Article(3, "article 3; Most newspapers are businesses, and they pay their expenses with a mixture of subscription revenue, newsstand sales, and advertising revenue. The journalism organizations that publish ", LocalDate.of(2010, 1, 1),  "Washington Post", "Author 1", Timestamp.from(Instant.now()));
		Article article4 = new Article(4,  "article 4; Newspapers have traditionally been published in print (usually on cheap, low-grade paper called newsprint). However, today most newspapers are also published on websites as online ",  LocalDate.of(2021, 10, 12),"Chicago Tribune","Author 1", Timestamp.from(Instant.now()));
		Article article5 = new Article(5, "article 5;  developed in the 17th century, as information sheets for merchants. By the early 19th century, many cities in Europe, as well as North and South America, published", LocalDate.of(2018, 6, 13),"Newsday",  "Author 2", Timestamp.from(Instant.now()));

		articleRepository.saveAll(List.of(article1, article2, article3, article4, article5));
	}
}
