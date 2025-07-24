# SWAPI Assignment

This project consists of a full-stack application built with:
- **Angular** (frontend)
- **Spring Boot** (Java backend adapter)
- **Docker Compose** for container orchestration

## 🧰 Prerequisites

Make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## 🚀 Running the Application from Scratch

To start the full stack (frontend + backend) using Docker Compose:

1. Open a terminal in the root of the project (where the `devops/docker-compose.yml` file is located).

2. Run the following command to build everything from scratch and start the containers:

docker compose up

## 🌐 Access the Application

Once running, navigate to:
 
	http://localhost:4200

The frontend should be fully functional and connected to the backend at:

	http://localhost:6969/api


## 📡 API Usage
The backend provides RESTful endpoints under /api/people and /api/planets. These endpoints support pagination, filtering, and sorting through query parameters.

http://localhost:6969/api/people
http://localhost:6969/api/planets

### 🔧 Query Parameters

| Parameter  | Type   | Required | Description                                                     |
|------------|--------|----------|-----------------------------------------------------------------|
| `page`     | number | No       | The page number to retrieve (starting from 1)                  |
| `size`     | number | No       | Number of items per page (e.g., 10, 15)                         |
| `name`     | string | No       | Filter results by partial or full name                         |
| `sort`     | string | No       | Field to sort by (e.g., `name`, `height`, `population`)        |
| `direction`| string | No       | Sort direction: `asc` for ascending, `desc` for descending      |


## 📦 Project Structure

.
├── api/
│   └── adapter/               # Java Spring Boot backend
├── site/                      # Angular frontend application
│   └── src/
├── devops/
│   ├── Dockerfile.adapter     # Backend Dockerfile
│   ├── Dockerfile.frontend    # Frontend Dockerfile
│   ├── nginx.conf             # NGINX config for serving Angular
│   └── docker-compose.yml     # Docker Compose config








