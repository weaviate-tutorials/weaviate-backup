package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.schema.api.ClassCreator;
import technology.semi.weaviate.client.v1.schema.model.DataType;
import technology.semi.weaviate.client.v1.schema.model.Property;
import technology.semi.weaviate.client.v1.schema.model.WeaviateClass;

import java.util.Arrays;

public class App_1_create_schema {

  public static void main(String[] args) {
    String hostW1 = "localhost:8080";

    WeaviateClass author = WeaviateClass.builder()
      .className("Author")
      .description("Fantasy Writers")
      .properties(Arrays.asList(
        Property.builder()
          .name("name")
          .description("The author's name")
          .dataType(Arrays.asList(DataType.STRING))
          .build(),
        Property.builder()
          .name("website")
          .description("Their website address")
          .dataType(Arrays.asList(DataType.STRING))
          .build(),
        Property.builder()
          .name("genres")
          .description("Genres they write")
          .dataType(Arrays.asList(DataType.TEXT_ARRAY))
          .build()
      ))
      .build();

    WeaviateClass book = WeaviateClass.builder()
      .className("Book")
      .description("Popular books")
      .properties(Arrays.asList(
        Property.builder()
          .name("title")
          .description("Title of the book")
          .dataType(Arrays.asList(DataType.STRING))
          .build(),
        Property.builder()
          .name("written_by")
          .description("Author of the book")
          .dataType(Arrays.asList("Author"))
          .build()
      ))
      .build();

    WeaviateClient client = new WeaviateClient(new Config("http", hostW1));
    ClassCreator classCreator = client.schema().classCreator();

    System.out.println("\nCreating Schema - Author");
    Result<Boolean> authorResult = classCreator.withClass(author).run();
    if (authorResult.hasErrors()) {
      System.err.println(authorResult.getError());
      return;
    }
    System.out.println("Created");

    System.out.println("\nCreating Schema - Book");
    Result<Boolean> bookResult = classCreator.withClass(book).run();
    if (bookResult.hasErrors()) {
      System.err.println(bookResult.getError());
      return;
    }
    System.out.println("Created");
  }
}
