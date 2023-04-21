# syntax=docker/dockerfile:1
FROM node:lts-alpine AS build

USER node
WORKDIR /home/node/app

COPY package*.json .
RUN yarn install --frozen-lockfile --production

COPY . .
RUN yarn run build

# EXPOSE 3000
# CMD ["yarn", "run", "start"]

FROM httpd:alpine AS runtime
COPY --from=build /home/node/app/dist /usr/local/apache2/htdocs/
EXPOSE 80