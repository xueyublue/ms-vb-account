call mvn clean install

call docker rm -f ms-vb-account

call docker image rm darren/ms-vb-account

call docker build . -t darren/ms-vb-account

call docker run -d --name ms-vb-account -p 8001:8080 darren/ms-vb-account
