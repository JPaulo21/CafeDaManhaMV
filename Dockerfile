# Usa a imagem base do Tomcat para Java 9 com jdk 8
FROM tomcat:9.0.84-jdk8

# Copia o arquivo WAR gerado pelo Maven para o diretório webapps do Tomcat
COPY /target/CafeDaManha.war /usr/local/tomcat/webapps/

# O Tomcat geralmente expõe a porta 8080, então pode não ser necessário expor novamente

# Comando para iniciar o Tomcat quando o contêiner for iniciado
CMD ["catalina.sh", "run"]