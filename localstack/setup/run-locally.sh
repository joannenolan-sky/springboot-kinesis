#!/usr/bin/env bash
set +e

script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
project_dir="${script_dir}/.."
cd ${project_dir}


docker rmi localstack
set -e

docker build -t localstack . -t localstack.local

docker run --rm -it -p 4566:4566 localstack.local AWS_CBOR_DISABLE=true 
