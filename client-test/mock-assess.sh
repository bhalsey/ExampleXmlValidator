#!/bin/sh

# Send a sample get assessmentItems request

#SERVICE_HOST="staging.pearsonkt.com"
SERVICE_HOST="localhost:8080"
#SERVICE_HOST="qa-corpweb.qa.pearsonkt.com"

echo "Running: " >&2
echo "curl -v -H 'Content-Type: text/xml' -d '@request-assessmentItems.xml' http://${SERVICE_HOST}/wtlService/initialAssessment/mockAssessmentItems" >&2
echo >&2

curl -v -H 'Content-Type: text/xml' -d '@request-assessmentItems.xml' http://${SERVICE_HOST}/wtlService/initialAssessment/mockAssessmentItems

echo >&2
