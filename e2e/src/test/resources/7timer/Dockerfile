FROM mockserver/mockserver

COPY mock-weather.json /api-mock/mock-weather.json
ENV MOCKSERVER_INITIALIZATION_JSON_PATH="/api-mock/mock-weather.json"
ENV MOCKSERVER_WATCH_INITIALIZATION_JSON="true"
