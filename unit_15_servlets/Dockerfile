FROM tomcat:jdk11

COPY target/servlet.war /usr/local/tomcat/webapps/servlet.war

EXPOSE 8080

CMD ["catalina.sh", "run"]