package edu.lms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(tags = {@Tag(name = "Course APIs"), @Tag(name= "Category APIs")}

)
public class ELearningBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningBeApplication.class, args);
	}

}
