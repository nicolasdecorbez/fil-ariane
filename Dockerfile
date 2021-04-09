FROM node:15

WORKDIR /usr/src/app

COPY ./package*.json ./

RUN npm ci

COPY . .

EXPOSE 8080

ENTRYPOINT ["npm"]

CMD ["start"]
