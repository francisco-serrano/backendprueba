wget -qO- https://toolbelt.heroku.com/install.sh | sh #install heroku CLI
docker login --username _ --password=$HEROKU_API_KEY registry.heroku.com
docker push registry.heroku.com/$HEROKU_APP_NAME/web
heroku container:release web --app $HEROKU_APP_NAME
