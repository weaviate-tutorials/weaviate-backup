package technology.semi.weaviatetutorials;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.base.Result;
import technology.semi.weaviate.client.v1.backup.model.Backend;
import technology.semi.weaviate.client.v1.backup.model.BackupRestoreStatusResponse;

public class App_4b_check_restore_status {

  public static void main(String[] args) {
    String hostW2 = "localhost:8090";

    WeaviateClient client = new WeaviateClient(new Config("http", hostW2));

    System.out.println("\nChecking restore status");
    Result<BackupRestoreStatusResponse> restoreStatusResult = client.backup().restoreStatusGetter()
      .withBackupId("my-very-first-backup")
      .withBackend(Backend.FILESYSTEM)
      .run();
    if (restoreStatusResult.hasErrors()) {
      System.err.println(restoreStatusResult.getError());
      return;
    }
    System.out.println(restoreStatusResult.getResult());
  }
}
