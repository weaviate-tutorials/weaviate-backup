package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;

public class App_8_delete_w2 {

  public static void main(String[] args) {
    String hostW2 = "localhost:8090";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW2));

    System.out.println("\nDeleting all schemas");
    Result<Boolean> deleteResult = client.schema().allDeleter().run();
    if (deleteResult.hasErrors()) {
      System.err.println(deleteResult.getError());
      return;
    }
    System.out.println("Deleted");
  }
}
