#!/bin/sh

BUCKET_NAME=paypaychallenge2019

## Creates S3 bucket
aws s3 mb s3://$BUCKET_NAME

## S3 cloudformation deployments
### Core
aws s3 cp IaC/core/ecs-fargate-cluster.yaml s3://$BUCKET_NAME/resources/cloudformation/core/ecs-fargate-cluster.yaml
aws s3 cp IaC/core/ecs-fargate-service.yaml s3://$BUCKET_NAME/resources/cloudformation/core/ecs-fargate-service.yaml
aws s3 cp IaC/core/vpc.yaml s3://$BUCKET_NAME/resources/cloudformation/core/vpc.yaml
### Service
aws s3 cp IaC/service/fargate-service-analytic.yaml s3://$BUCKET_NAME/resources/cloudformation/service/fargate-service-analytic.yaml
### CI/CD
aws s3 cp IaC/cicd/codebuild.yaml s3://$BUCKET_NAME/resources/cloudformation/cicd/codebuild.yaml
aws s3 cp IaC/cicd/codecommit.yaml s3://$BUCKET_NAME/resources/cloudformation/cicd/codecommit.yaml
### Stream
aws s3 cp IaC/stream/kinesis.yaml s3://$BUCKET_NAME/resources/cloudformation/stream/kinesis.yaml
aws s3 cp IaC/stream/emr-spark.yaml s3://$BUCKET_NAME/resources/cloudformation/stream/emr-spark.yaml
### Securities
aws s3 cp IaC/stream/emr-spark.yaml s3://$BUCKET_NAME/resources/cloudformation/securities/iam-role.yaml

