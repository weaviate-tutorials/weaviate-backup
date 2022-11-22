package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.data.model.WeaviateObject;
import technology.semi.weaviate.client.v1.schema.model.Schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App_0_query_instances {

  public static void main(String[] args) {
    Map<String, String> hosts = new HashMap<String, String>() {{
      put("W1", "localhost:8080");
      put("W2", "localhost:8090");
    }};

    hosts.forEach((name, host) -> {
      WeaviateClient client = new WeaviateClient(new Config("http", host));

      System.out.printf("\nQuerying schema in %s\n", name);
      Result<Schema> schemaResult = client.schema().getter().run();
      if (schemaResult.hasErrors()) {
        System.err.println(schemaResult.getError());
      } else {
        System.out.println(schemaResult.getResult());
      }

      System.out.printf("\nQuerying objects in %s\n", name);
      Result<List<WeaviateObject>> objectsResult = client.data().objectsGetter().run();
      if (objectsResult.hasErrors()) {
        System.err.println(objectsResult.getError());
      } else {
        System.out.println(objectsResult.getResult());
      }
    });
  }
}
