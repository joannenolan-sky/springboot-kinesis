#!/usr/bin/env bash

set -m

export KINESIS_SHARD_LIMIT=1
export SERVICES=dynamodb,kinesis,cloudformation,cloudwatch

docker-entrypoint.sh &

sleep 30
awslocal cloudformation create-stack \
    --stack-name stack \
    --template-body file://stream-setup/stack.yaml \
    --profile default

fg %1
