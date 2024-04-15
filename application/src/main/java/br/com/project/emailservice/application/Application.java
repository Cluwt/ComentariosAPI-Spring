package br.com.project.emailservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.project.emailservice.application.Services.CommentService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		CommentService commentService = new CommentService();
		try {
			List<String> comments = commentService.getComments();
			if (!comments.isEmpty()) {
				for (String comment : comments) {
					System.out.println(comment);
				}
			} else {
				System.out.println("Não foram encontrados comentários.");
			}
		} catch (IOException e) {
			System.err.println("Erro ao obter os comentários: " + e.getMessage());
		}
	}
}
