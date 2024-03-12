#!/bin/bash -x
CROWD_SNAPSHOT_FILE_PATH='target/crowd2.hpi'

export GITPOD_SITE="${HOSTNAME}.${GITPOD_WORKSPACE_CLUSTER_HOST}"
export JENKINS_URL="https://$JENKINS_SITE_NAME/"
export JENKINS_SITE_NAME="8080-${GITPOD_SITE}"

if [[ ! -f "$CROWD_SNAPSHOT_FILE_PATH" ]]; then
    echo "--- No builds found - building plugin ---"
    mvn -ntp clean verify
else
    echo "--- Build found - using it ---"
    mvn -ntp clean verify -DskipTests
fi

export JAVA_VERSION="${1:-11}"

# create new img with crowd2-snapshot file installed
# if there is args passed use java8
if [[ $# -gt 0 ]] || ! docker image inspect casc_jenkins:latest &>/dev/null; then
    echo "--- Build Docker img ---"
    docker compose -f casc/docker-compose.yml build
fi

# fetch all needed images (crowd and jenkins one)
docker compose -f casc/docker-compose.yml pull

echo '--- Start docker services ---'
docker compose -f casc/docker-compose.yml up -d --remove-orphans
