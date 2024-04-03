# Use an official Node.js runtime as a parent image
FROM node:21.7.1 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install -g @angular/cli

COPY . .

RUN ng build --configuration=production

FROM nginx:latest

COPY --from=build app/dist/get-dressed-fe /usr/share/nginx/html

EXPOSE 80
