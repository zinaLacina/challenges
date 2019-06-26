
# DESIGN A GOOGLE ANALYTIC LIKE BACKEND SYSTEM

![System Design](https://github.com/zinaLacina/challenges/blob/master/design/google-analytics.png)
Nowadays applications which handle billion of request are a microservices base for more scalability, easy to maintains, to upgrade and easy to rollback in case update or upgrade failed.
Let's take point per point the requirement and explain which services or combination of service are managing each point.
NB: I included some AWS services because of the high availability and AWS is responsible for the update.
## Handle large write volume: Billions write events per day.
First thing I thought about, is that users can be anywhere in the globe. And usually, Loadbalancer is regional, to make it global we need to duplicate infrastructure and use some services like route 53 to interconnect to the domain name. But this solution is too costly and need energy for the maintenance. The simple solution is CDN, so the flux generate will be sent directly to the closest edge location and then send to our application.
Our ALB or HAProxy restrict access only to CDN, that means it is private. Why? For security purpose, we want to avoid anonymous or hacker to access directly to our Load Balancer. This solution makes our application highly available. If the choice is HAProxy we need to put the auto-Scaling group to scale out or scale in(Scale in and scale down is not suitable). If ALB this an AWS managed service with is highly available with multiple functions.
The application will be microservices containerized based, managing by AWS Fargate which scales based on a load of request. In the schema, we choose the Spring framework.

## Handle large read/query volume: Millions merchants want to get insight about their business. Read/Query patterns are time-series related metrics.
ELK and Splunk are one of the best Time Series Database management, but they are more a search-engine than a storage option, that why we choice Dynamo DB over them. To accomplish the time series metric purpose and keep the real-time streaming we combined many technologies such us spark times series,  in-memory ignite, spark stream, Kafka or kinesis, and DynamoDB. Kinesis Data Stream can stream millions of data,  based on shard performance, more there are shards, more it becomes. It is a managed service of AWS than mean responsibilities are shared. Kafka is one the best streaming data management, it is open source. For high availabilty, we need to configure an auto-scaling and configure the server with technology such as chef for auto healing for more availability.
## Provide metrics to customers with at most one hour delay.
DynamoDB is one of the best Solution for NoSQL and it is automatically high available.AWS DynamoDB supports a strongly consistent read. For heavy intensive read, Dynamodb provides DAX(DynamoDB Accelerator), so a Spring microservice will read information from there and provide a dashboard in an appropriate time.

## Run with minimum downtime.
For less downtime, we use the auto-scaling system for all or service. DynamoDB is a highly available service in a region. For more security, we make replication in other regions using the feature Global Table. For the application, Fargate is highly available service where we don't need to manage servers and maintain them, but we can also combine (in AWS environment) lambda, Cloudwatch metric to take a snapshot of our containers and copy to other regions.
## Have the ability to reprocess historical data in case of bugs in the processing logic.
If the streaming process bug, we take information from the Dynamodb directly and provide to customers.
