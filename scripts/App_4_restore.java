package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.backup.model.Backend;
import technology.semi.weaviate.client.v1.backup.model.BackupRestoreResponse;

public class App_4_restore {

  public static void main(String[] args) {
    String hostW2 = "localhost:8090";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW2));

    System.out.println("\nRestoring Backup");
    Result<BackupRestoreResponse> restoreResult = client.backup().restorer()
      .withBackupId("my-very-first-backup")
      .withBackend(Backend.FILESYSTEM)
      .run();
    if (restoreResult.hasErrors()) {
      System.err.println(restoreResult.getError());
      return;
    }
    System.out.println(restoreResult.getResult());
  }
}
