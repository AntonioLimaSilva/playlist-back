# instrucoes
com os dois projetos baixados na sua maquina
usar container docker para subir via docker-compose e rodar os projetos
OBS: docker compose esta no projeto back

# BUILD FRONT

docker build -t luclimasilva23/playlist-front:0.1 -f devops/Dockerfile .

# BUILD BACK
mvn clean install

docker build -t luclimasilva23/playlist-back:0.1 -f devops/Dockerfile .

# Usar docker compose dentro da pasta devops
docker-compose up

# ACESSAR FRONT
http://localhost:8000/login