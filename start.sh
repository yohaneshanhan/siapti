#!/bin/bash

cd /home/cahyoko1994/siapti/service-dosen/
docker build --tag service-dosen .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8182:8182 --memory="512m" --name service-dosen service-dosen
sleep 5

cd /home/cahyoko1994/siapti/service-hasil-studi/
docker build --tag service-hasil-studi .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8192:8192 --memory="512m" --name service-hasil-studi service-hasil-studi
sleep 5

cd /home/cahyoko1994/siapti/service-id-generator/
docker build --tag service-id-generator .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8189:8189 --memory="512m" --name service-id-generator service-id-generator
sleep 5

cd /home/cahyoko1994/siapti/service-jadwal-kuliah/
docker build --tag service-jadwal-kuliah .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8190:8190 --memory="512m" --name service-jadwal-kuliah service-jadwal-kuliah
sleep 5

cd /home/cahyoko1994/siapti/service-login-detail/
docker build --tag service-login-detail .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8187:8187 --memory="512m" --name service-login-detail service-login-detail
sleep 5

cd /home/cahyoko1994/siapti/service-mahasiswa/
docker build --tag service-mahasiswa .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8184:8184 --memory="512m" --name service-mahasiswa service-mahasiswa
sleep 5

cd /home/cahyoko1994/siapti/service-matakuliah/
docker build --tag service-matakuliah .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8185:8185 --memory="512m" --name service-matakuliah service-matakuliah
sleep 5

cd /home/cahyoko1994/siapti/service-other/
docker build --tag service-other .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8186:8186 --memory="512m" --name service-other service-other
sleep 5

cd /home/cahyoko1994/siapti/service-registrasi-matakuliah/
docker build --tag service-registrasi-matakuliah .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8191:8191 --memory="512m" --name service-registrasi-matakuliah service-registrasi-matakuliah
sleep 5

cd /home/cahyoko1994/siapti/service-reset-password/
docker build --tag service-reset-password .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8188:8188 --memory="512m" --name service-reset-password service-reset-password
sleep 5

cd /home/cahyoko1994/siapti/service-transkrip/
docker build --tag service-transkrip .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8193:8193 --memory="512m" --name service-transkrip service-transkrip
sleep 5

cd /home/cahyoko1994/siapti/service-walistudi/
docker build --tag service-walistudi .
sleep 5
docker run -e TZ=Asia/Jakarta -it -d --restart always --network host -p 8183:8183 --memory="512m" --name service-walistudi service-walistudi
sleep 5
