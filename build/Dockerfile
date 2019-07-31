FROM reg-umf.com/umf/openjdk:8u212-jdk-stretch

ENV APP_DIR=/app
WORKDIR ${APP_DIR}

ADD lib/ ${APP_DIR}/lib/
ADD resources/ ${APP_DIR}/resources/
ADD dps-boot.jar ${APP_DIR}/

VOLUME ["${APP_DIR}/log/"]

ENV MAIN_CLASS=com.umpay.dps.BootApplication

COPY docker-entrypoint.sh ${APP_DIR}/
RUN chmod u+x ${APP_DIR}/docker-entrypoint.sh
RUN ln -s ${APP_DIR}/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh 
CMD ["docker-entrypoint.sh"]

HEALTHCHECK --interval=10s --timeout=3s --retries=5 --start-period=120s \
	CMD curl -f http://localhost:8080/health || exit 1

EXPOSE 8080

