wget -qO- https://toolbelt.heroku.com/install.sh | sh #install heroku CLI
docker login --username _ --password="$HEROKU_API_KEY" registry.heroku.com
echo "LOGIN TO HEROKU SUCCEED"
docker push registry.heroku.com/"$HEROKU_APP_NAME"/web
echo "PUSH TO HEROKU SUCCEED"
heroku container:release web --app "$HEROKU_APP_NAME"
echo "RELEASE TO HEROKU SUCCEED"
