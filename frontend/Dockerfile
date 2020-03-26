## Build
FROM node:12.16-alpine AS builder
WORKDIR /app
COPY package.json ./
RUN npm install

ENV PATH="./node_modules/.bin:$PATH"

COPY . ./
RUN ng build --prod

## Run
FROM nginx:1.17.9-alpine
COPY --from=builder /app/dist/frontend /usr/share/nginx/html

EXPOSE 8000
