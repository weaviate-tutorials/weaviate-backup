package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.backup.model.Backend;
import technology.semi.weaviate.client.v1.backup.model.BackupCreateResponse;

public class App_3_backup {

  public static void main(String[] args) {
    String hostW1 = "localhost:8080";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW1));

    System.out.println("\nCreating Backup");
    Result<BackupCreateResponse> createResult = client.backup().creator()
      .withBackupId("my-very-first-backup")
      .withBackend(Backend.FILESYSTEM)
      .run();
    if (createResult.hasErrors()) {
      System.err.println(createResult.getError());
      return;
    }
    System.out.println(createResult.getResult());
  }
}
