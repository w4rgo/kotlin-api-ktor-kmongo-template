#!/usr/bin/env bash
docker run -d -p 27017-27019:27017-27019 -v $(pwd)/mongodata:/data/db --name mongodb mongo:4.0.4
