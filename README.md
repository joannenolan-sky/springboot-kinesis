# Springboot Kinesis

This POC gives a jumping board for reading/writing Kinesis Streams using Springboot binder library. It uses localstack to emulate AWS backends.

There are multiple ways of getting message from a Kinesis queue:
- [AWS CLI](https://docs.aws.amazon.com/streams/latest/dev/fundamental-stream.html)
- [AWS KCL v2](https://docs.aws.amazon.com/streams/latest/dev/shared-throughput-kcl-consumers.html)
- [AWS Lambda](https://docs.aws.amazon.com/lambda/latest/dg/with-kinesis.html)

### Steps to run the code
1. In the `spring-kinesis` folder run the following command to start localstack:
```
TMPDIR=/private$TMPDIR docker-compose up
```
This will start a docker instance with the name localstack_main,  using the credentials and config in `localstack/setup/aws`.
It will also create the required stack using `localstack/setup/stream-setup/stack.yml` which creates a Kinesis stream and a dynamoDB table
 

2. Start the consumer  using `./gradlew :consumer:bootRun`

3. Start the produce  using `./gradlew :producer:bootRun`
4. Put messages o the kinesis stream using the controller endpoint. An example message and curl can be found in `kinesis-producer.http`'


### Useful tools for verifying manually:
- Install AWS CLI v2 using the following [instructions](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-mac.html)
This will allow manual validation of records in Kinesis
  - Get the Shard Iterator
```
aws  --endpoint-url=http://localhost:4566 --region us-east-1 kinesis get-shard-iterator --shard-id shardId-000000000000 --shard-iterator-type TRIM_HORIZON --stream-name teststream
```
  - Get Records From Kinesis
```
aws  --endpoint-url=http://localhost:4566 --region us-east-1 kinesis get-records --shard-iterator <USE_SHARD_ITERATOR_FROM_PERVIOUS_COMMAND>
```
 - List DynamoDb tables 
 ```
aws --endpoint-url=http://localhost:4566 --region us-east-1 dynamodb list-tables
```
 - List Kinesis sreams
 ```a
aws --endpoint-url=http://localhost:4566 --region us-east-1 kinesis list-streams
```
 - List shards of the stream 
 ```
aws kinesis list-shards --endpoint-url=http://localhost:4566 --region us-east-1 --stream-name teststream  --exclusive-start-shard-id shardId-000000000000
```

 - List DynamoDb tables 
 ```
aws --endpoint-url=http://localhost:4566 --region us-east-1 dynamodb list-tables
```
 - List Kinesis sreams
 ```a
aws --endpoint-url=http://localhost:4566 --region us-east-1 kinesis list-streams
```
 - List shards of the stream 
 ```
aws kinesis list-shards --endpoint-url=http://localhost:4566 --region us-east-1 --stream-name teststream  --exclusive-start-shard-id shardId-000000000000
```
### Docker-Compose

  - Start localstack using docker-compose
```
docker-compose -f localstack/docker-compose/docker-compose-localstack.yml up --build
```


### Kubernetes

- Create deployment, service of NodePort type, persistent volume with persistent volume claim

Use `kubernetes` profile to run application
```
kubectl apply -f localstack.yml
```
- Verify 
```
kubectl get pv -owide
kubectl get pvc -owide
kubectl get pods -owide

```

- Volumes

   - Lits Persistent Volumes and Persistent Volumes Claims:

```
kubectl get pv
kubectl get pvc
```

- Clean up

   - Delete the Pod, the PersistentVolumeClaim and the PersistentVolume:
```
kubectl delete service/localstack
kubectl delete deployment.apps/localstack
kubectl delete pvc localstack-data-volume-claim
kubectl delete pv localstack-data-volume
```

- Standalone pod with host volume 

```
kubectl apply -f pod.yml
```