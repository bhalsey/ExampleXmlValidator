#!/bin/sh

# Send a sample add cars request

curl -v -H 'Content-Type: text/xml' -d '@car-records.xml' http://localhost:8080/ExampleXmlValidator/car/addTestCache

echo >&2
