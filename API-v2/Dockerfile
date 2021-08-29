FROM node:16

WORKDIR /app

COPY ./package*.json ./
RUN npm ci

COPY . .
EXPOSE 9000

HEALTHCHECK --interval=10s --timeout=5s --retries=5 CMD curl --fail http://localhost:9000/users || exit 1

ENTRYPOINT ["npm"]
CMD ["start"]
