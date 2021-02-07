#!/bin/bash
trap 'kill $(jobs -p)' EXIT

(cd frontend/ || exit;
if [ ! -e node_modules ]; then
    echo "[INFO]: node_modules missing. Installing them..."
    npm install
fi)

cd backend/ || exit;
mvn spring-boot:run & cd ../frontend || exit; ng serve -o