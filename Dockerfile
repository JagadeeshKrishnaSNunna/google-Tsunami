FROM kalilinux/kali-rolling

RUN apt-get update && apt-get install nmap ncrack curl git openjdk-11-jdk -y

WORKDIR /home/tsunami

RUN bash -c "$(curl -sfL https://raw.githubusercontent.com/google/tsunami-security-scanner/master/quick_start.sh)"

COPY DocRunScritp.sh DocRunScritp.sh

CMD bash DocRunScritp.sh $IP
