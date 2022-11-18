package technology.semi.weaviatetutorials;

import com.google.gson.Gson;
import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.batch.model.ObjectGetResponse;
import technology.semi.weaviate.client.v1.data.model.WeaviateObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class App_2_import {

  public static void main(String[] args) throws IOException {
    String hostW1 = "localhost:8080";
    String dataPath = "src/main/resources/backup/data.json";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW1));
    Data data = new Gson().fromJson(Files.newBufferedReader(Paths.get(dataPath)), Data.class);

    System.out.println("\nImporting Data - Author");
    Result<ObjectGetResponse[]> authorResult = client.batch().objectsBatcher()
      .withObjects(data.authors)
      .run();
    if (authorResult.hasErrors()) {
      System.err.println(authorResult.getError());
      return;
    }
    System.out.println(Arrays.toString(authorResult.getResult()));

    System.out.println("\nImporting Data - Book");
    Result<ObjectGetResponse[]> bookResult = client.batch().objectsBatcher()
      .withObjects(data.books)
      .run();
    if (bookResult.hasErrors()) {
      System.err.println(bookResult.getError());
      return;
    }
    System.out.println(Arrays.toString(bookResult.getResult()));
  }

  private static class Data {
    WeaviateObject[] authors;
    WeaviateObject[] books;
  }
}
