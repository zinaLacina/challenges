
# DESIGN A GOOGLE ANALYTIC LIKE BACKEND SYSTEM

![System Design](https://github.com/zinaLacina/challenges/blob/master/design/google-analytics.png)
Nowadays applications which handle billion of request are a microservices base for more scalability, easy to maintains, to upgrade and easy to rollback in case update or upgrade failed.
Let's take point per point the requirement and explain which services or combination of service are managing each point.
NB: I included some AWS services because of the high availability and AWS is responsible for the update.

 1. Handle large write volume: Billions write events per day.
First thing I thought about, is that users can be anywhere in the globe. And usually, Loadbalancer is regional, to make it global we need to duplicate infrastructure and use some services like route 53 to interconnect to the domain name. But this solution is too costly and need energy for the maintenance. The simple solution is CDN, so the flux generate will be sent directly to the closest edge location and then send to our application.
Our ALB or HAProxy restrict access only to CDN, that means it is private. Why? For security purpose, we want to avoid anonymous or hacker to access directly to our Load Balancer. This solution makes our application highly available. If the choice is HAProxy we need to put the auto-Scaling group to scale out or scale up(Scale in and scale down is not suitable). If ALB this an AWS managed service with is highly available with multiple functions.
The application will be microservices containerized based, managing by AWS Fargate which scales based on a load of request. In the schema, we choose the Spring framework.

