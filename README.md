# CafeDaManhaMV
Desafio MV

### Obs.: Necessário ter serviço de banco de dados e mudar URL no arquivo persistence.xml

### Criar executável da aplicação
mvn install

### Criar imagem da aplicação
docker build -t cafe-da-manha-mv-app .

### Iniciar container 
docker run --name cafaDaManhaMV-app -p 8080:8080 cafe-da-manha-mv-app
