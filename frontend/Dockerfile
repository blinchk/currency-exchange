FROM node:lts-alpine AS prod-builder

WORKDIR /app

RUN npm install -g @angular/cli

COPY . .

RUN npm install --force

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0"]
