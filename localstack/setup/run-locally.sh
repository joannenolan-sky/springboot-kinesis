#!/usr/bin/env bash
set +e

script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
project_dir="${script_dir}/.."
cd ${project_dir}

echo ${project_dir}

docker-compose -f ../localstack/docker-compose/docker-compose-localstack.yml down

set -e

docker-compose -f ../localstack/docker-compose/docker-compose-localstack.yml up --build