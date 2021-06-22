FROM node:16

WORKDIR /app

COPY ./package*.json ./
RUN npm ci

COPY . .
EXPOSE 9000

ENTRYPOINT ["npm"]
CMD ["start"]
