#!/bin/bash
trap 'kill $(jobs -p)' EXIT

mvn spring-boot:run & cd src/main/js/ || exit; ng serve -o