#!/bin/bash
docker stop $(docker ps -a -q)
sleep 5
docker rm $(docker ps -a -q)
sleep 5
kill $(pgrep -f java)
sleep 5
sync; echo 3 > /proc/sys/vm/drop_caches