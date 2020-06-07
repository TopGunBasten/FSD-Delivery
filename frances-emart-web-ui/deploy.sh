if [ ! "$(docker ps -q -f name=emart-ui)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=emart-ui)" ]; then
        # cleanup
        docker stop emart-ui
        docker rm emart-ui
    fi
    # run your container
    docker run -d --name emart-ui  -p 3000:80 topgunbasten/emart-ui
fi