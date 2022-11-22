package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.backup.model.Backend;
import technology.semi.weaviate.client.v1.backup.model.BackupCreateStatusResponse;

public class App_3a_check_backup_status {

  public static void main(String[] args) {
    String hostW1 = "localhost:8080";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW1));

    System.out.println("\nChecking backup status");
    Result<BackupCreateStatusResponse> createStatusResult = client.backup().createStatusGetter()
      .withBackupId("my-very-first-backup")
      .withBackend(Backend.FILESYSTEM)
      .run();
    if (createStatusResult.hasErrors()) {
      System.err.println(createStatusResult.getError());
      return;
    }
    System.out.println(createStatusResult.getResult());
  }
}
