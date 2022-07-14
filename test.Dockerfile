FROM ubuntu:latest as builder

ARG DEBIAN_FRONTEND=noninteractive

ARG SPEEDTEST_VERSION="1.1.1"

ADD . /root/webnettools/

RUN apt-get update \
        && apt-get -yq install maven wget \
        && cd /root/webnettools \
        && mvn clean package

RUN cd /root \
        && wget https://install.speedtest.net/app/cli/ookla-speedtest-${SPEEDTEST_VERSION}-linux-`uname -m`.tgz \
        && tar xvf *.tgz

FROM alpine:latest

RUN apk update \
        && apk add \
                dumb-init \
                openjdk17-jre \
                tcptraceroute \
                mtr \
                bash \
                ca-certificates \
                curl \
                procps \
                bind-tools \
                util-linux \
                iputils \
                nmap

RUN apk add git \
        && git clone -–depth 1 https://github.com/drwetter/testssl.sh /root \
        && apk del git

RUN mkdir -p /root/.config/ookla \
        && echo -e "{\n" \
        "     \"Settings\": {\n" \
        "         \"LicenseAccepted\": \"604ec27f828456331ebf441826292c49276bd3c1bee1a2f65a6452f505c4061c\",\n" \
        "         \"GDPRTimeStamp\": 1615738481\n" \
        "     }\n" \
        "}\n" > /root/.config/ookla/speedtest-cli.json
#       && setcap cap_net_raw+eip /usr/bin/nmap

COPY --from=builder /root/webnettools/target/*-runner.jar /app/app.jar
COPY --from=builder /root/webnettools/target/lib/* /app/lib/
COPY --from=builder /root/speedtest /usr/local/bin/

WORKDIR /root

ENV PATH=/app/testssl:$PATH
ENV AVAILABLE_TOOLS=testssl,ping,traceroute,nmap,dig,mtr,speedtest
ENV CA_DIR=/certs
ENV PORT=8080

EXPOSE 8080
# USER 1001

# HEALTHCHECK --start-period=60s CMD curl -f http://localhost:8080/q/health/live || exit 1

ENTRYPOINT ["dumb-init", "java"]
CMD ["-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}", "-jar", "/app/app.jar", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager"]
