echo "buildin the tsunami image..!"

docker image build -t tsunami:latest .

echo "tsunami image build successful..!"

echo "initiating tsunami scan..!"

#docker run --rm -e IP=$1 -v $PWD:/home/tsunami --name tsunami tsunami:latest

echo "scan complete..!" 
