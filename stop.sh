lsof -i:9091
kill -9 $(lsof -t -i:9091)