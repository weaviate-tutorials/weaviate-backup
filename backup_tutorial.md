# How to back up and restore your Weaviate data with ease

## Introduction
Maintaining data integrity is one of the key goals for database users. So it should come as no surprise that backing up the data is an important part of database best practices.

Although it has been possible to back up Weaviate data, doing so required our users to perform many manual and inelegant steps. So, we have introduced a [backup feature](https://weaviate.io/developers/weaviate/current/configuration/backups.html) in Weaviate v1.15 that streamlines the back up process, whether it be to a local file system or to a cloud storage provider.

This tutorial will show you how you can use this feature to back up your data, and restore it to another instance of Weaviate. By the end of this tutorial, you will have:
- Spun up two instances of Weaviate,
- Populated one Weaviate instance with a new schema & data,
- Backed up the Weaviate instance, and
- Restored the backup data to the other instance.

## Preparations

You will be able to follow this tutorial using a Weaviate client of your choice. (The backup feature can [be used through any of our clients, or with an HTTP request](https://weaviate.io/developers/weaviate/current/configuration/backups.html#api).) If these steps are unfamiliar to you, we recommend checking out our [Getting Started guide](https://weaviate.io/developers/weaviate/current/getting-started/index.html) which should take just a short while to get through. :)

The tutorial makes use of two instances of Weaviate so that we can create a backup from one instance before restoring it to the other one. We have set up a `docker-compose.yaml` file to make this easier for you.

[Download it here](https://raw.githubusercontent.com/weaviate-tutorials/weaviate-backup/main/docker-compose.yaml), and then run `docker-compose up -d` to spin up the two Weaviate instances. You should be able to connect to them at `http://localhost:8080` and `http://localhost:8090` respectively. We’ll call them W1 and W2 from this point on for convenience.

The `docker-compose.yaml` file also specifies the below parameters to enable local backups.
```
environment:
  …
  ENABLE_MODULES: 'backup-filesystem'
  BACKUP_FILESYSTEM_PATH: '/tmp/backups'
volumes:
  - ./backups:/tmp/backups
```


The above enables the `backup-filesystem` module to back up data from Weaviate to the filesystem, and sets `/tmp/backups` as the `BACKUP_FILESYSTEM_PATH` which is the backup path within the Docker container. 

The `volumes` parameter below [mounts a volume](https://weaviate.io/developers/weaviate/current/configuration/persistence.html) from outside the container to within it. Setting it to `./backups:/tmp/backups` maps `./backups` on the local device to `/tmp/backups` within the container, so the generated backup data will end up in the `./backups` directory as you will see later on. Now let’s dive into it to see the backup functionality in action!

## Populate one instance
Both of our Weaviate instances W1 and W2 should be empty. (You can check the schema for W1 and W2 respectively by visiting http://localhost:8080/v1/schema and http://localhost:8090/v1/schema endpoints respectively, or using your favorite client.)

As our first order of action we will populate W1 with data to play with. 

We have prepared a demo dataset for you to use here[INSERT LINK]. Download it and run the following scripts to populate W1 : 

Create a schema:
https://gist.github.com/databyjp/87b0f548683e4a9e34ba66827814da3b

Generate and batch import data:
https://gist.github.com/databyjp/2948c70b98804dc5901860d73f38d6b0

If you check the schemas, you should be able to confirm that only W2 is empty, while W1 now contains a schema (and also data objects).

## Back up & restore data
First, just check our backups directory (`./backups`) is empty, and let’s move on to creating our first backup. 

Initiating a backup involves just a short bit of code. The below will for example back up all classes in `client_1` using an ID called `my-first-backup`.
```
client_1.backup.create(
    backup_id='my-first-backup',
    backend='filesystem',
    wait_for_completion=True,
)
```
> Note: The `backup_id` must be unique. Attempting to re-use an existing ID will cause Weaviate to throw an exception.

Now try running the full script provided here [INSERT LINK]() yourself to back up data from W1. If you check the contents of the backup directory again, you should see a new directory called `my-first-backup` containing the backup data files.

Restoring this data is similar - take a look at the example syntax below:
```
client_2.backup.restore(
    backup_id='my-first-backup',
    backend='filesystem',
    wait_for_completion=True,
)
```
Run the script provided here [INSERT LINK]() to restore the W1 backup data to W2. Now, check the schemas again for W1 and W2. Do they *both* now contain the same schema? What about the data objects? They should be identical.   

Great. You have successfully backed up data from W1 and restored it onto W2! 

## Backup features
Before we finish, let’s go back to talk a little more about additional backup options, and some important notes.

### Local backup location
If you wish to back up your data to a different location, you can edit the `volumes` parameter in `docker-compose.yaml` to replace `./backups` with the desired location.

For example, changing it from `./backups:/tmp/backups` to `./my_archive:/tmp/backups` would change the backup destination from `./backups` to `./my_archive/`.

### Cloud storage systems
Note, you can also configure Weaviate backup to work with **cloud storage systems** like **Google Cloud Storage** (**GCS**) and **S3-compatible blob storage** (like **AWS S3** or **MinIO**).

Each requires a different Weaviate backup module (`backups-gcs` and `backups-s3`), configuration parameters and authentication. Check the documentation to learn more about [GCS backup](https://weaviate.io/developers/weaviate/current/configuration/backups.html#gcs-google-cloud-storage) and [S3 backup](https://weaviate.io/developers/weaviate/current/configuration/backups.html#s3-aws-or-s3-compatible).

### Partial backup and restores
Weaviate’s backup functionality allows you to back up or restore a subset of available classes. This might be very useful in cases where, for example, you may wish to partially export a subset of data to a development environment, or to import an updated class.

For example, the below call will only restore the `RomanNumeral`  class regardless of whether any other classes have been also included in `my-first-backup`.
```
client_1.backup.restore(
    backup_id='my-first-backup',
    backend='filesystem',
    include_classes=['RomanNumeral'],
    wait_for_completion=False,
)
```
Try it out with this script, and you should see that W1 will only contain one class even though its data was restored from a backup that contains multiple classes.

You can restore a class as long as the target Weaviate instance does not already contain that class. So if you run another operation to restore the `Integer` class to W1, it will result in an instance containing both `RomanNumeral` and `Integer` classes. 

Try running this script yourself; and query the instance to check what classes it contains:
```
import weaviate
from json import dumps

client_1 = weaviate.Client('http://localhost:8080')

print(dumps(client_1.schema.get(), indent=2))
```

### Asynchronous operations
Did you notice the `wait_for_completion` option in our `backup` or `restore` requests earlier? 

As the name suggests, Weaviate’s backup operation status can be initiated and polled asynchronously. While backup operations are ongoing, Weaviate remains available for read and write operations, and you can poll the endpoint to check its status. 

You don’t need to maintain a connection to the server for the operation to complete, and you can look in on the status with a method such as below:
```
status = client_1.backup.get_restore_status(
    backup_id='my-first-backup',
    backend='filesystem',
)
```

## Wrap-up
We hope that this gave you a good overview of the new backup feature available in Weaviate. More importantly, we think that it will make it easier and faster for you to back up your data and help make your application more robust. 

To recap, it allows you to back up one or more classes from an instance of Weaviate to a backup, and to restore one or more classes from a backup to Weaviate. Weaviate remains functional during these processes, and you can poll the backup or restore operation status periodically if you wish. 

Weaviate currently supports backing up to your local filesystem, AWS or GCS. But as the backup orchestration is decoupled from the remote backup storage backends, it is relatively easy to add new providers and use them with the existing backup API.

If you would like to use another storage provider to use with Weaviate, we encourage you to open a feature request or consider contributing it yourself. For either option, join our Slack community to have a quick chat with us on how to get started.
