lsof -i:8080
kill -9 $(lsof -t -i:9091)