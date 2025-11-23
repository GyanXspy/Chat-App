# ---------- Stage 1: build ----------
FROM node:20-alpine AS build

WORKDIR /app

# copy only package files first for faster caching
COPY package*.json ./
COPY package.json ./

# install deps
RUN npm ci

# copy rest of app
COPY . .

# build app
RUN npm run build

# ---------- Stage 2: serve with nginx ----------
FROM nginx:stable-alpine AS production

# remove default nginx content
RUN rm -rf /usr/share/nginx/html/*

# copy built assets from builder
COPY --from=build /app/dist /usr/share/nginx/html

# copy nginx config (see nginx.conf below)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# expose default HTTP port
EXPOSE 80

# run nginx in foreground
CMD ["nginx", "-g", "daemon off;"]
