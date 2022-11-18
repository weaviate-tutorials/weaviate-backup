package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;

import java.util.HashMap;
import java.util.Map;

public class App_9_delete_all {

  public static void main(String[] args) {
    Map<String, String> hosts = new HashMap<String, String>() {{
      put("W1", "localhost:8080");
      put("W2", "localhost:8090");
    }};

    hosts.forEach((name, host) -> {
      WeaviateClient client = new WeaviateClient(new Config("http", host));

      System.out.printf("\nDeleting all schemas in %s\n", name);
      Result<Boolean> deleteResult = client.schema().allDeleter().run();
      if (deleteResult.hasErrors()) {
        System.err.println(deleteResult.getError());
      } else {
        System.out.println("Deleted");
      }
    });
  }
}
