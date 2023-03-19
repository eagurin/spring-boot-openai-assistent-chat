# makefile
include .env
export

bash-app:
	docker exec -it app bash

bash-nginx:
	docker exec -it nginx bash

bash-db:
	docker exec -it postgres bash

start:
	docker-compose up -d

up:
	docker-compose up

stop:
	docker-compose stop

restart:
	docker-compose restart

rm:
	docker-compose stop
	docker-compose rm

ps:
	docker-compose ps

remove:
	@eval docker stop $$(docker ps -a -q)
	docker-compose down

build-image:
	docker-compose build

start:
	docker-compose up -d --build

build:
	docker-compose up -d --build

start-swarm:
	docker swarm init
	docker stack deploy -c docker-compose.yml proj

stop-swarm:
	docker stack rm proj
	docker swarm leave --force

prune:
	docker image prune -a -f
	docker container prune -f
	docker volume prune -f
	docker network prune -f
	docker system prune --volumes -f

push:
	docker-compose build
	docker push ${APP_IMAGE_TAG}

pull:
	docker pull ${APP_IMAGE_TAG}

ssh-deploy:
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" down --remove-orphans
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" -f docker-compose.yml up -d --build
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" restart
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" logs -f

ssh-prune:
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" docker image prune -a -f
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" docker container prune -f
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" docker volume prune -f
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" docker network prune -f
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" docker system prune --volumes -f

ssh-logs:
	docker-compose -H "ssh://${SSH_STAGE_USER}@${SSH_STAGE_HOST}" logs -f

logs:
	docker-compose logs -f
