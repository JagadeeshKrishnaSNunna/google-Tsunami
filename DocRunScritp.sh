cd /root/tsunami && \
java -cp "tsunami-main-0.0.5-SNAPSHOT-cli.jar:/root/tsunami/plugins/*" \
  -Dtsunami-config.location=/root/tsunami/tsunami.yaml \
  com.google.tsunami.main.cli.TsunamiCli \
  --ip-v4-target=$1 \
  --scan-results-local-output-format=JSON \
  --scan-results-local-output-filename=/tmp/tsunami-output.json

cp /tmp/tsunami-output.json /home/tsunami/
